package org.whut.monitor.business.communication.service;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.whut.monitor.business.algorithm.service.AlgorithmService;
import org.whut.monitor.business.communication.observer.SensorObservable;
import org.whut.monitor.business.communication.observer.SensorObserver;
import org.whut.monitor.business.monitor.service.CollectorService;
import org.whut.monitor.business.monitor.service.SensorService;
import org.whut.monitor.business.monitor.service.WarnConditionService;
import org.whut.platform.fundamental.communication.api.WsMessageDispatcher;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.mongo.connector.MongoConnector;
import org.whut.platform.fundamental.redis.connector.RedisConnector;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 13-11-4
 * Time: 上午9:19
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SensorDataService implements InitializingBean {
    private static final PlatformLogger logger = PlatformLogger.getLogger(SensorDataService.class);

    private String sensorDB;
    private String sensorCollection;
    private RedisConnector redisConnector;
    private MongoConnector mongoConnector;
    private int keyExpireTime;

    @Autowired
    private SensorObservable sensorObservable;
    @Autowired
    private SensorObserver sensorObserver;

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
                sensorObservable.setObject(curSensor);
                String temp = mongoConnector.insertDocumentObject(curSensor);
                if(objectID!=null){
                    if(redisConnector.set(sensor,keyExpireTime,temp)){
                        objectID+=temp + " ";
                    }
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
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

    @Override
    public void afterPropertiesSet() throws Exception {
        sensorObservable.addObserver(sensorObserver);
    }
}