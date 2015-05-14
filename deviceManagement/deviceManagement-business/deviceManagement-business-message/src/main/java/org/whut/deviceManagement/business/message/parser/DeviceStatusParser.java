package org.whut.deviceManagement.business.message.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.deviceManagement.business.device.service.DeviceStatusService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.message.api.MessageParser;
import org.whut.platform.fundamental.message.api.MessageParserProxy;
import org.whut.platform.fundamental.util.json.JsonMapper;
import java.util.HashMap;

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

    private static final Integer app=2;
    private static final Integer command=1;

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
        logger.info("parse: "+message);
        if(canParse(message)){
            deviceStatusService.resolveStatus(message);
        }
    }

    public void init(){
        logger.info("registry DeviceStatusParser to MessageParserProxy!");
        messageParserProxy.registryParser(this);
    }
}
