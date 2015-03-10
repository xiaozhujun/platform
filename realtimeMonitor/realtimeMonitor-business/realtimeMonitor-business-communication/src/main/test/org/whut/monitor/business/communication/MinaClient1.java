package org.whut.monitor.business.communication;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.whut.platform.fundamental.communication.dataHander.HCoderFactory;
import org.whut.platform.fundamental.logger.PlatformLogger;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: tgq
 * Date: 15-3-4
 * Time: 下午1:46
 * To change this template use File | Settings | File Templates.
 */
public class MinaClient1 {
    public static final PlatformLogger logger = PlatformLogger.getLogger(MinaClient1.class);
    private static String HOST="localhost";
    private static int PORT=38888;
    public static void main(String[] args) {
        IoConnector conn = new NioSocketConnector();
        // 设置链接超时时间
        conn.setConnectTimeoutMillis(30000L);
        // 添加过滤器
        conn.getFilterChain().addLast("mycodec",new ProtocolCodecFilter(new HCoderFactory(Charset.forName("UTF-8"))));
        // 添加业务处理handler
        conn.setHandler(new MinaClientHandler());
        IoSession session =null;
        try {
            ConnectFuture future = conn.connect(new InetSocketAddress(HOST, PORT));// 创建连接
            future.awaitUninterruptibly();// 等待连接创建完成
            session = future.getSession();// 获得session
            long count = 1000000;
            Date startTime = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for (long j=0;j<count;j++){
//                random[].append(Math.round(Math.random()*600));
                long random[] = new long[24];
                for(int x=0;x<24;x++){
                    random[x] = Math.round(Math.random()*600);
                }
                String prefix="210000000001";
                Date now = new Date();
                String json = "{sensors:["+"{sensorNum:'2100000000010024',dataType:'Route',time:'"+format.format(now)+"',data:["+random[0]+"]},"
                        +"{sensorNum:'2100000000010025',dataType:'Route',time:'"+format.format(now)+"',data:["+random[1]+"]},"
                        +"{sensorNum:'2100000000010026',dataType:'Route',time:'"+format.format(now)+"',data:["+random[2]+"]},"
                        +"{sensorNum:'2100000000010027',dataType:'Route',time:'"+format.format(now)+"',data:["+random[3]+"]},"
                        +"{sensorNum:'2100000000010028',dataType:'Route',time:'"+format.format(now)+"',data:["+random[4]+"]},"
                        +"{sensorNum:'2100000000010029',dataType:'Route',time:'"+format.format(now)+"',data:["+random[5]+"]},"
                        +"{sensorNum:'2100000000010030',dataType:'Route',time:'"+format.format(now)+"',data:["+random[6]+"]},"
                        +"{sensorNum:'2100000000010031',dataType:'Route',time:'"+format.format(now)+"',data:["+random[7]+"]},"
                        +"{sensorNum:'2100000000010032',dataType:'Route',time:'"+format.format(now)+"',data:["+random[8]+"]},"
                        +"{sensorNum:'2100000000010033',dataType:'Route',time:'"+format.format(now)+"',data:["+random[9]+"]},"
                        +"{sensorNum:'2100000000010034',dataType:'Route',time:'"+format.format(now)+"',data:["+random[10]+"]},"
                        +"{sensorNum:'2100000000010035',dataType:'Route',time:'"+format.format(now)+"',data:["+random[11]+"]},"
                        +"{sensorNum:'2100000000010036',dataType:'Route',time:'"+format.format(now)+"',data:["+random[12]+"]},"
                        +"{sensorNum:'2100000000010037',dataType:'Route',time:'"+format.format(now)+"',data:["+random[13]+"]},"
                        +"{sensorNum:'2100000000010038',dataType:'Route',time:'"+format.format(now)+"',data:["+random[14]+"]},"
                        +"{sensorNum:'2100000000010039',dataType:'Route',time:'"+format.format(now)+"',data:["+random[15]+"]},"
                        +"{sensorNum:'2100000000010040',dataType:'Route',time:'"+format.format(now)+"',data:["+random[16]+"]},"
                        +"{sensorNum:'2100000000010041',dataType:'Route',time:'"+format.format(now)+"',data:["+random[17]+"]},"
                        +"{sensorNum:'2100000000010042',dataType:'Route',time:'"+format.format(now)+"',data:["+random[18]+"]},"
                        +"{sensorNum:'2100000000010043',dataType:'Route',time:'"+format.format(now)+"',data:["+random[19]+"]},"
                        +"{sensorNum:'2100000000010044',dataType:'Route',time:'"+format.format(now)+"',data:["+random[20]+"]},"
                        +"{sensorNum:'2100000000010045',dataType:'Route',time:'"+format.format(now)+"',data:["+random[21]+"]},"
                        +"{sensorNum:'2100000000010046',dataType:'Route',time:'"+format.format(now)+"',data:["+random[22]+"]},"
                        +"{sensorNum:'2100000000010047',dataType:'Route',time:'"+format.format(now)+"',data:["+random[23]+"]}]}";
                session.write(json);// 发送消息
                logger.info(json);

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }

            } catch (Exception e) {
            logger.error("客户端链接异常...", e);
        }

        session.getCloseFuture().awaitUninterruptibly();// 等待连接断开
        conn.dispose();
    }
}
