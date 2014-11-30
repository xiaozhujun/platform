package org.whut.trackSystem.business.communication.test;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-11-12
 * Time: 下午11:35
 * To change this template use File | Settings | File Templates.
 */
public class SocketTest {
    public static void main(String[] args)  {
        //为了简单起见，所有的异常都直接往外抛
//        String host = "www.cseicms.com";  //要连接的服务端IP地址
        String host = "127.0.0.1";  //要连接的服务端IP地
        int port = 38888;   //要连接的服务端对应的监听端址口
        //与服务端建立连接
        Socket client = null;
        try {
            client = new Socket(host, port);
            Writer writer = new OutputStreamWriter(client.getOutputStream());

            //向服务器端第二次发送字符串
            long count = 1000000;
            StringBuffer lng = new StringBuffer("");
            StringBuffer lat = new StringBuffer("");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long lngData = Math.round(Math.random()*120);
            Long latData = Math.round(Math.random()*30);
            for (long j=0;j<count;j++){
                lng.append(lngData + 0.5*j);
                lat.append(latData + 0.1*j);
                Date now = new Date();
                String json = "{devices:["+"{deviceNum:'001',time:'"+format.format(now)+"',lng:'"+lng+"',lat:'"+lat+"'}]}";
                writer.write(json);
                writer.flush();//写完后要记得flush
                System.out.println(json);
                lng.delete(0,lng.length());
                lat.delete(0,lat.length());
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
