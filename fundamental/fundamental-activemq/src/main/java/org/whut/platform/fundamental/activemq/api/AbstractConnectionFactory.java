package org.whut.platform.fundamental.activemq.api;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-3-8
 * Time: 下午4:13
 * To change this template use File | Settings | File Templates.
 */
public interface AbstractConnectionFactory {
    public ConnectionFactory getDefaultConnectionFactory();
    public Connection createConnection(List<String> brokers, String userName, String password) throws JMSException;
}
