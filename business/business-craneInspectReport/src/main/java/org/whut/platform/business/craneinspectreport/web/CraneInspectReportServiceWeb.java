package org.whut.platform.business.craneinspectreport.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.craneinspectreport.service.CraneInspectReportService;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.fileupload.FileInfo;
import org.whut.platform.fundamental.fileupload.FileService;
import org.whut.platform.fundamental.fileupload.MultipartRequestParser;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
/**
 * Created with IntelliJ IDEA.
 * User: zhuzhenhua
 * Date: 14-3-17
 * Time: 下午10:40
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/craneinspectreport")
public class CraneInspectReportServiceWeb {
    private static PlatformLogger logger = PlatformLogger.getLogger(CraneInspectReportServiceWeb.class);
    @Autowired
    private CraneInspectReportService craneInspectReportService;
    private MultipartRequestParser multipartRequestParser=new MultipartRequestParser();
    @Produces(MediaType.MULTIPART_FORM_DATA)
    @Path("/upload")
    @POST
    public String upload(@Context HttpServletRequest request){
        if (request == null) {
            return JsonResultUtils
                    .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
        try {
            request.setCharacterEncoding("UTF-8");
            int uploadMaxSize= Integer.parseInt(FundamentalConfigProvider.get("uploadMaxSize"));
            FileService  fileService=new FileService("xls");
            FileInfo fileInfo=new FileInfo();
            fileInfo=parseRequst(request,multipartRequestParser,fileService,uploadMaxSize);
            if(fileInfo.getName()==null){
                //文件名为空
            }else{
                craneInspectReportService.upload(fileInfo.getInputStream(),fileInfo.getName());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
        // 新增操作时，返回操作状态和状态码给客户端，数据区是为空的
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
      public FileInfo parseRequst(@Context HttpServletRequest request,MultipartRequestParser multipartRequestParser,FileService fileService,int uploadMaxSize){
               FileInfo fileInfo=null;
               try{
               fileInfo=multipartRequestParser.parseRequest(request,uploadMaxSize,fileService);
               }catch (Exception e){
                   e.printStackTrace();
               }
             return fileInfo;
      }
}
