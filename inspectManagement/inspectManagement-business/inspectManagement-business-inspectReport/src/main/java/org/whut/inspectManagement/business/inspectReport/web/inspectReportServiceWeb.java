package org.whut.inspectManagement.business.inspectReport.web;


import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.inspectReport.mapper.ReportSqlMapper;
import org.whut.platform.fundamental.report.PlatformReport;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-5-26
 * Time: 下午8:11
 * To change this template use File | Settings | File Templates.
 */

@Component
@Path("/inspectReport")
public class inspectReportServiceWeb {
    private ReportSqlMapper reportSqlMapper=new ReportSqlMapper();
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Context
    HttpServletRequest request;
    @Context
    HttpServletResponse response;

    private PlatformReport platformReport=new PlatformReport();

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/reportSearch")
    public String reportSearch(@FormParam("sTime")String sTime,@FormParam("eTime")String eTime,@FormParam("deviceId")String deviceId,@FormParam("userId")String userId,@FormParam("flag") String flag){
        Connection connection=null;
        try {
            connection=sqlSessionFactory.getConfiguration().getEnvironment().getDataSource().getConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
        if(flag.equals("0")){
            deviceCount(sTime,eTime,connection);
        }else if(flag.equals("1")){
            //deviceInfo
            deviceInfo(sTime,eTime,deviceId,connection);
        }else if(flag.equals("2")){
            //peopleCount
            peopleCount(sTime,eTime,deviceId,connection);
        }else if(flag.equals("3")){
            //peopleInfo
            peopleInfo(sTime,eTime,deviceId,userId,connection);
        }else if(flag.equals("4")){
            //deviceHistory
            deviceHistory(sTime,eTime,deviceId,connection);
        }
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
       }

    public String test(String sTime,String eTime,String deviceId,String userId){
        String sql=null;
        try{
            String name="test";
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("sTime",sTime);
            map.put("eTime",eTime);
            map.put("deviceId",deviceId);
            map.put("userId",userId);
            sql=reportSqlMapper.getSql("deviceCountSql",sqlSessionFactory,map);
            System.out.println(sql + "sql语句");
        }catch(Exception e){
            e.printStackTrace();
        }
        return sql;
    }
    public void deviceCount(String sTime,String eTime,Connection connection){
        try{
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("sTime",sTime);
            map.put("eTime",eTime);
            String deviceSql=reportSqlMapper.getSql("deviceCountSql",sqlSessionFactory,map);
            Map<String,String> parameters=new HashMap<String, String>();
            parameters.put("sql",deviceSql);
            String reportTemplate=request.getSession().getServletContext().getRealPath("/inspectReportTemplate/deviceCount1.jasper");
            platformReport.getMapToExportReport(reportTemplate,parameters,"html",request,response,"deviceCount",connection);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deviceInfo(String sTime,String eTime,String deviceId,Connection connection){
        try{
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("sTime",sTime);
            map.put("eTime",eTime);
            map.put("deviceId",deviceId);
            String deviceSql=reportSqlMapper.getSql("deviceInfoSql",sqlSessionFactory,map);
            Map<String,String> parameters=new HashMap<String, String>();
            parameters.put("sql",deviceSql);
            String reportTemplate=request.getSession().getServletContext().getRealPath("/inspectReportTemplate/deviceCount.jasper");
            platformReport.getMapToExportReport(reportTemplate,parameters,"html",request,response,"deviceInfo",connection);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void peopleCount(String sTime,String eTime,String deviceId,Connection connection){
        try{
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("sTime",sTime);
            map.put("eTime",eTime);
            map.put("deviceId",deviceId);
            String deviceSql=reportSqlMapper.getSql("peopleCountSql",sqlSessionFactory,map);
            Map<String,String> parameters=new HashMap<String, String>();
            parameters.put("sql",deviceSql);
            String reportTemplate=request.getSession().getServletContext().getRealPath("/inspectReportTemplate/peopleCount.jasper");
            platformReport.getMapToExportReport(reportTemplate,parameters,"html",request,response,"peopleCount",connection);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void peopleInfo(String sTime,String eTime,String deviceId,String userId,Connection connection){
        try{
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("sTime",sTime);
            map.put("eTime",eTime);
            map.put("deviceId",deviceId);
            String peopleInfoSql=reportSqlMapper.getSql("peopleInfoSql",sqlSessionFactory,map);
            Map<String,String> parameters=new HashMap<String, String>();
            parameters.put("sql",peopleInfoSql);
            String reportTemplate=request.getSession().getServletContext().getRealPath("/inspectReportTemplate/reportx2.jasper");
            platformReport.getMapToExportReport(reportTemplate,parameters,"html",request,response,"peopleInfo",connection);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deviceHistory(String sTime,String eTime,String deviceId,Connection connection){
        try{
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("sTime",sTime);
            map.put("eTime",eTime);
            map.put("deviceId",deviceId);
            String deviceHistorySql=reportSqlMapper.getSql("deviceHistory",sqlSessionFactory,map);
            Map<String,String> parameters=new HashMap<String, String>();
            parameters.put("sql",deviceHistorySql);
            String reportTemplate=request.getSession().getServletContext().getRealPath("/inspectReportTemplate/deviceHistory1.jasper");
            platformReport.getMapToExportReport(reportTemplate,parameters,"html",request,response,"deviceHistory",connection);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
