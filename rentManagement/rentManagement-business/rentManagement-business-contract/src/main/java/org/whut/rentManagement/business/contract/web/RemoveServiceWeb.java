package org.whut.rentManagement.business.contract.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.business.user.service.UserService;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.contract.entity.Remove;
import org.whut.rentManagement.business.contract.entity.RemoveDevice;
import org.whut.rentManagement.business.contract.service.RemoveDeviceService;
import org.whut.rentManagement.business.contract.service.RemoveService;
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
import java.text.ParseException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-14
 * Time: 下午5:09
 * To change this template use File | Settings | File Templates.
 */

@Component
@Path("/remove")
public class RemoveServiceWeb{

    private static final PlatformLogger logger = PlatformLogger.getLogger(RemoveServiceWeb.class);

    @Autowired
    RemoveService removeService;
    @Autowired
    RemoveDeviceService removeDeviceService;

    @Autowired
    DeviceService deviceService;

    @Autowired
    UserService userService;
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        long appId= UserContext.currentUserAppId();
        List<Map<String,String>> list=removeService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("jsonString") String jsonString){
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }

        Remove remove = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Remove.class);
        if(remove.getContractId()==0||remove.getRemoveMan()==null||remove.getRemoveMan().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }

        remove.setAppId(UserContext.currentUserAppId());
        remove.setCreateTime(new Date());

        //添加拆卸的设备明细
        if(remove.getDeviceId()!=null&&!remove.getDeviceId().equals("")){
            String[] deviceList = remove.getDeviceId().split(",");
            Set set = new TreeSet();
            RemoveDevice removeDevice;
            ArrayList<RemoveDevice> removeDeviceList = new ArrayList<RemoveDevice>();
            for(String deviceToTransport:deviceList){
                if(!set.contains(deviceToTransport)&&!deviceToTransport.trim().equals("")){
                    removeDevice = new RemoveDevice();
                    removeDevice.setDeviceId(Long.parseLong(deviceToTransport));
                    removeDeviceList.add(removeDevice);
                }
                set.add(deviceToTransport);
            }

            removeService.add(remove);
            for(RemoveDevice rd:removeDeviceList){
                rd.setRemoveId(remove.getId());
                removeDeviceService.add(rd);
            }
            deviceService.removeDevice(UserContext.currentUserAppId(),new ArrayList<String>(set));
            return  JsonResultUtils.getObjectResultByStringAsDefault(remove.getId(),JsonResultUtils.Code.SUCCESS);
        }

        return  JsonResultUtils.getObjectResultByStringAsDefault(remove.getId(), JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString")String jsonString) throws ParseException {
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        Remove remove = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Remove.class);
        if(remove.getId()==null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }

        int result=removeService.update(remove);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS) ;
        }
        else {
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }


    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getListByContractId")
    @POST
    public String getListByContractId(@FormParam("contractId") String contractId ){
        long appId= UserContext.currentUserAppId();
        List<Map<String,String>> list=removeService.getListByContractId(Long.parseLong(contractId));
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getListByDeviceId")
    @POST
    public String getListByDeviceId(@FormParam("removeDeviceId")long removeDeviceId){
        long appId= UserContext.currentUserAppId();
        List<Map<String,String>>list =removeService.getListByDeviceId(removeDeviceId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/delete")
    @POST
    public  String delete(@FormParam("jsonString") String jsonString){
        Remove remove= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Remove.class);
        RemoveDevice removeDevice = new RemoveDevice();
        removeDevice.setRemoveId(remove.getId());
        removeDeviceService.deleteByRemoveId(removeDevice);
        int result=removeService.delete(remove);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else {
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/info")
    @POST
    public String info(@FormParam("removeId") String removeId){
        if(removeId==null||removeId.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        Map<String,Object> condition = new HashMap<String, Object>();
        condition.put("removeId",Long.parseLong(removeId));
        condition.put("appId",UserContext.currentUserAppId());
        Map<String,Object> removeInfo = removeService.getInfo(condition);
        List<Map<String,Object>> deviceList = removeDeviceService.listByRemoveId(condition);
        removeInfo.put("deviceList",deviceList);
        return  JsonResultUtils.getObjectResultByStringAsDefault(removeInfo,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
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
        List<Map<String,String>> list=removeService.getRemoveList(condition);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
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

            long removeId = Long.parseLong(multipartRequest.getParameter("removeId"));
            long appId = UserContext.currentUserAppId();

            String rentImgRootPath =  FundamentalConfigProvider.get("rentManagement.img.root.path") ;
            String rentImgRelativePath =  FundamentalConfigProvider.get("rentManagement.img.relative.path") ;
            String imageDir = rentImgRootPath + rentImgRelativePath+"/"+appId+"/remove";
            String image = rentImgRelativePath+"/"+appId+"/remove/" + removeId +"_"+ filename;
            String imagePath = imageDir + "/" + removeId +"_"+ filename;

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
                Remove remove = new Remove();
                remove.setId(removeId);
                remove.setAppId(UserContext.currentUserAppId());
                remove.setImage(image);
                removeService.update(remove);
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
        List<Map<String,Object>> list=removeService.getListByContractId(appId, contractId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }


}
