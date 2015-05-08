package org.whut.platform.fundamental.activemq.pool;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.whut.platform.fundamental.activemq.config.SessionFactoryConfig;
import org.whut.platform.fundamental.logger.PlatformLogger;

import javax.jms.Connection;
import javax.jms.JMSException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: YangRichard
 * Date: 15-4-15
 * Time: 下午2:11
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionPool {
    private static final PlatformLogger LOG = PlatformLogger.getLogger(ConnectionPool.class);
    private Deque<Connection> connectionPool = new LinkedList<Connection>();
    private SessionFactoryConfig sessionFactoryConfig;

    public SessionFactoryConfig getSessionFactoryConfig() {
        return sessionFactoryConfig;
    }

    public void setSessionFactoryConfig(SessionFactoryConfig sessionFactoryConfig) {
        this.sessionFactoryConfig = sessionFactoryConfig;
    }

    public Deque<Connection> getConnectionPool() {
        return connectionPool;
    }

    public void start() {
        int k = 0, m = 0;
        for (int i = 0; i < SessionFactoryConfig.MAX_SIZE; i++) {
            if (k == SessionFactoryConfig.BROKER_NUM) k = 0;
            k++;
            try {
                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(sessionFactoryConfig.BROKER_URLS[k-1]);
                Connection connection = connectionFactory.createConnection();
                connection.start();
                connectionPool.add(connection);
            } catch (JMSException e) {
                m ++;
            }
        }
        System.out.println(m);
    }

    public void stop() {
        LOG.info("connectionPool shutdown!");

        for (Connection connection : connectionPool) {
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        LOG.info("connection closed!");
    }
}
