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
    @Path("/showCraneReport")
    @POST
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    public String showCraneReport(@FormParam("province") String province,@FormParam("city") String city,@FormParam("area") String area,@FormParam("unitaddress") String unitaddress,@FormParam("riskvalue") String riskvalue,@FormParam("equipmentvariety") String equipmentvariety){
     /*
     这个例子主要是展示传入一个sql语句，显示html格式的报表
      */
//        String sql=showCraneSql(province,city,area,unitaddress,equipmentvariety);
     /*
     模板在项目riskmanagement下的repoprtTemplate文件夹下
     */
        String whereArea="";
        String whereCranevariety="";
        String whereUnitaddress="";
        String whereRiskvalue="";
        String subreport="";
        if (!province.equals("")&&!province.equals("请选择"))
        {
        subreport="citychart.jasper";
        }
        if(!city.equals("")&&!city.equals("请选择"))
        {
            subreport="areachart.jasper";
        }
        if(!(area.trim()).equals("")&&!area.equals("请选择"))
        {
            whereArea=" and area="+"\""+area+"\"";
            subreport="blankchart.jasper";
        }
        if(!(unitaddress.trim()).equals("")&&!unitaddress.equals("请选择"))
        {
            whereUnitaddress=" and unitaddress="+"\""+unitaddress+"\"";
        }
        if(!(equipmentvariety.trim()).equals("")&&!equipmentvariety.equals("请选择"))
        {
            whereCranevariety=" and equipmentvariety="+"\""+equipmentvariety+"\"";
        }
        if(!riskvalue.equals("")&&!riskvalue.equals("请选择"))
        {
            whereRiskvalue=" and riskvalue="+riskvalue;
        }
        String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/reporttest.jasper");
        Map parameter=new HashMap();
        parameter.put("SUBREPORT_DIR",(request.getSession().getServletContext().getRealPath("/reportTemplate")+"/"));
        parameter.put("Subreport",subreport);
        parameter.put("Province",province);
        parameter.put("City",city);
        parameter.put("whereArea",whereArea);
        parameter.put("whereCranevariety",whereCranevariety);
        parameter.put("whereUnitaddress",whereUnitaddress);
        parameter.put("whereRiskvalue",whereRiskvalue);
        platformReport.getMapToExportReport(reportTemplate,parameter,"html",request,response);
        return JsonResultUtils
                .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Path("/exportCraneReport/{data}")
    @GET
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    public String exportCraneReport(@PathParam("data") String data)
    {
        String[] datalist=data.split(",");
        String type=datalist[0].split("=")[1];
        String province=datalist[1];
        String city=datalist[2];
        String area=datalist[3];
        String unitaddress=datalist[4];
        String riskvalue=datalist[5];
        String equipmentvariety=datalist[6];
        String whereArea="";
        String whereCranevariety="";
        String whereUnitaddress="";
        String whereRiskvalue="";
        String subreport="";
        System.out.println("风险值"+riskvalue);
        province="湖北";
        city="武汉市";
        if(province.equals(""))
        {
            return showChinaChart();
        }
        else
        {
            subreport="citychart.jasper";
        }
        if(!city.equals(""))
        {
            subreport="areachart.jasper";
        }
        if(!(area.trim()).equals(""))
        {
            whereArea=" and area="+"\""+area+"\"";
            subreport="blankchart.jasper";
        }
        if(!(unitaddress.trim()).equals(""))
        {
            whereUnitaddress=" and unitaddress="+"\""+unitaddress+"\"";
        }
        if(!(equipmentvariety.trim()).equals(""))
        {
            whereCranevariety=" and equipmentvariety="+"\""+equipmentvariety+"\"";
        }
        if(!(riskvalue.trim()).equals(""))
        {
            whereRiskvalue=" and riskvalue="+riskvalue;
        }
        String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/reporttest.jasper");
        Map parameter=new HashMap();
        parameter.put("SUBREPORT_DIR",(request.getSession().getServletContext().getRealPath("/reportTemplate")+"/"));
        parameter.put("Subreport",subreport);
        parameter.put("Province",province);
        parameter.put("City",city);
        parameter.put("whereArea",whereArea);
        parameter.put("whereCranevariety",whereCranevariety);
        parameter.put("whereUnitaddress",whereUnitaddress);
        parameter.put("whereRiskvalue",whereRiskvalue);
        platformReport.getMapToExportReport(reportTemplate,parameter,type,request,response);
/*       System.out.print(data+"..........");*/
        return JsonResultUtils
                .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Path("/showChinaChart")
    @POST
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    public String showChinaChart(){
        String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/chart/chinachart.jasper");
        Map parameter=new HashMap();
        parameter.put("SUBREPORT_DIR",(request.getSession().getServletContext().getRealPath("/reportTemplate/chart")+"/"));
        platformReport.getMapToExportReport(reportTemplate,parameter,"html",request,response);
/*       System.out.print(data+"..........");*/
        return JsonResultUtils
                .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Path("/showProvinceRiskValue")
    @POST
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    public String showChinaChart(@FormParam("province") String province){
        String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/getCityRiskValueByProvince.jasper");
        System.out.print(reportTemplate);
        Map parameter=new HashMap();
        parameter.put("province",province);
        System.out.print(province);
        platformReport.getMapToExportReport(reportTemplate,parameter,"html",request,response);
/*       System.out.print(data+"..........");*/
        return JsonResultUtils
                .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }


}
