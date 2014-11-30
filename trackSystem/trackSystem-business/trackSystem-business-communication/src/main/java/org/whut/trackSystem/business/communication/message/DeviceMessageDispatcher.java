package org.whut.trackSystem.business.communication.message;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.fundamental.communication.api.MessageDispatcher;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.message.api.PlatformMessageProducer;

import javax.jms.MessageNotWriteableException;

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
    PlatformMessageProducer platformMessageProducer;
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
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
