package org.whut.platform.fundamental.activemq.test;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.whut.platform.fundamental.activemq.api.PooledSessionConsumer;
import org.whut.platform.fundamental.activemq.api.PooledSessionProducer;
import org.whut.platform.fundamental.activemq.config.SessionFactoryConfig;
import org.whut.platform.fundamental.activemq.pool.ConnectionPool;
import org.whut.platform.fundamental.activemq.pool.SessionPool;
import org.whut.platform.fundamental.activemq.producer.PooledSessionProducerImpl;

import javax.jms.Destination;
import javax.jms.MessageNotWriteableException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: YangRichard
 * Date: 15-4-15
 * Time: 下午2:37
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    private static int ThreadNun = 1000;
    private static int MessageNum = 500;
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new FileSystemXmlApplicationContext(
                System.getProperty("user.dir")
                        + "\\fundamental\\fundamental-activemq\\src\\main\\resources\\META-INF\\spring\\activemq-applicationContext.xml"
        );
        SessionFactoryConfig sessionFactoryConfig = (SessionFactoryConfig) context.getBean("sessionFactoryConfig");

        Destination destination = new ActiveMQQueue("test");

        for (int i = 0; i < SessionFactoryConfig.BROKER_NUM; i++) {
            for (int j = 0; j < SessionFactoryConfig.LISTENER_NUM; j++) {
                PooledSessionConsumer consumer = (PooledSessionConsumer) context.getBean("testListener");
                System.out.println(consumer.getSessionPool().getSessions());
                consumer.register(sessionFactoryConfig.BROKER_NAMES[i], destination);
                consumer.setListener();
            }
        }

        for (int m = 0; m < ThreadNun; m++) {
            PooledSessionProducer producer = (PooledSessionProducer) context.getBean("pooledSessionProducer");
            Thread thread = new Thread(new TestRunnable(producer));
            thread.start();
        }
        System.out.println("发送完毕");

    }

    private static class TestRunnable implements Runnable {
        private PooledSessionProducer producer;

        public TestRunnable(PooledSessionProducer producer) {
            this.producer = producer;
        }

        @Override
        public void run() {
            for (int i = 0; i < MessageNum; i++) {
                ActiveMQTextMessage message = new ActiveMQTextMessage();
                try {
                    message.setText(producer + ": " + i);
                } catch (MessageNotWriteableException e) {
                    e.printStackTrace();
                }
                producer.sendQueue("test", message);
            }
        }
    }
}
