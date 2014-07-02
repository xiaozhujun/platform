package org.whut.inspectManagement.business.task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.task.entity.UserInspectPlan;
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

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("jsonString") String jsonString){
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        long appId= UserContext.currentUserAppId();
        UserInspectPlan userInspectPlan = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,UserInspectPlan.class);
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
    @Path("/list")
    @GET
    public String list(){
        long appId=UserContext.currentUserAppId();
        List<UserInspectPlan> list=userInspectPlanService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }


    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/query")
    @POST
    public String listEmployeeByNameDepartmentAndRole(@FormParam("userId") long userId){
        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put("userId",userId);
        params.put("appId",UserContext.currentUserAppId());

        List<UserInspectPlan> list =userInspectPlanService.query(params);
        if(list.toArray().length==0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "查询不到结果!");
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
}
