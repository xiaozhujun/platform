package org.whut.monitor.business.communication.message;

import org.apache.activemq.command.ActiveMQTextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.monitor.business.communication.service.SensorDataService;
import org.whut.monitor.business.monitor.service.CollectorService;
import org.whut.platform.fundamental.activemq.consumer.PooledMessageConsumerBase;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.redis.connector.RedisConnector;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-6-5
 * Time: 下午8:49
 * To change this template use File | Settings | File Templates.
 */
public class SensorMessageListener extends PooledMessageConsumerBase {

    public static final PlatformLogger logger = PlatformLogger.getLogger(SensorMessageListener.class);

    @Autowired
    private SensorDataService sensorDataService;

    @Autowired
    private CollectorService collectorService;

    private RedisConnector redisConnector = new RedisConnector();


    @Override
    public void onMessage(Message message) {
        String number = "";
        String lastDate = "";

        if (message instanceof ActiveMQTextMessage){
            try {
                String messageText = ((ActiveMQTextMessage) message).getText();
                logger.info("onMessage data: "+messageText);
                sensorDataService.saveMessage(messageText);
            } catch (JMSException e) {
                collectorService.updateTimeByNumber(redisConnector.get("sensor:{"+number+"}:collector"),lastDate);
                logger.error(e.getMessage());
            }
        }else{
            logger.error("message not text,but "+message.getClass().getName());
        }
    }

    public static void main(String[] args) {
//       NIOServer server = new NIOServer();
//       server.listen();
        String s1=" {sensors:[{sensorNum:'2100000000010000',dataType:'Route',time:'2014-09-04 15:58:18',data:[1,150,4360,225,131]   }]} ";
        String s="id:1,"+"meanVariance:"+1+","+"MaxValue:"+2+"," +"MinValue:"+3;

        int endIndex = s1.indexOf("}]}");
        System.out.println(s1.substring(0,endIndex));
        String s2= s1.substring(0,endIndex)+","+s+"}]}";
        System.out.println(s2);
    }

    @Override
    public void register(Destination destination) {
        receiveMessage(destination);
    }
}
