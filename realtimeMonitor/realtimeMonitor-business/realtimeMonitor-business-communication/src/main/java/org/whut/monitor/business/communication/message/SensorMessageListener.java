package org.whut.monitor.business.communication.message;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.monitor.business.communication.service.SensorDataService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.message.impl.PlatformMessageListenerBase;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-6-5
 * Time: 下午8:49
 * To change this template use File | Settings | File Templates.
 */
public class SensorMessageListener extends PlatformMessageListenerBase{

    public static final PlatformLogger logger = PlatformLogger.getLogger(SensorMessageListener.class);

    @Autowired
    private SensorDataService sensorDataService;

    @Override
    public String getMessageName() {
        return Constants.SENSOR_QUEUE_DESTINATION;
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof ActiveMQTextMessage){
            try {
                String messageText = ((ActiveMQTextMessage) message).getText();
                logger.info("onMessage data: "+messageText);
                sensorDataService.saveMessage(messageText);
            } catch (JMSException e) {
                logger.error(e.getMessage());
            }
        }else{
               logger.error("message not text,but "+message.getClass().getName());
        }
    }
}
