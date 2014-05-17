package org.whut.inspectManagement.business.device.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.device.entity.Device;
import org.whut.inspectManagement.business.device.service.DeviceService;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-15
 * Time: 下午3:42
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/device")
public class DeviceServiceWeb {

    @Autowired
    DeviceService deviceService;
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,@FormParam("number")long number,@FormParam("description")String description,
                      @FormParam("appId")long appId,@FormParam("deviceTypeId")long deviceTypeId
                      ){
        if(name==null||number==0||description==null||appId==0||deviceTypeId==0||name.equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }
        else {
            Device device=new Device();
            device.setName(name);
            device.setNumber(number);
            device.setDescription(description);
            device.setAppId(appId);
            device.setDeviceTypeId(deviceTypeId);
            deviceService.add(device);
        }


        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"操作成功");
    }


    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString)
    {
        Device device = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Device.class);
        if(device.getName()==null||device.getAppId()==0||device.getName().equals("")||device.getDeviceTypeId()==0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }
        deviceService.update(device);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString)
    {
        Device device=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Device.class);
        deviceService.delete(device);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }


    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        java.util.List<Device> list=deviceService.list();
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
}
