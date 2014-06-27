package org.whut.inspectManagement.business.configuration.web;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.configuration.entity.ConfigureParameter;
import org.whut.inspectManagement.business.configuration.service.PersonnelConfigurationService;
import org.whut.inspectManagement.business.deptAndEmployee.entity.EmployeeEmployeeRole;
import org.whut.inspectManagement.business.deptAndEmployee.service.EmployeeEmployeeRoleService;
import org.whut.platform.fundamental.logger.PlatformLogger;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-5-18
 * Time: 下午12:08
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/personnelConfiguration")
public class PersonnelConfigurationServiceWeb {
    private static PlatformLogger logger = PlatformLogger.getLogger(PersonnelConfigurationServiceWeb.class);

    @Autowired
    PersonnelConfigurationService personnelConfigurationService;

    @Autowired
    EmployeeEmployeeRoleService employeeEmployeeRoleService;
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/importPersonnelConfiguration/{data}")
    @GET
    public void importPersonnelConfiguration(@PathParam("data") String data,@Context HttpServletResponse response){
        String[] ids = data.split(",");
        List<EmployeeEmployeeRole> personnelList = new ArrayList<EmployeeEmployeeRole>();
        for(String s:ids){
           long id = Long.parseLong(s);
           EmployeeEmployeeRole eer =  employeeEmployeeRoleService.getById(id);
           personnelList.add(eer);
        }
        String result=personnelConfigurationService.configurationConstruction(personnelList);
        System.out.println(result);
        if(result!=""){
           try{
                  String fileName = ConfigureParameter.EmployerXml;
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
