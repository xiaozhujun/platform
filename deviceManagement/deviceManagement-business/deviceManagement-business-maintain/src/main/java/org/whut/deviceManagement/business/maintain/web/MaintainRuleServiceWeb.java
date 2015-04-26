package org.whut.deviceManagement.business.maintain.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.deviceManagement.business.maintain.entity.MaintainRule;
import org.whut.deviceManagement.business.maintain.service.MaintainRuleService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-4-26
 * Time: 上午12:00
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/maintainRule")
public class MaintainRuleServiceWeb {
    private static final PlatformLogger logger = PlatformLogger.getLogger(MaintainRuleServiceWeb.class);

    @Autowired
    private MaintainRuleService maintainRuleService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("jsonString") String jsonString)throws ParseException {
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
        }
        MaintainRule maintainRule = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,MaintainRule.class);
        if(maintainRule==null||maintainRule.getName()==null||maintainRule.getName().trim().equals("")
                ||maintainRule.getDeviceTypeId()==null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空!");
        }
        maintainRule.setCreateTime(new Date());
        maintainRule.setAppId(UserContext.currentUserAppId());
        maintainRuleService.add(maintainRule);

        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "添加成功!");
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString) throws ParseException{
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空!");
        }
        MaintainRule maintainRule = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,MaintainRule.class);
        if(maintainRule==null||maintainRule.getId()==null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空!");
        }
        long appId= UserContext.currentUserAppId();
        maintainRule.setCreateTime(null);
        maintainRule.setAppId(appId);
        maintainRuleService.update(maintainRule);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }


    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString)
    {
        MaintainRule maintainRule= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,MaintainRule.class);
        int result=maintainRuleService.delete(maintainRule);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS) ;
        }
        else {
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @GET
    public String list(){
        long appId= UserContext.currentUserAppId();
        List<Map<String,String>> list=maintainRuleService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
}
