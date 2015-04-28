package org.whut.platform.fundamental.sms;

import org.whut.platform.fundamental.logger.PlatformLogger;

import javax.jms.Message;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-8-22
 * Time: 上午12:15
 * To change this template use File | Settings | File Templates.
 */
public class SmsMessageListener {

    public static final PlatformLogger logger=PlatformLogger.getLogger(SmsMessageListener.class);
    public String getMessageName() {
        return Constants.SMS_QUEUE_DESTINATION;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void onMessage(Message message) {


    }
}
