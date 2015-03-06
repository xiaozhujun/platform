package org.whut.monitor.business.communication;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.whut.platform.fundamental.logger.PlatformLogger;

/**
 * Created with IntelliJ IDEA.
 * User: tgq
 * Date: 15-3-4
 * Time: 下午1:47
 * To change this template use File | Settings | File Templates.
 */
public class MinaClientHandler extends  IoHandlerAdapter {
    public static final PlatformLogger logger = PlatformLogger.getLogger(MinaClientHandler.class);

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message)throws Exception {
        logger.info("client消息接收到"+message);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        logger.info("client-消息已经发送"+message);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        logger.info("client -session关闭连接断开");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        logger.info("client -创建session");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        logger.info("client-系统空闲中...");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        logger.info("client-session打开");
    }
}

