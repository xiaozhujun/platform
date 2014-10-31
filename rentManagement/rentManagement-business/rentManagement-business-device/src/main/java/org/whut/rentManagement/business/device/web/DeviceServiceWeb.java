package org.whut.rentManagement.business.device.web;


import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.device.entity.Device;
import org.whut.rentManagement.business.device.entity.SubDevice;
import org.whut.rentManagement.business.device.service.DeviceService;

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
                      @FormParam("status")String status,@FormParam("number")String number,@FormParam("produceTime")String produceTime,@FormParam("createTime")String createTime
                     )throws ParseException{
/*    public String add(@FormParam("jsonStringList") String jsonStringList)throws JSONException, ParseException{
        long appId = UserContext.currentUserAppId();
        try{
        JSONObject jsonObject=new JSONObject(jsonStringList);
        if(jsonObject==null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空");
        } */
        long appId= UserContext.currentUserAppId();
        Long id;
        try{
            id=deviceService.getIdByNumber(number,appId);
        }
        catch(Exception e){
            id=null;
        }
        if(id==null) {
            Device device=new Device();
            device.setName(name);
            device.setNumber(number);
            device.setStatus(status);
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //定义时间格式
            device.setCreateTime(sdf.parse(createTime));  //String搞成date类型
            device.setProduceTime(sdf.parse(produceTime));
            long l = Long.parseLong(deviceTypeId);
            device.setTypeId(l);
            long m = Long.parseLong(storehouseId);
            device.setStorehouseId(m);
            long n = Long.parseLong(contractId);
            device.setContractId(n);
            device.setAppId(appId);
            deviceService.add(device);
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "添加成功!");
        }
        else{
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "已存在该设备!");
        }
        /*SubDevice subDevice = JsonMapper.buildNonDefaultMapper().fromJson(jsonStringList, SubDevice.class);
        Device device = new Device();
        device.setName(subDevice.getName());
        device.setNumber(subDevice.getNumber());
        device.setTypeId(subDevice.getTypeId());
        device.setStorehouseId(subDevice.getStorehouseId());
        device.setContractId(subDevice.getContractId());
        device.setStatus(subDevice.getStatus());
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //定义时间格式
        device.setCreateTime(sdf.parse(subDevice.getCreateTime()));  //String搞成date类型
        device.setProduceTime(sdf.parse(subDevice.getProduceTime()));
        device.setAppId(appId);
        deviceService.add(device);
        StoreHouse_Device storeHouse_device=new StoreHouse_Device();//对关联表storeHouse_device进行插入操作
        storeHouse_device.setStorehouseId(subDevice.getStorehouseId());
        storeHouse_device.setDeviceTypeId(subDevice.getTypeId());
        storeHouse_device.setAppId(appId);
        storeHouse_deviceService.add(storeHouse_device);
    }catch (JSONException e){
        e.printStackTrace();
    }
    return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);*/
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString) throws JSONException,ParseException
    {
        long appId= UserContext.currentUserAppId();
        try{
            JSONObject jsonObject=new JSONObject(jsonString);
            if(jsonObject==null){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能是空");
            }
        SubDevice subdevice = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubDevice.class);
        Device device=new Device();
        device.setName(subdevice.getName());
        device.setTypeId(subdevice.getTypeId());
        device.setStorehouseId(subdevice.getStorehouseId());
        device.setAddress(subdevice.getAddress());
        device.setContractId(subdevice.getContractId());
        device.setNumber(subdevice.getNumber());
        device.setPrice(subdevice.getPrice());
        device.setPriceUnit(subdevice.getPriceUnit());
        device.setStatus(subdevice.getStatus());
        device.setAppId(appId);
        DateFormat DF=new SimpleDateFormat("yyyy-MM-dd");
        device.setProduceTime(DF.parse(subdevice.getProduceTime()));
        device.setCreateTime(DF.parse(subdevice.getCreateTime()));
        deviceService.update(device);
        }catch (JSONException e){
                e.printStackTrace();
            }
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
        List<Device> list=deviceService.getListByAppId(appId);
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
}
