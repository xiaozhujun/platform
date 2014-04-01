package org.whut.platform.business.report.web;
import groovy.time.BaseDuration;
import org.springframework.stereotype.Component;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.report.PlatformReport;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
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
     /*
     这个例子主要是展示传入一个sql语句，显示html格式的报表
      */
     String sql=showUserSql();
     /*
     模板在项目riskmanagement下的repoprtTemplate文件夹下
     */
     String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/reportDemo.jasper");
     Map parameter=new HashMap();
     parameter.put("sql",sql);
     platformReport.getMapToExportReport(reportTemplate,parameter,"html",request,response);
        return JsonResultUtils
                .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    public String showUserSql(){
        /*
        涉及user表，user_authority表，authority表，authority_power表，power表的多表联查
         */
        String sql="select u.name as uname,u.sex,au.name as rname,p.description " +
                "from user u,user_authority ua,authority au,authority_power aup,power p " +
                "where u.id=ua.userId and au.id=ua.authorityId and au.id=aup.authorityId and aup.powerId=p.id";
        return sql;
    }
    public String showUserSqlWithName(String name){
        String sql="select u.name as uname,u.sex,au.name as rname,p.description"+
        " from user u,user_authority ua,authority au,authority_power aup,power p "+
        "where u.id=ua.userId and au.id=ua.authorityId and au.id=aup.authorityId and aup.powerId=p.id and u.name='"+name+"'";
        return sql;
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/showUserWithName/{name}")
    @GET
    public String showUserWithName(@PathParam("name") String name){
        System.out.print(name+".......bug.......");
        if(name==null||name.trim().equals("")){
        }
        String sql=showUserSqlWithName(name);
        Map parameter=new HashMap();
        parameter.put("sql",sql);
        String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/reportDemo.jasper");
        platformReport.getMapToExportReport(reportTemplate,parameter,"excel",request,response);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

}
