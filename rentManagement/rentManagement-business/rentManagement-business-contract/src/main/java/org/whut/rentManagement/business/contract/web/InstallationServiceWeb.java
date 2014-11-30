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
import org.whut.rentManagement.business.contract.entity.Installation;
import org.whut.rentManagement.business.contract.entity.InstallationDevice;
import org.whut.rentManagement.business.contract.service.InstallationDeviceService;
import org.whut.rentManagement.business.contract.service.InstallationService;
import org.whut.rentManagement.business.device.service.DeviceService;

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
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Aaron
 * Date: 14-10-12
 * Time: 下午4:36
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/installation")
public class InstallationServiceWeb {

    private static final PlatformLogger logger = PlatformLogger.getLogger(InstallationServiceWeb.class);

    @Autowired
    InstallationService installationservice;

    @Autowired
    InstallationDeviceService installationDeviceService;

    @Autowired
    DeviceService deviceService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        long appId = UserContext.currentUserAppId();
        List<Map<String,String>> list=installationservice.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("jsonString") String jsonString){
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }

        Installation installation = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Installation.class);
        if(installation.getDeviceId()==null||installation.getDeviceId().trim().equals("")||installation.getContractId()==0||installation.getType()==null
                ||installation.getInstallMan()==null||installation.getInstallMan().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }

        installation.setAppId(UserContext.currentUserAppId());
        installation.setInstallTime(new Date());

        //添加安装的设备明细
        if(installation.getDeviceId()!=null&&!installation.getDeviceId().equals("")){
            String[] deviceList = installation.getDeviceId().split(",");
            Set set = new TreeSet();
            InstallationDevice installationDevice;
            ArrayList<InstallationDevice> installationDeviceList = new ArrayList<InstallationDevice>();
            for(String deviceToTransport:deviceList){
                if(!set.contains(deviceToTransport)&&!deviceToTransport.trim().equals("")){
                    installationDevice = new InstallationDevice();
                    installationDevice.setDeviceId(Long.parseLong(deviceToTransport));
                    installationDeviceList.add(installationDevice);
                }
                set.add(deviceToTransport);
            }

            ArrayList<Long> mainDeviceIdList = deviceService.findMainDeviceList(UserContext.currentUserAppId(),new ArrayList<String>(set));
            if(mainDeviceIdList.size()==0){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"未提供主设备编号！");
            }else if(mainDeviceIdList.size()>1){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"提供多个主设备编号！");
            }else if(mainDeviceIdList.size()==1){
                installationservice.add(installation);
                for(InstallationDevice id:installationDeviceList){
                    id.setInstallationId(installation.getId());
                    installationDeviceService.add(id);
                }
                deviceService.installDevice(UserContext.currentUserAppId(),mainDeviceIdList.get(0),new ArrayList<String>(set));
                return  JsonResultUtils.getObjectResultByStringAsDefault(installation.getId(),JsonResultUtils.Code.SUCCESS);
            }
        }

        return  JsonResultUtils.getObjectResultByStringAsDefault(installation.getId(), JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString")String jsonString)  {
        Installation installation = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Installation.class);
        if ("".equals(installation.getInstallStatus().trim())){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }

        installation.setAppId(UserContext.currentUserAppId());
        installationservice.update(installation);
        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"添加成功!");

    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString")String jsonString) {
        Installation installation = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Installation.class);
        InstallationDevice  installationDevice = new InstallationDevice();
        installationDevice.setInstallationId(installation.getId());
        installationDeviceService.deleteByInstallationId(installationDevice);
        installationservice.delete(installation);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/info")
    @POST
    public String info(@FormParam("installationId") String installationId){
        if(installationId==null||installationId.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        Map<String,Object> condition = new HashMap<String, Object>();
        condition.put("installationId",Long.parseLong(installationId));
        condition.put("appId",UserContext.currentUserAppId());
        Map<String,Object> installationInfo = installationservice.getInfo(condition);
        List<Map<String,Object>> deviceList = installationDeviceService.listByInstallationId(condition);
        installationInfo.put("deviceList",deviceList);
        return  JsonResultUtils.getObjectResultByStringAsDefault(installationInfo,JsonResultUtils.Code.SUCCESS);
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
        List<Map<String,String>> list=installationservice.findByCondition(condition);
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

            long installationId = Long.parseLong(multipartRequest.getParameter("installationId"));
            long appId = UserContext.currentUserAppId();

            String rentImgRootPath =  FundamentalConfigProvider.get("rentManagement.img.root.path") ;
            String rentImgRelativePath =  FundamentalConfigProvider.get("rentManagement.img.relative.path") ;
            String imageDir = rentImgRootPath + rentImgRelativePath+"/"+appId+"/installation";
            String image = rentImgRelativePath+"/"+appId+"/installation/" + installationId +"_"+ filename;
            String imagePath = imageDir + "/" + installationId +"_"+ filename;

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
                Installation installation = new Installation();
                installation.setId(installationId);
                installation.setAppId(UserContext.currentUserAppId());
                installation.setImage(image);
                installationservice.update(installation);
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
        List<Map<String,Object>> list=installationservice.getListByContractId(appId, contractId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

}
