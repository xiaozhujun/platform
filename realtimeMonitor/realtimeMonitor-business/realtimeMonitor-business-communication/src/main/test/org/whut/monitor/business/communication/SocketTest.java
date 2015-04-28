package org.whut.monitor.business.communication;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-10-29
 * Time: 上午9:23
 * To change this template use File | Settings | File Templates.
 */
public class SocketTest {
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
            StringBuffer data = new StringBuffer("");
            StringBuffer data1 = new StringBuffer("");
            Date startTime = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for (long j=0;j<count;j++){
                for(int i=0;i<60;i++){
                    if(i==0){
                        data.append(Math.round(Math.random()*500));
                        data1.append(Math.round(Math.random()*400));
                    }else{
                        data.append(",").append(Math.round(Math.random()*500));
                        data1.append(",").append(Math.round(Math.random()*400));
                    }

                }
                Date now = new Date();
                String json = "{sensors:["+"{sensorNum:'2100000000010000',dataType:'Route',time:'"+format.format(now)+"',data:["+data+"]},"
                        +"{sensorNum:'2100000000010001',dataType:'Route',time:'"+format.format(now)+"',data:["+data1+"]}]}";
                writer.write(json);
                writer.flush();//写完后要记得flush
                System.out.println(json);
                data.delete(0,data.length());
                data1.delete(0,data1.length());
                try {
                    Thread.sleep(2000);
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
