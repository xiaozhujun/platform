package org.whut.rentManagement.business.contract.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.contract.entity.PreBury;
import org.whut.rentManagement.business.contract.service.PreBuryService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Aaron
 * Date: 14-10-18
 * Time: 下午4:37
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/preBury")
public class PreBuryServiceWeb {

    private static final PlatformLogger logger = PlatformLogger.getLogger(PreBuryServiceWeb.class);

    @Autowired
    PreBuryService preBuryService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        long appId = UserContext.currentUserAppId();
        List<Map<String,Object>> list=preBuryService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }


    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("jsonString")String jsonString){
        if(jsonString==null||"".equals(jsonString.trim().equals(""))){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }
        PreBury preBury = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,PreBury.class);
        if(preBury.getContractId()==null||preBury.getPreBuryMan()==null
                ||preBury.getPreBuryStatus()==null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }
        preBury.setAppId(UserContext.currentUserAppId());
        preBury.setPreBuryTime(new Date());
        preBuryService.add(preBury);
        return  JsonResultUtils.getObjectResultByStringAsDefault(preBury.getId(),JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString")String jsonString)  {
        if(jsonString==null||"".equals(jsonString.trim().equals(""))){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }
        PreBury preBury = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,PreBury.class);
        if(preBury.getPreBuryStatus()==null||"".equals(preBury.getPreBuryStatus().trim())){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "预埋状态不能为空!");
        }
        preBury.setAppId(UserContext.currentUserAppId());
        preBuryService.update(preBury);
        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"更新成功!");

    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString")String jsonString) {
        PreBury preBury = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,PreBury.class);
        preBuryService.delete(preBury);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/getPreburyList")
    @POST
    public String getPreburyList(@FormParam("user")String user,@FormParam("sTime")String sTime,@FormParam("eTime")String eTime){
        Map<String,Object> condition = new HashMap<String, Object>();
        if(user!=null&&!user.equals("")){
            condition.put("user",user);
        }
        if(sTime!=null&&!sTime.equals("")){
            condition.put("startTime",sTime+" 00:00:00");
        }
        if(eTime!=null&&!eTime.equals("")){
            condition.put("endTime",eTime+" 59:59:59");
        }
        condition.put("appId", UserContext.currentUserAppId());
        List<Map<String,Object>> list=preBuryService.getPreburyList(condition);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/findByCondition")
    @POST
    public String findByCondition(@FormParam("user")String user,@FormParam("device")String device,@FormParam("sTime")String sTime,@FormParam("eTime")String eTime){
        Map<String,Object> condition = new HashMap<String, Object>();
        if(user!=null&&!user.equals("")){
            condition.put("user",user);
        }
        if(device!=null&&!device.equals("")){
            condition.put("device",device);
        }
        if(sTime!=null&&!sTime.equals("")){
            condition.put("startTime",sTime+" 00:00:00");
        }
        if(eTime!=null&&!eTime.equals("")){
            condition.put("endTime",eTime+" 59:59:59");
        }
        condition.put("appId", UserContext.currentUserAppId());
        List<Map<String,Object>> list=preBuryService.findByCondition(condition);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/upload")
    @POST
    public String upload(@Context HttpServletRequest request
    ) {
        if (request == null) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        try {
            MultipartFile file = multipartRequest.getFile("filename");
            String filename = file.getOriginalFilename();
            String [] s = filename.split("\\.");

            long preBuryId = Long.parseLong(multipartRequest.getParameter("preBuryId"));
            long appId = UserContext.currentUserAppId();

            String rentImgRootPath =  FundamentalConfigProvider.get("rentManagement.img.root.path") ;
            String rentImgRelativePath =  FundamentalConfigProvider.get("rentManagement.img.relative.path") ;
            String imageDir = rentImgRootPath + rentImgRelativePath+"/"+appId+"/preBury";
            String image = rentImgRelativePath+"/"+appId+"/preBury/" + preBuryId +"_"+ filename;
            String imagePath = imageDir + "/" + preBuryId +"_"+ filename;

            //判断文件夹是否存在，不存在则创建
            File dir = new File(imageDir);
            if(!dir.exists()){
                dir.mkdirs();
            }
            File imageFile = new File(imagePath);
            if(imageFile.exists()){
                imageFile.delete();
            }
            imageFile.createNewFile();


            if (s[s.length - 1].equals("jpg")) {
                InputStream inputStream = file.getInputStream();
                byte[] bs = new byte[1024 * 2];
                int len;
                OutputStream outputStream = new FileOutputStream(imagePath);
                while ((len = inputStream.read(bs)) != -1) {
                    outputStream.write(bs,0,len);
                }
                outputStream.close();
                inputStream.close();
                PreBury preBury = new PreBury();
                preBury.setId(preBuryId);
                preBury.setAppId(UserContext.currentUserAppId());
                preBury.setImage(image);
                preBuryService.update(preBury);
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
    @Path("/listByContractId")
    @POST
    public String listByContractId(@FormParam("contractId") Long contractId){
        long appId = UserContext.currentUserAppId();
        List<Map<String,Object>> list=preBuryService.getListByContractId(appId, contractId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }



}
