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
 * User: yangyang
 * Date: 15-5-7
 * Time: 下午4:58
 * To change this template use File | Settings | File Templates.
 */
public class SpringTest {
    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext(
                System.getProperty("user.dir")
                        + "\\fundamental\\fundamental-activemq\\src\\main\\resources\\META-INF\\spring\\activemq-applicationContext.xml"
        );
        SessionFactoryConfig sessionFactoryConfig = (SessionFactoryConfig) context.getBean("sessionFactoryConfig");
        PooledSessionConsumer consumer = (PooledSessionConsumer) context.getBean("testListener");
        System.out.println(consumer.getSessionPool().getSessions());
        consumer.register(sessionFactoryConfig.BROKER_NAMES[0], new ActiveMQQueue("test"));
        consumer.setListener();

        PooledSessionProducer producer = (PooledSessionProducer) context.getBean("pooledSessionProducer");
        ActiveMQTextMessage message = new ActiveMQTextMessage();
        try {
            message.setText("test");
        } catch (MessageNotWriteableException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        producer.sendQueue("test", message);

        consumer.getSessionPool().stop();
        consumer.getSessionPool().getConnectionPool().stop();
    }
}
