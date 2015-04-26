package org.whut.deviceManagement.business.maintain.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.deviceManagement.business.maintain.entity.DeviceMaintain;
import org.whut.deviceManagement.business.maintain.service.DeviceMaintainService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
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
@Path("/deviceMaintain")
public class DeviceMaintainServiceWeb {
    private static final PlatformLogger logger = PlatformLogger.getLogger(DeviceMaintainServiceWeb.class);

    @Autowired
    private DeviceMaintainService deviceMaintainService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("jsonString") String jsonString)throws ParseException {
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
        }
        DeviceMaintain deviceMaintain = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,DeviceMaintain.class);
        if(deviceMaintain==null||deviceMaintain.getDeviceId()==null||deviceMaintain.getMaintainRuleId()==null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空!");
        }
        deviceMaintain.setLastMaintainTime(new Date());
        deviceMaintainService.add(deviceMaintain);

        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "添加成功!");
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString) throws ParseException{
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空!");
        }
        DeviceMaintain deviceMaintain = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,DeviceMaintain.class);
        if(deviceMaintain==null||deviceMaintain.getId()==null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空!");
        }
        long appId= UserContext.currentUserAppId();
        deviceMaintainService.update(deviceMaintain);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }


    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString)
    {
        DeviceMaintain deviceMaintain= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,DeviceMaintain.class);
        int result=deviceMaintainService.delete(deviceMaintain);
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
        List<Map<String,String>> list=deviceMaintainService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/getLastDeviceMaintainByDeviceGroup")
    @GET
    public String getLastDeviceMaintainByDeviceGroup(){
        long appId= UserContext.currentUserAppId();
        HashMap<String,List<Map<String,String>>> result=deviceMaintainService.getLastDeviceMaintainByDeviceGroup(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(result, JsonResultUtils.Code.SUCCESS);
    }

}
