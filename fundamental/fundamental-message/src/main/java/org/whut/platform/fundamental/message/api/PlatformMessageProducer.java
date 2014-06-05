package org.whut.platform.fundamental.message.api;

import javax.jms.Destination;
import javax.jms.Message;
import java.util.Map;

/**
 * JMS 消息生产者 按照topic或者按照queue发送消息
 * 
 * @author quanxiwei
 * 
 */

public interface PlatformMessageProducer {
	/**
	 * 发送消息
	 * 
	 * @param destination
	 *            目的地 可以是队列或者topic
	 * @param map
	 */
	void sendMessage(final Destination destination, Map<String, Object> map);

	/**
	 * 发送消息
	 * 
	 * @param destination
	 *            目的地 可以是队列或者topic
	 * @param message
	 */
	void sendMessage(final Destination destination, final Message message);

	/**
	 * 在中转器上面注册，可以让平台把收到的消息转发出来。让系统自动把从oriAddr来的消息转发到traAddr上。类型type可以是queue(=1)
	 * 或者topic(=2)
	 * 
	 * @param oriAddr
	 *            原始地址
	 * @param traAddr
	 *            转发地址
	 * @param type
	 *            以哪种形式转发，1：queue转发,2：topic转发.
	 */
	void registryRelay(String oriAddr, String traAddr, int type);

	/**
	 * 发送topic
	 * 
	 * @param destination
	 * @param map
	 */
	void sendTopic(String destination, Map<String, Object> map);

    /**
     * 发送topic
     *
     * @param destination
     * @param message
     */
    void sendTopic(String destination, Message message);

	/**
	 * 发送queue
	 * 
	 * @param destination
	 * @param map
	 */
	void sendQueue(String destination, Map<String, Object> map);

    /**
     * 发送queue
     *
     * @param destination
     * @param message
     */
    void sendQueue(String destination, Message message);
}
