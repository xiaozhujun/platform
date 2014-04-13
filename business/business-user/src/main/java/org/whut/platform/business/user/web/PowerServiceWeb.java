package org.whut.platform.business.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.entity.Power;
import org.whut.platform.business.user.service.AuthorityPowerService;
import org.whut.platform.business.user.service.PowerService;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-3-23
 * Time: 下午6:22
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/power")
public class PowerServiceWeb {

    @Autowired
    private PowerService powerService;

    @Autowired
    private AuthorityPowerService authorityPowerService;

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("resource") String resource,@FormParam("type") String type,@FormParam("description") String description){
        if(resource==null || type=="" || resource.trim().equals("") || type.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }
        List<Power> powerList = powerService.findByResource(resource);
        if(powerList.size()>0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"资源已存在！");
        }
        else{
            Power power = new Power();
            power.setResource(resource);
            power.setType(type);
            power.setDescription(description);
            powerService.add(power);
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/list")
    @GET
    public String list(){
        List<Power> list = powerService.list();
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        Power power = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Power.class);
        int result = powerService.update(power);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }else{
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }

    @Produces( MediaType.APPLICATION_JSON+ ";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString){
        Power power = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Power.class);
        String powerResource = power.getResource();
        int deleted = authorityPowerService.deleteByPowerResource(powerResource);
        int result = powerService.delete(power);
        if(result>0&&deleted>=0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }else{
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }
}

