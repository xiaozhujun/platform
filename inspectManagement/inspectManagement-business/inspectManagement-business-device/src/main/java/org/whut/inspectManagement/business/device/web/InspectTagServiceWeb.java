package org.whut.inspectManagement.business.device.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.device.entity.InspectTag;
import org.whut.inspectManagement.business.device.service.InspectAreaService;
import org.whut.inspectManagement.business.device.service.InspectTagService;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    InspectTagService  inspectTagService;
    @Autowired
    InspectAreaService inspectAreaService;
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,@FormParam("description") String description,@FormParam("createtime") Date createtime,@FormParam("number") long number,@FormParam("inspectAreaId") long inspectAreaId,@FormParam("deviceId") long deviceId,@FormParam("appId") long appId ){
        if(name==null||name.trim().equals("")||description==null||description.trim().equals("")||number==0||inspectAreaId==0||deviceId==0||appId==0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空！");
        }
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

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/list")
    @GET
    public String list(){
        List<InspectTag> list=inspectTagService.list();
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        InspectTag inspectTag= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,InspectTag.class);
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


    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/getId")
    @POST
    public  long getId(@FormParam("number") long number,@FormParam("name") String name){
        long id= inspectAreaService.findIdByName(name);
        return inspectTagService.FindByDNumberAndAreaId(number,id);
    }

}
