package org.whut.monitor.business.communication;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-5-7
 * Time: 下午3:55
 * To change this template use File | Settings | File Templates.
 */
public class SocketTestForXingBian {
    public static void main(String[] args)  {
        //为了简单起见，所有的异常都直接往外抛
        String host = "www.cseicms.com";  //要连接的服务端IP地址
//        String host = "127.0.0.1";  //要连接的服务端IP地
        int port = 38888;   //要连接的服务端对应的监听端址口
        //与服务端建立连接
        Socket client = null;
        try {
            client = new Socket(host, port);
            Writer writer = new OutputStreamWriter(client.getOutputStream());

            //向服务器端第二次发送字符串
            long count = 1000000;
//            StringBuffer random[] = new StringBuffer("");
//            StringBuffer random[]1 = new StringBuffer("");
            Date startTime = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for (long j=0;j<count;j++){
//                random[].append(Math.round(Math.random()*600));
                long random[] = new long[24];
                for(int x=0;x<24;x++){
                    random[x] = Math.round(Math.random()*600);
                }
                Date now = new Date();
                String json = "{sensors:["+"{sensorNum:'3100000000010000',dataType:'Route',time:'"+format.format(now)+"',data:["+random[0]+"]},"
                        +"{sensorNum:'3100000000010001',dataType:'Route',time:'"+format.format(now)+"',data:["+random[1]+"]},"
                        +"{sensorNum:'3100000000010002',dataType:'Route',time:'"+format.format(now)+"',data:["+random[2]+"]},"
                        +"{sensorNum:'3100000000010003',dataType:'Route',time:'"+format.format(now)+"',data:["+random[3]+"]},"
                        +"{sensorNum:'3100000000010004',dataType:'Route',time:'"+format.format(now)+"',data:["+random[4]+"]},"
                        +"{sensorNum:'3100000000010005',dataType:'Route',time:'"+format.format(now)+"',data:["+random[5]+"]},"
                        +"{sensorNum:'3100000000010006',dataType:'Route',time:'"+format.format(now)+"',data:["+random[6]+"]},"
                        +"{sensorNum:'3100000000010007',dataType:'Route',time:'"+format.format(now)+"',data:["+random[7]+"]},"
                        +"{sensorNum:'3100000000010008',dataType:'Route',time:'"+format.format(now)+"',data:["+random[8]+"]},"
                        +"{sensorNum:'3100000000010009',dataType:'Route',time:'"+format.format(now)+"',data:["+random[9]+"]},"
                        +"{sensorNum:'3100000000010010',dataType:'Route',time:'"+format.format(now)+"',data:["+random[10]+"]},"
                        +"{sensorNum:'3100000000010011',dataType:'Route',time:'"+format.format(now)+"',data:["+random[11]+"]},"
                        +"{sensorNum:'3100000000010012',dataType:'Route',time:'"+format.format(now)+"',data:["+random[12]+"]},"
                        +"{sensorNum:'3100000000010013',dataType:'Route',time:'"+format.format(now)+"',data:["+random[13]+"]},"
                        +"{sensorNum:'3100000000010014',dataType:'Route',time:'"+format.format(now)+"',data:["+random[14]+"]},"
                        +"{sensorNum:'3100000000010015',dataType:'Route',time:'"+format.format(now)+"',data:["+random[15]+"]},"
                        +"{sensorNum:'3100000000010016',dataType:'Route',time:'"+format.format(now)+"',data:["+random[16]+"]},"
                        +"{sensorNum:'3100000000010017',dataType:'Route',time:'"+format.format(now)+"',data:["+random[17]+"]},"
                        +"{sensorNum:'3100000000010018',dataType:'Route',time:'"+format.format(now)+"',data:["+random[18]+"]},"
                        +"{sensorNum:'3100000000010019',dataType:'Route',time:'"+format.format(now)+"',data:["+random[19]+"]},"
                        +"{sensorNum:'3100000000010020',dataType:'Route',time:'"+format.format(now)+"',data:["+random[20]+"]},"
                        +"{sensorNum:'3100000000010021',dataType:'Route',time:'"+format.format(now)+"',data:["+random[21]+"]},"
                        +"{sensorNum:'3100000000010022',dataType:'Route',time:'"+format.format(now)+"',data:["+random[22]+"]},"
                        +"{sensorNum:'3100000000010023',dataType:'Route',time:'"+format.format(now)+"',data:["+random[23]+"]}]}";
                writer.write(json);
                writer.flush();//写完后要记得flush
                System.out.println(json);
//                random[].delete(0,random[].length());
//                random[]1.delete(0,random[]1.length());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

            writer.flush();//写完后要记得flush
            writer.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
