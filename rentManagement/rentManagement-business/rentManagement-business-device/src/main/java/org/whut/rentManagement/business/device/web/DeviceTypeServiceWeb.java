package org.whut.rentManagement.business.device.web;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.device.entity.DeviceType;
import org.whut.rentManagement.business.device.entity.SubDeviceType;
import org.whut.rentManagement.business.device.service.DeviceTypeService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                      @FormParam("description")String description,@FormParam("createTime")String createTime)throws ParseException{
        if(name==null||name.trim().equals("")) {
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
            deviceType.setDescription(description);
            DateFormat DFT=new SimpleDateFormat("yyyy-MM-dd");
            deviceType.setCreateTime(DFT.parse(createTime));
            deviceType.setUnit(unit);
            deviceType.setAppId(appId);
            deviceType.setWarnTime(warnTime);
            deviceTypeService.add(deviceType);
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else{
            return  JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "已存在该设备类型!");
        }
        /*long appId = UserContext.currentUserAppId();
        try{
            JSONObject jsonObject=new JSONObject(jsonStringList);
            if(jsonObject==null){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空");
            }
            SubDeviceType subDeviceType = JsonMapper.buildNonDefaultMapper().fromJson(jsonStringList, SubDeviceType.class);
            DeviceType deviceType = new DeviceType();
            deviceType.setName(subDeviceType.getName());
            deviceType.setUnit(subDeviceType.getUnit());
            deviceType.setWarnTime(subDeviceType.getWarnTime());
            deviceType.setDescription(subDeviceType.getDescription());
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //定义时间格式
            deviceType.setCreateTime(sdf.parse(subDeviceType.getCreateTime()));  //String搞成date类型
            deviceType.setAppId(appId);
            deviceTypeService.add(deviceType);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);*/
    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString)  throws JSONException, ParseException {
        long appId = UserContext.currentUserAppId();
        try{
            JSONObject jsonObject=new JSONObject(jsonString);
            if(jsonObject==null){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能是空");
            }
        SubDeviceType subdeviceType= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubDeviceType.class);
        DeviceType deviceType=new DeviceType();
        deviceType.setName(subdeviceType.getName());
        deviceType.setMainDevice(subdeviceType.getMainDevice());
        deviceType.setWarnTime(subdeviceType.getWarnTime());
        deviceType.setUnit(subdeviceType.getUnit());
        deviceType.setDescription(subdeviceType.getDescription());
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        deviceType.setCreateTime(format.parse(subdeviceType.getCreateTime()));
        deviceType.setAppId(appId);
        deviceTypeService.update(deviceType);
        }catch (JSONException e){
                e.printStackTrace();
            }
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
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
