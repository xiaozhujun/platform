package org.whut.rentManagement.business.stock.web;

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
import org.whut.rentManagement.business.stock.entity.StockIn;
import org.whut.rentManagement.business.stock.entity.StockInDevice;
import org.whut.rentManagement.business.stock.service.StockInDeviceService;
import org.whut.rentManagement.business.stock.service.StockInService;

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
 * User: xiaozhujun
 * Date: 14-11-16
 * Time: 上午11:49
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/stockIn")
public class StockInServiceWeb {

    private static final PlatformLogger logger = PlatformLogger.getLogger(StockInServiceWeb.class);

    @Autowired
    StockInService stockInService;

    @Autowired
    StockInDeviceService stockInDeviceService;

    @Autowired
    DeviceService deviceService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        long appId = UserContext.currentUserAppId();
        List<Map<String,String>> list=stockInService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("jsonString") String jsonString){
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }

        StockIn stockIn = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,StockIn.class);
        if(stockIn.getDeviceId()==null||stockIn.getDeviceId().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "入库设备不能为空!");
        }

        stockIn.setAppId(UserContext.currentUserAppId());
        stockIn.setHandler(UserContext.currentUserName());
        stockIn.setCreateTime(new Date());

        //添加安装的设备明细
        if(stockIn.getDeviceId()!=null&&!stockIn.getDeviceId().equals("")){
            String[] deviceList = stockIn.getDeviceId().split(",");
            Set set = new TreeSet();
            StockInDevice stockInDevice;
            ArrayList<StockInDevice> stockInDeviceList = new ArrayList<StockInDevice>();
            for(String deviceToTransport:deviceList){
                if(!set.contains(deviceToTransport)&&!deviceToTransport.trim().equals("")){
                    stockInDevice = new StockInDevice();
                    stockInDevice.setDeviceId(Long.parseLong(deviceToTransport));
                    stockInDeviceList.add(stockInDevice);
                }
                set.add(deviceToTransport);
            }

            stockInService.add(stockIn);
            for(StockInDevice std:stockInDeviceList){
                std.setStockInId(stockIn.getId());
                stockInDeviceService.add(std);
            }
            deviceService.stockIn(UserContext.currentUserAppId(),new ArrayList<String>(set));
            return  JsonResultUtils.getObjectResultByStringAsDefault(stockIn.getId(),JsonResultUtils.Code.SUCCESS);
        }

        return  JsonResultUtils.getObjectResultByStringAsDefault(stockIn.getId(), JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString")String jsonString)  {
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        StockIn stockIn = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,StockIn.class);
        if (stockIn.getId()==0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"入库单id不能为空");
        }
        stockIn.setAppId(UserContext.currentUserAppId());
        stockInService.update(stockIn);
        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"操作成功!");

    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString")String jsonString) {
        StockIn stockIn = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,StockIn.class);
        StockInDevice  stockInDevice = new StockInDevice();
        stockInDevice.setStockInId(stockIn.getId());
        stockInDeviceService.deleteByStockInId(stockInDevice);
        stockInService.delete(stockIn);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/info")
    @POST
    public String info(@FormParam("stockInId") String stockInId){
        if(stockInId==null||stockInId.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        Map<String,Object> condition = new HashMap<String, Object>();
        condition.put("stockInId",Long.parseLong(stockInId));
        condition.put("appId",UserContext.currentUserAppId());
        Map<String,Object> stockInInfo = stockInService.getInfo(condition);
        List<Map<String,Object>> deviceList = stockInDeviceService.listByStockInId(condition);
        stockInInfo.put("deviceList",deviceList);
        return  JsonResultUtils.getObjectResultByStringAsDefault(stockInInfo,JsonResultUtils.Code.SUCCESS);
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
        List<Map<String,String>> list=stockInService.findByCondition(condition);
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

            long stockInId = Long.parseLong(multipartRequest.getParameter("stockInId"));
            long appId = UserContext.currentUserAppId();

            String rentImgRootPath =  FundamentalConfigProvider.get("rentManagement.img.root.path") ;
            String rentImgRelativePath =  FundamentalConfigProvider.get("rentManagement.img.relative.path") ;
            String imageDir = rentImgRootPath + rentImgRelativePath+"/"+appId+"/stockIn";
            String image = rentImgRelativePath+"/"+appId+"/stockIn/" + stockInId +"_"+ filename;
            String imagePath = imageDir + "/" + stockInId +"_"+ filename;

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
                StockIn stockIn = new StockIn();
                stockIn.setId(stockInId);
                stockIn.setAppId(UserContext.currentUserAppId());
                stockIn.setImage(image);
                stockInService.update(stockIn);
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
        List<Map<String,Object>> list=stockInService.getListByContractId(appId, contractId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }


}