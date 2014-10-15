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
    private SensorService sensorService;
    private RedisConnector redisConnector;
    private WarnConditionService warnConditionService;
    private AlgorithmService algorithmService;
    private CollectorService collectorService;
    private WsMessageDispatcher wsMessageDispatcher;
    DBObject curSensor;
    private int keyExpireTime = Integer.parseInt(FundamentalConfigProvider.get("redis.key.expire"));

    public SensorService getSensorService() {
        return sensorService;
    }

    public void setSensorService(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    public RedisConnector getRedisConnector() {
        return redisConnector;
    }

    public void setRedisConnector(RedisConnector redisConnector) {
        this.redisConnector = redisConnector;
    }

    public WarnConditionService getWarnConditionService() {
        return warnConditionService;
    }

    public void setWarnConditionService(WarnConditionService warnConditionService) {
        this.warnConditionService = warnConditionService;
    }

    public AlgorithmService getAlgorithmService() {
        return algorithmService;
    }

    public void setAlgorithmService(AlgorithmService algorithmService) {
        this.algorithmService = algorithmService;
    }

    public CollectorService getCollectorService() {
        return collectorService;
    }

    public void setCollectorService(CollectorService collectorService) {
        this.collectorService = collectorService;
    }

    public WsMessageDispatcher getWsMessageDispatcher() {
        return wsMessageDispatcher;
    }

    public void setWsMessageDispatcher(WsMessageDispatcher wsMessageDispatcher) {
        this.wsMessageDispatcher = wsMessageDispatcher;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("实际需要处理的参数：" + arg);
        System.out.println("检查是否注入2：" + this.sensorService + "|" + this.redisConnector
                + "|" + warnConditionService + "|" + algorithmService);
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
        String curData = Double.toString(algorithmService.calculate(redisConnector.get("sensor:{"+number+"}:warnType"),dataList));
        redisConnector.set("sensor:{"+number+"}:value",keyExpireTime,curData);
        String warnValue = redisConnector.get("sensor:{"+number+"}:warnValue");
        long count = Long.parseLong(redisConnector.get("sensor:{"+number+"}:warnCount"));
        Date date = new Date();
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        redisConnector.set("sensor:{"+number+"}:lastDate",dateString);
        if (algorithmService.compare(Double.parseDouble(curData),Double.parseDouble(warnValue))) {
            System.out.println("执行更新操作");
            updateWarnCondition(number,curData,count+1,date);
        }
        else {
            System.out.println("没有执行更新操作");
        }
        transmitMessage(number,dataList,dataType,time);
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

    private void transmitMessage(String number,ArrayList dataList,String dataType,String time) {
        double meanVariance= algorithmService.meanVariance(dataList);
        double MaxValue = algorithmService.MaxValue(dataList);
        double MinValue = algorithmService.MinValue(dataList);
        String warnCount = redisConnector.get("sensor:{"+number+"}:warnCount");
        String lastCommunicateTime = redisConnector.get("sensor:{"+number+"}:lastDate");
        String collectorNum = sensorService.getCNumBySNum(number) ;
        System.out.println("aaaaaaaaaaaa"+collectorNum);

        String s = "id:1,"+"meanVariance:"+meanVariance+","+"MaxValue:"+MaxValue+"," +"MinValue:"+MinValue+"," +"warnCount:"+warnCount+"," +"collectorNum:" +"'"+collectorNum+"'"+"," +"lastCommunicateTime:"+"'"+lastCommunicateTime+"',isConnected:" + "'true'";
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

    public int isNormal(String number,String lastDate){
//        String lastDate = redisConnector.get("sensor:{"+number+"}:lastDate");
        int flag=0;
        String lastMessageTime = lastDate;
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
                    //collectorService.updateStatusByNumber(redisConnector.get("sensor:{"+number+"}:collector"),"离线或异常");
                }
                else {
                    flag = 0;
                    //collectorService.updateStatusByNumber(redisConnector.get("sensor:{"+number+"}:collector"),"在线正常工作");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            flag = 2;
            //collectorService.updateStatusByNumber(redisConnector.get("sensor:{"+number+"}collector"),"暂无数据");
        }
        return flag;
    }
}
