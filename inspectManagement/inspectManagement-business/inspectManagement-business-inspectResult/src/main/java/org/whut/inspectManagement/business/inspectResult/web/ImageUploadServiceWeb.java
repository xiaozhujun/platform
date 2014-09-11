package org.whut.inspectManagement.business.inspectResult.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.whut.inspectManagement.business.inspectResult.entity.ImageUpload;
import org.whut.inspectManagement.business.inspectResult.service.ImageUploadService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-8-12
 * Time: 上午9:50
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/imageUpload")
public class ImageUploadServiceWeb {
    private static PlatformLogger logger = PlatformLogger.getLogger(InspectTableRecordServiceWeb.class);
    @Autowired
    private ImageUploadService imageUploadService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/upload")
    @POST
    public String upload(@Context HttpServletRequest request
//            ,@FormParam("tableRecordId")long tableRecordId,@FormParam("itemRecordId")long itemRecordId,@FormParam("itemId")long itemId
    ) {
        if (request == null) {
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        try {
            MultipartFile file = multipartRequest.getFile("filename");
            String filename = file.getOriginalFilename();
            String [] s = filename.split("\\.");
//            String realPath = request.getSession().getServletContext().getRealPath("/");
//            System.out.println(request.getSession().getServletContext().getRealPath("../upload"));
//            String image = realPath+"\\test\\"+filename;

            long itemId = Long.parseLong(multipartRequest.getParameter("itemId"));
            long itemRecordId = Long.parseLong(multipartRequest.getParameter("itemRecordId"));
            long tableRecordId = Long.parseLong(multipartRequest.getParameter("tableRecordId"));

            String userImgRootPath =  FundamentalConfigProvider.get("inspectException.img.root.path") ;
            String userImgRelativePath =  FundamentalConfigProvider.get("inspectException.img.relative.path") ;
            String image = userImgRelativePath + "/" + tableRecordId +"_"+ filename;
            String imagePath = userImgRootPath + image;


            long appId = UserContext.currentUserAppId();
            long id = 0;
            try {
                id = imageUploadService.getIdByItemIdAndItemRecordIdAndTableRecordIdAndAppIdAndPath(itemId,itemRecordId,tableRecordId,appId,image);
            } catch (Exception e) {
                id = 0;
            }
            if (id != 0) {
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"该图片已上传");
            }
            if (s[s.length - 1].equals("jpg")) {
                InputStream inputStream = file.getInputStream();
                byte[] bs = new byte[1024 * 2];
                int len;
//                OutputStream outputStream = new FileOutputStream("D://a.jpg");
                OutputStream outputStream = new FileOutputStream(imagePath);
                while ((len = inputStream.read(bs)) != -1) {
                    outputStream.write(bs,0,len);
                }
                outputStream.close();
                inputStream.close();
                ImageUpload imageUpload = new ImageUpload();
                Date date = new Date();
                imageUpload.setAppId(appId);
                imageUpload.setCreateTime(date);
                imageUpload.setImage(image);
                imageUpload.setItemId(itemId);
                imageUpload.setItemRecordId(itemRecordId);
                imageUpload.setTableRecordId(tableRecordId);
                imageUploadService.add(imageUpload);
            }
            else {
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "图片格式错误，请上传JPG格式文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getImageByNames")
    @POST
    public String getImageByNames(@FormParam("userName")String userName,@FormParam("deviceName")String deviceName,@FormParam("itemRecordId")long itemRecordId,@FormParam("itemId")long itemId){
        String image = "";
        long appId = UserContext.currentUserAppId();
        try {
            image = imageUploadService.getImageByNames(userName,deviceName,itemRecordId,itemId,appId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (image == null) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "该图片不存在");
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(image, JsonResultUtils.Code.SUCCESS);
    }
}
