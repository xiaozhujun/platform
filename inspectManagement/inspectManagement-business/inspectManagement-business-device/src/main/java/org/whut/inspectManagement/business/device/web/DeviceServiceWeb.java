package org.whut.inspectManagement.business.device.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.whut.inspectManagement.business.device.entity.Device;
import org.whut.inspectManagement.business.device.entity.SubDevice;
import org.whut.inspectManagement.business.device.service.DeviceService;
import org.whut.inspectManagement.business.device.service.DeviceTypeService;
import org.whut.platform.business.user.entity.User;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.business.user.service.UserService;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-15
 * Time: 下午3:42
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/device")
public class DeviceServiceWeb {

    @Autowired
    DeviceTypeService deviceTypeService;
    @Autowired
    DeviceService deviceService;
    @Autowired
    UserService userService;
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,@FormParam("number") String number,@FormParam("description")String description,
                     @FormParam("deviceTypeId")long deviceTypeId
                      ){
        if(name==null||number.equals("")||deviceTypeId==0||name.equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }

        long appId= UserContext.currentUserAppId();
         Long id;

        try{
               id=deviceService.getIdByNumber(number,appId);
        }
        catch(Exception e){
              id=null;
        }
        if(id==null) {
            Device device=new Device();
            device.setName(name);
            device.setNumber(number);
            device.setDescription(description);
            device.setAppId(appId);
            device.setDeviceTypeId(deviceTypeId);
            deviceService.add(device);
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"添加成功!");
        }
        else{
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"已存在该设备!");
        }
    }


    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString)
    {
        SubDevice subDevice = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubDevice.class);
        if(subDevice.getName()==null||subDevice.getAppId()==0||subDevice.getName().equals("")||subDevice.getDeviceTypeName().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }
        Device device=new Device();
        device.setId(subDevice.getId());
        device.setName(subDevice.getName());
        device.setDescription(subDevice.getDescription());
        device.setNumber(subDevice.getNumber());
        device.setAppId(subDevice.getAppId());
        device.setDeviceTypeId(deviceTypeService.getIdByName(subDevice.getDeviceTypeName(),subDevice.getAppId()));
        deviceService.update(device);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/get")
    @POST
    public String add(@FormParam("deviceId") String deviceId){
        if(deviceId==null||deviceId.equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"设备编号不能为空!");
        }

        long id = Long.parseLong(deviceId);
        Device device = deviceService.get(id);
        return JsonResultUtils.getObjectResultByStringAsDefault(device,JsonResultUtils.Code.SUCCESS);

    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString)
    {
        Device device=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Device.class);
        deviceService.delete(device);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }


    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @GET
    public String list(){

        long appId=UserContext.currentUserAppId();
        List<Device> list=deviceService.getListByAppId(appId);
        List<SubDevice> subDevicesList=new ArrayList<SubDevice>();
        for (Device device:list){
            SubDevice subDevice=new SubDevice();
            subDevice.setName(device.getName());
            subDevice.setNumber(device.getNumber());
            subDevice.setDescription(device.getDescription());
            subDevice.setAppId(device.getAppId());
            subDevice.setId(device.getId());
            subDevice.setDeviceTypeName(deviceTypeService.getNameById(device.getDeviceTypeId()));
            subDevice.setImage(device.getImage());
            subDevicesList.add(subDevice);
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(subDevicesList, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/getListByCondition")
    @POST
    public String getId(@FormParam("deviceType") String deviceType,@FormParam("deviceNumber") String deviceNumber,@FormParam("tagName") String tagName){
        long addId= UserContext.currentUserAppId();
        if((deviceType==null||deviceType.equals(""))&&(deviceNumber==null||deviceNumber.equals(""))&&(tagName==null||tagName.equals(""))) {
            deviceType=null;
            deviceNumber=null;
            tagName=null;
        }
        else if((deviceType!=null||!deviceType.equals(""))&&(deviceNumber==null||deviceNumber.equals(""))&&(tagName==null||tagName.equals(""))){
             deviceNumber=null;
             tagName=null;
        }
        else if((deviceType==null||deviceType.equals(""))&&(deviceNumber!=null||!deviceNumber.equals(""))&&(tagName==null||tagName.equals(""))){
            deviceType=null;
            tagName=null;
        }
        else if((deviceType==null||deviceType.equals(""))&&(deviceNumber==null||deviceNumber.equals(""))&&(tagName!=null||!tagName.equals(""))){
            deviceType=null;
            deviceNumber=null;
        }
        else if((deviceType!=null||!deviceType.equals(""))&&(deviceNumber!=null||!deviceNumber.equals(""))&&(tagName==null||tagName.equals(""))){
           tagName=null;
        }
        else if((deviceType!=null||!deviceType.equals(""))&&(deviceNumber==null||deviceNumber.equals(""))&&(tagName!=null||!tagName.equals(""))){
            deviceNumber=null;
        }
        else if((deviceType==null||deviceType.equals(""))&&(deviceNumber!=null||!deviceNumber.equals(""))&&(tagName!=null||!tagName.equals(""))){
               deviceType=null;
        }
        List<Map<String,String>> mapList = deviceService.getListByCondition(deviceType,deviceNumber,tagName,addId);
        return JsonResultUtils.getObjectResultByStringAsDefault(mapList, JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/query")
    @POST
    public String listEmployeeByNameDepartmentAndRole(@FormParam("name") String name,@FormParam("number") String number,@FormParam("deviceTypeName") String deviceTypeName){
      if((name==null||name.equals(""))&&(number==null||number.equals(""))&&(deviceTypeName==null||deviceTypeName.equals(""))){
          name="";
          number="";
          deviceTypeName=null;
      }
        else if ((name!=null||!name.equals(""))&&(number==null||number.equals(""))&&(deviceTypeName==null||deviceTypeName.equals(""))){
          number="";
          deviceTypeName=null;
      }
        else if((name==null||name.equals(""))&&(number!=null||!number.equals(""))&&(deviceTypeName==null||deviceTypeName.equals(""))){
          name="";
          deviceTypeName=null;
      }
       else if((name==null||name.equals(""))&&(number==null||number.equals(""))&&(deviceTypeName!=null||!deviceTypeName.equals(""))){
            name="";
            number="";

        }
       else if((name!=null||!name.equals(""))&&(number!=null||!number.equals(""))&&(deviceTypeName==null||deviceTypeName.equals(""))){

            deviceTypeName=null;
        }
       else if((name!=null||!name.equals(""))&&(number==null||number.equals(""))&&(deviceTypeName!=null||!deviceTypeName.equals(""))){

            number="";

        }
        else if((name==null||name.equals(""))&&(number!=null||!number.equals(""))&&(deviceTypeName!=null||!deviceTypeName.equals(""))){
            name="";
        }

        long appId = UserContext.currentUserAppId();
        name="%"+name+"%";
        number="%"+number+"%";
        Long  deviceTypeId=null;
        if(deviceTypeName!=null){
           deviceTypeId=deviceTypeService.getIdByName(deviceTypeName,appId);
        }
        List<Device> list=new ArrayList<Device>();
        if(deviceTypeId!=null){
        list=deviceService.getInfoByCondition(name,number,deviceTypeId,appId);
        }
        List<SubDevice> subDevicesList=new ArrayList<SubDevice>();
        for (Device device:list){
            SubDevice subDevice=new SubDevice();
            subDevice.setName(device.getName());
            subDevice.setNumber(device.getNumber());
            subDevice.setDescription(device.getDescription());
            subDevice.setAppId(device.getAppId());
            subDevice.setId(device.getId());
            subDevice.setDeviceTypeName(deviceTypeService.getNameById(device.getDeviceTypeId()));
            subDevicesList.add(subDevice);
        }
        if(subDevicesList.toArray().length==0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "查询不到结果!");
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(subDevicesList, JsonResultUtils.Code.SUCCESS);
    }

    //上传用户图片
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/uploadImage")
    @POST
    public String uploadImage(@Context HttpServletRequest request){
        if(request==null){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        MultipartFile file = multipartRequest.getFile("filename");
        String filename = file.getOriginalFilename();
        String[] temp = filename.split("\\.");
        String suffix = temp[temp.length-1];
        String deviceId = multipartRequest.getParameter("deviceId");

        //获得用户图片路径
        String deviceImgRootPath =  FundamentalConfigProvider.get("device.img.root.path") ;
        String deviceImgRelativePath =  FundamentalConfigProvider.get("device.img.relative.path") ;
        String userName = UserContext.currentUserName();
        long appId = UserContext.currentUserAppId();
        Device device = deviceService.get(Long.parseLong(deviceId));
        User currentUser = userService.getById(UserContext.currentUserId());
        if(device==null){
            JsonResultUtils.getObjectResultByStringAsDefault("设备不存在！",JsonResultUtils.Code.ERROR);
        }

        String deviceImagePath =  deviceImgRootPath + deviceImgRelativePath+"/"+appId+"/"+deviceId+"."+suffix;
        String deviceImageWebPath = deviceImgRelativePath+"/"+appId+"/"+deviceId+"."+suffix;



        //如果文件存在则删除
        File deviceImageFile = new File(deviceImagePath);
        String oldImagePath = device.getImage();
        if(oldImagePath!=null){
            File oldImage = new File(deviceImgRootPath+oldImagePath);
            if(oldImage.exists()){
                oldImage.delete();
            }
        }
        if(deviceImageFile.exists()){
            deviceImageFile.delete();
        }else{
            File imageDir = new File(deviceImgRootPath+"/"+deviceImgRelativePath+"/"+appId);
            if(!imageDir.exists()){
                imageDir.mkdirs();
            }
        }

        //写用户图片文件到指定路径
        try {
            file.transferTo(deviceImageFile);
            Device device1 = new Device();
            device1.setId(device.getId());
            device1.setImage(deviceImageWebPath);
            deviceService.updateImage(device1);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        // 新增操作时，返回操作状态和状态码给客户端，数据区是为空的
        return JsonResultUtils.getObjectResultByStringAsDefault(deviceImageWebPath,JsonResultUtils.Code.SUCCESS);
    }

}
