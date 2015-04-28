package org.whut.platform.fundamental.communication.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.fundamental.communication.api.MinaMessageDispatcher;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: tgq
 * Date: 15-3-4
 * Time: 下午9:43
 * To change this template use File | Settings | File Templates.
 */
public class MinaServerHanlder extends IoHandlerAdapter {
    public static final PlatformLogger logger = PlatformLogger.getLogger(MinaServerHanlder.class);
    public static HashMap<IoSession,StringBuffer> msgBufferMap = new HashMap<IoSession, StringBuffer>();
    public static HashMap<IoSession,Integer> IdleDeadlineMap = new HashMap<IoSession, Integer>();
    public static HashMap<IoSession,Date> IdleDeadlineTimeMap = new HashMap<IoSession, Date>();

    @Autowired
    private MinaMessageDispatcher minaMessageDispatcher;

    public MinaMessageDispatcher getMinaMessageDispatcher() {
        return minaMessageDispatcher;
    }

    public void setMinaMessageDispatcher(MinaMessageDispatcher minaMessageDispatcher) {
        this.minaMessageDispatcher = minaMessageDispatcher;
    }

    public StringBuffer getMsgBuffer(IoSession session){
        StringBuffer msgBuffer = msgBufferMap.get(session);
        if(msgBuffer==null){
            msgBuffer = new StringBuffer();
            msgBufferMap.put(session,msgBuffer);
        }
        return msgBuffer;
    }
    @Override
    public void exceptionCaught(IoSession session, Throwable cause)throws Exception {
        minaMessageDispatcher.exceptionProcess();
        cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message)throws Exception {
        String str = message.toString();
        resolveMessage(str,session);
        logger.info("server -消息已经接收到!"+message);
    }

    private void resolveMessage(String msg,IoSession key){
        minaMessageDispatcher.dispatchMessage(msg);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        msgBufferMap.remove(session);
        logger.info("server-session关闭连接断开");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        IdleDeadlineMap.put(session,1);
        logger.info("server-session创建，建立连接");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status)throws Exception {
        if (IdleDeadlineMap.get(session)==1){
            IdleDeadlineTimeMap.put(session,new Date());
            IdleDeadlineMap.put(session,2);
        }
        else {
              if ((new Date()).getTime()-IdleDeadlineTimeMap.get(session).getTime()>(1000*60*30)){
                  session.close(Boolean.TRUE);
              }
        }
        logger.info("server-服务端进入空闲状态..");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        logger.info("server-服务端与客户端连接打开...");
    }
}

