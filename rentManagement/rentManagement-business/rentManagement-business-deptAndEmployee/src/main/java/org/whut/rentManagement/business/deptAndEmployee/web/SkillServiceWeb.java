package org.whut.rentManagement.business.deptAndEmployee.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.deptAndEmployee.entity.Skill;
import org.whut.rentManagement.business.deptAndEmployee.service.SkillService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-16
 * Time: 上午12:12
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/skill")
public class SkillServiceWeb {
    @Autowired
    SkillService skillService;
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name, @FormParam("description") String description) {
        if ( name.trim().equals("") || description.trim().equals("")) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }
        Skill skill = new Skill();
        long appId= UserContext.currentUserAppId();
        skill.setName(name);
        skill.setDescription(description);
        Date createdate=new Date();
        skill.setCreateTime(createdate);
        skill.setAppId(appId);
        skillService.add(skill);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString)
    {
        Skill skill = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Skill.class);
        if(skill.getName()==null||skill.getName().equals("")||skill.getDescription()==null||skill.getDescription().equals("")||skill.getCreateTime().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
        }
        skillService.update(skill);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString)
    {
        Skill skill= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Skill.class);
        skillService.delete(skill);

        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/getById")
    @POST
    public String getById(@FormParam("skillId")long id){
        Skill employee=skillService.getById(id);
        return JsonResultUtils.getObjectResultByStringAsDefault(employee, JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        long appId= UserContext.currentUserAppId();
        List<Skill> list = skillService.list(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/getSkillNameById")
    @POST
    public String getDepartmentById(@FormParam("skillId")long skillId){
        String department=skillService.getSkilltNameById(skillId);
        return  JsonResultUtils.getObjectResultByStringAsDefault(department,JsonResultUtils.Code.SUCCESS);
    }

}
