package org.whut.platform.fundamental.activemq.api;


import org.whut.platform.fundamental.activemq.pool.SessionPool;

import javax.jms.Destination;
import javax.jms.Message;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: YangRichard
 * Date: 15-4-15
 * Time: 下午7:03
 * To change this template use File | Settings | File Templates.
 */
public interface PooledSessionProducer {
    public SessionPool getSessionPool();
    public void setSessionPool(SessionPool sessionPool);
    public void sendMessage(final Destination destination, Map<String, Object> map);
    public void sendMessage(final Destination destination, Message message);
    public void sendTopic(String destination, Map<String, Object> map);
    public void sendTopic(String destination, Message message);
    public void sendQueue(String destination, Map<String, Object> map);
    public void sendQueue(String destination, Message message);
    public void shutdownThreadPool();
}
