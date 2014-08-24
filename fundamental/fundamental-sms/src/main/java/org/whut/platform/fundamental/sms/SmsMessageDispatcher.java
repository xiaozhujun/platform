package org.whut.platform.fundamental.sms;

import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.fundamental.communication.api.MessageDispatcher;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.message.api.PlatformMessageProducer;

import javax.jms.MessageNotReadableException;
import javax.jms.MessageNotWriteableException;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-8-22
 * Time: 上午12:14
 * To change this template use File | Settings | File Templates.
 */
public class SmsMessageDispatcher implements MessageDispatcher{

    public static final PlatformLogger logger=PlatformLogger.getLogger(SmsMessageDispatcher.class);

    public static final String destination=Constants.SMS_QUEUE_DESTINATION;

    @Autowired
    private PlatformMessageProducer platformMessageProducer;
    @Override
    public void dispatchMessage(String messageBody) {
       if(messageBody!=null){
           logger.info("dispatch:"+messageBody);
           try{
               ActiveMQTextMessage message=new ActiveMQTextMessage();
               message.setText(messageBody);
               platformMessageProducer.sendTopic(destination,message);
           }catch(MessageNotWriteableException e){
               e.printStackTrace();
           }
       }
    }

    @Override
    public void exceptionProcess() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
