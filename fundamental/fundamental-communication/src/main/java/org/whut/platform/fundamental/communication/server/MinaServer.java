package org.whut.platform.fundamental.communication.server;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.fundamental.communication.dataHander.HCoderFactory;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;
/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-1-14
 * Time: 下午9:55
 * To change this template use File | Settings | File Templates.
 */


import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class MinaServer implements Runnable{

    public static final PlatformLogger logger = PlatformLogger.getLogger(MinaServer.class);

    public MinaServer(){

    }
    @Autowired
     private MinaServerHanlder minaServerHanlder;

    @Override
    public void run(){
        String portString = FundamentalConfigProvider.get("communication.port");
        logger.info("socket server listen:"+portString);
        int port = Integer.parseInt(portString);
        try{
            IoAcceptor acceptor = new NioSocketAcceptor(1);
            // 添加一个日志过滤器
            acceptor.getFilterChain().addLast("logger", new LoggingFilter());
            // 添加一个编码过滤器
            acceptor.getFilterChain().addLast("mycodec",
                    new ProtocolCodecFilter(new HCoderFactory(Charset.forName("UTF-8"))));
            // 绑定业务处理器,这段代码要在acceptor.bind()方法之前执行，因为绑定套接字之后就不能再做这些准备
            acceptor.setHandler(minaServerHanlder);
            // 设置读取数据的缓冲区大小
            acceptor.getSessionConfig().setReadBufferSize(2048);
            acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
            // 绑定一个监听端口
            acceptor.bind(new InetSocketAddress(port));
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
