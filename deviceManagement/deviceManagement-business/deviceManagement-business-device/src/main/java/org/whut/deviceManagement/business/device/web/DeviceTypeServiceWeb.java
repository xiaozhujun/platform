package org.whut.deviceManagement.business.device.web;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.deviceManagement.business.device.entity.DeviceType;
import org.whut.deviceManagement.business.device.service.DeviceTypeService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
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
    public String add(@FormParam("name") String name,@FormParam("unit")String unit,@FormParam("warnTime")String warnTime,
                      @FormParam("description")String description,@FormParam("mainDevice")String mainDevice)throws ParseException{
        if(name==null||name.trim().equals("")) {
            return  JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }
        long appId= UserContext.currentUserAppId();
        long id;
        try{
            id=deviceTypeService.getIdByName(name,appId);
        }catch (Exception e){
            id=0;
        }
        if(id==0){
            Date createTime=new Date();
            DeviceType deviceType=new DeviceType();
            deviceType.setName(name);
            deviceType.setDescription(description);
            deviceType.setCreateTime(createTime);
            deviceType.setUnit(unit);
            deviceType.setAppId(appId);
            deviceType.setWarnTime(warnTime);
            try {
                deviceType.setMainDevice(Long.parseLong(mainDevice));
            }catch (Exception e){
                deviceType.setMainDevice(null);
            }
            deviceTypeService.add(deviceType);
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "添加成功！");
        }else{
            return  JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "已存在该设备类型!");
        }
    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString)  throws JSONException {
            long appId = UserContext.currentUserAppId();
            DeviceType deviceType1= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,DeviceType.class);
            if(deviceType1==null){
                  return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能是空");
            }
            long id;
            try{
                id=deviceTypeService.getIdByName(deviceType1.getName(),appId);
            }catch (Exception e){
                id=0;
            }

            if(id!=0 && id!=deviceType1.getId())
            {
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"设备类型已存在");
            }
            else
            {
              DeviceType deviceType=new DeviceType();
              deviceType.setId(deviceType1.getId());
              deviceType.setName(deviceType1.getName());
              deviceType.setMainDevice(deviceType1.getMainDevice());
              deviceType.setWarnTime(deviceType1.getWarnTime());
              deviceType.setUnit(deviceType1.getUnit());
              deviceType.setDescription(deviceType1.getDescription());
              deviceType.setAppId(appId);
              deviceTypeService.update(deviceType);
              return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
            }
    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/delete")
    @POST
    public  String delete(@FormParam("jsonString") String jsonString){
        DeviceType deviceType= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,DeviceType.class);
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
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getIdByName")
    @POST
    public String getIdByName(@FormParam("name")String name){
        long appId= UserContext.currentUserAppId();
        long id=deviceTypeService.getIdByName(name,appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(id, JsonResultUtils.Code.SUCCESS);
    }
}
