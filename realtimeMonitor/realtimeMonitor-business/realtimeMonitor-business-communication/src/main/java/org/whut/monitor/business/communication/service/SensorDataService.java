package org.whut.monitor.business.communication.service;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.whut.monitor.business.algorithm.factory.AlgorithmServiceFactory;
import org.whut.monitor.business.algorithm.service.AlgorithmService;
import org.whut.monitor.business.monitor.entity.WarnCondition;
import org.whut.monitor.business.monitor.entity.WarnConditionFactory;
import org.whut.monitor.business.monitor.service.SensorService;
import org.whut.monitor.business.monitor.service.WarnConditionService;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.mongo.connector.MongoConnector;
import org.whut.platform.fundamental.redis.connector.RedisConnector;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 13-11-4
 * Time: 上午9:19
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SensorDataService {
    private String sensorDB;
    private String sensorCollection;
    private RedisConnector redisConnector;
    private MongoConnector mongoConnector;
    private int keyExpireTime;

    @Autowired
    private SensorService sensorService;

    @Autowired
    private WarnConditionService warnConditionService;

    //构造函数
    public SensorDataService(){
        sensorDB = FundamentalConfigProvider.get("monitor.mongo.sensorDB");
        sensorCollection = FundamentalConfigProvider.get("monitor.mongo.sensorCollection");
        keyExpireTime = Integer.parseInt(FundamentalConfigProvider.get("redis.key.expire"));

        redisConnector = new RedisConnector();
        mongoConnector = new MongoConnector(sensorDB,sensorCollection);
    }

    //保存消息对象
    public String saveMessage(String msg){
        String objectID = "";
        try{
            DBObject dbObject = (DBObject) JSON.parse(msg);
            ArrayList sensors = (ArrayList)dbObject.get("sensors");
            DBObject curSensor;
            for(int i=0;i<sensors.size();i++){
                curSensor = (DBObject)sensors.get(i);
                String sensor = curSensor.get(FundamentalConfigProvider.get("monitor.mongo.field.sensor.id")).toString();
                //long timestamp = new Date().getTime();
                System.out.println(curSensor+"jjjjjjjjjjjjjjjj");
                String temp = mongoConnector.insertDocumentObject(curSensor);

                if(objectID!=null){
                    if(redisConnector.set(sensor,keyExpireTime,temp)){
                        objectID+=temp + " ";
                    }
                }
                ArrayList data = (ArrayList)curSensor.get(FundamentalConfigProvider.get("monitor.mongo.field.sensor.data"));
                AlgorithmService algorithmService = AlgorithmServiceFactory.create();
                if (redisConnector.get("sensor:{"+sensor+"}:warnType") == null) {
                    Map map = sensorService.getWarnConditionByNumber(sensor);
                    redisConnector.set("sensor:{"+sensor+"}:warnType",map.get("warnType").toString());
                    redisConnector.set("sensor:{"+sensor+"}:warnValue",map.get("warnValue").toString());
                    redisConnector.set("sensor:{"+sensor+"}:warnCount",map.get("warnCount").toString());
                }
                String curData = Double.toString(algorithmService.calculate(redisConnector.get("sensor:{"+sensor+"}:warnType"),data));
                redisConnector.set("sensor:{"+sensor+"}:value",keyExpireTime,curData);
                String warnValue = redisConnector.get("sensor:{"+sensor+"}:warnValue");
                long count = Long.parseLong(redisConnector.get("sensor:{"+sensor+"}:warnCount"));
                Date date = new Date();
                String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                redisConnector.set("sensor:{"+sensor+"}:lastDate",keyExpireTime,dateString);
                if (algorithmService.compare(Double.parseDouble(curData),Double.parseDouble(warnValue))) {
                    System.out.println("执行更新操作");
                    updateWarnCondition(sensor,curData,count+1,date);
                }
                else {
                    System.out.println("没有执行更新操作");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return objectID;
    }

    //获得传感器当前最新的
    public DBObject getCurrentSensorData(String sensor){
        String mongoObjectID = redisConnector.get(sensor);
        if(mongoObjectID==null){
            return null;
        }
        return mongoConnector.getDocument(mongoObjectID);
    }

    //获得传感器当前数据数组
    public ArrayList getCurrentSensorDataArray(String sensor){
        DBObject dbObject = getCurrentSensorData(sensor);
        if(dbObject==null){
            return null;
        }
        return (ArrayList)dbObject.get(FundamentalConfigProvider.get("monitor.mongo.field.sensor.data"));
    }

    public void updateWarnCondition(String id,String curData,long count,Date date) {
        System.out.println("执行更新操作");
        sensorService.updateWarnCountByNumber(id,count+1);
        redisConnector.set("sensor:{"+id+"}:warnCount",Long.toString(count+1));
        Map tempMap = sensorService.findByNumber(id);
        WarnCondition warnCondition = WarnConditionFactory.create(tempMap.get("groupName").toString(),tempMap.get("areaName").toString(),
                tempMap.get("collectorName").toString(),tempMap.get("name").toString(),date,id,Double.parseDouble(curData));
        warnConditionService.add(warnCondition);
    }
}