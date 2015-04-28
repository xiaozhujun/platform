package org.whut.trackSystem.business.communication.message;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.fundamental.activemq.consumer.PooledMessageConsumerBase;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.trackSystem.business.communication.service.DeviceDataService;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-11-28
 * Time: 上午11:56
 * To change this template use File | Settings | File Templates.
 */
public class DeviceMessageListener extends PooledMessageConsumerBase {
    public static final PlatformLogger logger = PlatformLogger.getLogger(DeviceMessageListener.class);
    @Autowired
    private DeviceDataService deviceDataService;
    private static final int ExecutorNum = 5;
    private Executor executor = Executors.newFixedThreadPool(ExecutorNum);
//    @Autowired
//    private WsMessageDispatcher wsMessageDispatcher;

    public DeviceDataService getDeviceDataService() {
        return deviceDataService;
    }

    public void setDeviceDataService(DeviceDataService deviceDataService) {
        this.deviceDataService = deviceDataService;
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof ActiveMQTextMessage) {
            try {
                String messageText = ((ActiveMQTextMessage) message).getText();
                logger.info("onMessage that DeviceLocation Information is:" + messageText);
//                deviceDataService.saveMessage(messageText);
                executor.execute(new SaveMessageRunnable(messageText, deviceDataService));
//                wsMessageDispatcher.dispatchMessage(messageText);
            } catch (JMSException e) {
                logger.info("message not text,but " + message.getClass().getName());
            }
        }
//        System.out.println("On message: " + message);
    }

    @Override
    public void register(Destination destination) {
        receiveMessage(destination);
    }

    private class SaveMessageRunnable implements Runnable {
        private String messageText;
        private DeviceDataService deviceDataService;

        private SaveMessageRunnable(String messageText, DeviceDataService deviceDataService) {
            this.messageText = messageText;
            this.deviceDataService = deviceDataService;
        }

        @Override
        public void run() {
            deviceDataService.saveMessage(messageText);
            logger.info("save Message");
        }
    }
}
