package org.whut.inspectManagement.business.device.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.device.entity.DeviceType;
import org.whut.inspectManagement.business.device.service.DeviceTypeService;
import org.whut.inspectManagement.business.device.entity.DeviceType;
import org.whut.inspectManagement.business.device.service.DeviceTypeService;
import org.whut.inspectManagement.business.device.service.InspectAreaService;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-15
 * Time: 下午5:26
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/deviceType")
public class DeviceTypeServiceWeb {
    @Autowired
    DeviceTypeService deviceTypeService;
    @Autowired
    InspectAreaService inspectAreaService;
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,@FormParam("number") String number, @FormParam("description") String description,@FormParam("appId") long appId){
        if(name==null||name.trim().equals("")||number.equals("")||description==null||description
                .trim().equals("")||appId==0) {
            return  JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }
        DeviceType deviceType=new DeviceType();
        deviceType.setName(name);
        deviceType.setNumber(number);
        deviceType.setDescription(description);
        deviceType.setAppId(appId);
        deviceTypeService.add(deviceType);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/list")
    @GET
    public String list(){
        List<DeviceType> list = deviceTypeService.list();
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
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

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/getName")
    @POST
    public  String getName(@FormParam("id") long id){
       long id1= inspectAreaService.getTypeIdByAreaId(id);
        return  deviceTypeService.getNameById(id1);
    }
}
