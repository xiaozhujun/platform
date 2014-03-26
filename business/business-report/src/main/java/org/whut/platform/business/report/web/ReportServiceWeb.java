package org.whut.platform.business.report.web;
import org.springframework.stereotype.Component;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.report.PlatformReport;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhuzhenhua
 * Date: 14-3-26
 * Time: 下午4:13
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/report")
public class ReportServiceWeb {

    private static PlatformLogger logger=PlatformLogger.getLogger(ReportServiceWeb.class);
    private PlatformReport platformReport=new PlatformReport();
    @Context HttpServletRequest request;
    @Context HttpServletResponse response;
    @Path("/showUserReport")
    @POST
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    public String showUserAuthorityPowerReport(){
     String sql="select u.name as uname,u.sex,au.name as rname,p.description " +
                 "from user u,user_authority ua,authority au,authority_power aup,power p" +
                 "where u.id=ua.userId and au.id=ua.authorityId and au.id=aup.authorityId and aup.powerId=p.id";
     String reportTemplate=request.getContextPath()+"/reportTemplate/reportDemo.jasper";
     System.out.print(reportTemplate+"路径....");
     Map parameter=new HashMap();
     parameter.put("sql",sql);
     platformReport.getMapToExportReport(reportTemplate,parameter,"pdf",request,response);
        return JsonResultUtils
                .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }


}
