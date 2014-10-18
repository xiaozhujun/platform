package org.whut.rentManagement.business.device.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.device.entity.Device;
import org.whut.rentManagement.business.device.service.DeviceService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
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
    public String add(@FormParam("name") String name,@FormParam("mainDeviceId")long mainDeviceId,@FormParam("typeId")long typeId,
                      @FormParam("storehouseId")long storehouseId,@FormParam("contractId")long contractId,@FormParam("address")String address,
                      @FormParam("status")String status,@FormParam("number")String number,@FormParam("price")String price,@FormParam("produceTime")Date produceTime,@FormParam("priceUnit")String priceUnit,
                      @FormParam("createTime")Date createTime){
        if(name==null||number.equals("")||typeId==0||name.equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }

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
            device.setCreateTime(createTime);
            device.setAddress(address);
            device.setContractId(contractId);
            device.setMainDeviceId(mainDeviceId);
            device.setPrice(price);
            device.setPriceUnit(priceUnit);
            device.setStatus(status);
            device.setProduceTime(produceTime);
            device.setTypeId(typeId);
            device.setStorehouseId(storehouseId);
            device.setAppId(appId);
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
        Device device = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Device.class);
        int result=deviceService.update(device);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS) ;
        }
        else {
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
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
        long appId=UserContext.currentUserAppId();
        List<Device> list=deviceService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getIdByNumber")
    @POST
    public String getIdByNumber(@FormParam("number")String number){
        long appId=UserContext.currentUserAppId();
        long id=deviceService.getIdByNumber(number,appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(id,JsonResultUtils.Code.SUCCESS);
    }
}
