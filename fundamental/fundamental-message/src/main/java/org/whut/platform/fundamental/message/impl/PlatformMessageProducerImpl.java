package org.whut.platform.fundamental.message.impl;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.message.api.MessageConst;
import org.whut.platform.fundamental.message.api.PlatformMessageProducer;

import javax.jms.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 评估事件消息提供者
 * 
 * @author quanxiwei
 * 
 */
public class PlatformMessageProducerImpl implements PlatformMessageProducer {
	private static final PlatformLogger LOGGER = PlatformLogger
			.getLogger(PlatformMessageProducerImpl.class);
	private static final String MESSAGE_TYPE = "messageType";

	private ConnectionFactory connectionFactory;

	private Connection connection;
	private Deque<Session> sessions = new LinkedList<Session>();// 这是一个自定义的最最简单的session池实现
	private static final int THREADS=5;
	private Executor executor = Executors.newFixedThreadPool(THREADS);

	/** 设置连接工厂 */
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	/**
	 * 通过spring，在销毁bean之前，回收资源
	 */
	public void stop() {
		try {
			LOGGER.info("Closing JmsMessageProvider resouces");
			if (sessions != null) {
				for (Session session : sessions) {
					session.close();
				}
			}
			if (connection != null) {
				connection.close();
			}
		} catch (JMSException e) {
			LOGGER.error("Close jms ConnectionFactory error. errorCode:"
					+ e.getErrorCode() + " message:" + e.getMessage());
		}
	}

	public static String getMessageType() {
		return MESSAGE_TYPE;
	}

	/**
	 * 通过spring，在加载bean之后，初始化资源
	 * 
	 * @throws javax.jms.JMSException
	 */
	public void springInit() throws JMSException {
		LOGGER.info("Initializing JMS Message Provider.");

		// 把它搞到一个单独的线程中启动，不影响主程序的使用
		executor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					connection = connectionFactory.createConnection();
					connection.start();
					LOGGER.info("Jms Message Provider started");
				} catch (JMSException e) {
					LOGGER.error("Start Jms Message Provider failed. {}", e);
				}
			}
		});
	}

	private synchronized Session acquireSession() throws JMSException {
		if (sessions.size() == 0) {
			return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} else {
			return sessions.pop();
		}
	}

	private synchronized void releaseSession(Session session) {
		if (session == null) {
			return;
		}
		sessions.addLast(session);
	}

	public void sendTopic(String destination, Map<String, Object> map) {
		sendMessage(new ActiveMQTopic(destination), map);
	}

    @Override
    public void sendTopic(String destination, Message message) {
        sendMessage(new ActiveMQTopic(destination), message);
    }

    public void sendQueue(String destination, Map<String, Object> map) {
		sendMessage(new ActiveMQQueue(destination), map);
	}

    @Override
    public void sendQueue(String destination, Message message) {
        sendMessage(new ActiveMQQueue(destination), message);
    }

    /**
	 * 发送消息
	 */
	public void sendMessage(final Destination destination,
			final Map<String, Object> map) {
		executor.execute(new MapMessageSendRunnable(destination, map));
	}
	
	private class MapMessageSendRunnable implements Runnable{
		
		private Destination destination;
		
		private Map<String, Object> map;
		
		
		public MapMessageSendRunnable(final Destination destination,
				final Map<String, Object> map){
			this.destination = destination;
			this.map = map;
		}
		@Override
		public void run() {
			Session session = null;
			try {
				LOGGER.info("Sending message by invoke method->sendMessage(final Destination destination,final Map<String, Object> map)");
				session = acquireSession();
				MessageProducer producer = session
						.createProducer(destination);
				// 不设置消息的存活时间,则该消息一直有效producer#setTimeToLive(timeToLive_

				MapMessage message = session.createMapMessage();
				Set<Entry<String, Object>> entrySet = map.entrySet();
				for (Entry<String, Object> entry : entrySet) {
					message.setObject(entry.getKey(), entry.getValue());
				}
				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
				producer.send(message);
				producer.close();
				LOGGER.info("JMS destination is " + destination.toString()
						+ ",param is " + entrySet);
			} catch (JMSException e) {
				LOGGER.error("Send message by invoke method->sendMessage() error. errorCode:"
						+ e.getErrorCode() + " message:" + e.getMessage());
			} finally {
				releaseSession(session);
			}
		}
		
	}

	/**
	 * 发送消息
	 * 
	 * @param destination
	 * @param message
	 */
	public void sendMessage(final Destination destination, final Message message) {
		executor.execute(new MessageSendRunnable(destination, message));
	}
	
	private class MessageSendRunnable implements Runnable{
		
		private Destination destination;
		private Message message;
		
		public MessageSendRunnable(final Destination destination,Message message){
			this.destination =destination;
			this.message = message;
		}

		@Override
		public void run() {
			Session session = null;
			try {
				LOGGER.info("Sending message by invoke method->sendMessage(final Destination destination,final MapMessage message)");
				session = acquireSession();
				MessageProducer producer = session
						.createProducer(destination);
				// producer#setTimeToLive(timeToLive)不设置消息的存活时间,则该消息一直有效.
				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
				producer.send(message);
				producer.close();
				LOGGER.info("JMS destination is " + destination.toString()
						+ ",message is " + message.toString());
			} catch (JMSException e) {
				LOGGER.error("Send message by invoke method->sendMessage() error. errorCode:"
						+ e.getErrorCode() + " message:" + e.getMessage());
			} finally {
				releaseSession(session);
			}
			
		}
		
	}

	@Override
	public void registryRelay(String oriAddr, String traAddr, int type) {
		Destination destination = new ActiveMQTopic(
				FundamentalConfigProvider.get(MessageConst.REGIST_RELAY_TOPIC));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MessageConst.RELAY_MSG_ORI_ADDR, oriAddr);
		map.put(MessageConst.RELAY_MSG_TRS_ADDR, traAddr);
		map.put(MessageConst.RELAY_MSG_TYPE, type);
		sendMessage(destination, map);
	}
}
