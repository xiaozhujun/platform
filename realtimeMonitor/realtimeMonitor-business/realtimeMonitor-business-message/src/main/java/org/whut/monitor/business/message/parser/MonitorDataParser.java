package org.whut.monitor.business.message.parser;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.monitor.business.communication.service.SensorDataService;
import org.whut.monitor.business.monitor.service.CollectorService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.message.api.MessageParser;
import org.whut.platform.fundamental.message.api.MessageParserProxy;
import org.whut.platform.fundamental.redis.connector.RedisConnector;
import org.whut.platform.fundamental.util.json.JsonMapper;

import javax.jms.JMSException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-5-13
 * Time: 下午6:21
 * To change this template use File | Settings | File Templates.
 */
public class MonitorDataParser implements MessageParser {
    PlatformLogger logger = PlatformLogger.getLogger(MonitorDataParser.class);

    @Autowired(required = true)
    private MessageParserProxy messageParserProxy;

    private static final Integer app=1;
    private static final Integer command=1;

    @Autowired
    private SensorDataService sensorDataService;

    @Override
    public boolean canParse(String message) {
        HashMap<String,Object> messageMap = JsonMapper.buildNormalMapper().fromJson(message, HashMap.class);
        if(messageMap.get("app").equals(app)&&messageMap.get("command").equals(command)){
            return true;
        }
        return false;
    }

    @Override
    public void parse(String message) {
        if(canParse(message)){
            try {
                logger.info("onMessage data: "+message);
                sensorDataService.saveMessage(message);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    public void init(){
        logger.info("registry MonitorDataParser to MessageParserProxy!");
        messageParserProxy.registryParser(this);
    }
}

