package org.whut.inspectManagement.business.configuration.web;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.configuration.service.PersonnelConfigurationService;
import org.whut.inspectManagement.business.deptAndEmployee.entity.EmployeeEmployeeRole;
import org.whut.platform.fundamental.logger.PlatformLogger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    private static PlatformLogger logger = PlatformLogger.getLogger(InspectTableDownloadServiceWeb.class);

    @Autowired
    PersonnelConfigurationService personnelConfigurationService;
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/importPersonnelConfiguration/{data}")
    @GET
    public void importPersonnelConfiguration(@PathParam("data") String data){
        List<EmployeeEmployeeRole> personnelList = new ArrayList<EmployeeEmployeeRole>();
        try {
            JSONArray jsonArray = new JSONArray(data);
            for(int i = 0;i<jsonArray.length();i++){
                EmployeeEmployeeRole err = new EmployeeEmployeeRole();
                JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                err.setId(jsonObject.getLong("id"));
                err.setAppId(jsonObject.getLong("appId"));
                err.setEmployeeId(jsonObject.getLong("employeeId"));
                err.setEmployeeName(jsonObject.getString("employeeName"));
                err.setEmployeeRoleId(jsonObject.getLong("employeeRoleId"));
                err.setEmployeeRoleName(jsonObject.getString("employeeRoleName"));
                personnelList.add(err);
            }
            String result = personnelConfigurationService.configurationConstruction(personnelList);
            System.out.println(result);
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            logger.error(e.getMessage());
        }

    }

}
