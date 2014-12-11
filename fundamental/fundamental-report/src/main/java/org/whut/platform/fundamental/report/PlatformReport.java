package org.whut.platform.fundamental.report;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
/**
 * Created with IntelliJ IDEA.
 * User: zhuzhenhua
 * Date: 14-3-26
 * Time: 上午9:15
 * To change this template use File | Settings | File Templates.
 */
public class PlatformReport {
          private PlatformMysqlConnector ds;
          public PlatformReport(String className,String url,String username,String password){
              ds=new PlatformMysqlConnector(className,url,username,password);
          }
         /*
         传入报表模板，一个map集合，还有导出报表的类型,导出报表
          */
        public void getMapToExportReport(String reportTemplate,Map parameters,String type,HttpServletRequest request,HttpServletResponse response,String reportName){
              try{
                  request.setCharacterEncoding("UTF-8");
                  response.setContentType("text/html;charset=UTF-8");
                  exportReportByType(reportTemplate, type, parameters, request, response,reportName);
              }catch (Exception e){
                  e.printStackTrace();
              }
        }
            /*
               传入报表模板，一个map集合，还有导出报表的类型,导出报表
                */
            public void getMapToExportReport(String reportTemplate,Map parameters,String type,HttpServletRequest request,HttpServletResponse response,String reportName,Connection connection){
                try{
                    request.setCharacterEncoding("UTF-8");
                    response.setContentType("text/html;charset=UTF-8");
                    exportReportByType(reportTemplate, type, parameters, request, response,reportName,connection);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        /*
        传入模板，类型，map集合来根据map集合，输出不同类型的报表
         */
        public void exportReportByType(String reportTemplate,String type,Map parameters,HttpServletRequest request,HttpServletResponse response,String reportName){
            File reportFile=null;
            reportFile=new File(reportTemplate);
            Connection connection=ds.getConnection();
            try{
                JasperReport jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
                if(type.equals("html")){
                    PrintWriter out=response.getWriter();
                    response.setContentType("text/html");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(reportName, "UTF-8") + ".html\"");
                    JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport, parameters, connection);
                    request.getSession().setAttribute(
                            ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,jasperPrint
                    );
                    //输出html 用JRHtmlExporter
                    JRHtmlExporter exporter=new JRHtmlExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
                    exporter.setParameter(JRExporterParameter.OUTPUT_WRITER,out);
                    exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,"image?random="+Math.random()+"&image=");
                    exporter.exportReport();
                    out.flush();
                    out.close();
                }else if(type.equals("pdf")){
                    byte[] bytes=JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, connection);
                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(reportName, "UTF-8") + ".pdf\"");
                    response.setContentLength(bytes.length);
                    ServletOutputStream outputStream=response.getOutputStream();
                    outputStream.write(bytes,0,bytes.length);
                    outputStream.flush();
                    outputStream.close();
                }else if(type.equals("excel")){
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(reportName, "UTF-8") + ".xls\"");
                    JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, parameters, connection);
                    ServletOutputStream outputStream=response.getOutputStream();
                    JRXlsExporter exporter=new JRXlsExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,outputStream);
                    exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
                    exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
                    exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
                    exporter.exportReport();
                    outputStream.flush();
                    outputStream.close();
                }else if(type.equals("word")){
                    response.setContentType("application/msword;charset=utf-8");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(reportName, "UTF-8") + ".doc\"");
                    JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, parameters, connection);
                    ServletOutputStream outputStream=response.getOutputStream();
                    JRExporter exporter=new JRRtfExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,response.getOutputStream());
                    exporter.exportReport();
                    outputStream.flush();
                    outputStream.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally{
                ds.close(connection,null,null);
            }
        }
    /*
      传入模板，类型，map集合来根据map集合，输出不同类型的报表
       */
    private void exportReportByType(String reportTemplate,String type,Map parameters,HttpServletRequest request,HttpServletResponse response,String reportName,Connection connection){
        File reportFile=null;
        reportFile=new File(reportTemplate);
        try{
            JasperReport jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
            if(type.equals("html")){
                PrintWriter out=response.getWriter();
                response.setContentType("text/html");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(reportName, "UTF-8") + ".html\"");
                JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport, parameters, connection);
                request.getSession().setAttribute(
                        ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,jasperPrint
                );
                //输出html 用JRHtmlExporter
                JRHtmlExporter exporter=new JRHtmlExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_WRITER,out);
                exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,"image?random="+Math.random()+"&image=");
                exporter.exportReport();
                out.flush();
                out.close();
            }else if(type.equals("pdf")){
                byte[] bytes=JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, connection);
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(reportName, "UTF-8") + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream=response.getOutputStream();
                outputStream.write(bytes,0,bytes.length);
                outputStream.flush();
                outputStream.close();
            }else if(type.equals("excel")){
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(reportName, "UTF-8") + ".xls\"");
                JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, parameters, connection);
                ServletOutputStream outputStream=response.getOutputStream();
                JRXlsExporter exporter=new JRXlsExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,outputStream);
                exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
                exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
                exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
                exporter.exportReport();
                outputStream.flush();
                outputStream.close();
            }else if(type.equals("word")){
                response.setContentType("application/msword;charset=utf-8");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(reportName, "UTF-8") + ".doc\"");
                JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, parameters, connection);
                ServletOutputStream outputStream=response.getOutputStream();
                JRExporter exporter=new JRRtfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,response.getOutputStream());
                exporter.exportReport();
                outputStream.flush();
                outputStream.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            ds.close(connection,null,null);
        }
    }
        /*
      传入模板，类型，map集合来根据map集合，输出不同类型的报表 ,传入一个数据源
       */
    public void exportReportByType(String reportTemplate,String type,Map parameters,HttpServletRequest request,HttpServletResponse response,String reportName,List list){
        File reportFile=null;
        reportFile=new File(reportTemplate);
        JRBeanCollectionDataSource ds=new JRBeanCollectionDataSource(list);
        try{
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            JasperReport jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
            if(type.equals("html")){
                PrintWriter out=response.getWriter();
                response.setContentType("text/html");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(reportName, "UTF-8") + ".html\"");
                JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport, parameters, ds);
                request.getSession().setAttribute(
                        ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,jasperPrint
                );
                //输出html 用JRHtmlExporter
                JRHtmlExporter exporter=new JRHtmlExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_WRITER,out);

                String imageDIR = request.getSession().getServletContext().getRealPath("d://images");
                exporter.setParameter(JRHtmlExporterParameter.IMAGES_DIR_NAME,imageDIR);//设置图片文件存放路径，此路径为服务器上的绝对路径


                exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,"image?random="+Math.random()+"&image=");
                exporter.exportReport();
                out.flush();
                out.close();
            }else if(type.equals("pdf")){
                byte[] bytes=JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, ds);
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(reportName, "UTF-8") + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream=response.getOutputStream();
                outputStream.write(bytes,0,bytes.length);
                outputStream.flush();
                outputStream.close();
            }else if(type.equals("excel")){
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(reportName, "UTF-8") + ".xls\"");
                JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, parameters, ds);
                ServletOutputStream outputStream=response.getOutputStream();
                JRXlsExporter exporter=new JRXlsExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,outputStream);
                exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
                exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
                exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
                exporter.exportReport();
                outputStream.flush();
                outputStream.close();
            }else if(type.equals("word")){
                response.setContentType("application/msword;charset=utf-8");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(reportName, "UTF-8") + ".doc\"");
                JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, parameters, ds);
                ServletOutputStream outputStream=response.getOutputStream();
                JRExporter exporter=new JRRtfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,response.getOutputStream());
                exporter.exportReport();
                outputStream.flush();
                outputStream.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
