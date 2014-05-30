package org.whut.inspectManagement.business.configuration.web;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.configuration.service.RoleTablesDownloadService;
import org.whut.platform.fundamental.logger.PlatformLogger;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.OutputStream;

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

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/downloadRoleTablesConfiguration/{data}")
    @GET
    public void setRoleTablesDownload(@PathParam("data") String data,@Context HttpServletResponse response){
        try {
            JSONArray jsonArray = new JSONArray(data);
            String result =  roleTablesDownloadService.roleTablesDocConstruction(jsonArray);
            System.out.println(result);
            if(result!=""){
                try{
                    String fileName = "RoleTables.xml";
                    response.setContentType("text/plain");
                    response.setHeader("Location",new String(fileName.getBytes("GBK"),"UTF-8"));
                    response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("gb2312"),"ISO8859-1"));
                    OutputStream outputStream = response.getOutputStream();
                    outputStream.write(result.getBytes());
                    outputStream.flush();
                    outputStream.close();
                }
                catch(Exception e){
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            logger.error(e.getMessage());
        }
    }
}
