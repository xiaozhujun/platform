package org.whut.platform.fundamental.websocket;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.fundamental.communication.api.WsMessageDispatcher;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.message.api.PlatformMessageProducer;

import javax.jms.MessageNotWriteableException;

/**
 * Created with IntelliJ IDEA.
 * User: tgq
 * Date: 14-9-2
 * Time: 下午7:59
 * To change this template use File | Settings | File Templates.
 */
public class WebsocketMessageDispatcher implements WsMessageDispatcher {

    public static final PlatformLogger logger=PlatformLogger.getLogger(WebsocketMessageDispatcher.class);

    public static final String destination= Constants.WWBSOCKEY_QUEUE_DESTINATION;

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
                logger.info("dispatch message: "+messageBody);
            }catch(MessageNotWriteableException e){
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void exceptionProcess() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
