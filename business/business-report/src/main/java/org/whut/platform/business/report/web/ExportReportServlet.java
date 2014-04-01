package org.whut.platform.business.report.web;

import org.whut.platform.fundamental.report.PlatformReport;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-3-31
 * Time: 下午11:40
 * To change this template use File | Settings | File Templates.
 */
public class ExportReportServlet extends HttpServlet {
    private PlatformReport platformReport=new PlatformReport();
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response){
        String name=request.getParameter("name");
        String sql=showUserSqlWithName(name);
        Map parameter=new HashMap();
        parameter.put("sql",sql);
        String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/reportDemo.jasper");
        platformReport.getMapToExportReport(reportTemplate,parameter,"pdf",request,response);
    }
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response){
            doPost(request,response);
    }
    public String showUserSqlWithName(String name){
        String sql="select u.name as uname,u.sex,au.name as rname,p.description"+
                " from user u,user_authority ua,authority au,authority_power aup,power p "+
                "where u.id=ua.userId and au.id=ua.authorityId and au.id=aup.authorityId and aup.powerId=p.id and u.name='"+name+"'";
        return sql;
    }
}
