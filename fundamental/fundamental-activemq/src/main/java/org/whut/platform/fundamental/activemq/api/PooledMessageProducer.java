package org.whut.platform.fundamental.activemq.api;

import org.apache.activemq.Service;

import javax.jms.Destination;
import javax.jms.Message;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-3-10
 * Time: 下午2:50
 * To change this template use File | Settings | File Templates.
 */
public interface PooledMessageProducer extends Service {
    /**
     * 向队列中发送map消息
     * @param destination
     * @param map
     */
    public void sendQueue(String destination, Map<String, Object> map);

    /**
     * 向队列中发送message消息
     * @param destination
     * @param message
     */
    public void sendQueue(String destination, Message message);

    /**
     * 向主题中发送map消息
     * @param destination
     * @param map
     */
    public void sendTopic(String destination, Map<String, Object> map);

    /**
     * 向主题中发送message消息
     * @param destination
     * @param message
     */
    public void sendTopic(String destination, Message message);

    /**
     * 发送map消息
     * @param destination
     * @param map
     */
    public void sendMessage(final Destination destination, Map<String, Object> map);

    /**
     * 发送message消息
     * @param destination
     * @param message
     */
    public void sendMessage(final Destination destination, final Message message);
}
