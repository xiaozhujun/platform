package org.whut.inspectManagement.business.deptAndEmployee.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.deptAndEmployee.entity.EmployeeRole;
import org.whut.inspectManagement.business.deptAndEmployee.entity.EmployeeRoleInspectTable;
import org.whut.inspectManagement.business.deptAndEmployee.entity.SubEmployeeRole;
import org.whut.inspectManagement.business.deptAndEmployee.service.EmployeeRoleInspectTableService;
import org.whut.inspectManagement.business.deptAndEmployee.service.EmployeeRoleService;
import org.whut.inspectManagement.business.inspectTable.service.InspectTableService;
import org.whut.platform.business.user.entity.User;
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
    @Autowired
    private EmployeeRoleInspectTableService employeeRoleInspectTableService;
    @Autowired
    private InspectTableService inspectTableService;



    @Produces( MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,@FormParam("status") String status,@FormParam("description") String description,@FormParam("authority") String authority,@FormParam("appid") long appid,@FormParam("inspectTable") String inspectTable)
    {
        if(name==null||name.trim().equals("")||authority==null||inspectTable==null)
        {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }
        long id;
        try
        {
            id=employeeRoleService.getIdByName(name,appid);
        }
        catch (Exception e)
        {
            id=0;
        }
        if(id==0)
        {
            EmployeeRole employeeRole=new EmployeeRole();
            long authorityid = authorityService.getIdByName(authority);
            employeeRole.setAppId(appid);
            employeeRole.setDescription(description);
            employeeRole.setStatus(status);
            employeeRole.setAuthorityId(authorityid);
            employeeRole.setName(name);
            employeeRoleService.add(employeeRole);
            long employeeRoleId=employeeRoleService.getIdByName(name,appid);
            String [] inspectTableArray=inspectTable.split(";");
            for(int i=0;i<inspectTableArray.length;i++)
            {
                EmployeeRoleInspectTable employeeRoleInspectTable=new EmployeeRoleInspectTable();
                employeeRoleInspectTable.setAppId(appid);
                employeeRoleInspectTable.setEmployeeRoleName(name);
                employeeRoleInspectTable.setEmployeeRoleId(employeeRoleId);
                employeeRoleInspectTable.setInspectTableId(inspectTableService.getIdByName(inspectTableArray[i],appid));
                employeeRoleInspectTable.setInspectTableName(inspectTableArray[i]);
                employeeRoleInspectTableService.add(employeeRoleInspectTable);
            }
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
        long id;
        try {
            id =employeeRoleService.getIdByName(subEmployeeRole.getName(),subEmployeeRole.getAppId());
        } catch (Exception ex) {
            id = 0;
        }
        if (id == 0||id==subEmployeeRole.getId())
        {
        employeeRoleInspectTableService.deleteByEmployeeRoleId(subEmployeeRole.getId());
        String[] inspectTableNameArray=subEmployeeRole.getInspectTable().split(";");
           for(int i=0;i<inspectTableNameArray.length;i++){
            long inspectTableId = inspectTableService.getIdByName(inspectTableNameArray[i],subEmployeeRole.getAppId());
            EmployeeRoleInspectTable employeeRoleInspectTable=new EmployeeRoleInspectTable();
            employeeRoleInspectTable.setEmployeeRoleName(subEmployeeRole.getName());
            employeeRoleInspectTable.setEmployeeRoleId(subEmployeeRole.getId());
            employeeRoleInspectTable.setAppId(subEmployeeRole.getAppId());
            employeeRoleInspectTable.setInspectTableId(inspectTableId);
            employeeRoleInspectTable.setInspectTableName(inspectTableNameArray[i]);
            employeeRoleInspectTableService.add(employeeRoleInspectTable);
            }

        EmployeeRole employeeRole= employeeRoleService.getById(subEmployeeRole.getId());
        employeeRole.setAuthorityId(authorityService.getIdByName(subEmployeeRole.getAuthority()));
        employeeRole.setName(subEmployeeRole.getName());
        employeeRole.setStatus(subEmployeeRole.getStatus());
        employeeRole.setDescription(subEmployeeRole.getDescription());
        employeeRoleService.update(employeeRole);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else
        {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"修改的用户名已存在！");
        }
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString)
    {
        SubEmployeeRole subEmployeeRole=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubEmployeeRole.class);
        EmployeeRole employeeRole=new EmployeeRole();
        User user=new User();
        employeeRole.setId(subEmployeeRole.getId());
        employeeRole.setName(subEmployeeRole.getName());


        int result2= employeeRoleInspectTableService.deleteByEmployeeRoleId(subEmployeeRole.getId());
        int result1=employeeRoleService.delete(employeeRole);
        if(result1>0&&result2>0)
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        else
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @POST
    public String list()
    {
        List<EmployeeRole> employeeRoleList=employeeRoleService.list();
        List<SubEmployeeRole> subEmployeeRoleList=new ArrayList<SubEmployeeRole>();

        for(EmployeeRole e:employeeRoleList)
        {
            List<EmployeeRoleInspectTable> employeeRoleInspectTablesList=employeeRoleInspectTableService.getByEmployeeRoleId(e.getId());
          String inspectTableName="";
            for(int i=0;i<employeeRoleInspectTablesList.toArray().length;i++)
            {
                if(i==0)
                 {
                     inspectTableName=employeeRoleInspectTablesList.get(i).getInspectTableName();
                 }
                else
                 {
                     inspectTableName+=";"+employeeRoleInspectTablesList.get(i).getInspectTableName();
                 }
            }
           SubEmployeeRole subEmployeeRole=new SubEmployeeRole();
           subEmployeeRole.setName(e.getName());
            subEmployeeRole.setStatus(e.getStatus());
            subEmployeeRole.setDescription(e.getDescription());
            subEmployeeRole.setId(e.getId());
            subEmployeeRole.setAppId(e.getAppId());
            subEmployeeRole.setInspectTable(inspectTableName);
            String authority=authorityService.getNameById(e.getAuthorityId());
            subEmployeeRole.setAuthority(authority);
            subEmployeeRoleList.add(subEmployeeRole);
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(subEmployeeRoleList,JsonResultUtils.Code.SUCCESS);
    }
}
