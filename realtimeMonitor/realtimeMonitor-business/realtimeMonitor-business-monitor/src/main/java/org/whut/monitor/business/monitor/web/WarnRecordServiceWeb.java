package org.whut.monitor.business.monitor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.monitor.business.monitor.entity.WarnRecord;
import org.whut.monitor.business.monitor.service.WarnRecordService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-7-30
 * Time: 下午3:04
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/warnRecord")
public class WarnRecordServiceWeb {
    @Autowired
    private WarnRecordService warnRecordService;
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("groupName")String groupName,@FormParam("areaName")String areaName,@FormParam("collectorName")String collectorName,
                      @FormParam("sensorName")String sensorName,@FormParam("number")String number,@FormParam("curWarnValue")long curWarnValue) {
        long appId = UserContext.currentUserAppId();
        if (groupName.trim().equals("") || areaName.trim().equals("") || collectorName.trim().equals("") ||
                sensorName.trim().equals("") || number.trim().equals("") || curWarnValue == 0) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数错误");
        }
        Date tempData = new Date();
        WarnRecord warnRecord = new WarnRecord();
        warnRecord.setAppId(appId);
        warnRecord.setGroupName(groupName);
        warnRecord.setAreaName(areaName);
        warnRecord.setCollectorName(collectorName);
        warnRecord.setSensorName(sensorName);
        warnRecord.setCurWarnValue(curWarnValue);
        warnRecord.setWarnTime(tempData);
        warnRecord.setNumber(number);
        warnRecordService.add(warnRecord);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(@FormParam("groupName")String groupName,@FormParam("areaName")String areaName,@FormParam("collectorName")String collectorName,
                       @FormParam("sensorName")String sensorName,@FormParam("number")String number) {
        long appId = UserContext.currentUserAppId();
        List<WarnRecord> list = warnRecordService.getListByAppId(groupName,areaName,collectorName,sensorName,number,appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
}
