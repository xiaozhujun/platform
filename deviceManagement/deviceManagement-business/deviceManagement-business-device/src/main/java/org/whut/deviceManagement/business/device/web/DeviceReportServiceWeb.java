package org.whut.deviceManagement.business.device.web;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.business.user.service.UserService;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.report.PlatformReport;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.deviceManagement.business.device.misc.JasperReportTemplate;
import org.whut.deviceManagement.business.device.service.DeviceService;

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



    public void exportReport(String reportTemplate,String type,List list){
        Map<String,String> parameters=new HashMap<String, String>();
        platformReport.exportReportByType(reportTemplate,type,parameters,request,response,"report",list);
    }
}
