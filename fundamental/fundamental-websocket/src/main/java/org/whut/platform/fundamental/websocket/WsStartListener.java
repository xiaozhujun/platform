package org.whut.platform.fundamental.websocket;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.whut.platform.fundamental.message.api.PlatformMessageMonitorRegistry;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created with IntelliJ IDEA.
 * User: tgq
 * Date: 14-9-3
 * Time: 上午8:54
 * To change this template use File | Settings | File Templates.
 */
public class WsStartListener implements ServletContextListener {

    private WebApplicationContext springContext;

    private PlatformMessageMonitorRegistry platformMessageMonitorRegistry;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        springContext =  WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        platformMessageMonitorRegistry = (PlatformMessageMonitorRegistry)springContext.getBean("platformMessageMonitorRegistry");
        platformMessageMonitorRegistry.registerMonitor(new ActiveMQTopic(Constants.WWBSOCKEY_QUEUE_DESTINATION));
        // System.out.println("ssssssssssssssssssssssssss");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
