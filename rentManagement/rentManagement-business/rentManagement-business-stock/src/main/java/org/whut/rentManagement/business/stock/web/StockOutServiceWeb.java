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
import org.whut.rentManagement.business.stock.entity.StockOut;
import org.whut.rentManagement.business.stock.entity.StockOutDevice;
import org.whut.rentManagement.business.stock.service.StockOutDeviceService;
import org.whut.rentManagement.business.stock.service.StockOutService;

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
@Path("/stockOut")
public class StockOutServiceWeb {

    private static final PlatformLogger logger = PlatformLogger.getLogger(StockOutServiceWeb.class);

    @Autowired
    StockOutService stockOutService;

    @Autowired
    StockOutDeviceService stockOutDeviceService;

    @Autowired
    DeviceService deviceService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        long appId = UserContext.currentUserAppId();
        List<Map<String,String>> list=stockOutService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("jsonString") String jsonString){
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }

        StockOut stockOut = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,StockOut.class);
        if(stockOut.getDeviceId()==null||stockOut.getDeviceId().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "入库设备不能为空!");
        }

        stockOut.setAppId(UserContext.currentUserAppId());
        stockOut.setHandler(UserContext.currentUserName());
        stockOut.setCreateTime(new Date());

        //添加安装的设备明细
        if(stockOut.getDeviceId()!=null&&!stockOut.getDeviceId().equals("")){
            String[] deviceList = stockOut.getDeviceId().split(",");
            Set set = new TreeSet();
            StockOutDevice stockOutDevice;
            ArrayList<StockOutDevice> stockOutDeviceList = new ArrayList<StockOutDevice>();
            for(String deviceToTransport:deviceList){
                if(!set.contains(deviceToTransport)&&!deviceToTransport.trim().equals("")){
                    stockOutDevice = new StockOutDevice();
                    stockOutDevice.setDeviceId(Long.parseLong(deviceToTransport));
                    stockOutDeviceList.add(stockOutDevice);
                }
                set.add(deviceToTransport);
            }

            stockOutService.add(stockOut);
            for(StockOutDevice std:stockOutDeviceList){
                std.setStockOutId(stockOut.getId());
                stockOutDeviceService.add(std);
            }
            deviceService.stockOut(UserContext.currentUserAppId(),new ArrayList<String>(set));
            return  JsonResultUtils.getObjectResultByStringAsDefault(stockOut.getId(),JsonResultUtils.Code.SUCCESS);
        }

        return  JsonResultUtils.getObjectResultByStringAsDefault(stockOut.getId(), JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString")String jsonString)  {
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        StockOut stockOut = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,StockOut.class);
        if (stockOut.getId()==0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"入库单id不能为空");
        }
        stockOut.setAppId(UserContext.currentUserAppId());
        stockOutService.update(stockOut);
        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"操作成功!");

    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString")String jsonString) {
        StockOut stockOut = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,StockOut.class);
        StockOutDevice  stockOutDevice = new StockOutDevice();
        stockOutDevice.setStockOutId(stockOut.getId());
        stockOutDeviceService.deleteByStockOutId(stockOutDevice);
        stockOutService.delete(stockOut);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/info")
    @POST
    public String info(@FormParam("stockOutId") String stockOutId){
        if(stockOutId==null||stockOutId.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        Map<String,Object> condition = new HashMap<String, Object>();
        condition.put("stockOutId",Long.parseLong(stockOutId));
        condition.put("appId",UserContext.currentUserAppId());
        Map<String,Object> stockOutInfo = stockOutService.getInfo(condition);
        List<Map<String,Object>> deviceList = stockOutDeviceService.listByStockOutId(condition);
        stockOutInfo.put("deviceList",deviceList);
        return  JsonResultUtils.getObjectResultByStringAsDefault(stockOutInfo,JsonResultUtils.Code.SUCCESS);
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
        List<Map<String,String>> list=stockOutService.findByCondition(condition);
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

            long stockOutId = Long.parseLong(multipartRequest.getParameter("stockOutId"));
            long appId = UserContext.currentUserAppId();

            String rentImgRootPath =  FundamentalConfigProvider.get("rentManagement.img.root.path") ;
            String rentImgRelativePath =  FundamentalConfigProvider.get("rentManagement.img.relative.path") ;
            String imageDir = rentImgRootPath + rentImgRelativePath+"/"+appId+"/stockOut";
            String image = rentImgRelativePath+"/"+appId+"/stockOut/" + stockOutId +"_"+ filename;
            String imagePath = imageDir + "/" + stockOutId +"_"+ filename;

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
                StockOut stockOut = new StockOut();
                stockOut.setId(stockOutId);
                stockOut.setAppId(UserContext.currentUserAppId());
                stockOut.setImage(image);
                stockOutService.update(stockOut);
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
        List<Map<String,Object>> list=stockOutService.getListByContractId(appId, contractId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

}