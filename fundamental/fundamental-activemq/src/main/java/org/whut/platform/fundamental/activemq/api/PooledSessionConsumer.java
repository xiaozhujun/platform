package org.whut.platform.fundamental.activemq.api;


import org.whut.platform.fundamental.activemq.pool.SessionPool;

import javax.jms.Destination;
import javax.jms.Message;

/**
 * Created with IntelliJ IDEA.
 * User: YangRichard
 * Date: 15-4-15
 * Time: 下午9:43
 * To change this template use File | Settings | File Templates.
 */
public interface PooledSessionConsumer {
    public SessionPool getSessionPool();
    public void setSessionPool(SessionPool sessionPool);
    public void register(String broker, Destination destination);
    public void onMessage(Message message);
    public void setListener();
    public void close();
}
