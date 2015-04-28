package org.whut.platform.fundamental.communication.listener;

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

public class MinaStartListener implements ServletContextListener {
    private static final PlatformLogger logger = PlatformLogger.getLogger(MinaStartListener.class);

    private Thread mServerListenThread;
    private Thread socketServerListenThread;

    private WebApplicationContext springContext;
    private MinaServer server;

    public void contextInitialized(ServletContextEvent event) {
        springContext =  WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        server = (MinaServer)springContext.getBean("minaServer");
        socketServerListenThread = new Thread(server);
        socketServerListenThread.start();
        logger.info("MINA server is stated!");
    }

    //tomcat关闭时，关闭线程，释放端口
    public void contextDestroyed(ServletContextEvent event) {

    }


}

