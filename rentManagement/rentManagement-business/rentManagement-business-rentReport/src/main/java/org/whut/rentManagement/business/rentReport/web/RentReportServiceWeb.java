package org.whut.rentManagement.business.rentReport.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.report.PlatformReport;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.rentReport.entity.RentReport;
import org.whut.rentManagement.business.rentReport.service.RentReportService;
import org.whut.rentManagement.business.rentReport.entity.SearchReportBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cwb
 * Date: 14-11-20
 * Time: 下午5:25
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/RentReport")
public class RentReportServiceWeb {
    private PlatformReport platformReport=new PlatformReport(FundamentalConfigProvider.get("dbcp.rentmanagement.driverClassName"),
                                                                 FundamentalConfigProvider.get("dbcp.rentmanagement.url"),
                                                                 FundamentalConfigProvider.get("dbcp.rentmanagement.username"),
                                                                 FundamentalConfigProvider.get("dbcp.rentmanagement.password"));
    @Context
    HttpServletRequest request;
    @Context
    HttpServletResponse response;
    @Autowired
    RentReportService rentReportService;

    private static List<Map<String,String>> reportInfoList=new ArrayList<Map<String, String>>();
    private static List<SearchReportBean> searchReportBeanList=new ArrayList<SearchReportBean>();

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/reportSearch")
    public String reportSearch(@FormParam("jsonString")String jsonString,@FormParam("type")String type){
        RentReport rentReport = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,RentReport.class);
        String eTime = rentReport.geteTime();
        String sTime = rentReport.getsTime();
        String deviceType = rentReport.getDeviceType();
        String deviceStatus = rentReport.getDeviceStatus();
        reportInfoList = rentReportService.getRentTableRecordList(deviceType, deviceStatus, sTime, eTime, UserContext.currentUserAppId());
        String reportTemplate = request.getSession().getServletContext().getRealPath("/reportTemplate/rentReport1.jasper");//起到什么作用 ?
        //System.out.println(reportTemplate);
        Map<String,String> parameters=new HashMap<String, String>();
        platformReport.exportReportByType(reportTemplate, type, parameters, request, response, "test", reportInfoList);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    //根据不同的类型导出相应的报表
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @GET
    @Path("/exportSearchReport/{type}")
    public void exportSearchReport(@PathParam("type")String type){
        String reportTemplate=null;
        reportTemplate = request.getSession().getServletContext().getRealPath("/reportTemplate/report1.jasper");//request.getSession()是获取session（服务器端的存储空间）
        Map<String,String> parameters=new HashMap<String, String>();
        platformReport.exportReportByType(reportTemplate, type, parameters, request, response, "test", reportInfoList);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/ShowRentReport")
    public String ShowRentReport(@FormParam("jsonString")String jsonString) throws SQLException {

        RentReport rentReport = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,RentReport.class);
        String reportTemplate=null;
        reportTemplate = request.getSession().getServletContext().getRealPath("/rentReportTemplate/rentReport.jasper"); //request.getSession().getServletContext() 获取的是Servlet容器对象，相当于tomcat容器
        String eTime = rentReport.geteTime();
        String sTime = rentReport.getsTime();
        String deviceType = rentReport.getDeviceType();
        String deviceStatus = rentReport.getDeviceStatus();
        String wheredeviceType="";
        String wheredeviceStatus="";
        String wheresTime="";
        String whereeTime="";
        boolean flag=false;
        if(!(deviceType.trim()).equals("")&&!deviceType.equals("请选择"))
        {
            wheredeviceType="t.name="+"\""+deviceType+"\"";
            flag=true;
        }
        if(!(deviceStatus.trim()).equals("")&&!deviceStatus.equals("请选择"))
        {
            wheredeviceStatus=" d.status="+"\""+deviceStatus+"\"";
            if(flag){
                wheredeviceStatus=" and " +wheredeviceStatus;
            }
            flag=true;
        }
        if((!(sTime.trim()).equals("")&&!sTime.equals("请选择"))&&(!(eTime.trim()).equals("")&&!eTime.equals("请选择")))
        {
            wheresTime=" t.createTime BETWEEN "+"\""+sTime+"\"";
            whereeTime=" and "+"\""+eTime+"\"";
            if(flag){
                wheresTime=" and " +wheresTime;
            }
        }


        Map parameter=new HashMap();
        parameter.put("deviceType",wheredeviceType);
        parameter.put("deviceStatus",wheredeviceStatus);
        parameter.put("eTime",whereeTime);
        parameter.put("sTime",wheresTime);
        //利用spring取得applicationContext.xml配置的数据源,datasource,然后取得ds，这一步可以问杨阳,他做过
        //BasicDataSource ds = rentManagement.getObject();
        //Connection connection=ds.getConnection() ;
        //platformReport.getMapToExportReport(reportTemplate,parameter,"html",request,response,"test",connection);    //有这个方法但是就是不研究这两个方法的区别.
        platformReport.getMapToExportReport(reportTemplate,parameter,"html",request,response,"test");
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }


   /* @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @GET
    @Path("/whut")
    public String aaa(){
        long appId = UserContext.currentUserAppId();
        Map<String,String> value = rentReportService.aaa(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(value, JsonResultUtils.Code.SUCCESS);
    }*/



    /*public String getReportSQL(){
        String sql="SELECT\n" +
                "     device.`typeId` AS device_typeId,\n" +
                "     device.`optionType` AS device_optionType,\n" +
                "     device.`address` AS device_address,\n" +
                "     device.`name` AS device_name,\n" +
                "     device.`number` AS device_number,\n" +
                "     device.`price` AS device_price,\n" +
                "     device.`produceTime` AS device_produceTime,\n" +
                "     device.`priceUnit` AS device_priceUnit,\n" +
                "     device_type.`name` AS device_type_name\n" +
                "FROM\n" +
                "     `device` device INNER JOIN `device_type` device_type ON device.`typeId` = device_type.`id`"+
                "WHERE\n"+
                "      ";
        return sql;
    }*/
}
