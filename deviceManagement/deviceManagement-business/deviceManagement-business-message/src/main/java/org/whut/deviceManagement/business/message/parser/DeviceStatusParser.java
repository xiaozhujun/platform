package org.whut.deviceManagement.business.message.parser;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.whut.deviceManagement.business.device.service.DeviceStatusService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.message.api.MessageParser;
import org.whut.platform.fundamental.message.api.MessageParserProxy;
import org.whut.platform.fundamental.message.consumer.MessageConsumer;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-4-28
 * Time: 下午2:53
 * To change this template use File | Settings | File Templates.
 */
public class DeviceStatusParser implements MessageParser{
    PlatformLogger logger = PlatformLogger.getLogger(DeviceStatusParser.class);

    @Autowired
    private DeviceStatusService deviceStatusService;

    @Autowired(required = true)
    private MessageParserProxy messageParserProxy;

    @Override
    public boolean canParse(String message) {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void parse(String message) {
        if(canParse(message)){
            deviceStatusService.resolveStatus(message);
        }
    }

    public void init(){
        logger.info("registry DeviceStatusParser to MessageParserProxy!");
        messageParserProxy.registryParser(this);
    }
}
