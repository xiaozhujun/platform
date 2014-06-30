package org.whut.inspectManagement.business.inspectReport.web;
import com.mongodb.DBObject;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.device.service.DeviceService;
import org.whut.inspectManagement.business.inspectReport.entity.JasperReportTemplate;
import org.whut.inspectManagement.business.inspectReport.entity.MongoConstant;
import org.whut.inspectManagement.business.inspectReport.entity.ReportSearch;
import org.whut.inspectManagement.business.inspectReport.mapper.ReportSqlMapper;
import org.whut.inspectManagement.business.inspectReport.service.InspectReportService;
import org.whut.platform.business.user.service.UserService;
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
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Context
    HttpServletRequest request;
    @Context
    HttpServletResponse response;
    @Autowired
    private InspectReportService inspectReportService;
    @Autowired
    private UserService userService;
    @Autowired
    private DeviceService deviceService;

    private PlatformReport platformReport=new PlatformReport();

    private static List<Map<String,String>> reportInfoList=new ArrayList<Map<String, String>>();

    private static Map<String,String> reportNameMap=new HashMap<String, String>();

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/reportSearch")
    public String reportSearch(@FormParam("jsonString")String jsonString,@FormParam("flag") String flag){
        ReportSearch reportSearch=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,ReportSearch.class);
        if(flag.equals("0")){
            deviceCount(reportSearch.getsTime(),reportSearch.geteTime());
        }else if(flag.equals("1")){
            //deviceInfo
            deviceInfo(reportSearch.getsTime(), reportSearch.geteTime(), reportSearch.getDeviceId());
        }else if(flag.equals("2")){
            //peopleCount
            peopleCount(reportSearch.getsTime(),reportSearch.geteTime(),reportSearch.getDeviceId());
        }else if(flag.equals("3")){
            //peopleInfo
            peopleInfo(reportSearch.getsTime(),reportSearch.geteTime(),reportSearch.getDeviceId(),reportSearch.getUserId());
        }else if(flag.equals("4")){
            //deviceHistory
            deviceHistory(reportSearch.getsTime(),reportSearch.geteTime(),reportSearch.getDeviceId());
        }
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
       }
    public void deviceCount(String sTime,String eTime){
        //根据sTime和eTime来查出相应的值,封装成list，到报表
        try{
            String reportTemplate=request.getSession().getServletContext().getRealPath("/inspectReportTemplate/deviceCount1.jasper");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deviceInfo(String sTime,String eTime,String deviceId){
        try{
            String reportTemplate=request.getSession().getServletContext().getRealPath("/inspectReportTemplate/deviceCount.jasper");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void peopleCount(String sTime,String eTime,String deviceId){
        try{
            String reportTemplate=request.getSession().getServletContext().getRealPath("/inspectReportTemplate/peopleCount.jasper");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void peopleInfo(String sTime,String eTime,String deviceId,String userId){
        try{
            String reportTemplate=request.getSession().getServletContext().getRealPath("/inspectReportTemplate/reportx2.jasper");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deviceHistory(String sTime,String eTime,String deviceId){
        try{
            String reportTemplate=request.getSession().getServletContext().getRealPath("/inspectReportTemplate/deviceHistory1.jasper");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getInspectTableRecordList")
    public String getInspectTableRecordList(@FormParam("userName")String userName,@FormParam("deviceName")String deviceName,@FormParam("sTime")String sTime,@FormParam("eTime")String eTime){
        String userId=String.valueOf(userService.getIdByName(userName));
        String deviceId=String.valueOf(deviceService.getIdByName(deviceName,0L));
        List<Map<String,String>> list=inspectReportService.getInspectTableRecordList(userId,deviceId,sTime,eTime);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getInspectTableRecordGroupByEmployer")
    public String getInspectTableRecordGroupByEmployer(@FormParam("userName")String userName,@FormParam("deviceName")String deviceName,@FormParam("sTime")String sTime,@FormParam("eTime")String eTime){
        String userId=String.valueOf(userService.getIdByName(userName));
        String deviceId=String.valueOf(deviceService.getIdByName(deviceName,0L));
        List<Map<String,String>> list=inspectReportService.getInspectTableRecordList(userId,deviceId,sTime,eTime);
        HashMap<String,List> data = new HashMap<String, List>();
        String key;
        for(Map<String,String> o:list){
            key = o.get("employeeRoleName");
            if(data.containsKey(key)){
               data.get(key).add(o);
            }else{
               ArrayList<Map<String,String>> listTemp = new ArrayList<Map<String, String>>();
                listTemp.add(o);
                data.put(key,listTemp);
            }
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(data,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getInspectTableRecordGroupByDevice")
    public String getInspectTableRecordGroupByDevice(@FormParam("userName")String userName,@FormParam("deviceName")String deviceName,@FormParam("sTime")String sTime,@FormParam("eTime")String eTime){
        String userId=String.valueOf(userService.getIdByName(userName));
        String deviceId=String.valueOf(deviceService.getIdByName(deviceName,0L));
        List<Map<String,String>> list=inspectReportService.getInspectTableRecordList(userId,deviceId,sTime,eTime);
        HashMap<String,List> data = new HashMap<String, List>();
        String key;
        for(Map<String,String> o:list){
            key = o.get("devname");
            if(data.containsKey(key)){
                data.get(key).add(o);
            }else{
                ArrayList<Map<String,String>> listTemp = new ArrayList<Map<String, String>>();
                listTemp.add(o);
                data.put(key,listTemp);
            }
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(data,JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getInspectInfo")
    public String getInspectInfo(@FormParam("jsonString")String jsonString,@FormParam("type") String type){
        reportInfoList.clear();
        reportNameMap.clear();
        Map<String,String> map= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Map.class);
        //根据传过来的map取得mongoId,然后从mongo中查出相应的值.然后根据各个Id来查出相应的信息来放到list中后导出html格式的报表
        //reportInfoList=getReportListSource(map);
        reportInfoList=getReportListSourceByMap(map);
        Map<String,String> parameters=new HashMap<String, String>();
        String path=request.getSession().getServletContext().getRealPath(JasperReportTemplate.reportTemplateDir) + "/";
        //parameters.put(MongoConstant.SUBREPORT_DIR,path);
        String reportTemplate=request.getSession().getServletContext().getRealPath(JasperReportTemplate.reportInfoTemplate);
        reportNameMap.put("tableName",map.get(MongoConstant.tableName));
        platformReport.exportReportByType(reportTemplate,type,parameters,request,response,map.get(MongoConstant.tableName),reportInfoList);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    //根据mongoId从mongoDb中取出数据
    public List<DBObject> getInfoFromMongoByMongoId(String mongoId){
        MongoConnector mongoConnector=new MongoConnector(MongoConstant.craneInspectReportDB,MongoConstant.inspectItemRecordCollection);
        List<DBObject> d=mongoConnector.getInspectItemRecordByMongoId(mongoId);
        return d;
    }
    //从mongoDb中取出的id直接查询出相应的数据
    public List<Map<String,String>> getInfoByMongoDbObject(List<DBObject> dList){
           List<Map<String,String>> mapList=new ArrayList<Map<String, String>>();
           for(DBObject dd:dList){
               Map<String,String> d=dd.toMap();
               Map<String,String> map=inspectReportService.getInfoByMongoDbObject(d);
               mapList.add(map);
           }
           return mapList;
    }
    //封装报表的数据源
    public List<Map<String,String>> getReportListSourceByMap(Map<String,String> map){
        List<DBObject> d=getInfoFromMongoByMongoId(map.get(MongoConstant.mongoId));
        List<Map<String,String>> l=getInfoByMongoDbObject(d);
        List<Map<String,String>> reportInfoList=new ArrayList<Map<String,String>>();
        for(Map<String,String> m:l){
            if(m!=null){
                Map<String,String> reportInfoMap=new HashMap<String, String>();
                reportInfoMap.put(MongoConstant.userName,m.get(MongoConstant.userName));
                reportInfoMap.put(MongoConstant.tagName, m.get(MongoConstant.tagName));
                reportInfoMap.put(MongoConstant.tName, m.get(MongoConstant.tName));
                reportInfoMap.put(MongoConstant.devName, m.get(MongoConstant.devName));
                reportInfoMap.put(MongoConstant.reportCreateTime, map.get(MongoConstant.createTime));
                reportInfoMap.put(MongoConstant.itemName,m.get(MongoConstant.itemName));
                reportInfoMap.put(MongoConstant.inspectChoiceValue,m.get(MongoConstant.inspectChoiceValue));
                reportInfoList.add(reportInfoMap);
            }
        }
        return reportInfoList;
    }
    //根据不同的类型导出相应的报表
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @GET
    @Path("/exportPeopleInfoReport/{type}")
    public void exportPeopleInfoReport(@PathParam("type")String type){
        Map<String,String> parameters=new HashMap<String, String>();
        String path=request.getSession().getServletContext().getRealPath(JasperReportTemplate.reportTemplateDir) + "/";
        parameters.put(MongoConstant.SUBREPORT_DIR,path);
        String reportTemplate=request.getSession().getServletContext().getRealPath(JasperReportTemplate.reportInfoTemplate);
        platformReport.exportReportByType(reportTemplate,type,parameters,request,response,"report",reportInfoList);
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
}
