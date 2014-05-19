package org.whut.inspectManagement.business.employeeRole.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.employeeRole.entity.EmployeeRole;
import org.whut.inspectManagement.business.employeeRole.service.EmployeeRoleService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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

    @Produces( MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,@FormParam("status") String status,@FormParam("description") String description,@FormParam("authorityid") long authorityid,@FormParam("appid") long appid)
    {
        EmployeeRole employeeRole=new EmployeeRole();
        employeeRole.setAppId(appid);
        employeeRole.setDescription(description);
        employeeRole.setStatus(status);
        employeeRole.setAuthorityId(authorityid);
        employeeRole.setName(name);
        employeeRoleService.add(employeeRole);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces( MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString)
    {
        EmployeeRole employeeRole = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,EmployeeRole.class);
        if(employeeRole.getName()==null||employeeRole.getAppId()==0||employeeRole.getName().equals("")||employeeRole.getStatus()==null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }
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
        List<EmployeeRole> list=employeeRoleService.list();
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
}
