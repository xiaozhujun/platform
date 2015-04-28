package org.whut.platform.fundamental.message.listener;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.message.consumer.MessageConsumer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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

    private MessageConsumer messageConsumer;


    public void contextInitialized(ServletContextEvent event) {
        springContext =  WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        messageConsumer = (MessageConsumer) springContext.getBean("messageConsumer");
        messageConsumer.register(new ActiveMQQueue(FundamentalConfigProvider.get("message.queue.destination")));
        logger.info("nioServer is stated!");
    }

    //tomcat关闭时，关闭线程，释放端口
    public void contextDestroyed(ServletContextEvent event) {

    }




}