package org.whut.inspectManagement.business.inspectReport.web;

import com.mongodb.DBObject;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.device.service.DeviceService;
import org.whut.inspectManagement.business.inspectReport.entity.JasperReportTemplate;
import org.whut.inspectManagement.business.inspectReport.entity.MongoConstant;
import org.whut.inspectManagement.business.inspectReport.entity.ReportSearch;
import org.whut.inspectManagement.business.inspectReport.entity.SearchReportBean;
import org.whut.inspectManagement.business.inspectReport.service.InspectReportService;
import org.whut.inspectManagement.business.inspectResult.entity.InspectTableRecord;
import org.whut.inspectManagement.business.inspectResult.service.InspectTableRecordService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.business.user.service.UserService;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.mongo.connector.MongoConnector;
import org.whut.platform.fundamental.report.PlatformReport;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Context
    HttpServletRequest request;
    @Context
    HttpServletResponse response;
    @Autowired
    private InspectReportService inspectReportService;

    @Autowired
    private InspectTableRecordService inspectTableRecordService;

    @Autowired
    private UserService userService;
    @Autowired
    private DeviceService deviceService;

    private PlatformReport platformReport=new PlatformReport(FundamentalConfigProvider.get("dbcp.inspectmanagement.driverClassName"),FundamentalConfigProvider.get("dbcp.inspectmanagement.url"),
            FundamentalConfigProvider.get("dbcp.inspectmanagement.username"),FundamentalConfigProvider.get("dbcp.inspectmanagement.password"));

    private static List<Map<String,String>> reportInfoList=new ArrayList<Map<String, String>>();

    private static List<SearchReportBean> searchReportBeanList=new ArrayList<SearchReportBean>();

    private final String flag1="0";

    private final String flag2="1";

    private final String flag3="2";

    private final String flag4="3";

    private final String flag5="4";

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/reportSearch")
    public String reportSearch(@FormParam("jsonString")String jsonString,@FormParam("flag") String flag,@FormParam("type")String type){
        ReportSearch reportSearch=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,ReportSearch.class);
        if(flag.equals(flag1)){
            deviceCount(reportSearch.getsTime(),reportSearch.geteTime(),type);
        }else if(flag.equals(flag2)){
            //deviceInfo
            deviceInfo(reportSearch.getsTime(), reportSearch.geteTime(), reportSearch.getDeviceName(),type);
        }else if(flag.equals(flag3)){
            //peopleCount
            peopleCount(reportSearch.getsTime(),reportSearch.geteTime(),reportSearch.getDeviceName(),type);
        }else if(flag.equals(flag4)){
            //peopleInfo
            peopleInfo(reportSearch.getsTime(),reportSearch.geteTime(),reportSearch.getDeviceName(),reportSearch.getUserName(),type);
        }else if(flag.equals(flag5)){
            //deviceHistory
            deviceHistory(reportSearch.getsTime(),reportSearch.geteTime(),reportSearch.getDeviceName(),type);
        }
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
       }
    public void deviceCount(String sTime,String eTime,String type){
        //根据sTime和eTime来查出相应的值,封装成list，到报表
        searchReportBeanList.clear();
        try{
            searchReportBeanList=inspectReportService.getInspectTableRecordListByBean("null", "null", sTime, eTime,UserContext.currentUserAppId());
            String reportTemplate=request.getSession().getServletContext().getRealPath(JasperReportTemplate.deviceCountTemplate);
            exportReport(reportTemplate,type,searchReportBeanList);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deviceInfo(String sTime,String eTime,String deviceName,String type){
           searchReportBeanList.clear();
          try{
            //先根据条件拿到mongoId，然后根据mongoId来获取相应的mongo中的信息,将mongo中的信息以及拿出的Id组装成list，赋给jasperreport即可.
            String deviceId=String.valueOf(deviceService.getIdByName(deviceName,0L));
            List<SearchReportBean> list=inspectReportService.getInspectTableRecordListByBean("null",deviceId,sTime,eTime,UserContext.currentUserAppId());
            searchReportBeanList=getSearchReportListSourceByMongoId(list);
            String reportTemplate=request.getSession().getServletContext().getRealPath(JasperReportTemplate.deviceInfoTemplate);
            exportReport(reportTemplate,type,searchReportBeanList);
          }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void peopleCount(String sTime,String eTime,String deviceName,String type){
        searchReportBeanList.clear();
        try{
            String deviceId=String.valueOf(deviceService.getIdByName(deviceName,0L));
            searchReportBeanList=inspectReportService.getInspectTableRecordListByBean("null", deviceId, sTime, eTime,UserContext.currentUserAppId());
            String reportTemplate=request.getSession().getServletContext().getRealPath(JasperReportTemplate.peopleCountTemplate);
            exportReport(reportTemplate,type,searchReportBeanList);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void peopleInfo(String sTime,String eTime,String deviceName,String userName,String type){
        searchReportBeanList.clear();
        try{
            String deviceId=String.valueOf(deviceService.getIdByName(deviceName,0L));
            String userId=String.valueOf(userService.getIdByName(userName));
            List<SearchReportBean> list=inspectReportService.getInspectTableRecordListByBean(userId,deviceId,sTime,eTime,UserContext.currentUserAppId());
            searchReportBeanList=getSearchReportListSourceByMongoId(list);
            String reportTemplate=request.getSession().getServletContext().getRealPath(JasperReportTemplate.peopleInfoTemplate);
            exportReport(reportTemplate,type,searchReportBeanList);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deviceHistory(String sTime,String eTime,String deviceName,String type){
        try{
            String deviceId=String.valueOf(deviceService.getIdByName(deviceName,0L));
            searchReportBeanList=inspectReportService.getDeviceHistoryData(sTime,eTime,deviceId,UserContext.currentUserAppId());
            String reportTemplate=request.getSession().getServletContext().getRealPath(JasperReportTemplate.deviceHistoryTemplate);
            exportReport(reportTemplate,type,searchReportBeanList);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getInspectTableRecordList")
    public String getInspectTableRecordList(@FormParam("userId")String userName,@FormParam("deviceId")String deviceName,@FormParam("sTime")String sTime,@FormParam("eTime")String eTime){
        if (userName == null || userName.equals("") || deviceName == null || deviceName.equals("")) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空！");
        }
        String userId=String.valueOf(userService.getIdByName(userName));
        String deviceId=String.valueOf(deviceService.getIdByName(deviceName,UserContext.currentUserAppId()));
        List<Map<String,String>> list=inspectReportService.getInspectTableRecordList(userId,deviceId,sTime,eTime,UserContext.currentUserAppId());
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getInspectTableRecordGroupByEmployer")
    public String getInspectTableRecordGroupByEmployer(@FormParam("userName")String userName,@FormParam("deviceName")String deviceName,@FormParam("sTime")String sTime,@FormParam("eTime")String eTime){
        String userId=String.valueOf(userService.getIdByName(userName));
        String deviceId=String.valueOf(deviceService.getIdByName(deviceName,UserContext.currentUserAppId()));
        List<Map<String,String>> list=inspectReportService.getInspectTableRecordList(userId,deviceId,sTime,eTime, UserContext.currentUserAppId());
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
        List<Map<String,String>> list=inspectReportService.getInspectTableRecordList(userId,deviceId,sTime,eTime,UserContext.currentUserAppId());
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
    public String getInspectInfo(@FormParam("id")String id,@FormParam("type") String type){
        reportInfoList.clear();
        InspectTableRecord inspectTableRecord = inspectTableRecordService.getById(Long.parseLong(id));
        HashMap<String,String> map = new HashMap<String, String>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        map.put("mongoId",inspectTableRecord.getMongoId());
        map.put("inspectTime",format.format(inspectTableRecord.getInspectTime()));

        reportInfoList=getReportListSourceByMap(map);
        String reportTemplate=request.getSession().getServletContext().getRealPath(JasperReportTemplate.reportInfoTemplate);
        exportReport(reportTemplate,type,reportInfoList);
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
               map.put(MongoConstant.note,d.get(MongoConstant.note));
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
                reportInfoMap.put(MongoConstant.reportCreateTime, map.get(MongoConstant.inspectTime));
                reportInfoMap.put(MongoConstant.itemName,m.get(MongoConstant.itemName));
                reportInfoMap.put(MongoConstant.inspectChoiceValue,m.get(MongoConstant.inspectChoiceValue));
                reportInfoMap.put(MongoConstant.note,m.get(MongoConstant.note));
                reportInfoList.add(reportInfoMap);
            }
        }
        return reportInfoList;
    }
    //对报表分析模块的list的封装
    public List<SearchReportBean> getSearchReportListSourceByMongoId(List<SearchReportBean> mapList){
        List<SearchReportBean> reportInfoList=new ArrayList<SearchReportBean>();
        for(SearchReportBean map:mapList){
        List<DBObject> d=getInfoFromMongoByMongoId(map.getMongoId());
        List<Map<String,String>> l=getInfoByMongoDbObject(d);
        for(Map<String,String> m:l){
            if(m!=null){
                if(m.get(MongoConstant.inspectChoiceValue).equals(MongoConstant.abnormal)){
                SearchReportBean searchReportBean=new SearchReportBean();
                searchReportBean.setUserName(m.get(MongoConstant.userName));
                searchReportBean.setTagName(m.get(MongoConstant.tagName));
                searchReportBean.setDevname(m.get(MongoConstant.devName));
                searchReportBean.setCreatetime(map.getCreatetime());
                searchReportBean.setItemName(m.get(MongoConstant.itemName));
                searchReportBean.setInspectChoiceValue(m.get(MongoConstant.inspectChoiceValue));
                searchReportBean.setExceptioncount(map.getExceptioncount());
                searchReportBean.setNote(m.get(MongoConstant.note));
                reportInfoList.add(searchReportBean);
                }
            }
        }
        }
        return reportInfoList;
    }
    //根据不同的类型导出相应的报表
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @GET
    @Path("/exportPeopleInfoReport/{type}")
    public void exportPeopleInfoReport(@PathParam("type")String type){
        String reportTemplate=request.getSession().getServletContext().getRealPath(JasperReportTemplate.reportInfoTemplate);
        exportReport(reportTemplate,type,reportInfoList);
    }
    //根据不同的类型导出相应的报表
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @GET
    @Path("/exportSearchReport/{typeFlag}")
    public void exportSearchReport(@PathParam("typeFlag")String typeFlag){
        String reportTemplate=null;
        String[] s=typeFlag.split(",");
        String type=s[0];
        String flag=s[1];
        if(flag.equals(flag1)){
            reportTemplate=request.getSession().getServletContext().getRealPath(JasperReportTemplate.deviceCountTemplate);
        }else if(flag.equals(flag2)){
            reportTemplate=request.getSession().getServletContext().getRealPath(JasperReportTemplate.deviceInfoTemplate);
        }else if(flag.equals(flag3)){
            reportTemplate=request.getSession().getServletContext().getRealPath(JasperReportTemplate.peopleCountTemplate);
        }else if(flag.equals(flag4)){
            reportTemplate=request.getSession().getServletContext().getRealPath(JasperReportTemplate.peopleInfoTemplate);
        }else if(flag.equals(flag5)){
            reportTemplate=request.getSession().getServletContext().getRealPath(JasperReportTemplate.deviceHistoryTemplate);
        }
        exportReport(reportTemplate,type,searchReportBeanList);
    }
    public void exportReport(String reportTemplate,String type,List list){
        Map<String,String> parameters=new HashMap<String, String>();
        platformReport.exportReportByType(reportTemplate,type,parameters,request,response,"report",list);
    }
}
