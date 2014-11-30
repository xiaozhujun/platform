package org.whut.rentManagement.business.transport.web;

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
import org.whut.rentManagement.business.device.service.DeviceService;
import org.whut.rentManagement.business.transport.entity.Transport;
import org.whut.rentManagement.business.transport.entity.TransportDevice;
import org.whut.rentManagement.business.transport.service.TransportDeviceService;
import org.whut.rentManagement.business.transport.service.TransportService;

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
@Path("/transport")
public class TransportServiceWeb {

    private static PlatformLogger logger = PlatformLogger.getLogger(TransportServiceWeb.class);

    @Autowired
    TransportService transportService;

    @Autowired
    TransportDeviceService transportDeviceService;

    @Autowired
    DeviceService deviceService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        long appId = UserContext.currentUserAppId();
        List<Map<String,String>> list=transportService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("jsonString") String jsonString){
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        Transport transport = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Transport.class);
        if(transport.getDriver()==null||transport.getDriver().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        long appId= UserContext.currentUserAppId();
        transport.setAppId(UserContext.currentUserAppId());
        transport.setCreateTime(new Date());
        transport.setHandler(UserContext.currentUserName());

        //添加运输的设备明细
        if(transport.getDeviceId()!=null&&!transport.getDeviceId().equals("")){
            String[] deviceList = transport.getDeviceId().split(",");
            Set set = new TreeSet();
            TransportDevice transportDevice;
            ArrayList<TransportDevice> transportDeviceList = new ArrayList<TransportDevice>();
            for(String deviceToTransport:deviceList){
                if(!set.contains(deviceToTransport)&&!deviceToTransport.trim().equals("")){
                    transportDevice = new TransportDevice();
                    transportDevice.setDeviceId(Long.parseLong(deviceToTransport));
                    transportDeviceList.add(transportDevice);
                }
                set.add(deviceToTransport);
            }

            transportService.add(transport);
            for(TransportDevice td:transportDeviceList){
                td.setTransportId(transport.getId());
                transportDeviceService.add(td);
            }
            deviceService.transportDevice(UserContext.currentUserAppId(),new ArrayList<String>(set));
            return  JsonResultUtils.getObjectResultByStringAsDefault(transport.getId(),JsonResultUtils.Code.SUCCESS);
        }

        return  JsonResultUtils.getObjectResultByStringAsDefault(transport.getId(), JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString")String jsonString)  {
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        Transport transport = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Transport.class);
        if(transport.getId()==0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，没有指定对象!");
        }
        transport.setAppId(UserContext.currentUserAppId());
        transportService.update(transport);
        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"添加成功!");
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString")String jsonString) {
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        Transport transport = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Transport.class);
        if(transport.getId()==0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，没有指定对象!");
        }
        TransportDevice transportDevice = new TransportDevice();
        transportDevice.setTransportId(transport.getId());
        transportDeviceService.deleteByTransportId(transportDevice);
        transportService.delete(transport);

        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/findByCondition")
    @POST
    public String getInstallList(@FormParam("user")String user,@FormParam("device")String device,@FormParam("sTime")String sTime,@FormParam("eTime")String eTime){
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
        List<Map<String,String>> list=transportService.findByCondition(condition);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/info")
    @POST
    public String info(@FormParam("transportId") String transportId){
        if(transportId==null||transportId.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        Map<String,Object> condition = new HashMap<String, Object>();
        condition.put("transportId",Long.parseLong(transportId));
        condition.put("appId",UserContext.currentUserAppId());
        Map<String,Object> transportInfo = transportService.getInfo(condition);
        List<Map<String,Object>> deviceList = transportDeviceService.listByTransportId(condition);
        transportInfo.put("deviceList",deviceList);
        return  JsonResultUtils.getObjectResultByStringAsDefault(transportInfo,JsonResultUtils.Code.SUCCESS);
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

            long transportId = Long.parseLong(multipartRequest.getParameter("transportId"));
            long appId = UserContext.currentUserAppId();

            String rentImgRootPath =  FundamentalConfigProvider.get("rentManagement.img.root.path") ;
            String rentImgRelativePath =  FundamentalConfigProvider.get("rentManagement.img.relative.path") ;
            String imageDir = rentImgRootPath + rentImgRelativePath+"/"+appId+"/transport";
            String image = rentImgRelativePath+"/"+appId+"/transport/" + transportId +"_"+ filename;
            String imagePath = imageDir + "/" + transportId +"_"+ filename;

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
                Transport transport = new Transport();
                transport.setId(transportId);
                transport.setAppId(UserContext.currentUserAppId());
                transport.setImage(image);
                transportService.update(transport);
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

}
