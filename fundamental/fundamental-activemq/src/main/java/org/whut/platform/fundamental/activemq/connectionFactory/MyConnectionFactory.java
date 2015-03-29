package org.whut.platform.fundamental.activemq.connectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.whut.platform.fundamental.activemq.api.AbstractConnectionFactory;
import org.whut.platform.fundamental.activemq.broker.BrokerConfigBean;
import org.whut.platform.fundamental.activemq.broker.BrokerInfo;
import org.whut.platform.fundamental.logger.PlatformLogger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-3-29
 * Time: 下午5:43
 * To change this template use File | Settings | File Templates.
 */
public class MyConnectionFactory extends PooledConnectionFactory implements AbstractConnectionFactory {
    private static final PlatformLogger logger = PlatformLogger.getLogger(MyConnectionFactory.class);
    private static final String PROTOCOL_HEAD = "tcp://";
    private static final String PROTOCOL_SEPERATEOR = ",";
    private static final String BROKERURL_SEPERATOR = ":";
    private static final String CLUSTERCONNECT_PROTOCOL_PREFFIX = "failover:(";
    private static final String CLUSTERCONNECT_PROTOCOL_SUFFIX = ")";

    private int maxConnections;
    private int maximumActiveSessionPerConnection;
    private Boolean blockIfSessionPoolIsFull;

    private ActiveMQConnectionFactory activeMQConnectionFactory;

    public BrokerConfigBean getBrokerConfigBean() {
        return brokerConfigBean;
    }

    public void setBrokerConfigBean(BrokerConfigBean brokerConfigBean) {
        this.brokerConfigBean = brokerConfigBean;
    }

    private BrokerConfigBean brokerConfigBean;

    public ActiveMQConnectionFactory getActiveMQConnectionFactory() {
        return activeMQConnectionFactory;
    }

    public void setActiveMQConnectionFactory(ActiveMQConnectionFactory activeMQConnectionFactory) {
        this.activeMQConnectionFactory = activeMQConnectionFactory;
    }

    public void init(int maxConnections, int maximumActiveSessionPerConnection, boolean blockIfSessionPoolIsFull) {
        this.maxConnections = maxConnections;
        this.maximumActiveSessionPerConnection = maximumActiveSessionPerConnection;
        this.blockIfSessionPoolIsFull = blockIfSessionPoolIsFull;
    }

    @Override
    public ConnectionFactory getDefaultConnectionFactory() {
        return super.getConnectionFactory();
    }

    @Override
    public Connection createConnection(List<String> brokers, String userName, String password) throws JMSException {
        String urlStr = "";
        for (BrokerInfo brokerInfo : brokerConfigBean.getBrokerInfos()) {
            if (brokers.contains(brokerInfo.getBrokerName())) {
                urlStr += PROTOCOL_HEAD + brokerInfo.getBrokerAddr() + BROKERURL_SEPERATOR + brokerInfo.getBrokerPort() + PROTOCOL_SEPERATEOR;
            }
        }

        int start,end;
        start = urlStr.indexOf("");
        end = urlStr.length()-1;
        String brokerURL = CLUSTERCONNECT_PROTOCOL_PREFFIX + urlStr.substring(start, end) + CLUSTERCONNECT_PROTOCOL_SUFFIX;
        logger.info("brokerURL: " + brokerURL);

        return connectionInvokeHandler(userName, password, brokerURL);
    }

    private Connection connectionInvokeHandler(String userName, String password, String brokerURL) throws JMSException {
        activeMQConnectionFactory.setUserName(userName);
        activeMQConnectionFactory.setPassword(password);
        activeMQConnectionFactory.setBrokerURL(brokerURL);

        super.setConnectionFactory(activeMQConnectionFactory);
        super.setMaxConnections(maxConnections);
        super.setMaximumActiveSessionPerConnection(maximumActiveSessionPerConnection);
        super.setBlockIfSessionPoolIsFull(blockIfSessionPoolIsFull);
        return super.createConnection();
    }

}
