package org.whut.monitor.business.communication.service;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.whut.monitor.business.algorithm.factory.AlgorithmServiceFactory;
import org.whut.monitor.business.algorithm.service.AlgorithmService;
import org.whut.monitor.business.monitor.entity.WarnCondition;
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
                double meanValue = algorithmService.meanVariance(data);
                String curWarnValue =  Double.toString(meanValue);
                double warnValue;
                long count;
                String warnCount;
                Map map = new HashMap();
                if (redisConnector.get(sensor+"Condition") == null) {
                    map = sensorService.getWarnConditionByNumber(sensor);
                    redisConnector.set(sensor+"Condition",keyExpireTime,map.get("warnValue")+"|"+map.get("warnCount"));
                }
                else {
                    if (map.get("warnValue") == null || map.get("warnCount") == null) {
                        String sensorCondition = redisConnector.get(sensor+"Condition");
                        if (sensorCondition != null) {
                            String[] tempArray = sensorCondition.split("\\|");
                            String[] tempName = {"warnValue","warnCount"};
                            for (int k=0;k<tempArray.length;k++) {
                                map.put(tempName[k],tempArray[k]);
                            }
                        }
                    }
                }
                warnValue = Double.parseDouble(map.get("warnValue").toString());
                warnCount = map.get("warnCount").toString();
                count = (long)Double.parseDouble(warnCount);
                if (algorithmService.compare(meanValue,warnValue)) {
                    count++;
                    sensorService.updateWarnCountByNumber(sensor,count);
                    String groupName,areaName,collectorName,sensorName;
                    Map tempMap = sensorService.findByNumber(sensor);
                    groupName = tempMap.get("groupName").toString();
                    areaName = tempMap.get("areaName").toString();
                    collectorName = tempMap.get("collectorName").toString();
                    sensorName = tempMap.get("name").toString();
                    Date date = new Date();
                    WarnCondition warnCondition = new WarnCondition();
                    warnCondition.setGroupName(groupName);
                    warnCondition.setAreaName(areaName);
                    warnCondition.setCollectorName(collectorName);
                    warnCondition.setSensorName(sensorName);
                    warnCondition.setWarnTime(date);
                    warnCondition.setCurWarnValue((long)meanValue);
                    warnCondition.setNumber(sensor);
                    warnConditionService.add(warnCondition);
                    if (redisConnector.set(sensor+"warnCondition",keyExpireTime,curWarnValue+"|"+Long.toString(count))) {
                        redisConnector.set(sensor+"Condition",keyExpireTime,Double.toString(warnValue)+"|"+Long.toString(count));
                        System.out.println("OK!");
                        System.out.println(redisConnector.get(sensor+"warnCondition"));
                        System.out.println(redisConnector.get(sensor+"Condition"));
                    }
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
}