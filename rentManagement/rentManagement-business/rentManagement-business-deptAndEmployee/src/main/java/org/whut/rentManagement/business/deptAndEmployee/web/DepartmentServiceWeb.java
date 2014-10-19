package org.whut.rentManagement.business.deptAndEmployee.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.deptAndEmployee.entity.Department;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liubei1203
 * Date: 14-10-12
 * Time: 下午9:18
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/department")
public class DepartmentServiceWeb
{
    private static PlatformLogger logger = PlatformLogger.getLogger(DepartmentServiceWeb.class);
     @Autowired
     private org.whut.rentManagement.business.deptAndEmployee.service.DepartmentService departmentService;

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name")String name,@FormParam("description")String description){
     if ((name==null||name.trim().equals(""))){
         return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
     }
     long appId= UserContext.currentUserAppId();
     long id;
     try{
         id =departmentService.getIdByName(name,appId);
     }catch (Exception e){
         id=0;
     }
      if (id==0){
          Department department=new Department();
          department.setName(name);
          department.setDescription(description);
          department.setAppId(appId);
          Date now=new Date();
          department.setCreateTime(now);
          departmentService.add(department);
          return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
      }
      else
          return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "已存在该部门");
      }
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString)
    {
        Department department = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Department.class);
        if(department.getName()==null||department.getName().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
        }
        long appid = UserContext.currentUserAppId();
        long id;
        try{
            id=departmentService.getIdByName(department.getName(),appid);
        }catch (Exception e) {
            id = 0;
        }
        if(id==0||id==department.getId()) {
            departmentService.update(department);
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "修改的部门已存在！");
     }
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString)
    {
        Department department= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Department.class);
        departmentService.delete(department);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        long appId= UserContext.currentUserAppId();

        List<Department> list=departmentService.list(appId,null,null);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/canUseList")
    @POST
    public String canUseList(){
        long appId= UserContext.currentUserAppId();

        List<Department> list=departmentService.list(appId,null,null);
        List<Department> canUseDepartmentList= new ArrayList<Department>();
        for(Department department:list){
        canUseDepartmentList.add(department);
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(canUseDepartmentList, JsonResultUtils.Code.SUCCESS);
    }



}
