package org.whut.inspectManagement.business.task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.task.entity.InspectPlan;
import org.whut.inspectManagement.business.task.service.InspectPlanService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Path("/add")
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
            inspectPlan.setCreatetime(new Date());
            inspectPlan.setAppId(appId);
            String[] ruleItem = rule.split(" ");
            inspectPlan.setTimeEnd(Integer.parseInt(ruleItem[0]));
            inspectPlan.setTimeStart(Integer.parseInt(ruleItem[1]));
            String ruleTemp = "";
            for(int i=2;i<ruleItem.length;i++){
                if(ruleTemp.equals("")){
                    ruleTemp += ruleItem[i];
                }else{
                    ruleTemp += " "+ruleItem[i];
                }
            }
            inspectPlan.setRule(ruleTemp);
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

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("id") long id,@FormParam("name") String name,@FormParam("inspectTableId") long inspectTableId,@FormParam("description")String description,
                         @FormParam("rule")String rule,@FormParam("dayStart")String dayStart,@FormParam("dayEnd")String dayEnd)
    {
        if(id==0||name==null||name.trim().equals("")||rule==null||rule.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }

        long appId= UserContext.currentUserAppId();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        InspectPlan inspectPlan=new InspectPlan();
        inspectPlan.setId(id);
        inspectPlan.setName(name);
        inspectPlan.setDescription(description);
        inspectPlan.setInspectTableId(inspectTableId);
        inspectPlan.setCreatetime(new Date());
        inspectPlan.setAppId(appId);
        String[] ruleItem = rule.split(" ");
        inspectPlan.setTimeEnd(Integer.parseInt(ruleItem[0]));
        inspectPlan.setTimeStart(Integer.parseInt(ruleItem[1]));
        String ruleTemp = "";
        for(int i=2;i<ruleItem.length;i++){
            if(ruleTemp.equals("")){
                ruleTemp += ruleItem[i];
            }else{
                ruleTemp += " "+ruleItem[i];
            }
        }
        inspectPlan.setRule(ruleTemp);
        try {
            inspectPlan.setDayStart(format.parse(dayStart));
            inspectPlan.setDayEnd(format.parse(dayEnd));
        } catch (ParseException e) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"对不起，日期格式不正确!");
        }

        inspectPlanService.update(inspectPlan);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);

    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString)
    {
        InspectPlan inspectPlan = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,InspectPlan.class);
        inspectPlanService.delete(inspectPlan);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }


    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @GET
    public String list(){
        long appId=UserContext.currentUserAppId();
        List<InspectPlan> list=inspectPlanService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }


    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/query")
    @POST
    public String query(@FormParam("name") String name){
        if((name==null||name.equals(""))){
            name="";
        }
        long appId = UserContext.currentUserAppId();
        name="%"+name+"%";
        long  deviceTypeId=0;
        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put("name",name);
        params.put("appId",appId);

        List<InspectPlan> list =inspectPlanService.query(params);
        if(list.toArray().length==0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "查询不到结果!");
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
}
