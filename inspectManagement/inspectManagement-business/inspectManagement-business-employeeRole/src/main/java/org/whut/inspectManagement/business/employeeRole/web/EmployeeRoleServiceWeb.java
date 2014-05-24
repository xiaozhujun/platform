package org.whut.inspectManagement.business.employeeRole.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.employeeRole.entity.EmployeeRole;
import org.whut.inspectManagement.business.employeeRole.entity.SubEmployeeRole;
import org.whut.inspectManagement.business.employeeRole.service.EmployeeRoleService;
import org.whut.platform.business.user.service.AuthorityService;
import org.whut.platform.fundamental.logger.PlatformLogger;
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
 * Created with IntelliJ IDEA.
 * User: chenqw
 * Date: 14-5-15
 * Time: 下午3:52
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/employeeRole")
public class EmployeeRoleServiceWeb {

    private static PlatformLogger logger = PlatformLogger.getLogger(EmployeeRoleServiceWeb.class);

    @Autowired
    private EmployeeRoleService employeeRoleService;
    @Autowired
    private AuthorityService authorityService;

    @Produces( MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,@FormParam("status") String status,@FormParam("description") String description,@FormParam("authority") String authority,@FormParam("appid") String appid)
    {
        if(name==null||name.trim().equals("")||authority==null)
        {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }
        long id;
        try
        {
            id=employeeRoleService.getIdByName(name,Long.parseLong(appid));
        }
        catch (Exception e)
        {
            id=0;
        }
        if(id==0)
        {
        EmployeeRole employeeRole=new EmployeeRole();
        long authorityid = authorityService.getIdByName(authority);
        employeeRole.setAppId(Long.parseLong(appid));
        employeeRole.setDescription(description);
        employeeRole.setStatus(status);
        employeeRole.setAuthorityId(authorityid);
        employeeRole.setName(name);
        employeeRoleService.add(employeeRole);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"员工角色名已存在！");
    }

    @Produces( MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString)
    {
        SubEmployeeRole subEmployeeRole = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubEmployeeRole.class);
        if(subEmployeeRole.getName()==null||subEmployeeRole.getAppId()==0||subEmployeeRole.getName().equals("")||subEmployeeRole.getStatus()==null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }
        EmployeeRole employeeRole= employeeRoleService.getById(subEmployeeRole.getId());
        employeeRole.setAuthorityId(authorityService.getIdByName(subEmployeeRole.getAuthority()));
        employeeRole.setName(subEmployeeRole.getName());
        employeeRole.setStatus(subEmployeeRole.getStatus());
        employeeRole.setDescription(subEmployeeRole.getDescription());
       int result= employeeRoleService.update(employeeRole);
        if(result>0)
        {
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else
        {
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString)
    {
        EmployeeRole employeeRole=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,EmployeeRole.class);
        int result=employeeRoleService.delete(employeeRole);
        if(result>0)
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        else
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @POST
    public String list()
    {
        List<EmployeeRole> employeeRolelist=employeeRoleService.list();
        List<SubEmployeeRole> subEmployeeRoleList=new ArrayList<SubEmployeeRole>();
        for(EmployeeRole e:employeeRolelist)
        {
           SubEmployeeRole subEmployeeRole=new SubEmployeeRole();
           subEmployeeRole.setName(e.getName());
            subEmployeeRole.setStatus(e.getStatus());
            subEmployeeRole.setDescription(e.getDescription());
            subEmployeeRole.setId(e.getId());
            subEmployeeRole.setAppId(e.getAppId());
            String authority=authorityService.getNameById(e.getAuthorityId());
            subEmployeeRole.setAuthority(authority);
            subEmployeeRoleList.add(subEmployeeRole);
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(subEmployeeRoleList,JsonResultUtils.Code.SUCCESS);
    }
}
