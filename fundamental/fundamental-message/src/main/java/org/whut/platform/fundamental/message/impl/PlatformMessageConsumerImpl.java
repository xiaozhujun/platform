package org.whut.platform.fundamental.message.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.whut.platform.fundamental.logger.PlatformLogger;

import javax.jms.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PlatformMessageConsumerImpl {
	private static final PlatformLogger LOGGER = PlatformLogger
			.getLogger(PlatformMessageConsumerImpl.class);

	private ConnectionFactory connectionFactory;
	private Connection connection;
	private Map<Destination, Session> consumerSessionMap = new HashMap<Destination, Session>();
	private static final int THREADS = 5;
	private Executor executor = Executors.newFixedThreadPool(THREADS);

	private Lock lock = new ReentrantLock();
	private Condition doMonitorCondition = lock.newCondition();
	// 是否初始化完成
	private boolean isInitialized = false;

	private MessageListener messageListener;

	// inject
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	// inject
	public void setMessageListener(MessageListener messageListener) {
		this.messageListener = messageListener;
	}

	public void springInit() {
		LOGGER.info("Initializing JMS Message Consumer.");
		// 单独启动线程来做这件事情。保证主线程正常将系统启动
		executor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					lock.lock();
					connection = connectionFactory.createConnection();
					connection.start();

					// 队列初始化成功
					isInitialized = true;
					// 唤醒等待的线程
					doMonitorCondition.signal();
					LOGGER.info("PlatformMessageConsumer initialized successful!");
				} catch (JMSException e) {
					LOGGER.debug("error:", e);
				} finally {
					lock.unlock();
				}
			}
		});
	}

	public void removeMonitor(Destination destination) {
		Session session = consumerSessionMap.remove(destination);
		if (session == null) {
			return;
		}
		try {
			session.close();
		} catch (JMSException e) {
			LOGGER.debug("error:", e);
		}
	}

	public void doMonitor(Destination destination) {
		lock.lock();
		try {
			while (!isInitialized) {
				doMonitorCondition.await();
			}
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			MessageConsumer consumer = session.createConsumer(destination);
			// 将session放入map,方便移除这个监听
			consumerSessionMap.put(destination, session);
			consumer.setMessageListener(new CustomerMessageListener(
					messageListener));
			LOGGER.info("Jms Message Consumer started, destination:{}",
					destination);
		} catch (JMSException e) {
			LOGGER.error(
					"Start Jms Message Consumer failed. errorCode:{} message{}",
					new Object[] { e.getErrorCode(), e.getMessage() });
		} catch (InterruptedException e) {
			LOGGER.error("PeixunMessageConsumer do monitor error: "
					+ e.getCause().getMessage());
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 静态内部类，以提高性能.
	 * 
	 * @author yangjie01
	 * 
	 */
	static class CustomerMessageListener implements MessageListener {

		private MessageListener messageListener;

		public CustomerMessageListener(MessageListener messageListener) {
			this.messageListener = messageListener;
		}

		@Override
		public void onMessage(Message message) {
			if (ObjectUtils.notEqual(messageListener, null)) {
				messageListener.onMessage(message);
			}
		}
	}

	public void stop() {
		try {
			LOGGER.info("Closing MessageConsumer's resources");
			// 后面那个条件没啥用吧？consumerSessionMap != null &&
			// consumerSessionMap.size() > 0
			if (ObjectUtils.notEqual(consumerSessionMap, null)) {
				for (Session session : consumerSessionMap.values()) {
					session.close();
				}
			}
			if (connection != null) {
				connection.close();
			}
			LOGGER.info("MessageConsumer's resources closed");
		} catch (JMSException e) {
			LOGGER.error(
					"Close MessageConsumer's resources occur error. code:{} message:{}",
					e.getErrorCode(), e.getMessage());
		}
	}

}
