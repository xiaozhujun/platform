package org.whut.inspectManagement.business.Department.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.Department.entity.Department;
import org.whut.inspectManagement.business.Department.service.DepartmentService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenqw
 * Date: 14-5-14
 * Time: 上午11:01
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/department")
public class DepartmentServiceWeb {
    private static PlatformLogger logger = PlatformLogger.getLogger(DepartmentServiceWeb.class);

    @Autowired
    private DepartmentService departmentService;

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,@FormParam("description") String description,@FormParam("status") String status,@FormParam("appid") long appid)
    {
        Department department=new Department();
        department.setName(name);
        department.setStatus(status);
        department.setDescription(description);
        department.setAppId(appid);
        Date now= new Date();
        department.setCreatetime(now);
        departmentService.add(department);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString)
    {
    Department department = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Department.class);
    if(department.getName()==null||department.getAppId()==0||department.getName().equals("")||department.getStatus()==null){
        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
    }
    departmentService.update(department);
    return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString)
    {
        Department department=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Department.class);
        departmentService.delete(department);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    /*添加saas化*/
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        List<Department> list=departmentService.list();
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
}
