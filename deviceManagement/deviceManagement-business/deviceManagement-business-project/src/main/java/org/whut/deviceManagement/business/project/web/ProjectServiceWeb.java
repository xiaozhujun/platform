package org.whut.deviceManagement.business.project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.deviceManagement.business.project.entity.Project;
import org.whut.deviceManagement.business.project.service.ProjectService;
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
 * Date: 15-4-26
 * Time: 下午5:07
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/project")
public class ProjectServiceWeb{

private static final PlatformLogger logger = PlatformLogger.getLogger(ProjectServiceWeb.class);

@Autowired
private ProjectService projectService;

        @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
        @Path("/add")
        @POST
        public String add(@FormParam("jsonString") String jsonString)throws ParseException {
            if(jsonString==null||jsonString.trim().equals("")){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
            }
            Project project = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Project.class);
            if(project==null||project.getName()==null||project.getName().trim().equals("")){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空!");
            }

            project.setAppId(UserContext.currentUserAppId());
            project.setCreateTime(new Date());
            projectService.add(project);
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "添加成功!");
        }

        @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
        @Path("/update")
        @POST
        public String update(@FormParam("jsonString") String jsonString) throws ParseException{
            if(jsonString==null||jsonString.trim().equals("")){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空!");
            }
            Project project = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Project.class);
            if(project==null||project.getId()==null){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空!");
            }
            long appId= UserContext.currentUserAppId();
            project.setCreateTime(null);
            project.setAppId(appId);
            projectService.update(project);
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }


        @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
        @Path("/delete")
        @POST
        public String delete(@FormParam("jsonString") String jsonString)
        {
            Project project= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Project.class);
            int result=projectService.delete(project);
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
            List<Map<String,String>> list=projectService.getListByAppId(appId);
            return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
        }
}