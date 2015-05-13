package org.whut.platform.fundamental.activemq.pool;



import org.whut.platform.fundamental.activemq.config.SessionFactoryConfig;
import org.whut.platform.fundamental.logger.PlatformLogger;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: YangRichard
 * Date: 15-4-15
 * Time: 下午3:55
 * To change this template use File | Settings | File Templates.
 */
public class SessionPool {
    private static final PlatformLogger LOG = PlatformLogger.getLogger(SessionPool.class);
    private Map<String, LinkedList<Session>> sessions = new ConcurrentHashMap<String, LinkedList<Session>>();
    private Deque<Connection> connections = new LinkedList<Connection>();
    private ConnectionPool connectionPool;
    private SessionFactoryConfig sessionFactoryConfig;

    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public SessionFactoryConfig getSessionFactoryConfig() {
        return sessionFactoryConfig;
    }

    public void setSessionFactoryConfig(SessionFactoryConfig sessionFactoryConfig) {
        this.sessionFactoryConfig = sessionFactoryConfig;
    }

    public Map<String, LinkedList<Session>> getSessions() {
        return sessions;
    }

    public void setSessions(Map<String, LinkedList<Session>> sessions) {
        this.sessions = sessions;
    }

    public void start() throws JMSException {
        connections = connectionPool.getConnectionPool();
        int k = 0;

        for (Connection c : connections) {
            LinkedList<Session> l = new LinkedList<Session>();
            for (int i = 0; i < SessionFactoryConfig.MAX_SESSION; i++) {
                Session session = c.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
                l.push(session);
            }
            if (k == SessionFactoryConfig.BROKER_NUM) k = 0;
            if (sessions.get(sessionFactoryConfig.BROKER_NAMES[k]) != null) {
                sessions.get(sessionFactoryConfig.BROKER_NAMES[k]).addAll(l);
            } else {
                sessions.put(sessionFactoryConfig.BROKER_NAMES[k], l);
            }
            k++;
        }
    }

    public void stop() {
        LOG.info("sessionPool shutdown!");
        for (Map.Entry<String, LinkedList<Session>> m : sessions.entrySet()) {
            LinkedList<Session> l = m.getValue();
            for (Session s : l) {
                try {
                    s.close();
                } catch (JMSException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }

        LOG.info("sessionPool closed!");
    }

    public Session acquireSession(String broker) {
        if (sessions.get(broker).size() == 0) {
            LOG.info("out of pool size!!!");
            return null;
        } else {
            return sessions.get(broker).pop();
        }
    }

    public void releaseSession(String broker, Session session) {
        if (session == null) {
            return;
        } else {
            sessions.get(broker).add(session);
        }
    }
}
