package org.whut.deviceManagement.business.project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.deviceManagement.business.project.entity.ProjectSegment;
import org.whut.deviceManagement.business.project.service.ProjectSegmentService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-4-27
 * Time: 下午3:04
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/projectSegment")
public class ProjectSegmentServiceWeb {

    private static final PlatformLogger logger = PlatformLogger.getLogger(ProjectSegmentServiceWeb.class);

    @Autowired
    private ProjectSegmentService projectSegmentService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("jsonString") String jsonString)throws ParseException {
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
        }
        ProjectSegment projectSegment = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,ProjectSegment.class);
        if(projectSegment==null||projectSegment.getProjectUnitId()==null||projectSegment.getTotalCount()==null
                ||projectSegment.getSequence()==null||projectSegment.getDeviceId()==null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空!");
        }

        projectSegment.setAppId(UserContext.currentUserAppId());
        projectSegment.setCreateTime(new Date());
        projectSegmentService.add(projectSegment);
        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "添加成功!");
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString) throws ParseException{
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空!");
        }
        ProjectSegment projectSegment = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,ProjectSegment.class);
        if(projectSegment==null||projectSegment.getId()==null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空!");
        }
        long appId= UserContext.currentUserAppId();
        projectSegment.setCreateTime(null);
        projectSegment.setAppId(appId);
        projectSegmentService.update(projectSegment);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }


    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString)
    {
        ProjectSegment projectSegment= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,ProjectSegment.class);
        int result=projectSegmentService.delete(projectSegment);
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
        List<Map<String,String>> list=projectSegmentService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/getListByProjectId")
    @POST
    public String getListByProjectId(@FormParam("jsonString") String jsonString){
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空!");
        }
        HashMap params = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,HashMap.class);
        if(params.get("projectId")==null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空!");
        }
        long appId= UserContext.currentUserAppId();
        Long projectId = Long.parseLong((String)params.get("projectId"));
        List<Map<String,String>> list=projectSegmentService.getListByProjectId(projectId,appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
}