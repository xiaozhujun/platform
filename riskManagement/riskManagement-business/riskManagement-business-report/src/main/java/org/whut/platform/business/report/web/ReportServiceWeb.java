package org.whut.platform.business.report.web;
import org.springframework.stereotype.Component;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
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
    private PlatformReport platformReport=new PlatformReport(FundamentalConfigProvider.get("dbcp.riskmanagement.driverClassName"),FundamentalConfigProvider.get("dbcp.riskmanagement.url"),FundamentalConfigProvider.get("dbcp.riskmanagement.username"),FundamentalConfigProvider.get("dbcp.riskmanagement.password"));
    @Context HttpServletRequest request;
    @Context HttpServletResponse response;
    @Path("/showCraneReport")
    @POST
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    public String showCraneReport(@FormParam("province") String province,@FormParam("city") String city,@FormParam("area") String area,@FormParam("unitaddress") String unitaddress,@FormParam("riskvalueareaslider") String riskvalueareaslider,@FormParam("equipmentvariety") String equipmentvariety,@FormParam("minweight") String minweight,@FormParam("maxweight") String maxweight){
     /*
     这个例子主要是显示html格式的报表
     模板在项目riskmanagement下的repoprtTemplate文件夹下
     */
        String whereArea="";
        String whereCranevariety="";
        String whereUnitAddress="";
        String whereRiskvalue="";
        String whereWeightLevel="";
        String whereProvince="";
        String whereCity="";
        String[] values= riskvalueareaslider.split(";");
        float startValue = Float.parseFloat(values[0]);
        float endValue=Float.parseFloat(values[1]);

        if(!(province.trim()).equals("")&&!province.equals("请选择"))
        {

            whereProvince=" and province="+"\""+province+"\"";
        }
        if(!(city.trim()).equals("")&&!city.equals("请选择"))
        {

            whereCity=" and city="+"\""+city+"\"";
        }
        if(!(area.trim()).equals("")&&!area.equals("请选择"))
        {

            whereArea=" and area="+"\""+area+"\"";
        }
        if(!(unitaddress.trim()).equals("")&&!unitaddress.equals("请选择"))
        {
            whereUnitAddress=" and unitaddress="+"\""+unitaddress+"\"";
        }
        if(!(equipmentvariety.trim()).equals("")&&!equipmentvariety.equals("请选择"))
        {
            whereCranevariety=" and equipmentvariety="+"\""+equipmentvariety+"\"";
        }
        whereWeightLevel=" and ratedliftweight>="+minweight+" and ratedliftweight<="+maxweight;
        whereRiskvalue=" and riskvalue>="+startValue+" and riskvalue<="+endValue;
        String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/detail/chinaReport.jasper");
        Map parameter=new HashMap();
        parameter.put("SUBREPORT_DIR",(request.getSession().getServletContext().getRealPath("/reportTemplate/detail")+"/"));
        System.out.println((request.getSession().getServletContext().getRealPath("/reportTemplate/detail")+"/")+"hahah");
        parameter.put("whereProvince",whereProvince);
        parameter.put("whereCity",whereCity);
        parameter.put("whereArea",whereArea);
        parameter.put("whereCranevariety",whereCranevariety);
        parameter.put("whereUnitAddress",whereUnitAddress);
        parameter.put("whereRiskvalue",whereRiskvalue);
        parameter.put("whereWeightLevel",whereWeightLevel);
        platformReport.getMapToExportReport(reportTemplate,parameter,"html",request,response,"test");
        return JsonResultUtils
                .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Path("/exportCraneReport/{data}")
    @GET
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    public String exportCraneReport(@PathParam("data") String data)
    {
         //根据传入的data打印不同格式的报表
        /*data=excel,湖北,武汉市,江岸区,湖北省武汉市江岸区赵家条319号,4,6,汽车起重机,0,200*/
        String[] datalist=data.split(",");
        String type=datalist[0].split("=")[1];
        String province=datalist[1];
        String city=datalist[2];
        String area=datalist[3];
        String unitaddress=datalist[4];
        String startvalue=datalist[5];
        String endvalue=datalist[6];
        String equipmentvariety=datalist[7];
        String minweight=datalist[8];
        String maxweight=datalist[9];
        String whereArea="";
        String whereCranevariety="";
        String whereUnitAddress="";
        String whereRiskvalue="";
        String whereWeightLevel="";
        String  whereProvince="";
        String whereCity="";
        if(!(province.trim()).equals("")&&!province.equals("请选择"))
        {

            whereProvince=" and province="+"\""+province+"\"";
        }
        if(!(city.trim()).equals("")&&!city.equals("请选择"))
        {

            whereCity=" and city="+"\""+city+"\"";
        }
        if(!(area.trim()).equals("")&&!area.equals("请选择"))
        {
            whereArea=" and area="+"\""+area+"\"";
        }
        if(!(unitaddress.trim()).equals("")&&!unitaddress.equals("请选择"))
        {
            whereUnitAddress=" and unitaddress="+"\""+unitaddress+"\"";
        }
        if(!(equipmentvariety.trim()).equals("")&&!equipmentvariety.equals("请选择"))
        {
            whereCranevariety=" and equipmentvariety="+"\""+equipmentvariety+"\"";
        }
        whereWeightLevel=" and ratedliftweight>="+minweight+" and ratedliftweight<="+maxweight;
        whereRiskvalue=" and riskvalue>="+startvalue+" and riskvalue<="+endvalue;
        String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/detail/chinaReport.jasper");
        Map parameter=new HashMap();
        parameter.put("SUBREPORT_DIR",(request.getSession().getServletContext().getRealPath("/reportTemplate/detail")+"/"));
        parameter.put("whereWeightLevel",whereWeightLevel);
        parameter.put("whereProvince",whereProvince);
        parameter.put("whereCity",whereCity);
        parameter.put("whereArea",whereArea);
        parameter.put("whereCranevariety",whereCranevariety);
        parameter.put("whereUnitAddress",whereUnitAddress);
        parameter.put("whereRiskvalue",whereRiskvalue);
        platformReport.getMapToExportReport(reportTemplate,parameter,type,request,response, "report");
/*       System.out.print(data+"..........");*/
        return JsonResultUtils
                .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Path("/showChinaChart")
    @GET
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    public String showChinaChart(){
        //展示全国各地区风险图
        String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/chart/chinachart.jasper");
        Map parameter=new HashMap();
        parameter.put("SUBREPORT_DIR",(request.getSession().getServletContext().getRealPath("/reportTemplate/chart")+"/"));
        platformReport.getMapToExportReport(reportTemplate,parameter,"html",request,response,"test");
/*       System.out.print(data+"..........");*/
        return JsonResultUtils
                .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Path("/showProvinceRiskValue")
    @POST
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    public String showProvinceRiskValue(@FormParam("province") String province){
        //展示某个省各个市的风险分布
        String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/reportAnalysis/getCityRiskValueByProvince.jasper");
        Map parameter=new HashMap();
        parameter.put("province",province);
        parameter.put("SUBREPORT_DIR",(request.getSession().getServletContext().getRealPath("/reportTemplate/reportAnalysis")+"/"));
        /*System.out.print(province);*/
        platformReport.getMapToExportReport(reportTemplate,parameter,"html",request,response,"test");
/*       System.out.print(data+"..........");*/
        return JsonResultUtils
                .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Path("/showCityRiskValueByProvince")
    @POST
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    public String showCityRiskValueByProvince(@FormParam("province") String province,@FormParam("city") String city){
        //展示某个市各个区域的风险分布
        String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/reportAnalysis/getAreaRiskValueByProvinceAndCity.jasper");
        System.out.print(reportTemplate);
        Map parameter=new HashMap();
        parameter.put("province",province);
        parameter.put("city",city);
        parameter.put("SUBREPORT_DIR",(request.getSession().getServletContext().getRealPath("/reportTemplate/reportAnalysis")+"/"));
        platformReport.getMapToExportReport(reportTemplate,parameter,"html",request,response,"test");
        return JsonResultUtils
                .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Path("/showAreaRiskValueByProvinceCityArea")
    @POST
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    public String showAreaRiskValueByProvinceCityArea(@FormParam("province") String province,@FormParam("city") String city,@FormParam("area") String area){
        //展示某个区域各个单位的风险分布
        String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/reportAnalysis/getUnitaddressRiskValueByProvinceCityArea.jasper");
        Map parameter=new HashMap();
        parameter.put("province",province);
        parameter.put("city",city);
        parameter.put("area",area);
        parameter.put("SUBREPORT_DIR",(request.getSession().getServletContext().getRealPath("/reportTemplate/reportAnalysis")+"/"));
        /*System.out.print(province);*/
        platformReport.getMapToExportReport(reportTemplate,parameter,"html",request,response,"test");
/*       System.out.print(data+"..........");*/
        return JsonResultUtils
                .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Path("/exportChartReport/{data}")
    @GET
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    public String exportChartReport(@PathParam("data") String data)
    {
        //根据传入的data打印不同格式的报表
        /*data=pdf,湖北,武汉市,江岸区*/
        String[] datalist=data.split(",");
        String type=datalist[0].split("=")[1];
        String province=datalist[1];
        String city=datalist[2];
        String area=datalist[3];
        Map parameter=new HashMap();
        if(!(province.trim()).equals("")&&!province.equals("请选择"))
        {
            if(!(city.trim()).equals("")&&!city.equals("请选择"))
            {
                if(!(area.trim()).equals("")&&!area.equals("请选择"))
                {
                    String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/reportAnalysis/getUnitaddressRiskValueByProvinceCityArea.jasper");
                    parameter.put("province",province);
                    parameter.put("city",city);
                    parameter.put("area",area);
                    parameter.put("SUBREPORT_DIR",(request.getSession().getServletContext().getRealPath("/reportTemplate/reportAnalysis")+"/"));
                    platformReport.getMapToExportReport(reportTemplate,parameter,type,request,response,"test");
                    return JsonResultUtils
                            .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
                }
                else
                {
                    String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/reportAnalysis/getAreaRiskValueByProvinceAndCity.jasper");
                    parameter.put("province",province);
                    parameter.put("city",city);
                    parameter.put("SUBREPORT_DIR",(request.getSession().getServletContext().getRealPath("/reportTemplate/reportAnalysis")+"/"));
                    platformReport.getMapToExportReport(reportTemplate,parameter,type,request,response,"test");
                    return JsonResultUtils
                            .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
                }
            }
            else
            {
                String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/reportAnalysis/getCityRiskValueByProvince.jasper");
                parameter.put("SUBREPORT_DIR",(request.getSession().getServletContext().getRealPath("/reportTemplate/reportAnalysis")+"/"));
                parameter.put("province",province);
                platformReport.getMapToExportReport(reportTemplate,parameter,type,request,response,"test");
                return JsonResultUtils
                        .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
            }
        }
        else
        {
            String reportTemplate=request.getSession().getServletContext().getRealPath("/reportTemplate/chart/chinachart.jasper");
            parameter.put("SUBREPORT_DIR",(request.getSession().getServletContext().getRealPath("/reportTemplate/chart")+"/"));
            platformReport.getMapToExportReport(reportTemplate,parameter,type,request,response,"test");
            return JsonResultUtils
                    .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
    }

}
