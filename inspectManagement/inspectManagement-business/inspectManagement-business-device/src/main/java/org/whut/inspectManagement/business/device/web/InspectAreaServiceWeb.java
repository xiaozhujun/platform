package org.whut.inspectManagement.business.device.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.device.entity.InspectArea;
import org.whut.inspectManagement.business.device.entity.SubInspectArea;
import org.whut.inspectManagement.business.device.service.InspectAreaService;
import  org.whut.inspectManagement.business.device.service.DeviceTypeService;

import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-16
 * Time: 下午1:06
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/inspectArea")
public class InspectAreaServiceWeb {

    @Autowired
    private DeviceTypeService deviceTypeService;
    @Autowired
    private InspectAreaService inspectAreaService;
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,@FormParam("description") String description,@FormParam("number") String number,@FormParam("deviceTypeId") long deviceTypeId){
        if(name==null||name.trim().equals("")||number.equals("")||deviceTypeId==0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空！");
        }
        long appId= UserContext.currentUserAppId();
        long id;
        try{
            id=inspectAreaService.getIdByNumber(number,appId);
        }
        catch (Exception e){
            id=0;
        }
        if(id==0){
            Date date=new Date();
            InspectArea inspectArea=new InspectArea();
            inspectArea.setName(name);
            inspectArea.setDescription(description);
            inspectArea.setCreatetime(date);
            inspectArea.setNumber(number);
            inspectArea.setDeviceTypeId(deviceTypeId);
            inspectArea.setAppId(appId);
            inspectAreaService.add(inspectArea);
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else{
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"已存在该设备区域！");
        }
    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/list")
    @GET
    public String list(){

        long appId= UserContext.currentUserAppId();
        List<InspectArea> list=inspectAreaService.getListByAppId(appId);
        List<SubInspectArea> subEmployeeList=new ArrayList<SubInspectArea>();
         for (InspectArea inspectArea:list){
             SubInspectArea subInspectArea=new SubInspectArea();
             subInspectArea.setName(inspectArea.getName());
             subInspectArea.setId(inspectArea.getId());
             subInspectArea.setDescription(inspectArea.getDescription());
             subInspectArea.setCreatetime(inspectArea.getCreatetime());
             subInspectArea.setAppId(inspectArea.getAppId());
             subInspectArea.setNumber(inspectArea.getNumber());
             subInspectArea.setDeviceTypeName(deviceTypeService.getNameById(inspectArea.getDeviceTypeId()));
             subEmployeeList.add(subInspectArea);
         }
        return JsonResultUtils.getObjectResultByStringAsDefault(subEmployeeList,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        SubInspectArea  subInspectArea= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubInspectArea.class);
         InspectArea inspectArea=new InspectArea();
        inspectArea.setId(subInspectArea.getId());
        inspectArea.setName(subInspectArea.getName());
        inspectArea.setDescription(subInspectArea.getDescription());
        inspectArea.setAppId(subInspectArea.getAppId());
        inspectArea.setNumber(subInspectArea.getNumber());
        inspectArea.setCreatetime(subInspectArea.getCreatetime());
        inspectArea.setDeviceTypeId(deviceTypeService.getIdByName(subInspectArea.getDeviceTypeName(),subInspectArea.getAppId()));
        int result=inspectAreaService.update(inspectArea);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else {
            return  JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/delete")
    @POST
    public  String delete(@FormParam("jsonString") String jsonString){
        InspectArea inspectArea=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,InspectArea.class);
        int result=inspectAreaService.delete(inspectArea);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else {
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getAreaNameByAppId")
    @POST
    public String getAreaNameByAppId(){
        long appId=UserContext.currentUserAppId();
        List<Map<String,String>> list=inspectAreaService.getAreaNameByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
}
