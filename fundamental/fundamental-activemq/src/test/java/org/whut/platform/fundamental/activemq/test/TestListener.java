package org.whut.platform.fundamental.activemq.test;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.whut.platform.fundamental.activemq.consumer.PooledMessageConsumerBase;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-3-21
 * Time: 下午2:25
 * To change this template use File | Settings | File Templates.
 */
public class TestListener extends PooledMessageConsumerBase {
    @Override
    public void onMessage(Message message) {
        if (message instanceof ActiveMQTextMessage) {
            try {
                System.out.println(this.getMessageConsumer() + " " + ((ActiveMQTextMessage)message).getText());
            } catch (JMSException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
//        super.onMessage(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void register(Destination destination) {
       receiveMessage(destination);
    }
}
