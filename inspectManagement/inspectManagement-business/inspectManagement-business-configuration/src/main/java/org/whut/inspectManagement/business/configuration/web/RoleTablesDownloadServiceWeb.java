package org.whut.inspectManagement.business.configuration.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.configuration.entity.ConfigureParameter;
import org.whut.inspectManagement.business.configuration.service.RoleTablesDownloadService;
import org.whut.inspectManagement.business.deptAndEmployee.entity.EmployeeRole;
import org.whut.inspectManagement.business.deptAndEmployee.entity.EmployeeRoleInspectTable;
import org.whut.inspectManagement.business.deptAndEmployee.entity.SubEmployeeRole;
import org.whut.inspectManagement.business.deptAndEmployee.service.EmployeeRoleInspectTableService;
import org.whut.inspectManagement.business.deptAndEmployee.service.EmployeeRoleService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.logger.PlatformLogger;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-5-30
 * Time: 下午1:53
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/roleTablesConfiguration")
public class RoleTablesDownloadServiceWeb {
     private static PlatformLogger logger = PlatformLogger.getLogger(RoleTablesDownloadServiceWeb.class);

     @Autowired
     RoleTablesDownloadService roleTablesDownloadService;

    @Autowired
    EmployeeRoleInspectTableService employeeRoleInspectTableService;

    @Autowired
    EmployeeRoleService employeeRoleService;
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/downloadRoleTablesConfiguration/{data}")
    @GET
    public void setRoleTablesDownload(@PathParam("data") String data,@Context HttpServletResponse response){
        List<SubEmployeeRole> list=new ArrayList<SubEmployeeRole>();
        String[] ids = data.split(",");
        for(String s:ids){
            long id = Long.parseLong(s);
            EmployeeRole er = employeeRoleService.getById(id);
            SubEmployeeRole ser = new SubEmployeeRole();
            ser.setId(id);
            ser.setName(er.getName());
            List<EmployeeRoleInspectTable> eriList = employeeRoleInspectTableService.getByEmployeeRoleId(id);
            String inspectTableName="";
            int length = eriList.toArray().length;
            if(length>0){
                for(int i=0;i<length;i++)
                {
                   if(i==0)
                   {
                      inspectTableName=eriList.get(i).getInspectTableName();
                   }
                   else
                   {
                    inspectTableName+=";"+eriList.get(i).getInspectTableName();
                   }
                }
            }
            ser.setInspectTable(inspectTableName);
            list.add(ser);
        }
        String result =  roleTablesDownloadService.roleTablesDocConstruction(list);
        System.out.println(result);
        if(result!=""){
           try{
               String fileName = ConfigureParameter.RolesTableXml;
               response.setContentType("text/plain;charset=utf-8");
               response.setHeader("Location",fileName);
               response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("utf-8"),"ISO8859-1"));
               OutputStream outputStream = response.getOutputStream();
               outputStream.write(result.getBytes("utf-8"));
               outputStream.flush();
               outputStream.close();
           }
           catch(Exception e){
               e.printStackTrace();
               logger.error(e.getMessage());
           }
        }
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/downloadRoleTable")
    @GET
    public void setRoleTablesDownload(@Context HttpServletResponse response){

        long appId= UserContext.currentUserAppId();
        List<EmployeeRole> employeeRoleList=employeeRoleService.getListByAppId(appId);

        List<SubEmployeeRole> list=new ArrayList<SubEmployeeRole>();
        for(EmployeeRole s:employeeRoleList){
            long id = s.getId();
            EmployeeRole er = employeeRoleService.getById(id);
            SubEmployeeRole ser = new SubEmployeeRole();
            ser.setId(id);
            ser.setName(er.getName());
            List<EmployeeRoleInspectTable> eriList = employeeRoleInspectTableService.getByEmployeeRoleId(id);
            String inspectTableName="";
            int length = eriList.toArray().length;
            if(length>0){
                for(int i=0;i<length;i++)
                {
                    if(i==0)
                    {
                        inspectTableName=eriList.get(i).getInspectTableName();
                    }
                    else
                    {
                        inspectTableName+=";"+eriList.get(i).getInspectTableName();
                    }
                }
            }
            ser.setInspectTable(inspectTableName);
            list.add(ser);
        }
        String result =  roleTablesDownloadService.roleTablesDocConstruction(list);
        System.out.println(result);
        if(result!=""){
            try{
                String fileName = ConfigureParameter.RolesTableXml;
                response.setContentType("text/plain;charset=utf-8");
                response.setHeader("Location",fileName);
                response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("utf-8"),"ISO8859-1"));
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(result.getBytes("utf-8"));
                outputStream.flush();
                outputStream.close();
            }
            catch(Exception e){
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
    }
}
