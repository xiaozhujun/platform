package org.whut.trackSystem.business.communication.service;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.springframework.stereotype.Service;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.mongo.connector.MongoConnector;
import org.whut.platform.fundamental.redis.connector.RedisConnector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-11-28
 * Time: 下午1:51
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DeviceDataService {
    private static final PlatformLogger logger = PlatformLogger.getLogger(DeviceDataService.class);
    private String deviceDB;
    private String deviceCollection;
    private RedisConnector redisConnector;
    private MongoConnector mongoConnector;
    private int keyExpireTime;

    public DeviceDataService() {
        deviceDB = FundamentalConfigProvider.get("device.mongo.deviceDB");
        deviceCollection = FundamentalConfigProvider.get("device.mongo.deviceCollection");
        redisConnector = new RedisConnector();
        mongoConnector = new MongoConnector(deviceDB,deviceCollection);
        keyExpireTime = Integer.parseInt(FundamentalConfigProvider.get("redis.key.expire"));
    }

    public String saveMessage(String msg) {
        logger.info("开始保存消息");
        String objectId = "";
        try {
            DBObject dbObject = (DBObject) JSON.parse(msg);
            logger.info("DBObject: " + dbObject.toString());
            ArrayList devices = (ArrayList)dbObject.get("devices");
            DBObject curDevice;
            for (int i=0; i<devices.size(); i++) {
                curDevice = (DBObject)devices.get(i);
                String device = curDevice.get(FundamentalConfigProvider.get("device.mongo.id")).toString();
                String temp = mongoConnector.insertDocumentObject(curDevice);
                if (objectId != null) {
                    if (redisConnector.set(device,keyExpireTime,temp)) {
                        objectId += temp;
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return objectId;
    }

    public DBObject getCurDeviceData(String device) {
        String mongoObjectID = redisConnector.get(device);
        if (mongoObjectID == null) {
            return null;
        }
        return mongoConnector.getDocument(mongoObjectID);
    }

    public Map<String,String> getCurDeviceDataMap(String device) {
        DBObject dbObject = getCurDeviceData(device);
        if (dbObject == null) {
            return null;
        }
        Map<String,String> map = new HashMap<String, String>();
        String lng = FundamentalConfigProvider.get("device.mongo.lng");
        String lat = FundamentalConfigProvider.get("device.mongo.lat");
        map.put("lng",dbObject.get(lng).toString());
        map.put("lat",dbObject.get(lat).toString());
        return map;
    }
}
