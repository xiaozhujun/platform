package org.whut.platform.fundamental.activemq.consumer;

import org.apache.activemq.Service;
import org.whut.platform.fundamental.activemq.api.PooledMessageConsumer;
import org.whut.platform.fundamental.activemq.broker.BrokerInfo;
import org.whut.platform.fundamental.activemq.connectionFactory.MyConnectionFactory;

import javax.jms.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-3-15
 * Time: 下午3:54
 * To change this template use File | Settings | File Templates.
 */
public abstract class PooledMessageConsumerBase implements PooledMessageConsumer, Service {
    private static final Logger logger = Logger.getLogger(PooledMessageConsumerBase.class.getName());
    private MyConnectionFactory myConnectionFactory;
    private MessageConsumer messageConsumer;
    private List<String> brokers;

    public MyConnectionFactory getMyConnectionFactory() {
        return myConnectionFactory;
    }

    public void setMyConnectionFactory(MyConnectionFactory myConnectionFactory) {
        this.myConnectionFactory = myConnectionFactory;
    }

    public MessageConsumer getMessageConsumer() {
        return messageConsumer;
    }

    public void setMessageConsumer(MessageConsumer messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    public List<String> getBrokers() {
        return brokers;
    }

    public void setBrokers(List<String> brokers) {
        this.brokers = brokers;
    }

    @Override
    public void onMessage(Message message) {
        logger.info("on Message(super) : " + message);
    }

    protected void receiveMessage(Destination destination) {

        Connection connection;
        try {
            connection = myConnectionFactory.createConnection(brokers,
                        BrokerInfo.USERNAME,
                        BrokerInfo.PASSWORD);
            logger.info("Consumer connection:" + connection);
            connection.start();
            Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            messageConsumer = session.createConsumer(destination);
            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    logger.info("on message(original):" + message);
                    PooledMessageConsumerBase.this.onMessage(message);
                }
            });

        } catch (JMSException e) {
            logger.info("fail to create consumer");
        }

    }

    @Override
    public void start() {
        myConnectionFactory.init(BrokerInfo.MAX_CONNECTIONS, BrokerInfo.MAXIMUN_Active, BrokerInfo.BLOCKED);
    }

    @Override
    public void stop() {
        myConnectionFactory.clear();
    }
}
