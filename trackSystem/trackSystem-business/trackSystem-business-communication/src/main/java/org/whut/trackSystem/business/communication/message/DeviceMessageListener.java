package org.whut.trackSystem.business.communication.message;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.message.impl.PlatformMessageListenerBase;
import org.whut.trackSystem.business.communication.service.DeviceDataService;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-11-28
 * Time: 上午11:56
 * To change this template use File | Settings | File Templates.
 */
public class DeviceMessageListener extends PlatformMessageListenerBase{
    public static final PlatformLogger logger = PlatformLogger.getLogger(DeviceMessageListener.class);
    @Autowired
    private DeviceDataService deviceDataService;

    public DeviceDataService getDeviceDataService() {
        return deviceDataService;
    }

    public void setDeviceDataService(DeviceDataService deviceDataService) {
        this.deviceDataService = deviceDataService;
    }

    @Override
    public String getMessageName() {
        return Constants.DEVICE_QUEUE_DESTINATION;
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof ActiveMQTextMessage) {
            try {
                String messageText = ((ActiveMQTextMessage) message).getText();
                logger.info("onMessage that DeviceLocation Information is:" + messageText);
                deviceDataService.saveMessage(messageText);
            } catch (JMSException e) {
                logger.info("message not text,but " + message.getClass().getName());
            }
        }
//        System.out.println("On message: " + message);
    }
}
