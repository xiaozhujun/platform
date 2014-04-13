package org.whut.platform.business.report.web;

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
 * User: chenqw
 * Date: 14-3-28
 * Time: 下午1:06
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/report")
public class CraneReportServiceWeb {
    private static PlatformLogger logger=PlatformLogger.getLogger(CraneReportServiceWeb.class);
    private PlatformReport platformReport=new PlatformReport();
    @Context
    HttpServletRequest request;
    @Context
    HttpServletResponse response;
    @Path("/showCraneReport")
    @POST
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    public String showCraneReport(@FormParam("province") String province,@FormParam("city") String city,@FormParam("area") String area,@FormParam("unitaddress") String unitaddress,@FormParam("equipmentvariety") String equipmentvariety){
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
        province="湖北";
        city="武汉市";
        if(!(area.trim()).equals(""))
        {
            whereArea=" and area="+"\""+area+"\"";
        }
        if(!(unitaddress.trim()).equals(""))
        {
            whereUnitaddress=" and unitaddress="+"\""+unitaddress+"\"";
        }
        if(!(equipmentvariety.trim()).equals(""))
        {
            whereCranevariety=" and equipmentvariety="+"\""+equipmentvariety+"\"";
        }
        String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/reporttest.jasper");
        Map parameter=new HashMap();
        parameter.put("Province",province);
        parameter.put("City",city);
        parameter.put("whereArea",whereArea);
        parameter.put("whereCranevariety",whereCranevariety);
        parameter.put("whereUnitaddress",whereUnitaddress);
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
        String equipmentvariety=datalist[5];
        String whereArea="";
        String whereCranevariety="";
        String whereUnitaddress="";
        province="湖北";
        city="武汉市";
        if(!(area.trim()).equals(""))
        {
            whereArea=" and area="+"\""+area+"\"";
        }
        if(!(unitaddress.trim()).equals(""))
        {
            whereUnitaddress=" and unitaddress="+"\""+unitaddress+"\"";
        }
        if(!(equipmentvariety.trim()).equals(""))
        {
            whereCranevariety=" and equipmentvariety="+"\""+equipmentvariety+"\"";
        }
        String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/reporttest.jasper");
        Map parameter=new HashMap();
        parameter.put("Province",province);
        parameter.put("City",city);
        parameter.put("whereArea",whereArea);
        parameter.put("whereCranevariety",whereCranevariety);
        parameter.put("whereUnitaddress",whereUnitaddress);
        platformReport.getMapToExportReport(reportTemplate,parameter,type,request,response);
/*       System.out.print(data+"..........");*/
        return JsonResultUtils
                .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }


   /* public String showCraneSql(String province,String city,String area,String unitaddress,String equipmentvariety)
    {
        String sql="select reportnumber,unitaddress,addressid,organizecode,usepoint,safemanager,contactphone,equipmentvariety,unitnumber from craneinspectreport";
        String variety_sql="";
        if(unitaddress.equals(""))
        {
            sql +=" where unitaddress like \"%"+province+"%"+city+"%"+area+"%\"";
        }
        else
        {
            sql+=" where unitaddress=\""+unitaddress+"\"";
        }
        if (!equipmentvariety.equals(""))
        {
            variety_sql=" and equipmentvariety=\""+equipmentvariety+"\"";
            sql+=variety_sql;
        }
        return sql;
    }*/
}



