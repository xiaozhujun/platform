package org.whut.inspectManagement.business.device.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.device.entity.InspectTag;
import org.whut.inspectManagement.business.device.entity.SubInspectTag;
import org.whut.inspectManagement.business.device.service.DeviceService;
import org.whut.inspectManagement.business.device.service.InspectAreaService;
import org.whut.inspectManagement.business.device.service.InspectTagService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-17
 * Time: 下午10:10
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/inspectTag")
public class InspectTagServiceWeb {
    @Autowired
    DeviceService deviceService;
    @Autowired
    InspectTagService  inspectTagService;
    @Autowired
    InspectAreaService inspectAreaService;
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,@FormParam("description") String description,@FormParam("createtime") Date createtime,@FormParam("number") String number,@FormParam("inspectAreaId") long inspectAreaId,@FormParam("deviceId") long deviceId ){
        if(name==null||name.trim().equals("")||number.equals("")||inspectAreaId==0||deviceId==0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空！");
        }
        long appId= UserContext.currentUserAppId();
        long id;
        try{
            id=inspectTagService.getIdByNumber(number,appId);
        }
        catch (Exception e){
            id=0;
        }
        if(id==0){
            Date date=new Date();
            InspectTag inspectTag=new InspectTag();
            inspectTag.setName(name);
            inspectTag.setDescription(description);
            inspectTag.setNumber(number);
            inspectTag.setCreatetime(date);
            inspectTag.setInspectAreaId(inspectAreaId);
            inspectTag.setDeviceId(deviceId);
            inspectTag.setAppId(appId);
            inspectTagService.add(inspectTag);
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "已存在该标签！");
        }

    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/list")
    @GET
    public String list(){
        long appId= UserContext.currentUserAppId();

        List<InspectTag> list=inspectTagService.getListByAppId(appId);
        List<SubInspectTag> subInspectTagList=new ArrayList<SubInspectTag>();
        for(InspectTag inspectTag:list){
            SubInspectTag subInspectTag=new SubInspectTag();
            subInspectTag.setName(inspectTag.getName());
            subInspectTag.setNumber(inspectTag.getNumber());
            subInspectTag.setCreatetime(inspectTag.getCreatetime());
            subInspectTag.setDescription(inspectTag.getDescription());
            subInspectTag.setId(inspectTag.getId());
            subInspectTag.setAppId(inspectTag.getAppId());
            subInspectTag.setDeviceName(deviceService.getNameById(inspectTag.getDeviceId()));
            subInspectTag.setInspectAreaName(inspectAreaService.getAreaById(inspectTag.getInspectAreaId()));
             subInspectTagList.add(subInspectTag);
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(subInspectTagList,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        SubInspectTag subInspectTag= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubInspectTag.class);
        InspectTag inspectTag=new InspectTag();
        inspectTag.setId(subInspectTag.getId());
        inspectTag.setName(subInspectTag.getName());
        inspectTag.setDescription(subInspectTag.getDescription());
        inspectTag.setCreatetime(subInspectTag.getCreatetime());
        inspectTag.setAppId(subInspectTag.getAppId());
        inspectTag.setNumber(subInspectTag.getNumber());
        inspectTag.setDeviceId(deviceService.getIdByName(subInspectTag.getDeviceName(),subInspectTag.getAppId()));
        inspectTag.setInspectAreaId(inspectAreaService.getIdByName(subInspectTag.getInspectAreaName(),subInspectTag.getAppId()));
        int result=inspectTagService.update(inspectTag);
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
        InspectTag inspectTag=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,InspectTag.class);
        int result=inspectTagService.delete(inspectTag);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else {
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }

}
