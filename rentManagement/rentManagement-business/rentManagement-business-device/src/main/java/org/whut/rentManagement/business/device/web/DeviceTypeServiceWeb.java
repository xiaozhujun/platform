package org.whut.rentManagement.business.device.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.device.entity.DeviceType;
import org.whut.rentManagement.business.device.service.DeviceTypeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Steven
 * Date: 14-10-10
 * Time: 上午11:21
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/deviceType")
public class DeviceTypeServiceWeb {
    @Autowired
    private DeviceTypeService deviceTypeService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name, @FormParam("description") String description,@FormParam("createTime") Date createTime,@FormParam("unit")String unit,@FormParam("warnTime")String warnTime,@FormParam("ismainDevice")long ismainDevice){
        if(name==null||name.trim().equals("")||unit.equals("")) {
            return  JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }
        long appId= UserContext.currentUserAppId();
        long id;
        try{
            id=deviceTypeService.getIdByName(name,appId);
        }
        catch (Exception e){
            id=0;
        }
        if(id==0){
            DeviceType deviceType=new DeviceType();
            deviceType.setName(name);
            deviceType.setCreateTime(createTime);
            deviceType.setDescription(description);
            deviceType.setUnit(unit);
            deviceType.setAppId(appId);
            deviceType.setWarnTime(warnTime);
            deviceType.setMainDevice(ismainDevice);
            deviceTypeService.add(deviceType);
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else{
            return  JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"已存在该设备类型!");
        }
    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        DeviceType deviceType= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,DeviceType.class);
        int result=deviceTypeService.update(deviceType);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS) ;
        }
        else {
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/delete")
    @POST
    public  String delete(@FormParam("jsonString") String jsonString){
        DeviceType deviceType=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,DeviceType.class);
        int result=deviceTypeService.delete(deviceType);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else {
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        List<DeviceType> list = deviceTypeService.getListByAppId(UserContext.currentUserAppId());
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getIdByName")
    @POST
    public String getIdByName(@FormParam("name")String name){
        long appId=UserContext.currentUserAppId();
        long id=deviceTypeService.getIdByName(name,appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(id,JsonResultUtils.Code.SUCCESS);
    }
}
