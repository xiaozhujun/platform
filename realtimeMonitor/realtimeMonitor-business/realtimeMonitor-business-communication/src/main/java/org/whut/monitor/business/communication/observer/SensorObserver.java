package org.whut.monitor.business.communication.observer;

import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.monitor.business.algorithm.service.AlgorithmService;
import org.whut.monitor.business.monitor.entity.WarnCondition;
import org.whut.monitor.business.monitor.entity.WarnConditionFactory;
import org.whut.monitor.business.monitor.service.CollectorService;
import org.whut.monitor.business.monitor.service.SensorService;
import org.whut.monitor.business.monitor.service.WarnConditionService;
import org.whut.platform.fundamental.communication.api.WsMessageDispatcher;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.redis.connector.RedisConnector;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: YangRichard
 * Date: 14-10-11
 * Time: 上午9:14
 * To change this template use File | Settings | File Templates.
 */
public class SensorObserver implements Observer {
    @Autowired
    private SensorService sensorService;
    private RedisConnector redisConnector = new RedisConnector();
    @Autowired
    private WarnConditionService warnConditionService;
    @Autowired
    private AlgorithmService algorithmService;
    @Autowired
    private CollectorService collectorService;
    @Autowired
    private WsMessageDispatcher wsMessageDispatcher;
    DBObject curSensor;
    private int keyExpireTime = Integer.parseInt(FundamentalConfigProvider.get("redis.key.expire"));


    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof DBObject) {
            curSensor = (DBObject) arg;
        }
        String number = curSensor.get(FundamentalConfigProvider.get("monitor.mongo.field.sensor.id")).toString();
        ArrayList dataList = (ArrayList) curSensor.get(FundamentalConfigProvider.get("monitor.mongo.field.sensor.data"));
        String time = curSensor.get(FundamentalConfigProvider.get("monitor.mongo.field.sensor.time")).toString();
        String dataType = curSensor.get(FundamentalConfigProvider.get("monitor.mongo.field.sensor.dataType")).toString();
        handleMessage(number,dataList,dataType,time);
    }

    private void handleMessage(String number,ArrayList dataList,String dataType,String time) {
        System.out.println("消息处理正式开始");
        if (redisConnector.get("sensor:{"+number+"}:warnType") == null || !redisConnector.get("sensor:{"+number+"}:warnType").equals(redisConnector.get("sensor:{"+number+"}:warnTypeChanged"))) {
            Map map1 = sensorService.getWarnConditionByNumber(number);
            redisConnector.set("sensor:{"+number+"}:warnType",map1.get("warnType").toString());
            redisConnector.set("sensor:{"+number+"}:warnValue",map1.get("warnValue").toString());
            redisConnector.set("sensor:{"+number+"}:warnCount",map1.get("warnCount").toString());
        }
        Double warnValue = Double.parseDouble(redisConnector.get("sensor:{"+number+"}:warnValue"));
        String warnType = redisConnector.get("sensor:{"+number+"}:warnType");
        boolean isWarn = algorithmService.calculate(warnType,dataList,warnValue);
        String curData = algorithmService.getCurData().get(warnType).toString();
        redisConnector.set("sensor:{"+number+"}:value",keyExpireTime,curData);
        long count = Long.parseLong(redisConnector.get("sensor:{"+number+"}:warnCount"));
        Date date = new Date();
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        redisConnector.set("sensor:{"+number+"}:lastDate",dateString);
        if (isWarn) {
            System.out.println("执行更新操作");
            updateWarnCondition(number,curData,count+1,date);
        }
        else {
            System.out.println("没有执行更新操作");
        }
        transmitMessage(number,dataList,dataType,time,warnType);
    }

    private void transmitMessage(String number,ArrayList dataList,String dataType,String time,String warnType) {
        double meanVariance,MaxValue,MinValue;
        meanVariance = MaxValue = MinValue = 0;
        if (warnType.equals("均方差")) {
            meanVariance = (Double)algorithmService.getCurData().get("均方差");
        }
        else if (warnType.equals("最大值")) {
            MaxValue = (Double)algorithmService.getCurData().get("最大值");
        }
        else if (warnType.equals("最小值")){
            MinValue = (Double)algorithmService.getCurData().get("最小值");
        }
        String warnCount = redisConnector.get("sensor:{"+number+"}:warnCount");
        String lastCommunicateTime = redisConnector.get("sensor:{"+number+"}:lastDate");
        String collectorNum = sensorService.getCNumBySNum(number) ;
        Long appId=sensorService.getAppIdBySNum(number);
        String groupName=sensorService.getGroupNameBySNum(number);
        String areaName=sensorService.getAreaNameBySNum(number);
        Double warnValue = Double.parseDouble(redisConnector.get("sensor:{"+number+"}:warnValue"));
        String s = "warnValue:"+warnValue+","+"areaName:"+"'"+areaName +"'"+","+"groupName:"+"'"+groupName +"'"+","+"appId:"+appId+","+"id:1,"+"meanVariance:"+meanVariance+","+"MaxValue:"+MaxValue+"," +"MinValue:"+MinValue+"," +"warnCount:"+warnCount+"," +"collectorNum:" +"'"+collectorNum+"'"+"," +"lastCommunicateTime:"+"'"+lastCommunicateTime+"',isConnected:" + "'true'";
        String s2 = "{sensors:[{sensorNum:'" + number + "',dataType:'" + dataType + "',time:'" + time +"',data:" + dataList + "," + s + "}]}";
        System.out.println("s2:" + s2);
        wsMessageDispatcher.dispatchMessage(s2);
        redisConnector.set("sensorNum",number);
        System.out.println("number:"+number);
        String lastDate = redisConnector.get("sensor:{"+number+"}:lastDate");
        switch (isNormal(number,lastDate)){
            case 0:collectorService.updateStatusByNumber(redisConnector.get("sensor:{"+number+"}:collector"),"在线正常工作");
                break;
            case 1:collectorService.updateStatusByNumber(redisConnector.get("sensor:{"+number+"}:collector"),"离线或异常");
                break;
            case 2:collectorService.updateStatusByNumber(redisConnector.get("sensor:{"+number+"}:collector"),"暂无数据");
                break;
        }
    }

    private void updateWarnCondition(String id,String curData,long count,Date date) {
        System.out.println("执行更新操作");
        sensorService.updateWarnCountByNumber(id,count+1);
        redisConnector.set("sensor:{"+id+"}:warnCount",Long.toString(count+1));
        Map tempMap = sensorService.findByNumber(id);
        WarnCondition warnCondition = WarnConditionFactory.create(tempMap.get("groupName").toString(), tempMap.get("areaName").toString(),
                tempMap.get("collectorName").toString(), tempMap.get("name").toString(), date, id, Double.parseDouble(curData));
        warnConditionService.add(warnCondition);
    }

    public int isNormal(String number,String lastDate){
        int flag=0;
        if (redisConnector.get("sensor:{"+number+"}:collector") == null) {
            String collectorNum = collectorService.getCollectNumberBySensorNumber(number);
            redisConnector.set("sensor:{"+number+"}:collector",collectorNum);
        }
        if (lastDate!=null){
            System.out.println("lastDate "+lastDate);
            redisConnector.set("sensor:{"+redisConnector.get("sensor:{"+number+"}:collector")+"}:collectorTime",lastDate);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = sdf.parse(lastDate);
                Date now=new Date();
                long dif = (now.getTime()- date.getTime())/(1000);
                System.out.println("dif:"+dif);

                if (dif >60 || (redisConnector.get("sensor:{"+number+"}:collector").equals("") || redisConnector.get("sensor:{"+number+"}:collector") == null)) {
                    flag=1;
                }
                else {
                    flag = 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            flag = 2;
        }
        return flag;
    }
}
