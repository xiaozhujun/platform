package org.whut.rentManagement.business.device.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.device.entity.Device;
import org.whut.rentManagement.business.device.entity.SubDevice;
import org.whut.rentManagement.business.device.service.DeviceService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @Autowired
    private DeviceService deviceService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
   public String add(@FormParam("name") String name,@FormParam("deviceTypeId")String deviceTypeId,@FormParam("storehouseId")String storehouseId,@FormParam("contractId")String contractId,
                      @FormParam("status")String status,@FormParam("number")String number,@FormParam("produceTime")String produceTime
                     )throws ParseException{
        if(name==null||name.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"设备名不能为空");
        }
        if (produceTime==null||produceTime.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"生产时间不能为空");
        }
        long appId= UserContext.currentUserAppId();
        Long id;
        try{
            id=deviceService.getIdByNumber(number,appId);
        }catch(Exception e){
            id=null;
        }
        if(id==null){
            Device device=new Device();
            Date createTime=new Date();
            device.setName(name);
            device.setNumber(number);
            device.setStatus(status);
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //定义时间格式
            device.setCreateTime(createTime);  //String搞成date类型
            device.setProduceTime(sdf.parse(produceTime));
            try{
                device.setTypeId(Long.parseLong(deviceTypeId));
            }catch (Exception e){
                device.setTypeId(null);
            }
            try{
                device.setStorehouseId(Long.parseLong(storehouseId));
            }catch (Exception e){
                device.setStorehouseId(null);
            }
            try{
                device.setContractId(Long.parseLong(contractId));
            }catch (Exception e){
                device.setContractId(null);
            }
            device.setAppId(appId);
            deviceService.add(device);
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "添加成功!");
        }else{
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "已存在该设备!");
        }
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString) throws ParseException{
            long appId= UserContext.currentUserAppId();
            SubDevice subdevice = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubDevice.class);
            if(subdevice==null){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空!");
            }
            long id;
            try{
                id=deviceService.getIdByNumber(subdevice.getNumber(),appId);
            }catch (Exception e){
                id=0;
            }
            if(id!=0 && id!=subdevice.getId())
            {
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"设备编号已存在");
            }
            else
            {
                Device device=new Device();
                device.setId(subdevice.getId());
                device.setName(subdevice.getName());
                device.setTypeId(Long.parseLong(subdevice.getDeviceType()));
                device.setStorehouseId(Long.parseLong(subdevice.getStorehouse()));
                device.setAddress(subdevice.getAddress());
                device.setContractId(Long.parseLong(subdevice.getContract()));
                device.setNumber(subdevice.getNumber());
                device.setPrice(subdevice.getPrice());
                device.setPriceUnit(subdevice.getPriceUnit());
                device.setStatus(subdevice.getStatus());
                device.setAppId(appId);
                DateFormat DF=new SimpleDateFormat("yyyy-MM-dd");
                device.setProduceTime(DF.parse(subdevice.getProduceTime()));
                deviceService.update(device);
                return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
            }
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
        List<Map<String,String>> list=deviceService.getMainDeviceList(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/findByCondition")
    @POST
    public String listByCondition(@FormParam("jsonString") String jsonString){
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        Map<String,Object> condition = JsonMapper.buildNonDefaultMapper().fromJson(jsonString, HashMap.class);
        condition.put("appId",UserContext.currentUserAppId());
        List<Map<String,String>> list = deviceService.findByCondition(condition);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
}
