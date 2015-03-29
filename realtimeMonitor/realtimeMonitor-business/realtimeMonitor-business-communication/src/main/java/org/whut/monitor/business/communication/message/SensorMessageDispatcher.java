package org.whut.monitor.business.communication.message;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.monitor.business.monitor.service.CollectorService;
import org.whut.monitor.business.monitor.service.SensorService;
import org.whut.platform.fundamental.activemq.api.PooledMessageProducer;
import org.whut.platform.fundamental.communication.api.MinaMessageDispatcher;
import org.whut.platform.fundamental.communication.api.WsMessageDispatcher;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.redis.connector.RedisConnector;

import javax.jms.MessageNotWriteableException;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-6-5
 * Time: 下午7:26
 * To change this template use File | Settings | File Templates.
 */
public class SensorMessageDispatcher implements MinaMessageDispatcher {
//    public class SensorMessageDispatcher implements MessageDispatcher {
    public static final PlatformLogger logger = PlatformLogger.getLogger(SensorMessageDispatcher.class);

    private static final String destination = Constants.SENSOR_QUEUE_DESTINATION;

    @Autowired
    private PooledMessageProducer pooledMessageProducer;
    @Autowired
    private CollectorService collectorService;
    @Autowired
    private SensorService sensorService;
    @Autowired
    private WsMessageDispatcher wsMessageDispatcher;
    private RedisConnector redisConnector = new RedisConnector();

    @Override
    public void dispatchMessage(String messageBody) {
        if (messageBody!=null){
            logger.info("dispatch: "+messageBody);
            try {
                ActiveMQTextMessage message = new ActiveMQTextMessage();
                message.setText(messageBody);
                pooledMessageProducer.sendQueue(destination,message);
            } catch (MessageNotWriteableException e) {
                logger.error(e.getMessage());
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }
    }

    @Override
    public void exceptionProcess() {
        String sensorNum = redisConnector.get("sensorNum");
        Long appId=sensorService.getAppIdBySNum(sensorNum);
        System.out.println(sensorNum);
        String collectorNum = collectorService.getCollectNumberBySensorNumber(sensorNum);
        List<String> sensorNumbers = sensorService.getSensorNumByCNum(collectorNum);
        for (int i=0; i<sensorNumbers.size(); i++) {
            String number = sensorNumbers.get(i).toString();
            logger.info("jjjjjjjjjjjjjjjjjj " + number);
            wsMessageDispatcher.dispatchMessage("{sensors:[{sensorNum:'" + sensorNumbers.get(i) + "',dataType:'Route',time:'"+new Date().toString()+"',data:[],id:" + sensorService.getSensorId(sensorNum,1) +
                    ",appId:" + appId + ",meanVariance:0,MaxValue:0,MinValue:0,warnCount:'暂无数据',collectorNum:'" + collectorNum + "',lastCommunicateTime:'"+redisConnector.get("sensor:{"+sensorNum+"}:lastDate")+"',"+"isConnected:'"+"false"+"'"+"}]}");
        }
//        wsMessageDispatcher.dispatchMessage("{sensors:[{sensorNum:'" + sensorNum + "',dataType:'Route',time:'"+new Date().toString()+"',data:[],id:" + sensorService.getSensorId(sensorNum,1) +
//                ",meanVariance:0,MaxValue:0,MinValue:0,warnCount:'暂无数据',collectorNum:'" + sensorService.getCNumBySNum(sensorNum) + "',lastCommunicateTime:'"+redisConnector.get("sensor:{"+sensorNum+"}:lastDate")+"',"+"isConnected:'"+"false"+"'"+"}]}");
        collectorService.updateTimeByNumber(redisConnector.get("sensor:{"+sensorNum+"}:collector"),redisConnector.get("sensor:{"+sensorNum+"}:lastDate"));
        collectorService.updateStatusByNumber(redisConnector.get("sensor:{"+sensorNum+"}:collector"),"离线或异常");
    }
}
