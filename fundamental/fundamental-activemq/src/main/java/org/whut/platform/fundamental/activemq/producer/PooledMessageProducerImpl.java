package org.whut.platform.fundamental.activemq.producer;

import org.apache.activemq.Service;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.whut.platform.fundamental.activemq.api.PooledMessageProducer;
import org.whut.platform.fundamental.activemq.broker.BrokerInfo;
import org.whut.platform.fundamental.activemq.connectionFactory.MyConnectionFactory;

import javax.jms.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-3-10
 * Time: 下午2:55
 * To change this template use File | Settings | File Templates.
 */
public class PooledMessageProducerImpl implements PooledMessageProducer, Service {
    private static final Logger logger = Logger.getLogger(PooledMessageProducerImpl.class.getName());
    private MyConnectionFactory myConnectionFactory;
    private List<String> brokers;

    private static final int THREADS=10;
    private ExecutorService executor = Executors.newFixedThreadPool(THREADS);

    public MyConnectionFactory getMyConnectionFactory() {
        return myConnectionFactory;
    }

    public void setMyConnectionFactory(MyConnectionFactory myConnectionFactory) {
        this.myConnectionFactory = myConnectionFactory;
    }

    public List<String> getBrokers() {
        return brokers;
    }

    public void setBrokers(List<String> brokers) {
        this.brokers = brokers;
    }

    protected synchronized MyConnectionFactory getInnerClassConnectionFactory() {
        return myConnectionFactory;
    }

    @Override
    public void sendQueue(String destination, Map<String, Object> map) {
        sendMessage(new ActiveMQQueue(destination), map);
    }

    @Override
    public void sendQueue(String destination, Message message) {
        sendMessage(new ActiveMQQueue(destination), message);
    }

    @Override
    public void sendTopic(String destination, Map<String, Object> map) {
        sendMessage(new ActiveMQTopic(destination), map);
    }

    @Override
    public void sendTopic(String destination, Message message) {
        sendMessage(new ActiveMQTopic(destination), message);
    }

    @Override
    public void sendMessage(Destination destination, final Map<String, Object> map) {
        executor.execute(new MapMessageSendRunnable(destination, map));
    }

    @Override
    public void sendMessage(Destination destination, final Message message) {
        executor.execute(new MessageSendRunnable(destination, message));
    }

    public class MapMessageSendRunnable implements Runnable {
        private Destination destination;

        private Map<String, Object> map;


        public MapMessageSendRunnable(final Destination destination,
                                      final Map<String, Object> map){
            this.destination = destination;
            this.map = map;
        }

        @Override
        public void run() {
            Connection connection = null;
            Session session = null;
            MyConnectionFactory myConnectionFactory = getInnerClassConnectionFactory();

            try {
                connection = myConnectionFactory.createConnection(brokers,
                        BrokerInfo.USERNAME,
                        BrokerInfo.PASSWORD);
                connection.start();
                session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
                MessageProducer producer = session
                        .createProducer(destination);

                MapMessage message = session.createMapMessage();
                Set<Map.Entry<String, Object>> entrySet = map.entrySet();
                for (Map.Entry<String, Object> entry : entrySet) {
                    message.setObject(entry.getKey(), entry.getValue());
                }
                producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                producer.send(message);
                producer.close();
                logger.info("Send Message success: " + message);
            } catch (JMSException e) {
                logger.info("Send Message Failed");
            } finally {
                try {
                    assert session != null;
                    session.close();
                    logger.info("session is closed");
                    connection.close();
                    logger.info("connection is closed");
                } catch (JMSException e) {
                    logger.info("cannot release session and connection");
                }
            }
        }
    }

    public class MessageSendRunnable implements Runnable {
        private Destination destination;
        private Message message;

        public MessageSendRunnable(final Destination destination,Message message){
            this.destination =destination;
            this.message = message;
        }

        @Override
        public void run() {
            Connection connection = null;
            Session session = null;
            MyConnectionFactory myConnectionFactory = getInnerClassConnectionFactory();
            try {
                connection = myConnectionFactory.createConnection(brokers,
                        BrokerInfo.USERNAME,
                        BrokerInfo.PASSWORD);
                connection.start();
                session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
                MessageProducer producer = session
                        .createProducer(destination);
                producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                producer.send(message);
                producer.close();
                logger.info("Send Message success: " + message);
            } catch (JMSException e) {
                logger.info("Send Message Failed");
            } finally {
                try {
                    assert session != null;
                    session.close();
                    logger.info("session is closed");
                    connection.close();
                    logger.info("connection is closed");
                } catch (JMSException e) {
                    logger.info("cannot release session and connection");
                }
            }
        }
    }

    @Override
    public void start() throws Exception {
        myConnectionFactory.init(BrokerInfo.MAX_CONNECTIONS, BrokerInfo.MAXIMUN_Active, BrokerInfo.BLOCKED);
    }

    @Override
    public void stop() {
        logger.info("stop!");
        executor.shutdown();

        while (true) {
            if (executor.isTerminated()) {
                logger.info("closed!");
                break;
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                logger.info("sleep");
            }
        }
        logger.info("shutdown Thread Pool !");
        myConnectionFactory.clear();
    }

}
