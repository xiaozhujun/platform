package org.whut.deviceManagement.business.device.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.mongo.connector.MongoConnector;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.deviceManagement.business.device.entity.Device;
import org.whut.deviceManagement.business.device.service.DeviceService;

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
    @GET
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

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/detailInfo")
    @POST
    public String detailInfo(@FormParam("id") Long id){
        long appId= UserContext.currentUserAppId();
        Map<String,Object> deviceDetail = deviceService.detailInfo(id,appId);
        String mongoId = (String)deviceDetail.get("mongoId");
        if(mongoId!=null){
            MongoConnector mongoConnector = new MongoConnector(FundamentalConfigProvider.get("deviceManagement.mongo.db"),FundamentalConfigProvider.get("deviceManagement.mongo.device.collection"));
            deviceDetail.put("status",mongoConnector.getDocument(mongoId));
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(deviceDetail, JsonResultUtils.Code.SUCCESS);
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


}
