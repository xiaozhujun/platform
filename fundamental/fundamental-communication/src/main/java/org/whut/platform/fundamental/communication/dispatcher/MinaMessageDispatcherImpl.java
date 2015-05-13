package org.whut.platform.fundamental.communication.dispatcher;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.fundamental.activemq.api.PooledMessageProducer;
import org.whut.platform.fundamental.activemq.api.PooledSessionProducer;
import org.whut.platform.fundamental.communication.api.ExceptionResolver;
import org.whut.platform.fundamental.communication.api.MinaMessageDispatcher;
import org.whut.platform.fundamental.communication.api.WsMessageDispatcher;
import org.whut.platform.fundamental.config.Constants;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;

import javax.jms.MessageNotWriteableException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-4-28
 * Time: 下午1:49
 * To change this template use File | Settings | File Templates.
 */
public class MinaMessageDispatcherImpl implements MinaMessageDispatcher {
    public static final PlatformLogger logger = PlatformLogger.getLogger(MinaMessageDispatcherImpl.class);

    //private static final String destination = FundamentalConfigProvider.get("message.queue.destination");
    private HashMap<String,String> destinationMap;

//    @Autowired
//    private PooledMessageProducer pooledMessageProducer;
    @Autowired
    private PooledSessionProducer pooledSessionProducer;

    @Autowired(required =false)
    private ExceptionResolver exceptionResolver;

    @Override
    public void dispatchMessage(String messageBody) {
        if (messageBody!=null){
            logger.info("dispatch: "+messageBody);
            try {
                ActiveMQTextMessage message = new ActiveMQTextMessage();
                message.setText(messageBody);
                String destination = getDestination(messageBody);
                logger.info("destination:"+destination);
                if(destination!=null){
                    pooledSessionProducer.sendQueue(destination,message);
                }
            } catch (MessageNotWriteableException e) {
                logger.error(e.getMessage());
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }
    }

    public void iniDestinationMap(){
        if(destinationMap!=null) return;
        String destinationConfig = FundamentalConfigProvider.get("message.queue.destination.config");
        destinationMap = JsonMapper.buildNonDefaultMapper().fromJson( destinationConfig,HashMap.class);
    }

    public static void main(String[] args)
    {

        String test="{\"app\":1,\"command\":1,sensors:["+"{sensorNum:\"";
        System.out.println(test.indexOf("app\":"));
    }

    public String getDestination(String message)
    {
        int startIndex = message.indexOf("app\":");
        int endIndex = message.indexOf(",",startIndex);
        String app="";
        if(endIndex>startIndex)
        {
            app=message.substring(startIndex+5,endIndex);
            if(destinationMap==null){
                iniDestinationMap();
            }
            if(destinationMap!=null){
                return destinationMap.get(app);
            }
        }
        return null;
    }

    @Override
    public void exceptionProcess() {
        if(exceptionResolver!=null){
            exceptionResolver.resolve();
        }
        /*
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
        collectorService.updateTimeByNumber(redisConnector.get("sensor:{"+sensorNum+"}:collector"),redisConnector.get("sensor:{"+sensorNum+"}:lastDate"));
        collectorService.updateStatusByNumber(redisConnector.get("sensor:{"+sensorNum+"}:collector"),"离线或异常");
        **/
    }
}