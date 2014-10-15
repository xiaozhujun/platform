package org.whut.platform.fundamental.message.impl;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.lang3.StringUtils;
import org.whut.platform.fundamental.exception.BusinessException;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.message.api.PlatformMessageListener;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.ArrayList;
import java.util.List;

/**
 * 消息分发者
 *
 * @author quanxiwei
 *
 */
public class PlatformMessageDistributer implements MessageListener {
    private static final PlatformLogger LOGGER = PlatformLogger
            .getLogger(PlatformMessageDistributer.class);
    private  List<PlatformMessageListener> listeners=new ArrayList<PlatformMessageListener>();

    public void setListeners(List<PlatformMessageListener> listeners) {
        this.listeners = listeners;
    }

    //添加监听器
    public synchronized void  addListener(PlatformMessageListener listener) throws BusinessException{

        if(this.listeners!=null){
            listeners.add(listener);
        }

    }
    /**
     * 将消息分发出去
     */
    public void onMessage(Message message) {

        LOGGER.info("message receive:{}", message);
        if (listeners == null || listeners.size() == 0) {
            LOGGER.info("no listener injected in distributer. return, message discard.");
            return;
        }

        String messageName = getMessageName(message);
        String monitorDestination;
        for (int i=0;i<listeners.size();i++) {
            monitorDestination =listeners.get(i).getMessageName();
            LOGGER.info(monitorDestination +"=? "+ messageName);
            if (StringUtils.isNotBlank(monitorDestination)&& StringUtils.equalsIgnoreCase(monitorDestination,messageName))
            {
                listeners.get(i).onMessage(message);
            }
        }
    }

    private String getMessageName(Message message) {
        String mesageName = null;
        try {
            Destination msgDestination;
            msgDestination = message.getJMSDestination();
            // 判断类型，针对queue和topic，分别处理
            if (msgDestination instanceof ActiveMQQueue) {
                ActiveMQQueue queue = (ActiveMQQueue) msgDestination;
                mesageName = queue.getQueueName();
            } else if (msgDestination instanceof ActiveMQTopic) {
                ActiveMQTopic topic = (ActiveMQTopic) msgDestination;
                mesageName = topic.getTopicName();
            }
        } catch (JMSException e) {
            LOGGER.error("error:{}", e);
        }
        return mesageName;
    }
}
