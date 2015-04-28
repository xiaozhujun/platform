package org.whut.deviceManagement.business.project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.deviceManagement.business.project.entity.ProjectUnit;
import org.whut.deviceManagement.business.project.service.ProjectUnitService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-4-27
 * Time: 下午3:03
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/projectUnit")
public class ProjectUnitServiceWeb {

    private static final PlatformLogger logger = PlatformLogger.getLogger(ProjectUnitServiceWeb.class);

    @Autowired
    private ProjectUnitService projectUnitService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("jsonString") String jsonString)throws ParseException {
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
        }
        ProjectUnit projectUnit = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,ProjectUnit.class);
        if(projectUnit==null||projectUnit.getName()==null||projectUnit.getName().trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空!");
        }

        projectUnit.setAppId(UserContext.currentUserAppId());
        projectUnit.setCreateTime(new Date());
        projectUnitService.add(projectUnit);
        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "添加成功!");
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString) throws ParseException{
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空!");
        }
        ProjectUnit projectUnit = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,ProjectUnit.class);
        if(projectUnit==null||projectUnit.getId()==null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空!");
        }
        long appId= UserContext.currentUserAppId();
        projectUnit.setCreateTime(null);
        projectUnit.setAppId(appId);
        projectUnitService.update(projectUnit);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }


    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString)
    {
        ProjectUnit projectUnit= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,ProjectUnit.class);
        int result=projectUnitService.delete(projectUnit);
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
        List<Map<String,String>> list=projectUnitService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
}