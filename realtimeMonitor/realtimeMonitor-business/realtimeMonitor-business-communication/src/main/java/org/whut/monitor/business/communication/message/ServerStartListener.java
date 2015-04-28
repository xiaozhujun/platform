package org.whut.monitor.business.communication.message;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.whut.platform.fundamental.communication.server.MinaServer;
import org.whut.platform.fundamental.logger.PlatformLogger;

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
    private MinaServer server;
//    private NIOServer server;
//    private PlatformMessageMonitorRegistry platformMessageMonitorRegistry;
    private SensorMessageListener sensorMessageListener;


    public void contextInitialized(ServletContextEvent event) {
        springContext =  WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        server = (MinaServer)springContext.getBean("minaServer");
       // server = (NIOServer)springContext.getBean("nioServer");
//        platformMessageMonitorRegistry = (PlatformMessageMonitorRegistry)springContext.getBean("platformMessageMonitorRegistry");
        socketServerListenThread = new Thread(server);
        socketServerListenThread.start();
//        platformMessageMonitorRegistry.registerMonitor(new ActiveMQTopic(Constants.SENSOR_QUEUE_DESTINATION));
        sensorMessageListener = (SensorMessageListener) springContext.getBean("sensorMessageListener");
        sensorMessageListener.register(new ActiveMQQueue(Constants.SENSOR_QUEUE_DESTINATION));
        logger.info("nioServer is stated!");
    }

            //tomcat关闭时，关闭线程，释放端口
    public void contextDestroyed(ServletContextEvent event) {

    }




}

