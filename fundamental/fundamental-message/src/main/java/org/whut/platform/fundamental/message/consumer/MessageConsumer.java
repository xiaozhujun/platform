package org.whut.platform.fundamental.message.consumer;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.fundamental.activemq.consumer.PooledMessageConsumerBase;
import org.whut.platform.fundamental.activemq.consumer.PooledSessionListenerBase;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.message.parser.MessageParserProxyImpl;

import javax.jms.*;

public class MessageConsumer extends PooledSessionListenerBase {

    public static final PlatformLogger logger = PlatformLogger.getLogger(MessageConsumer.class);

    @Autowired
    private MessageParserProxyImpl messageParserProxy;

    @Override
    public void onMessage(Message message) {
        String number = "";
        String lastDate = "";

        if (message instanceof ActiveMQTextMessage){
            try {
                String messageText = ((ActiveMQTextMessage) message).getText();
                logger.info("onMessage data: "+messageText);
                messageParserProxy.parse(messageText);
            } catch (JMSException e) {
                logger.error(e.getMessage());
            }
        }else{
            logger.error("message not text,but "+message.getClass().getName());
        }
    }

//    @Override
//    public void register(Destination destination) {
//        receiveMessage(destination);
//    }
}
