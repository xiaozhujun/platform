package org.whut.monitor.business.communication.message;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.whut.platform.fundamental.communication.server.NIOServer;

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
    private Thread mServerListenThread;
    private Thread socketServerListenThread;

    private WebApplicationContext springContext;
    private NIOServer server;


    public void contextInitialized(ServletContextEvent event) {
        springContext =  WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        server = (NIOServer)springContext.getBean("nioServer");
        socketServerListenThread = new Thread(server);
        socketServerListenThread.start();

    }

            //tomcat关闭时，关闭线程，释放端口
    public void contextDestroyed(ServletContextEvent event) {

    }




}

