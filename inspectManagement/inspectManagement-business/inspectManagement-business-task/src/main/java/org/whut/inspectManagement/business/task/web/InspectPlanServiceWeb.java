package org.whut.inspectManagement.business.task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.task.entity.InspectPlan;
import org.whut.inspectManagement.business.task.service.InspectPlanService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-6-30
 * Time: 下午4:27
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/taskPlan")
public class InspectPlanServiceWeb {
    @Autowired
    InspectPlanService inspectPlanService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/addTaskPlan")
    @POST
    public String add(@FormParam("name") String name,@FormParam("inspectTableId") long inspectTableId,@FormParam("description")String description,
                      @FormParam("rule")String rule,@FormParam("dayStart")String dayStart,@FormParam("dayEnd")String dayEnd
    ){
        if(name==null||name.trim().equals("")||rule==null||rule.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }

        long appId= UserContext.currentUserAppId();
        long id;

        try{
            id=inspectPlanService.getIdByName(name, appId);
        }
        catch(Exception e){
            id=0;
        }
        if(id==0) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            InspectPlan inspectPlan=new InspectPlan();
            inspectPlan.setName(name);
            inspectPlan.setDescription(description);
            inspectPlan.setInspectTableId(inspectTableId);
            inspectPlan.setRule(rule);
            inspectPlan.setCreatetime(new Date());
            inspectPlan.setAppId(appId);
            String[] ruleItem = rule.split(" ");
            inspectPlan.setTimeEnd(Integer.parseInt(ruleItem[0]));
            inspectPlan.setTimeStart(Integer.parseInt(ruleItem[1]));
            try {
                inspectPlan.setDayStart(format.parse(dayStart));
                inspectPlan.setDayEnd(format.parse(dayEnd));
            } catch (ParseException e) {
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"对不起，日期格式不正确!");
            }

            inspectPlanService.add(inspectPlan);
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"恭喜您，任务计划添加成功!");
        }
        else{
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"对不起，任务计划已经存在!");
        }
    }
}
