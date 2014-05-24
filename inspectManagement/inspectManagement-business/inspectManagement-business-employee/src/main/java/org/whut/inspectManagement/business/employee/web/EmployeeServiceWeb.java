package org.whut.inspectManagement.business.employee.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.Department.service.DepartmentService;
import org.whut.inspectManagement.business.app.service.AppService;
import org.whut.inspectManagement.business.employee.entity.Employee;
import org.whut.inspectManagement.business.employee.entity.EmployeeEmployeeRole;
import org.whut.inspectManagement.business.employee.service.EmployeeEmployeeRoleService;
import org.whut.inspectManagement.business.employee.service.EmployeeService;
import org.whut.inspectManagement.business.employeeRole.entity.EmployeeRole;
import org.whut.inspectManagement.business.employeeRole.service.EmployeeRoleService;
import org.whut.platform.business.user.entity.User;
import org.whut.platform.business.user.entity.UserAuthority;
import org.whut.platform.business.user.service.AuthorityService;
import org.whut.platform.business.user.service.UserAuthorityService;
import org.whut.platform.business.user.service.UserService;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/5/19.
 */

@Component
@Path("/employee")
public class EmployeeServiceWeb {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    AuthorityService authorityService;
    @Autowired
    UserAuthorityService userAuthorityService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    UserService userService;
    @Autowired
    AppService appService;
    @Autowired
    EmployeeRoleService employeeRoleService;
    @Autowired
    EmployeeEmployeeRoleService employeeEmployeeRoleService;

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name, @FormParam("password") String password, @FormParam("sex") String sex, @FormParam("departmentName") String departmentName, @FormParam("status") String status, @FormParam("employeeRoleName") String employeeRoleName, @FormParam("appId") long appId) {
        if (name == null || name.trim().equals("") || password == null || password.trim().equals("") || sex == null || sex.trim().equals("")) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }
        long id;
        try {
            id = userService.getIdByName(name);
        } catch (Exception ex) {
            id = 0;
        }
        if (id == 0) {
            ArrayList<Long> authorityIdList=new ArrayList<java.lang.Long>();
            String[] employeeRoleArray=employeeRoleName.split(";");
            for(int i=0;i<employeeRoleArray.length;i++)
            {
                boolean isExist=false;
                EmployeeRole employeeRole= employeeRoleService.getByName(employeeRoleArray[i],appId);
                for(long aid:authorityIdList)
                {
                    if(aid==employeeRole.getAuthorityId())
                        isExist=true;
                }
                if(!isExist)
                    authorityIdList.add(employeeRole.getAuthorityId());
            }
            String role="";
            for(int i=0;i<authorityIdList.toArray().length;i++)
            {
                if(i==0)
                {
                    role=authorityService.getNameById(authorityIdList.get(i));
                }
                else
                {
                    role+=";"+authorityService.getNameById(authorityIdList.get(i));
                }
            }
            User user = new User();
            Employee employee = new Employee();
            user.setName(name);
            user.setPassword(password);
            user.setSex(sex);
            user.setRole(role);
            user.setStatus(status);
            user.setAppId(appId);
            userService.add(user);

            long userId = userService.getIdByName(name);
            String[] roleArray = role.split(";");
            for (int i = 0; i < roleArray.length; i++) {
                long authorityId = authorityService.getIdByName(roleArray[i]);
                UserAuthority userAuthority = new UserAuthority();
                userAuthority.setUserId(userId);
                userAuthority.setAuthorityId(authorityId);
                userAuthority.setUserName(name);
                userAuthority.setAuthorityName(roleArray[i]);
                userAuthorityService.add(userAuthority);
            }

            long departmentId = departmentService.getIdByName(departmentName,appId);
            employee.setName(name);
            employee.setPassword(password);
            employee.setSex(sex);
            employee.setEmployeeRoleName(employeeRoleName);
            employee.setStatus(status);
            employee.setAppId(appId);
            employee.setDepartmentId(departmentId);
            employee.setUserId(userId);
            employeeService.add(employee);
            long employeeId=employeeService.getIdByName(name);
            for(int i=0;i<employeeRoleArray.length;i++)
            {
                long roleId=employeeRoleService.getIdByName(employeeRoleArray[i],appId);
                EmployeeEmployeeRole employeeEmployeeRole=new EmployeeEmployeeRole();
                employeeEmployeeRole.setAppId(appId);
                employeeEmployeeRole.setEmployeeId(employeeId);
                employeeEmployeeRole.setEmployeeName(name);
                employeeEmployeeRole.setEmployeeRoleId(roleId);
                employeeEmployeeRole.setEmployeeRoleName(employeeRoleArray[i]);
                employeeEmployeeRoleService.add(employeeEmployeeRole);
            }
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        } else {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "用户名已存在!");
        }
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString)
    {
        Employee employee = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Employee.class);
        if(employee.getName()==null||employee.getAppId()==0||employee.getName().equals("")||employee.getStatus()==null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }
        UserAuthority userAuthority = new UserAuthority();
        User user = new User();
        long userId =employee.getUserId();
        String userName=  employee.getName();
       // String role =userAuthorityService.getAuthorityNameByUserName(userName);
        user.setId(userId);
        user.setName(userName);
        user.setPassword(employee.getPassword());
        user.setSex(employee.getSex());
        user.setAppId(employee.getAppId());
       // user.setRole(role);
        user.setStatus(employee.getStatus());


       // userAuthority.setId();
          userAuthority.setUserId(userId);
       // userAuthority.setAuthorityId();
      //  userAuthority.setAuthorityName();
        userAuthority.setUserName(userName);

      //  userAuthorityService.update(userAuthority);
        userService.update(user);
        employeeService.update(employee);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString)
    {
        Employee employee=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Employee.class);
        User user = new User();
        long userId =employee.getUserId();
        String userName=employee.getName();
        user.setId(userId);
        user.setName(userName);

        userAuthorityService.deleteByUserName(userName);
        userService.delete(user);
        employeeService.delete(employee);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    /*添加saas化*/
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        List<Employee> list=employeeService.list();
        for(int i=0;i<list.size();i++){

            //appName=appService.getNameById( list.get(i).getAppId());

        }

        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
}




