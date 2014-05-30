package org.whut.inspectManagement.business.device.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.device.entity.Device;
import org.whut.inspectManagement.business.device.service.DeviceService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.platform.business.user.security.UserContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.*;
import java.util.List;

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
    public String add(@FormParam("name") String name,@FormParam("number") String number,@FormParam("description")String description,
                     @FormParam("deviceTypeId")long deviceTypeId
                      ){
        if(name==null||number.equals("")||description==null||deviceTypeId==0||name.equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }
        else {
            long appId= UserContext.currentUserAppId();
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
    @GET
    public String list(){
        java.util.List<Device> list=deviceService.list();
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/getListByCondition")
    @POST
    public String getId(@FormParam("deviceType") String deviceType,@FormParam("deviceNumber") String deviceNumber,@FormParam("tagName") String tagName){
        long addId= UserContext.currentUserAppId();
        if((deviceType==null||deviceType.equals(""))&&(deviceNumber==null||deviceNumber.equals(""))&&(tagName==null||tagName.equals(""))) {
            deviceType=null;
            deviceNumber=null;
            tagName=null;
        }
        else if((deviceType!=null||!deviceType.equals(""))&&(deviceNumber==null||deviceNumber.equals(""))&&(tagName==null||tagName.equals(""))){
             deviceNumber=null;
             tagName=null;
        }
        else if((deviceType==null||deviceType.equals(""))&&(deviceNumber!=null||!deviceNumber.equals(""))&&(tagName==null||tagName.equals(""))){
            deviceType=null;
            tagName=null;
        }
        else if((deviceType==null||deviceType.equals(""))&&(deviceNumber==null||deviceNumber.equals(""))&&(tagName!=null||!tagName.equals(""))){
            deviceType=null;
            deviceNumber=null;
        }
        else if((deviceType!=null||!deviceType.equals(""))&&(deviceNumber!=null||!deviceNumber.equals(""))&&(tagName==null||tagName.equals(""))){
           tagName=null;
        }
        else if((deviceType!=null||!deviceType.equals(""))&&(deviceNumber==null||deviceNumber.equals(""))&&(tagName!=null||!tagName.equals(""))){
            deviceNumber=null;
        }
        else if((deviceType==null||deviceType.equals(""))&&(deviceNumber!=null||!deviceNumber.equals(""))&&(tagName!=null||!tagName.equals(""))){
               deviceType=null;
        }
        List<Map<String,String>> mapList = deviceService.getListByCondition(deviceType,deviceNumber,tagName,addId);
        System.out.println(mapList.get(0).get("deviceType"));
        return JsonResultUtils.getObjectResultByStringAsDefault(mapList, JsonResultUtils.Code.SUCCESS);
    }


}
