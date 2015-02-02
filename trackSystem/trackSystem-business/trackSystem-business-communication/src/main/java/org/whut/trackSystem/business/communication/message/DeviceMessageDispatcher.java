package org.whut.trackSystem.business.communication.message;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.fundamental.communication.api.MessageDispatcher;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.message.api.PlatformMessageProducer;
import org.whut.platform.fundamental.redis.connector.RedisConnector;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.trackSystem.business.device.entity.Device;
import org.whut.trackSystem.business.device.service.DeviceService;

import javax.jms.MessageNotWriteableException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-11-28
 * Time: 上午11:56
 * To change this template use File | Settings | File Templates.
 */
public class DeviceMessageDispatcher implements MessageDispatcher {
    public static final PlatformLogger logger = PlatformLogger.getLogger(DeviceMessageDispatcher.class);
    private static final String destination = Constants.DEVICE_QUEUE_DESTINATION;
    @Autowired
    private PlatformMessageProducer platformMessageProducer;
    private RedisConnector redisConnector = new RedisConnector();
    @Autowired
    private DeviceService deviceService;
    @Override
    public void dispatchMessage(String messageBody) {
        if (messageBody != null) {
            logger.info("dispatch: " + messageBody);
            try {
                ActiveMQTextMessage message = new ActiveMQTextMessage();
                message.setText(messageBody);
                platformMessageProducer.sendTopic(destination,message);
            } catch (MessageNotWriteableException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public void exceptionProcess() {
        logger.info("DeviceMessageDispatcher --> exceptionProcess");
        List<Device> list = deviceService.getListByAppId(Long.parseLong(redisConnector.get("appId")));
        Device curDevice;
        for (int i=0; i<list.size(); i++) {
            try {
                JSONObject object = new JSONObject(redisConnector.get("device:{"+list.get(i).getNumber()+"}:jsonString"));
                String jsonStr = object.get("devices").toString();
                int startIndex = jsonStr.indexOf("[");
                int endIndex = jsonStr.indexOf("]");
                String jsonString = jsonStr.substring(startIndex+1,endIndex);
                curDevice = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Device.class);
                curDevice.setNumber(list.get(i).getNumber());
            } catch (Exception e) {
                logger.info(e.getMessage());
                curDevice = null;
            }
            if (curDevice != null) {
                curDevice.setId(deviceService.getIdByNumber(curDevice.getNumber(),Long.parseLong(redisConnector.get("appId"))));
                deviceService.update(curDevice);
            }
        }
    }
}
