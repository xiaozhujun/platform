package org.whut.rentManagement.business.deptAndEmployee.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.deptAndEmployee.entity.Skill;
import org.whut.rentManagement.business.deptAndEmployee.entity.SubEmployee;
import org.whut.rentManagement.business.deptAndEmployee.entity.SubSkill;
import org.whut.rentManagement.business.deptAndEmployee.service.SkillService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
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
        if (name==null|| name.trim().equals("") ||description==null|| description.trim().equals("")) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }
        if(!"".equals(name.trim())){
            if(!name.matches("^[\\u4E00-\\u9FA5A-Za-z]+$")){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请输入为中文或英文的技能!");
            }
        }
        long id;
        try
        {
            id=skillService.getIdBySkillName(name);
            System.out.println("skillId的值为："+id);
        }
        catch (Exception e)
        {
            id=0;
        }
        if(id==0)
        {
            Skill skill = new Skill();
            long appId= UserContext.currentUserAppId();
            skill.setName(name);
            skill.setDescription(description);
            Date createdate=new Date();
            skill.setCreateTime(createdate);
            skill.setAppId(appId);
            skillService.add(skill);
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }else
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"改技能已经存在！");
    }
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString)
    {
        SubSkill subSkill = JsonMapper.buildNonDefaultMapper().fromJson(jsonString, SubSkill.class);
        if(subSkill.getName()==null||subSkill.getName().equals("")||subSkill.getDescription()==null||subSkill.getDescription().equals("")||subSkill.getCreateTime().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
        }
        long appId = UserContext.currentUserAppId();
        Skill skill=new Skill();
        Date createDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("测试subSkill是否获取数据："+subSkill.getName()+"和"+subSkill.getId()+"和"+subSkill.getCreateTime());
        try{
            createDate=sdf.parse(subSkill.getCreateTime());
        }catch (Exception e){
            JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"日期格式错误");
        }
        skill.setId(subSkill.getId());
        skill.setName(subSkill.getName());
        skill.setDescription(subSkill.getDescription());
        skill.setCreateTime(createDate);
        skill.setAppId(appId);
        System.out.println("测试skill是否获取数据："+skill.getName()+"和"+skill.getId());
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
    public String getSkillNameById(@FormParam("skillId")long skillId){
        String skill=skillService.getSkillNameById(skillId);
        return  JsonResultUtils.getObjectResultByStringAsDefault(skill,JsonResultUtils.Code.SUCCESS);
    }

}
