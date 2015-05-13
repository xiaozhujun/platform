package org.whut.platform.fundamental.activemq.producer;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.whut.platform.fundamental.activemq.api.PooledSessionManager;
import org.whut.platform.fundamental.activemq.api.PooledSessionProducer;
import org.whut.platform.fundamental.activemq.config.SessionFactoryConfig;
import org.whut.platform.fundamental.activemq.pool.SessionPool;
import org.whut.platform.fundamental.logger.PlatformLogger;

import javax.jms.*;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: YangRichard
 * Date: 15-4-15
 * Time: 下午8:02
 * To change this template use File | Settings | File Templates.
 */
public class PooledSessionProducerImpl implements PooledSessionProducer, PooledSessionManager {
    private static final PlatformLogger LOG = PlatformLogger.getLogger(PooledSessionProducerImpl.class);
    private static SessionPool sessionPool;
    private Map<Session, String> curSessions = new ConcurrentHashMap<Session, String>();
    private int COUNTER = 0;
    private SessionFactoryConfig sessionFactoryConfig;

    public SessionFactoryConfig getSessionFactoryConfig() {
        return sessionFactoryConfig;
    }

    public void setSessionFactoryConfig(SessionFactoryConfig sessionFactoryConfig) {
        this.sessionFactoryConfig = sessionFactoryConfig;
    }

    private static final int THREADS=10;
    private ExecutorService executor = Executors.newFixedThreadPool(THREADS);

    @Override
    public SessionPool getSessionPool() {
        return sessionPool;
    }

    @Override
    public void shutdownThreadPool() {
        executor.shutdown();

        while (true) {
            if (executor.isTerminated()) {
                LOG.info("thread pool closed!");
                break;
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                LOG.info("sleep");
            }
        }
    }

    @Override
    public void setSessionPool(SessionPool sessionPool) {
        PooledSessionProducerImpl.sessionPool = sessionPool;
    }

    @Override
    public synchronized Session acquireSession() {
        if (COUNTER == SessionFactoryConfig.BROKER_NUM) COUNTER = 0;
        Session session = sessionPool.acquireSession(sessionFactoryConfig.BROKER_NAMES[COUNTER]);
        if (session == null) {
            LOG.info("session已达最大值");
        } else {
            curSessions.put(session, sessionFactoryConfig.BROKER_NAMES[COUNTER]);
            LOG.info("after acquire: " + sessionPool.getSessions().get(sessionFactoryConfig.BROKER_NAMES[COUNTER]).getLast());
        }
        LOG.info("COUNTER: " + COUNTER);
        COUNTER++;
        return session;
    }

    @Override
    public synchronized void releaseSession(Session session) {
        sessionPool.releaseSession(curSessions.get(session), session);
        LOG.info("after release: " + sessionPool.getSessions().get(curSessions.get(session)).getLast());
    }

    @Override
    public void sendMessage(Destination destination, Map<String, Object> map) {
        executor.execute(new MapMessageSendRunnable(destination, map));
    }

    @Override
    public void sendMessage(Destination destination, Message message) {
        executor.execute(new MessageSendRunnable(destination, message));
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
    public void sendQueue(String destination, Map<String, Object> map) {
        sendMessage(new ActiveMQQueue(destination), map);
    }

    @Override
    public void sendQueue(String destination, Message message) {
        sendMessage(new ActiveMQQueue(destination), message);
    }

    private class MapMessageSendRunnable implements Runnable {
        private Destination destination;
        private Map<String, Object> map;

        public MapMessageSendRunnable(Destination destination, Map<String, Object> map) {
            this.destination = destination;
            this.map = map;
        }

        @Override
        public void run() {
            Session session = null;
            try {
                LOG.info("Sending message by invoke method->sendMessage(final Destination destination,final Map<String, Object> map)");
                session = acquireSession();
                MessageProducer producer = session
                        .createProducer(destination);
                // 不设置消息的存活时间,则该消息一直有效producer#setTimeToLive(timeToLive_

                MapMessage message = session.createMapMessage();
                Set<Map.Entry<String, Object>> entrySet = map.entrySet();
                for (Map.Entry<String, Object> entry : entrySet) {
                    message.setObject(entry.getKey(), entry.getValue());
                }
                producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                producer.send(message);
                producer.close();
                LOG.info("JMS destination is " + destination.toString()
                        + ",param is " + entrySet);
            } catch (JMSException e) {
                LOG.info("Send message by invoke method->sendMessage() error. errorCode:"
                        + e.getErrorCode() + " message:" + e.getMessage());
            } finally {
                releaseSession(session);
            }
        }
    }

    private class MessageSendRunnable implements Runnable {
        private Destination destination;
        private Message message;

        public MessageSendRunnable(Destination destination, Message message) {
            this.destination = destination;
            this.message = message;
        }

        @Override
        public void run() {
            Session session = null;
            try {
                LOG.info("Sending message by invoke method->sendMessage(final Destination destination,final MapMessage message)");
                session = acquireSession();
                MessageProducer producer = session
                        .createProducer(destination);
                producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                producer.send(message);
                producer.close();
                LOG.info("JMS destination is " + destination.toString()
                        + ",message is " + message.toString());
            } catch (JMSException e) {
                LOG.info("Send message by invoke method->sendMessage() error. errorCode:"
                        + e.getErrorCode() + " message:" + e.getMessage());
            } finally {
                releaseSession(session);
            }
        }
    }
}
