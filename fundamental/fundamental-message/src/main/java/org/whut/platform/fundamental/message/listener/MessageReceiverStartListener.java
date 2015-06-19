package org.whut.platform.fundamental.message.listener;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.whut.platform.fundamental.activemq.config.SessionFactoryConfig;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.message.consumer.MessageConsumer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-4-28
 * Time: 下午2:43
 * To change this template use File | Settings | File Templates.
 */
public class MessageReceiverStartListener implements ServletContextListener {
    private static final PlatformLogger logger = PlatformLogger.getLogger(MessageReceiverStartListener.class);


    private WebApplicationContext springContext;

    private int messageConsumerCount = 3;
    private MessageConsumer messageConsumer;
    private List<MessageConsumer> list = new ArrayList<MessageConsumer>();


    public void contextInitialized(ServletContextEvent event) {
        springContext =  WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        SessionFactoryConfig sessionFactoryConfig = (SessionFactoryConfig) springContext.getBean("sessionFactoryConfig");
        for (int i = 0; i < SessionFactoryConfig.BROKER_NUM; i++) {

            for (int j = 0; j < messageConsumerCount; j++) {

                messageConsumer = (MessageConsumer) springContext.getBean("messageConsumer");
                messageConsumer.register(sessionFactoryConfig.BROKER_NAMES[i], new ActiveMQQueue(FundamentalConfigProvider.get("message.queue.destination")));
                messageConsumer.setListener();
                list.add(messageConsumer);
            }
        }

//        messageConsumer = (MessageConsumer) springContext.getBean("messageConsumer");
//        messageConsumer.register(sessionFactoryConfig.BROKER_NAMES[0], new ActiveMQQueue(FundamentalConfigProvider.get("message.queue.destination")));

        logger.info("messageReceiver is start!");
    }

    //tomcat关闭时，关闭线程，释放端口
    public void contextDestroyed(ServletContextEvent event) {
        for (MessageConsumer consumer : list) {
            consumer.close();
        }
    }




}