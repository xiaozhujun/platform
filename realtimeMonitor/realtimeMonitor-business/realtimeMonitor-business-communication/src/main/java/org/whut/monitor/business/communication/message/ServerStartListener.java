package org.whut.monitor.business.communication.message;

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
 * 用于对mserver进行监听
 * User: xiaozhujun
 * Date: 13-10-19
 * Time: 下午2:14
 * To change this template use File | Settings | File Templates.
 */

public class ServerStartListener implements ServletContextListener {
    private static final PlatformLogger logger = PlatformLogger.getLogger(ServerStartListener.class);

    private Thread mServerListenThread;
    private Thread socketServerListenThread;

    private WebApplicationContext springContext;
    private NIOServer server;

    private PlatformMessageMonitorRegistry platformMessageMonitorRegistry;


    public void contextInitialized(ServletContextEvent event) {
        springContext =  WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        server = (NIOServer)springContext.getBean("nioServer");
        platformMessageMonitorRegistry = (PlatformMessageMonitorRegistry)springContext.getBean("platformMessageMonitorRegistry");
        socketServerListenThread = new Thread(server);
        socketServerListenThread.start();
        platformMessageMonitorRegistry.registerMonitor(new ActiveMQTopic(Constants.SENSOR_QUEUE_DESTINATION));
        logger.info("nioServer is stated!");
    }

            //tomcat关闭时，关闭线程，释放端口
    public void contextDestroyed(ServletContextEvent event) {

    }




}

