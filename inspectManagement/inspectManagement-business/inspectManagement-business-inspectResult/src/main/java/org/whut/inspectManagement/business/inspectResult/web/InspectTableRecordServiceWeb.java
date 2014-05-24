package org.whut.inspectManagement.business.inspectResult.web;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.whut.inspectManagement.business.inspectResult.service.InspectTableRecordService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-5-13
 * Time: 下午2:12
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/inspectResult")
public class InspectTableRecordServiceWeb {
    private static PlatformLogger logger = PlatformLogger.getLogger(InspectTableRecordServiceWeb.class);

    @Autowired
    private InspectTableRecordService inspectTableRecordService;

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/upload")
    @POST
    public String upload(@Context HttpServletRequest request){
        if(request==null){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        try{
            //XML文档解析
            MultipartFile file = multipartRequest.getFile("filename");
            String filename = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            SAXReader sr = new SAXReader();
            Document document = sr.read(inputStream);
            Element root = document.getRootElement();
            // System.out.println(">>>>>>>>>"+root.getName());
            inspectTableRecordService.DomReadXml(document);

            return  JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"操作成功！");

        }
        catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }
}
