package org.whut.inspectManagement.business.device.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.device.entity.InspectArea;
import org.whut.inspectManagement.business.device.service.InspectAreaService;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

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
    private InspectAreaService inspectAreaService;
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,@FormParam("description") String description,@FormParam("number") long number,@FormParam("deviceTypeId") long deviceTypeId,@FormParam("appId") long appId ){
        if(name==null||name.trim().equals("")||description==null||description.trim().equals("")||number==0||deviceTypeId==0||appId==0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空！");
        }
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

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/list")
    @GET
    public String list(){
        List<InspectArea> list=inspectAreaService.list();
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        InspectArea inspectArea= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,InspectArea.class);
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
}
