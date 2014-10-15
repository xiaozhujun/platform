package org.whut.monitor.business.monitor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.monitor.business.monitor.entity.WarnCondition;
import org.whut.monitor.business.monitor.service.WarnConditionService;
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
 * Date: 14-7-31
 * Time: 上午9:17
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/warnCondition")
public class WarnConditionServiceWeb {
    @Autowired
    private WarnConditionService warnConditionService;
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("groupName")String groupName,@FormParam("areaName")String areaName,@FormParam
            ("collectorName")String collectorName,
                      @FormParam("sensorName")String sensorName,@FormParam("number")String number,@FormParam
            ("curWarnValue")double curWarnValue) {
        long appId = UserContext.currentUserAppId();
        if (groupName.trim().equals("") || areaName.trim().equals("") || collectorName.trim().equals("") ||
                sensorName.trim().equals("") || number.trim().equals("") || curWarnValue == 0) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数错误");
        }
        Date tempData = new Date();
        WarnCondition warnCondition = new WarnCondition();
        warnCondition.setAppId(appId);
        warnCondition.setGroupName(groupName);
        warnCondition.setAreaName(areaName);
        warnCondition.setCollectorName(collectorName);
        warnCondition.setSensorName(sensorName);
        warnCondition.setCurWarnValue(curWarnValue);
        warnCondition.setWarnTime(tempData);
        warnCondition.setNumber(number);
        warnConditionService.add(warnCondition);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(@FormParam("warnType")String warnType,@FormParam("groupName")String groupName,@FormParam("areaName")String
            areaName,@FormParam("collectorName")String collectorName,
                       @FormParam("sensorName")String sensorName,@FormParam("number")String number
            ,@FormParam("sTime")String sTime,@FormParam("eTime")String eTime) {
        List<WarnCondition> list = warnConditionService.getListByAppId(warnType,groupName,areaName,collectorName,sensorName,number,sTime,eTime);
        if (list == null) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"列表为空");
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
}
