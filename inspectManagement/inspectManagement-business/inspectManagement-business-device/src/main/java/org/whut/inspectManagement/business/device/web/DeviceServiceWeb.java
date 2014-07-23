package org.whut.inspectManagement.business.device.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.device.entity.Device;
import org.whut.inspectManagement.business.device.entity.SubDevice;
import org.whut.inspectManagement.business.device.service.DeviceService;
import org.whut.inspectManagement.business.device.service.DeviceTypeService;
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
    DeviceTypeService deviceTypeService;
    @Autowired
    DeviceService deviceService;
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,@FormParam("number") String number,@FormParam("description")String description,
                     @FormParam("deviceTypeId")long deviceTypeId
                      ){
        if(name==null||number.equals("")||deviceTypeId==0||name.equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }

        long appId= UserContext.currentUserAppId();
        long id;

        try{
               id=deviceService.getIdByNumber(number,appId);
        }
        catch(Exception e){
              id=0;
        }
        if(id==0) {
            Device device=new Device();
            device.setName(name);
            device.setNumber(number);
            device.setDescription(description);
            device.setAppId(appId);
            device.setDeviceTypeId(deviceTypeId);
            deviceService.add(device);
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"添加成功!");
        }
        else{
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"已存在该设备!");
        }
    }


    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString)
    {
        SubDevice subDevice = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubDevice.class);
        if(subDevice.getName()==null||subDevice.getAppId()==0||subDevice.getName().equals("")||subDevice.getDeviceTypeName().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }
        Device device=new Device();
        device.setId(subDevice.getId());
        device.setName(subDevice.getName());
        device.setDescription(subDevice.getDescription());
        device.setNumber(subDevice.getNumber());
        device.setAppId(subDevice.getAppId());
        device.setDeviceTypeId(deviceTypeService.getIdByName(subDevice.getDeviceTypeName(),subDevice.getAppId()));
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

        long appId=UserContext.currentUserAppId();
        List<Device> list=deviceService.getListByAppId(appId);
        List<SubDevice> subDevicesList=new ArrayList<SubDevice>();
        for (Device device:list){
            SubDevice subDevice=new SubDevice();
            subDevice.setName(device.getName());
            subDevice.setNumber(device.getNumber());
            subDevice.setDescription(device.getDescription());
            subDevice.setAppId(device.getAppId());
            subDevice.setId(device.getId());
            subDevice.setDeviceTypeName(deviceTypeService.getNameById(device.getDeviceTypeId()));
            subDevicesList.add(subDevice);
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(subDevicesList, JsonResultUtils.Code.SUCCESS);
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
        return JsonResultUtils.getObjectResultByStringAsDefault(mapList, JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/query")
    @POST
    public String listEmployeeByNameDepartmentAndRole(@FormParam("name") String name,@FormParam("number") String number,@FormParam("deviceTypeName") String deviceTypeName){
      if((name==null||name.equals(""))&&(number==null||number.equals(""))&&(deviceTypeName==null||deviceTypeName.equals(""))){
          name="";
          number="";
          deviceTypeName=null;
      }
        else if ((name!=null||!name.equals(""))&&(number==null||number.equals(""))&&(deviceTypeName==null||deviceTypeName.equals(""))){
          number="";
          deviceTypeName=null;
      }
        else if((name==null||name.equals(""))&&(number!=null||!number.equals(""))&&(deviceTypeName==null||deviceTypeName.equals(""))){
          name="";
          deviceTypeName=null;
      }
       else if((name==null||name.equals(""))&&(number==null||number.equals(""))&&(deviceTypeName!=null||!deviceTypeName.equals(""))){
            name="";
            number="";

        }
       else if((name!=null||!name.equals(""))&&(number!=null||!number.equals(""))&&(deviceTypeName==null||deviceTypeName.equals(""))){

            deviceTypeName=null;
        }
       else if((name!=null||!name.equals(""))&&(number==null||number.equals(""))&&(deviceTypeName!=null||!deviceTypeName.equals(""))){

            number="";

        }
        else if((name==null||name.equals(""))&&(number!=null||!number.equals(""))&&(deviceTypeName!=null||!deviceTypeName.equals(""))){
            name="";
        }

        long appId = UserContext.currentUserAppId();
        name="%"+name+"%";
        number="%"+number+"%";
        long  deviceTypeId=0;
        if(deviceTypeName!=null){
           deviceTypeId=deviceTypeService.getIdByName(deviceTypeName,appId);
        }

        List<Device> list =deviceService.getInfoByCondition(name,number,deviceTypeId,appId);
        List<SubDevice> subDevicesList=new ArrayList<SubDevice>();
        for (Device device:list){
            SubDevice subDevice=new SubDevice();
            subDevice.setName(device.getName());
            subDevice.setNumber(device.getNumber());
            subDevice.setDescription(device.getDescription());
            subDevice.setAppId(device.getAppId());
            subDevice.setId(device.getId());
            subDevice.setDeviceTypeName(deviceTypeService.getNameById(device.getDeviceTypeId()));
            subDevicesList.add(subDevice);
        }
        if(subDevicesList.toArray().length==0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "查询不到结果!");
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(subDevicesList, JsonResultUtils.Code.SUCCESS);
    }

}
