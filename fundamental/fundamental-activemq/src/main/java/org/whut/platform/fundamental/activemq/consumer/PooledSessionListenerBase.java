package org.whut.platform.fundamental.activemq.consumer;

import org.whut.platform.fundamental.activemq.api.PooledSessionConsumer;
import org.whut.platform.fundamental.activemq.api.PooledSessionManager;
import org.whut.platform.fundamental.activemq.pool.SessionPool;
import org.whut.platform.fundamental.logger.PlatformLogger;

import javax.jms.*;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: YangRichard
 * Date: 15-4-15
 * Time: 下午9:47
 * To change this template use File | Settings | File Templates.
 */
public abstract class PooledSessionListenerBase implements PooledSessionConsumer, PooledSessionManager {
    private static final PlatformLogger LOG = PlatformLogger.getLogger(PooledSessionListenerBase.class);
    private SessionPool sessionPool;
    private MessageConsumer messageConsumer;
    private Destination destination;
    private String broker;
    private Session session;

    public SessionPool getSessionPool() {
        return sessionPool;
    }

    public void setSessionPool(SessionPool sessionPool) {
        this.sessionPool = sessionPool;
    }

    @Override
    public void register(String broker, Destination destination) {
        this.destination = destination;
        this.broker = broker;
    }

    @Override
    public void onMessage(Message message) {
        LOG.info("super: " + message);
    }

    @Override
    public void setListener() {
        Session session = null;

        try {
            session = acquireSession();
            this.session = session;
            messageConsumer = session.createConsumer(destination);

            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    PooledSessionListenerBase.this.onMessage(message);
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public void close() {
        releaseSession(this.session);
    }

    @Override
    public Session acquireSession() {
        return sessionPool.acquireSession(broker);
    }

    @Override
    public void releaseSession(Session session) {
        sessionPool.releaseSession(broker, session);
    }
}
