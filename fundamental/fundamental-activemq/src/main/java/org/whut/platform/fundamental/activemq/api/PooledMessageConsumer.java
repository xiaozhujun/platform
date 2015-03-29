package org.whut.platform.fundamental.activemq.api;

import javax.jms.Destination;
import javax.jms.MessageListener;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-3-15
 * Time: 下午3:49
 * To change this template use File | Settings | File Templates.
 */
public interface PooledMessageConsumer extends MessageListener {
    /**
     * 注册监听器
     * @param destination
     */
    public void register(Destination destination);
}
