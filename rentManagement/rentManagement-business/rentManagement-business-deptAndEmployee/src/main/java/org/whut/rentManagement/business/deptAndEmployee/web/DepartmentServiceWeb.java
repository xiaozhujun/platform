package org.whut.rentManagement.business.deptAndEmployee.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.deptAndEmployee.entity.Department;
import org.whut.rentManagement.business.deptAndEmployee.entity.SubDepartment;
import org.whut.rentManagement.business.deptAndEmployee.service.DepartmentService;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private DepartmentService departmentService;

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
        SubDepartment subDepartment = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubDepartment.class);
        long appId = UserContext.currentUserAppId();
        if(subDepartment.getName()==null||subDepartment.getName().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "部门名不能为空");
        }
//        if(subDepartment.getCreateTime()==null){
//            Department departmentNoDate = new Department();
//            departmentNoDate.setId(subDepartment.getId());
//            departmentNoDate.setAppId(appId);
//            departmentNoDate.setDescription(subDepartment.getDescription());
//            departmentNoDate.setName(subDepartment.getName());
//            long idNoDate;
//            try{
//                idNoDate=departmentService.getIdByName(subDepartment.getName(), appId);
//            }catch (Exception e) {
//                idNoDate = 0;
//            }
//            if(idNoDate!=0){
//                if (idNoDate!=subDepartment.getId()){
//                    return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "修改的部门名已存在！");
//                }
//            }
//            departmentService.update(departmentNoDate);
//            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
//        }
        Department department = new Department();
//        Date date = null;
        department.setId(subDepartment.getId());
        department.setName(subDepartment.getName());
        department.setDescription(subDepartment.getDescription());
        department.setAppId(appId);
//        SimpleDateFormat DFT = new SimpleDateFormat("yyyy-MM-dd");
//        try{
//            date=DFT.parse(subDepartment.getCreateTime());
//        }catch (Exception e){
//            JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"日期格式错误");
//        }
//        //调节时区，解决更新时间会显示前一天的问题
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
//        int day = c.get(Calendar.DATE);
//        c.set(Calendar.DATE, day + 1);
//        Date dateAfter = c.getTime();
//        department.setCreateTime(dateAfter);
        long id;
        try{
            id=departmentService.getIdByName(subDepartment.getName(), appId);
        }catch (Exception e) {
            id = 0;
        }
        if(id!=0){
            if (id!=subDepartment.getId()){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "修改的部门名已存在！");
            }
        }
        departmentService.update(department);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
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
}
