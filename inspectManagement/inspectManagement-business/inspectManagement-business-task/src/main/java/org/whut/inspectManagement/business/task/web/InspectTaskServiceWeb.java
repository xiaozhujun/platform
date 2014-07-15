package org.whut.inspectManagement.business.task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.task.entity.InspectTask;
import org.whut.inspectManagement.business.task.service.InspectTaskService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-6-30
 * Time: 下午4:27
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/inspectTask")
public class InspectTaskServiceWeb {

    @Autowired
    private InspectTaskService inspectTaskService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/lastTaskByDeviceGroup")
    @POST
    public String getLastTaskByDeviceGroup(@FormParam("userId")long userId,@FormParam("deviceId")long deviceId,@FormParam("sTime")String sTime,@FormParam("eTime")String eTime){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        InspectTask condition = new InspectTask();
        condition.setUserId(userId);
        condition.setDeviceId(deviceId);
        condition.setAppId(UserContext.currentUserAppId());
        Date today = null;
        try {
            today = format.parse(format.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        try {
            if(sTime!=null&&!sTime.trim().equals("")){
                condition.setStartDay(format.parse(sTime));
            }else {
                condition.setStartDay(today);
            }
            if(eTime!=null&&!eTime.trim().equals("")){
                condition.setEndDay(format.parse(eTime));
            }
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(inspectTaskService.getLastTaskByDeviceGroup(condition), JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/dispatchTask")
    @GET
    public String dispatchTask(){
        inspectTaskService.dispatchTask();
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/userLastTask")
    @GET
    public String userLastTask(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        InspectTask condition = new InspectTask();
        condition.setUserId(UserContext.currentUserId());
        condition.setAppId(UserContext.currentUserAppId());
        Date today = null;
        try {
            today =  format.parse(format.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        condition.setTaskDate(today);

        List<InspectTask> list = inspectTaskService.findByCondition(condition);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getInspectTaskInfo")
    @POST
    public String getInspectTaskInfo(@FormParam("appId")String appId){
        List<Map<String,String>> list=inspectTaskService.getInspectTaskInfo(appId);
        return JsonResultUtils.getObjectStrResultByStringAsDefault(list,200,appId);
    }
}
