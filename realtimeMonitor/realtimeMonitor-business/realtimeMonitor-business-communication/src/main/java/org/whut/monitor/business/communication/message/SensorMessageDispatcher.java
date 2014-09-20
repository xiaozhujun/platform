package org.whut.monitor.business.communication.message;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.monitor.business.communication.service.CollectorStatusService;
import org.whut.monitor.business.monitor.service.CollectorService;
import org.whut.monitor.business.monitor.service.SensorService;
import org.whut.platform.fundamental.communication.api.MessageDispatcher;
import org.whut.platform.fundamental.communication.api.WsMessageDispatcher;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.message.api.PlatformMessageProducer;
import org.whut.platform.fundamental.redis.connector.RedisConnector;

import javax.jms.MessageNotWriteableException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-6-5
 * Time: 下午7:26
 * To change this template use File | Settings | File Templates.
 */
public class SensorMessageDispatcher implements MessageDispatcher {

    public static final PlatformLogger logger = PlatformLogger.getLogger(SensorMessageDispatcher.class);

    private static final String destination = Constants.SENSOR_QUEUE_DESTINATION;

    @Autowired
    private PlatformMessageProducer platformMessageProducer;

    @Autowired
    private CollectorService collectorService;
    @Autowired
    private SensorService sensorService;
    @Autowired
    private CollectorStatusService collectorStatusService;
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
                platformMessageProducer.sendTopic(destination,message);
            } catch (MessageNotWriteableException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }
    }

    public PlatformMessageProducer getPlatformMessageProducer() {
        return platformMessageProducer;
    }

    public void setPlatformMessageProducer(PlatformMessageProducer platformMessageProducer) {
        this.platformMessageProducer = platformMessageProducer;
    }

    @Override
    public void exceptionProcess() {
        //{sensors:[{sensorNum:'2100000000010000',dataType:'Route',time:'2014-09-20 10:04:46',data:[313,272,462,84,223,100,209,416,85,398,106,183,76,27,157,255,85,259,97,212,307,139,279,145,218,342,188,175,135,409,241,299,270,458,374,465,349,395,96,418,114,187,53,424,192,416,340,395,122,188,216,286,28,403,92,350,220,10,118,428],id:1,meanVariance:129.19405193566595,MaxValue:465.0,MinValue:10.0,warnCount:19514,collectorNum:'01',lastCommunicateTime:'2014-09-20 10:04:46'}]}
        String sensorNum = redisConnector.get("sensorNum");
        System.out.println(sensorNum);
        if (collectorStatusService.delete(sensorNum)) {
//            wsMessageDispatcher.dispatchMessage(sensorService.getCNumBySNum(sensorNum) + "离线");
            wsMessageDispatcher.dispatchMessage("{sensors:[{sensorNum:'" + sensorNum + "',dataType:'Route',time:'"+new Date().toString()+"',data:[],id:" + sensorService.getSensorId(sensorNum,1) +
                    ",meanVariance:0,MaxValue:0,MinValue:0,warnCount:'暂无数据',collectorNum:'" + sensorService.getCNumBySNum(sensorNum) + "',lastCommunicateTime:'"+new Date().toString()+"',"+"isConnected:'"+"false"+"'"+"}]}");
        }
        collectorService.updateTimeByNumber(redisConnector.get("sensor:{"+sensorNum+"}:collector"),redisConnector.get("sensor:{"+sensorNum+"}:lastDate"));
        collectorService.updateStatusByNumber(redisConnector.get("sensor:{"+sensorNum+"}:collector"),"离线或异常");
    }
}
