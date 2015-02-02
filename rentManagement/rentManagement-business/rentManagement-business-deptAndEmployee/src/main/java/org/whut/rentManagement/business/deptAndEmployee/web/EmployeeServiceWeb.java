package org.whut.rentManagement.business.deptAndEmployee.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.deptAndEmployee.entity.Employee;
import org.whut.rentManagement.business.deptAndEmployee.entity.SubEmployee;
import org.whut.rentManagement.business.deptAndEmployee.service.DepartmentService;
import org.whut.rentManagement.business.deptAndEmployee.service.EmployeeService;
import org.whut.rentManagement.business.deptAndEmployee.service.SkillService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-16
 * Time: 上午12:11
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/employee")
public class EmployeeServiceWeb {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    SkillService skillService;
    @Autowired
    DepartmentService departmentService;


    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("departmentId") String departmentId, @FormParam("name") String name, @FormParam("sex") String sex, @FormParam("skillId") String skillId, @FormParam("telephone") String telephone, @FormParam("email") String email, @FormParam("employedTime") String employedTime, @FormParam("position") String position) {
        if (departmentId==null|| departmentId.trim().equals("") ||name==null|| name.trim().equals("") ||sex==null|| sex.trim().equals("")||skillId==null|| skillId.trim().equals("")||telephone==null||telephone.trim().equals("")||email==null||email.trim().equals("")||employedTime==null||position==null||position.trim().equals("")) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }
        if(!"".equals(name.trim())){
            if(!name.matches("^[\\u4E00-\\u9FA5A-Za-z]+$")){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请输入为中文或英文的姓名!");
            }
        }
        if(!"".equals(email.trim())){
            if(!email.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "邮箱格式不正确!");
            }
        }
        if(!"".equals(telephone.trim())){
            if(!telephone.matches("^[0-9]*$")){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "联系电话必须是数字!");
            }
        }
        if(!"".equals(telephone.trim())){
            if(telephone.length()<7||telephone.length()>11){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "输入的联系电话长度为7—11之间!");
            }
        }
        if(!"".equals(position.trim())){
            if(!position.matches("^[\\u4E00-\\u9FA5A-Za-z]+$")){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请输入为中文或英文的职位!");
            }
        }
        long id;
        try
        {
            id=employeeService.getIdByName(name);
            System.out.println("employeeId的值为："+id);
        }
        catch (Exception e)
        {
            id=0;
        }
        if(id==0)
        {
            Employee employee = new Employee();
            long appId= UserContext.currentUserAppId();
            Date employeedate = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try{
                employeedate = sdf.parse(employedTime);
            }catch (Exception e){
                JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"日期格式错误");
            }
            Date createdate=new Date();

            employee.setDepartmentId(Long.parseLong(departmentId.replace(" ","")));
            employee.setName(name);
            employee.setSex(sex);
            employee.setSkillId(Long.parseLong(skillId.replace(" ","")));
            employee.setTelephone(telephone);
            employee.setEmail(email);
            employee.setEmployedTime(employeedate);
            employee.setPosition(position);
            employee.setCreateTime(createdate);
            employee.setAppId(appId);
            employeeService.add(employee);
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }else
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"用户已经存在！");

    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString)
    {
        SubEmployee subEmployee = JsonMapper.buildNonDefaultMapper().fromJson(jsonString, SubEmployee.class);
        if(subEmployee.getName()==null||subEmployee.getName().equals("")||subEmployee.getEmail()==null||subEmployee.getEmail().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
        }
        long appId = UserContext.currentUserAppId();
        Employee employee=new Employee();
        Date employeeDate = null;
        Date createDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            employeeDate = sdf.parse(subEmployee.getEmployedTime());
            createDate=sdf.parse(subEmployee.getCreateTime());
        }catch (Exception e){
            JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"日期格式错误");
        }
        employee.setId(subEmployee.getId());
        employee.setName(subEmployee.getName());
        employee.setDepartmentId(Long.parseLong(subEmployee.getDepartmentId()));
        employee.setSex(subEmployee.getSex());
        employee.setSkillId(Long.parseLong(subEmployee.getSkillId()));
        employee.setTelephone(subEmployee.getTelephone());
        employee.setEmail(subEmployee.getEmail());
        employee.setEmployedTime(employeeDate);
        employee.setPosition(subEmployee.getPosition());
        employee.setCreateTime(createDate);
        employee.setAppId(appId);
        System.out.println("测试employee是否获取数据："+employee.getName()+"和"+employee.getId());
        employeeService.update(employee);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString)
    {
        Employee employee= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Employee.class);
        employeeService.delete(employee);

        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }


    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/getById")
    @POST
    public String getById(@FormParam("employeeId")long id){
        Employee employee=employeeService.getById(id);
        return JsonResultUtils.getObjectResultByStringAsDefault(employee, JsonResultUtils.Code.SUCCESS);
    }

    /*添加saas化*/
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        long appId= UserContext.currentUserAppId();
        List<Map<String,String>> list=employeeService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/getDepartmentById")
    @POST
    public String getDepartmentById(@FormParam("departmentId")long departmentId){
        long appId= UserContext.currentUserAppId();
        String department=departmentService.getNameById(departmentId);
        return  JsonResultUtils.getObjectResultByStringAsDefault(department,JsonResultUtils.Code.SUCCESS);
    }
}
