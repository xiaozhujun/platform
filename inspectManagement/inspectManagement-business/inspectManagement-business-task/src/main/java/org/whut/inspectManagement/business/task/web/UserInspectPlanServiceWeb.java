package org.whut.inspectManagement.business.task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.task.entity.InspectPlan;
import org.whut.inspectManagement.business.task.entity.UserInspectPlan;
import org.whut.inspectManagement.business.task.service.InspectPlanService;
import org.whut.inspectManagement.business.task.service.UserInspectPlanService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-6-30
 * Time: 下午4:28
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/userInspectPlan")
public class UserInspectPlanServiceWeb {
    @Autowired
    UserInspectPlanService userInspectPlanService;

    @Autowired
    InspectPlanService inspectPlanService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("jsonString") String jsonString){
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        UserInspectPlan userInspectPlan = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,UserInspectPlan.class);
        if(userInspectPlan.getUserId()==0||userInspectPlan.getDeviceId()==0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        long appId= UserContext.currentUserAppId();
        long inspectPlanId = inspectPlanService.getIdByName(userInspectPlan.getInspectPlanName(),appId);
        userInspectPlan.setInspectPlanId(inspectPlanId);
        userInspectPlan.setCreatetime(new Date());
        userInspectPlan.setAppId(appId);
        userInspectPlanService.add(userInspectPlan);
        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"恭喜您，任务计划添加成功!");
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString)
    {
        UserInspectPlan userInspectPlan = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,UserInspectPlan.class);
        userInspectPlan.setAppId(UserContext.currentUserAppId());
        userInspectPlanService.update(userInspectPlan);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString)
    {
        UserInspectPlan userInspectPlan = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,UserInspectPlan.class);
        userInspectPlanService.delete(userInspectPlan);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }


    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/planGroupList")
    @GET
    public String planGroupList(){
        long appId=UserContext.currentUserAppId();
        List<InspectPlan> inspectPlanList = inspectPlanService.getListByAppId(appId);
        HashMap<String,List<UserInspectPlan>> userInspectPlanMap = new HashMap<String,List<UserInspectPlan>>();

        UserInspectPlan condition = new UserInspectPlan();
        condition.setAppId(UserContext.currentUserAppId());
        for(InspectPlan inspectPlan:inspectPlanList){
            condition.setInspectPlanId(inspectPlan.getId());
            userInspectPlanMap.put(inspectPlan.getName(), userInspectPlanService.query(condition));
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(userInspectPlanMap, JsonResultUtils.Code.SUCCESS);
    }


    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/query")
    @POST
    public String query(@FormParam("jsonString") String jsonString){
        UserInspectPlan userInspectPlan = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,UserInspectPlan.class);
        userInspectPlan.setAppId(UserContext.currentUserAppId());
        List<UserInspectPlan> list =userInspectPlanService.query(userInspectPlan);

        if(list.toArray().length==0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "查询不到结果!");
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
}
