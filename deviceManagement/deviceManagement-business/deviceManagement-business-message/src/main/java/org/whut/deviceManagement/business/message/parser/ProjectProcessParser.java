package org.whut.deviceManagement.business.message.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.deviceManagement.business.device.service.DeviceStatusService;
import org.whut.deviceManagement.business.project.service.ProjectSegmentService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.message.api.MessageParser;
import org.whut.platform.fundamental.message.api.MessageParserProxy;
import org.whut.platform.fundamental.util.json.JsonMapper;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-5-14
 * Time: 下午5:07
 * To change this template use File | Settings | File Templates.
 */
public class ProjectProcessParser  implements MessageParser {
    PlatformLogger logger = PlatformLogger.getLogger(ProjectProcessParser.class);

    @Autowired
    private ProjectSegmentService projectSegmentService;
    @Autowired(required = true)
    private MessageParserProxy messageParserProxy;

    private static final Integer app=2;
    private static final Integer command=2;

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
            projectSegmentService.resolveMessage(message);
        }
    }

    public void init(){
        logger.info("registry ProjectProcessParser to MessageParserProxy!");
        messageParserProxy.registryParser(this);
    }
}