package org.whut.trackSystem.business.communication.message;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.whut.platform.fundamental.communication.server.NIOServer;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.message.api.PlatformMessageMonitorRegistry;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-11-28
 * Time: 下午1:04
 * To change this template use File | Settings | File Templates.
 */
public class DeviceServerStartListener implements ServletContextListener{
    private static final PlatformLogger logger = PlatformLogger.getLogger(DeviceServerStartListener.class);
    private NIOServer nioServer;
    private WebApplicationContext springContext;
    private Thread deviceTrackThread;
    private PlatformMessageMonitorRegistry platformMessageMonitorRegistry;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        springContext = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        nioServer = (NIOServer)springContext.getBean("nioServer");
        platformMessageMonitorRegistry = (PlatformMessageMonitorRegistry)springContext.getBean("platformMessageMonitorRegistry");
        deviceTrackThread = new Thread(nioServer);
        deviceTrackThread.start();
        platformMessageMonitorRegistry.registerMonitor(new ActiveMQTopic(Constants.DEVICE_QUEUE_DESTINATION));
        logger.info("nioServer is start!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
