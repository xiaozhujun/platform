package org.whut.inspectManagement.business.configuration.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.configuration.service.InspectTableDownloadService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

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
 * Date: 14-5-20
 * Time: 下午6:15
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/configuration")
public class InspectTableDownloadServiceWeb {
    private static PlatformLogger logger = PlatformLogger.getLogger(InspectTableDownloadServiceWeb.class);

    @Autowired
    private InspectTableDownloadService inspectTableDownloadService;
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/downloadInspectTable/{id}")
    @GET
    public String  downloadInspectTable(@PathParam("id") String id,@Context HttpServletResponse response){
        long tableId = Long.parseLong(id);
        String tableName = inspectTableDownloadService.getTableNameById(tableId);
        String result = inspectTableDownloadService.docConstruction(tableId);
        if(result!=""){
           try{
               String downloadFileName = tableName+".xml";
               response.setContentType("text/plain;charset=utf-8");
               response.setHeader("Location", downloadFileName);
               response.setHeader("Content-Disposition","attachment; filename="
                       + new String(downloadFileName.getBytes("utf-8"),"ISO8859-1"));
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
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
}
