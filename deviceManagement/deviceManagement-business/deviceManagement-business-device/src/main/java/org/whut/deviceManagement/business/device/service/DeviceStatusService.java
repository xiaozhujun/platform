package org.whut.deviceManagement.business.device.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.deviceManagement.business.device.entity.Device;
import org.whut.deviceManagement.business.device.mapper.DeviceMapper;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.mongo.connector.MongoConnector;
import org.whut.platform.fundamental.util.json.JsonMapper;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-4-28
 * Time: 下午2:58
 * To change this template use File | Settings | File Templates.
 */
public class DeviceStatusService {
    @Autowired
    DeviceMapper deviceMapper;

    PlatformLogger logger = PlatformLogger.getLogger(DeviceStatusService.class);
    MongoConnector mongoConnector = new MongoConnector(FundamentalConfigProvider.get("deviceManagement.mongo.db"),FundamentalConfigProvider.get("deviceManagement.mongo.device.collection"));

    public void resolveStatus(String message){
        HashMap<String,Object> status = JsonMapper.buildNonDefaultMapper().fromJson(message,HashMap.class);
        String mongoId = mongoConnector.insertDocument(message);
        String deviceNumber = (String)status.get("number");
        Device device = new Device();
        device.setNumber(deviceNumber);
        device.setMongoId(mongoId);
        deviceMapper.updateByNumber(device);
        logger.info("DeviceStatusService.resolveStatus: "+message);
    }
}
