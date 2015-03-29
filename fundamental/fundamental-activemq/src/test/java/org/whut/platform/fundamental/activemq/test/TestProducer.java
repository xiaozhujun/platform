package org.whut.platform.fundamental.activemq.test;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.whut.platform.fundamental.activemq.api.PooledMessageDispatcher;
import org.whut.platform.fundamental.activemq.api.PooledMessageProducer;

import javax.jms.MessageNotWriteableException;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-3-21
 * Time: 下午2:24
 * To change this template use File | Settings | File Templates.
 */
public class TestProducer implements PooledMessageDispatcher {
    private PooledMessageProducer pooledMessageProducer;

    public PooledMessageProducer getPooledMessageProducer() {
        return pooledMessageProducer;
    }

    public void setPooledMessageProducer(PooledMessageProducer pooledMessageProducer) {
        this.pooledMessageProducer = pooledMessageProducer;
    }

    @Override
    public void dispatchMessage(String messageBody) {
        try {
            ActiveMQTextMessage message = new ActiveMQTextMessage();
            message.setText(messageBody);
            pooledMessageProducer.sendQueue(TestConstants.TEST_TOPIC, message);
        } catch (MessageNotWriteableException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public void exceptionProcess() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
