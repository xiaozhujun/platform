package org.whut.rentManagement.business.device.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.device.entity.Device;
import org.whut.rentManagement.business.device.service.DeviceService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: Steven
 * Date: 14-10-10
 * Time: 上午11:18
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/device")
public class DeviceServiceWeb {

    private static final PlatformLogger logger = PlatformLogger.getLogger(DeviceServiceWeb.class);

    @Autowired
    private DeviceService deviceService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
   public String add(@FormParam("jsonString") String jsonString)throws ParseException{
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }
        Device device = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Device.class);
        if(device==null||device.getNumber()==null||device.getNumber().trim().equals("")
                ||device.getTypeId()==null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空!");
        }

        Long appId= UserContext.currentUserAppId();
        Long id;
        try{
            id=deviceService.getIdByNumber(device.getNumber(),appId);
        }catch(Exception e){
            id=null;
        }
        if(id==null){
            device.setCreateTime(new Date());
            device.setHavePrint(0);
            device.setAppId(appId);
            deviceService.add(device);
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "添加成功!");
        }else{
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "设备编号已存在!");
        }
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString) throws ParseException{
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空!");
        }
        Device device = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Device.class);
        if(device==null||device.getId()==null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空!");
        }
        long appId= UserContext.currentUserAppId();
        device.setCreateTime(null);
        device.setAppId(appId);
        deviceService.update(device);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }


    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString)
    {
        Device device= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Device.class);
        int result=deviceService.delete(device);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS) ;
        }
        else {
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        long appId= UserContext.currentUserAppId();
        List<Map<String,String>> list=deviceService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getIdByNumber")
    @POST
    public String getIdByNumber(@FormParam("number")String number){
        long appId= UserContext.currentUserAppId();
        long id=deviceService.getIdByNumber(number,appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(id, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/getMainDeviceList")
    @GET
    public String getMainDeviceList(){
        long appId= UserContext.currentUserAppId();
        List<Map<String,String>> list=deviceService.getMainDeviceListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/findByCondition")
    @POST
    public String listByCondition(@FormParam("jsonString") String jsonString){
        logger.error("jsonString="+jsonString);
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }

        Map<String,Object> condition = JsonMapper.buildNonDefaultMapper().fromJson(jsonString, HashMap.class);
        condition.put("appId",UserContext.currentUserAppId());
        List<Map<String,Object>> list = deviceService.findByCondition(condition);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/mainDeviceInfo")
    @POST
    public String mainDeviceInfo(@FormParam("mainDeviceId") String mainDeviceId){
        if(mainDeviceId==null||mainDeviceId.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        Map<String,Object> condition = new HashMap<String, Object>();
        condition.put("mainDeviceId",Long.parseLong(mainDeviceId));
        condition.put("appId",UserContext.currentUserAppId());
        Map<String,Object> mainDeviceInfo = deviceService.getMainDeviceInfo(condition);
        List<Map<String,Object>> deviceList = deviceService.listByMainDeviceId(condition);
        mainDeviceInfo.put("deviceList",deviceList);
        return  JsonResultUtils.getObjectResultByStringAsDefault(mainDeviceInfo,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/setPrint")
    @POST
    public String setPrint(@FormParam("deviceId") String deviceId,@FormParam("havePrint") String havePrint){
        if(havePrint==null||havePrint.trim().equals("")||deviceId==null||deviceId.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        Device device = new Device();
        device.setId(Long.parseLong(deviceId));
        device.setHavePrint(Integer.parseInt(havePrint));
        device.setAppId(UserContext.currentUserAppId());
        deviceService.update(device);
        return  JsonResultUtils.getObjectResultByStringAsDefault(device.getId(),JsonResultUtils.Code.SUCCESS);
    }


}
