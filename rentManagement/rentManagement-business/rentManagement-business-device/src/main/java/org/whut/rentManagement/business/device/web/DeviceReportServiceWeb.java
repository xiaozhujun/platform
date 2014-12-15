package org.whut.rentManagement.business.device.web;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.business.user.service.UserService;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.report.PlatformReport;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.rentManagement.business.device.misc.JasperReportTemplate;
import org.whut.rentManagement.business.device.service.DeviceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-12-2
 * Time: 下午4:28
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/deviceReport")
public class DeviceReportServiceWeb {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Context
    HttpServletRequest request;
    @Context
    HttpServletResponse response;
    @Autowired
    private DeviceService deviceService;

    @Autowired
    private UserService userService;

    private PlatformReport platformReport=new PlatformReport(FundamentalConfigProvider.get("dbcp.rentmanagement.driverClassName"),FundamentalConfigProvider.get("dbcp.rentmanagement.url"),
            FundamentalConfigProvider.get("dbcp.rentmanagement.username"),FundamentalConfigProvider.get("dbcp.rentmanagement.password"));


    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/deviceAccountReport")
    public void deviceAccountReport(@FormParam("jsonString")String jsonString){
        /*if(jsonString==null||jsonString.trim().equals("")){
           return JsonResultUtils.getObjectResultByStringAsDefault("查询条件不能为空！", JsonResultUtils.Code.ERROR);
        }*/
        HashMap<String,Object> condition= JsonMapper.buildNormalMapper().fromJson(jsonString,HashMap.class);
        condition.put("appId", UserContext.currentUserAppId());
        condition.put("orderByCondition"," order by deviceType,mainDeviceNumber asc");
        List<Map<String,Object>> reportInfoList=deviceService.findMainDeviceByCondition(condition);
        String reportTemplate=request.getSession().getServletContext().getRealPath(JasperReportTemplate.deviceAccountReport);
        String type = (String)condition.get("reportType");
        exportReport(reportTemplate,type,reportInfoList);
//        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    //根据不同的类型导出相应的报表
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @GET
    @Path("/exportDeviceAccountReport")
    public void exportDeviceAccountReport(@QueryParam("jsonString") String jsonString){

//        if(jsonString==null||jsonString.trim().equals("")){
//            return JsonResultUtils.getObjectResultByStringAsDefault("查询条件不能为空！", JsonResultUtils.Code.ERROR);
//        }
        HashMap<String,Object> condition= JsonMapper.buildNormalMapper().fromJson(jsonString,HashMap.class);

        String reportTemplate=request.getSession().getServletContext().getRealPath(JasperReportTemplate.deviceAccountReport);
        String reportType=(String)condition.get("reportType");
        condition.put("appId",UserContext.currentUserAppId());
        condition.put("orderByCondition"," order by deviceType,mainDeviceNumber asc");
        List<Map<String,Object>> reportInfoList=deviceService.findMainDeviceByCondition(condition);
        exportReport(reportTemplate,reportType,reportInfoList);
//        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/rentDeviceReport")
    public void rentDeviceReport(@FormParam("jsonString")String jsonString){
//        if(jsonString==null||jsonString.trim().equals("")){
//            return JsonResultUtils.getObjectResultByStringAsDefault("查询条件不能为空！", JsonResultUtils.Code.ERROR);
//        }
        HashMap<String,Object> condition= JsonMapper.buildNormalMapper().fromJson(jsonString,HashMap.class);
        condition.put("appId", UserContext.currentUserAppId());
        condition.put("orderByCondition"," order by contractName,mainDeviceNumber,deviceType asc");
        List<Map<String,Object>> reportInfoList=deviceService.findMainDeviceByCondition(condition);
        String reportTemplate=request.getSession().getServletContext().getRealPath(JasperReportTemplate.rentedDeviceAccountReport);
        String type = (String)condition.get("reportType");
        exportReport(reportTemplate,type,reportInfoList);
//        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    //根据不同的类型导出相应的报表
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @GET
    @Path("/exportRentDeviceReport")
    public void exportRentDeviceReport(@QueryParam("jsonString") String jsonString){

//        if(jsonString==null||jsonString.trim().equals("")){
//            return JsonResultUtils.getObjectResultByStringAsDefault("查询条件不能为空！", JsonResultUtils.Code.ERROR);
//        }
        HashMap<String,Object> condition= JsonMapper.buildNormalMapper().fromJson(jsonString,HashMap.class);

        String reportTemplate=request.getSession().getServletContext().getRealPath(JasperReportTemplate.rentedDeviceAccountReport);
        String reportType=(String)condition.get("reportType");
        condition.put("appId",UserContext.currentUserAppId());
        condition.put("orderByCondition"," order by contractName,mainDeviceNumber,deviceType asc");
        List<Map<String,Object>> reportInfoList=deviceService.findMainDeviceByCondition(condition);
        exportReport(reportTemplate,reportType,reportInfoList);
//        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/deviceTrendReport")
    public void deviceTrendReport(@FormParam("jsonString")String jsonString){
//        if(jsonString==null||jsonString.trim().equals("")){
//            return JsonResultUtils.getObjectResultByStringAsDefault("查询条件不能为空！", JsonResultUtils.Code.ERROR);
//        }
        HashMap<String,Object> condition= JsonMapper.buildNormalMapper().fromJson(jsonString,HashMap.class);
        condition.put("appId", UserContext.currentUserAppId());
        condition.put("orderByCondition"," order by age desc,deviceType desc");
        List<Map<String,Object>> reportInfoList=deviceService.findMainDeviceByCondition(condition);
        String reportTemplate=request.getSession().getServletContext().getRealPath(JasperReportTemplate.deviceTrendReport);
        String type = (String)condition.get("reportType");
        exportReport(reportTemplate,type,reportInfoList);
//        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    //根据不同的类型导出相应的报表
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @GET
    @Path("/exportDeviceTrendReport")
    public void exportDeviceTrendReport(@QueryParam("jsonString") String jsonString){

//        if(jsonString==null||jsonString.trim().equals("")){
//            return JsonResultUtils.getObjectResultByStringAsDefault("查询条件不能为空！", JsonResultUtils.Code.ERROR);
//        }
        HashMap<String,Object> condition= JsonMapper.buildNormalMapper().fromJson(jsonString,HashMap.class);

        String reportTemplate=request.getSession().getServletContext().getRealPath(JasperReportTemplate.deviceTrendReport);
        String reportType=(String)condition.get("reportType");
        condition.put("appId",UserContext.currentUserAppId());
        condition.put("orderByCondition"," order by age desc,deviceType desc");
        List<Map<String,Object>> reportInfoList=deviceService.findMainDeviceByCondition(condition);
        //platformReport.exportReportByType(reportTemplate,reportType,new HashMap<String, String>(),request,response,"report");
        exportReport(reportTemplate,reportType,reportInfoList);
//        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }


    public void exportReport(String reportTemplate,String type,List list){
        Map<String,String> parameters=new HashMap<String, String>();
        platformReport.exportReportByType(reportTemplate,type,parameters,request,response,"report",list);
    }
}
