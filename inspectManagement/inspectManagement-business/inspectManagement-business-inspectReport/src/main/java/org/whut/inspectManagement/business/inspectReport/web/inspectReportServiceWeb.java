package org.whut.inspectManagement.business.inspectReport.web;


import com.lowagie.text.xml.xmp.XmpArray;
import com.mongodb.DBObject;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.inspectReport.entity.MongoConstant;
import org.whut.inspectManagement.business.inspectReport.entity.ReportInfo;
import org.whut.inspectManagement.business.inspectReport.entity.SubReportInfo;
import org.whut.inspectManagement.business.inspectReport.mapper.ReportSqlMapper;
import org.whut.inspectManagement.business.inspectReport.service.InspectReportService;
import org.whut.platform.fundamental.mongo.connector.MongoConnector;
import org.whut.platform.fundamental.report.PlatformReport;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private InspectReportService inspectReportService;

    private PlatformReport platformReport=new PlatformReport();

    private static List<ReportInfo> reportInfoList=new ArrayList<ReportInfo>();

    private static Map<String,String> reportNameMap=new HashMap<String, String>();

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
            deviceInfo(sTime, eTime, deviceId, connection);
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
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getInspectTableRecordList")
    public String getInspectTableRecordList(){
        Date createTime=new Date();
        List<Map<String,String>> list=inspectReportService.getInspectTableRecordList(transferDateToString(createTime));
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    public String transferDateToString(Date d){
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        String s=sf.format(d);
        return s;
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getInspectInfo")
    public String getInspectInfo(@FormParam("jsonString")String jsonString,@FormParam("type") String type){
        reportInfoList.clear();
        reportNameMap.clear();
        Map<String,String> map= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Map.class);
        //根据传过来的map取得mongoId,然后从mongo中查出相应的值.然后根据各个Id来查出相应的信息来放到list中后导出html格式的报表
        reportInfoList=getReportListSource(map);
        Map<String,String> parameters=new HashMap<String, String>();
        String path=request.getSession().getServletContext().getRealPath("/reportTemplate/") + "/";
        parameters.put(MongoConstant.SUBREPORT_DIR,path);
        String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/report2.jasper");
        reportNameMap.put("tableName",map.get(MongoConstant.tableName));
        platformReport.exportReportByType(reportTemplate,type,parameters,request,response,map.get(MongoConstant.tableName),reportInfoList);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    public List<DBObject> getInfoFromMongoByMongoId(String mongoId){
        MongoConnector mongoConnector=new MongoConnector(MongoConstant.craneInspectReportDB,MongoConstant.inspectItemRecordCollection);
        List<DBObject> d=mongoConnector.getInspectItemRecordByMongoId(mongoId);
        return d;
    }
    public List<Map<String,String>> getInfoByMongoDbObject(List<DBObject> dList){
           List<Map<String,String>> mapList=new ArrayList<Map<String, String>>();
           for(DBObject dd:dList){
               Map<String,String> d=dd.toMap();
               Map<String,String> map=inspectReportService.getInfoByMongoDbObject(d);
               mapList.add(map);
           }
           return mapList;
    }
    public List<ReportInfo> getReportListSource(Map<String,String> map){
        List<DBObject> d=getInfoFromMongoByMongoId(map.get(MongoConstant.mongoId));
        List<Map<String,String>> l=getInfoByMongoDbObject(d);
        List<ReportInfo> reportInfoList=new ArrayList<ReportInfo>();
        for(Map<String,String> m:l){
            if(m!=null){
            ReportInfo reportInfo=new ReportInfo();
            reportInfo.setUserName(m.get(MongoConstant.userName));
            reportInfo.setTagName(m.get(MongoConstant.tagName));
            reportInfo.settName(m.get(MongoConstant.tName));
            reportInfo.setDevName(m.get(MongoConstant.devName));
            reportInfo.setCreateTime(map.get(MongoConstant.createTime));
            List<SubReportInfo> subReportInfos=new ArrayList<SubReportInfo>();
            SubReportInfo subReportInfo=new SubReportInfo();
            subReportInfo.setItemName(m.get(MongoConstant.itemName));
            subReportInfo.setInspectChoiceValue(m.get(MongoConstant.inspectChoiceValue));
            subReportInfos.add(subReportInfo);
            reportInfo.setSubReportInfoList(subReportInfos);
            reportInfoList.add(reportInfo);
            }
        }
        return reportInfoList;
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @GET
    @Path("/exportPeopleInfoReport/{type}")
    public void exportPeopleInfoReport(@PathParam("type")String type){
        Map<String,String> parameters=new HashMap<String, String>();
        String path=request.getSession().getServletContext().getRealPath("/reportTemplate/") + "/";
        parameters.put(MongoConstant.SUBREPORT_DIR,path);
        String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/report2.jasper");
        platformReport.exportReportByType(reportTemplate,type,parameters,request,response,"report",reportInfoList);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @GET
    @Path("/getInspectPeoplePie")
    public String getInspectPeoplePie(){
        Connection connection=getConnection();
        String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/peopleInspect/peopleInspectPie.jasper");
        getPeopleReportChart(reportTemplate,connection);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @GET
    @Path("/getInspectDevicePie")
    public String getInspectDevicePie(){
        Connection connection=getConnection();
        String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/deviceInspect/deviceInspectPie.jasper");
        getPeopleReportChart(reportTemplate,connection);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    public Connection getConnection(){
        Connection connection=null;
        try {
            connection=sqlSessionFactory.getConfiguration().getEnvironment().getDataSource().getConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
        return connection;
    }
    public String getPeopleSql(){
        String sql="SELECT itr.createtime,sum(itr.exceptioncount) as exceptioncount,u.id,u.name as username,u.role,t.id,t.name as tablename,d.name as devname,dt.name as typename,em.employeeRoleName FROM inspecttablerecord itr,user u,inspecttable t,device d,devicetype dt,employee em WHERE itr.userId=u.id AND itr.inspectTableId=t.id AND itr.deviceId=d.id AND dt.id=d.deviceTypeId AND em.userId=u.id AND itr.createtime='"+transferDateToString(new Date())+"' group by em.employeeRoleName";
        return sql;
    }
    public String getDeviceSql(){
        String sql="SELECT itr.createtime,sum(itr.exceptioncount) as exceptioncount,u.id,u.name as username,u.role,t.id,t.name as tablename,d.name as devname,dt.name as typename,em.employeeRoleName FROM inspecttablerecord itr,user u,inspecttable t,device d,devicetype dt,employee em WHERE itr.userId=u.id AND itr.inspectTableId=t.id AND itr.deviceId=d.id AND dt.id=d.deviceTypeId AND em.userId=u.id AND itr.createtime='"+transferDateToString(new Date())+"' group by d.name";
        return sql;
    }
    public void getPeopleReportChart(String reportTemplate,Connection connection){
        String peopleSql=getPeopleSql();
        Map<String,String> parameters=new HashMap<String, String>();
        parameters.put("sql",peopleSql);
        platformReport.getMapToExportReport(reportTemplate,parameters,"html",request,response,"report",connection);
    }
    public void getDeviceReportChart(String reportTemplate,Connection connection){
        String deviceSql=getDeviceSql();
        Map<String,String> parameters=new HashMap<String, String>();
        parameters.put("sql",deviceSql);
        platformReport.getMapToExportReport(reportTemplate,parameters,"html",request,response,"report",connection);
    }
}
