package org.whut.platform.business.craneinspectreport.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.stereotype.Component;
import org.whut.platform.business.address.service.AddressService;
import org.whut.platform.business.craneinspectreport.entity.CraneInspectReport;
import org.whut.platform.business.craneinspectreport.service.CraneInspectReportService;
import org.whut.platform.business.user.security.MyUserDetail;
import org.whut.platform.business.user.security.MyUserDetailsService;
import org.whut.platform.business.user.service.UserService;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.fileupload.FileInfo;
import org.whut.platform.fundamental.fileupload.FileService;
import org.whut.platform.fundamental.fileupload.MultipartRequestParser;
import org.whut.platform.fundamental.fileupload.MultipartRequestResult;
import org.whut.platform.fundamental.fileupload.MultipartRequestResult;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.map.BaiduMapUtil;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;
    private String singlePicURL="";
    private BaiduMapUtil baiduMapUtil=new BaiduMapUtil();
    private MultipartRequestParser multipartRequestParser=new MultipartRequestParser();
    @Produces(MediaType.MULTIPART_FORM_DATA)
    @Path("/upload")
    @POST
    public String upload(@Context HttpServletRequest request){     //excel上传功能
        if (request == null) {
            return JsonResultUtils
                    .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
        try {
            request.setCharacterEncoding("UTF-8");
            int uploadMaxSize= Integer.parseInt(FundamentalConfigProvider.get("uploadMaxSize"));
            FileService fileService=new FileService("xls");
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
        List<CraneInspectReport> list=craneInspectReportService.getRepeatList();
        if (list.size()==0||list==null){
            return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
        }
        else{
            return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.DUPLICATE);
        }
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
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/list")
    @GET
      public String list(){
          List<CraneInspectReport> list=craneInspectReportService.list();
          return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
      }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/delete")
    @POST
      public String delete(@FormParam("jsonString") String jsonString){
          CraneInspectReport craneInspectReport= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,CraneInspectReport.class);
          int result=craneInspectReportService.delete(craneInspectReport);
          if (result>0){
              return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
          }else{
              return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
          }
      }
/*    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/refreshRepeatList")
    @POST
    public String refreshRepeatList(){
        craneInspectReportService.refreshRepeatList();
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/refreshList")
    @POST
    public String refreshList(){
        craneInspectReportService.refreshList();
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }*/
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/addRepeat")
    @POST
    public String addRepeat(){
        CraneInspectReport craneInspectReport=new CraneInspectReport();
        List<CraneInspectReport> list=craneInspectReportService.getRepeatList();
        for(CraneInspectReport repeat:list){
            craneInspectReport.setReportNumber(repeat.getReportNumber());
            craneInspectReport.setUnitAddress(repeat.getUnitAddress());
            craneInspectReport.setAddressId(repeat.getAddressId());
            craneInspectReport.setOrganizeCode(repeat.getOrganizeCode());
            craneInspectReport.setUserPoint(repeat.getUserPoint());
            craneInspectReport.setSafeManager(repeat.getSafeManager());
            craneInspectReport.setContactPhone(repeat.getContactPhone());
            craneInspectReport.setEquipmentVariety(repeat.getEquipmentVariety());
            craneInspectReport.setUnitNumber(repeat.getUnitNumber());
            craneInspectReport.setManufactureUnit(repeat.getManufactureUnit());
            craneInspectReport.setManufactureLicenseNumber(repeat.getManufactureLicenseNumber());
            craneInspectReport.setManufactureDate(repeat.getManufactureDate());
            craneInspectReport.setSpecification(repeat.getSpecification());
            craneInspectReport.setpNumber(repeat.getpNumber());
            craneInspectReport.setWorkLevel(repeat.getWorkLevel());
            craneInspectReportService.update(craneInspectReport);
        }
        list.clear();
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/getAreaInfo")
    @POST
    public String getAreaInfo(@FormParam("city") String city,@FormParam("area") String area){
        //根据市和区得到craneinpectreport的信息
        if(city==null||city.trim().equals("")||area==null||area.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
      /*  Long addressId=addressService.findIdByCityArea(city,area);
        if(addressId==null){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }else{
        List<List<CraneInspectReport>> resultList=new ArrayList<List<CraneInspectReport>>();
        List<CraneInspectReport> list=craneInspectReportService.getInfoByAddressId(addressId);
        List<CraneInspectReport> riskValueList=new ArrayList<CraneInspectReport>();
        Iterator it=list.iterator();
        while(it.hasNext()){
            CraneInspectReport craneInspectReport=(CraneInspectReport)it.next();
            CraneInspectReport craneReport=new CraneInspectReport();
            Long riskValue=craneInspectReportService.findReportNumberByUnitAddress(craneInspectReport.getUnitAddress());
            craneReport.setRiskValue(riskValue);
            riskValueList.add(craneReport);
        }
         resultList.add(list);
         resultList.add(riskValueList);
        if(resultList==null){
          return  JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }else{
        return JsonResultUtils.getObjectResultByStringAsDefault(resultList,JsonResultUtils.Code.SUCCESS);
        }
        }*/
         List<CraneInspectReport> list=craneInspectReportService.getAreaInfo(city,area);
         return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getAreaInfoByUnitAddress")
    public String  getAreaInfoByUnitAddress(@FormParam("name") String name){
        //通过unitAddress得到区域信息
        if(name==null||name.trim().equals("")){
            return  JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
        List<CraneInspectReport> list=craneInspectReportService.getInfoByUnitAddress(name);
        return JsonResultUtils.getObjectStrResultByStringAsDefault(list,200,name);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getCraneInspectReportInfoByAddressAndEquipment")
    public String getCraneInspectReportInfoByAddressAndEquipment(@FormParam("address_equipmentvariety") String address_equipmentvariety,@FormParam("itemInfoId") String itemInfo){
        //根据unitAddress以及设备种类得到craneinspectreport信息
        if(address_equipmentvariety==null||address_equipmentvariety.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
           String[] str=address_equipmentvariety.split(",");
           List<CraneInspectReport> list=craneInspectReportService.getCraneInspectReportInfoByAddressAndEquipment(str[0],str[1]);
           return JsonResultUtils.getObjectStrResultByStringAsDefault(list,200,itemInfo);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getUnitaddressByArea")
    @POST
    public String getUnitaddressByArea(@FormParam("province") String province,@FormParam("city") String city,@FormParam("area") String area)
    {
        //根据省市区得到unitAddress(公司)
        List<CraneInspectReport> list=craneInspectReportService.getUnitaddressByArea(province,city,area);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);

    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/showRiskRank")
    @POST
    public String showRiskRank(@FormParam("city") String city,@FormParam("area") String area)
    {
        //根据城市以及地区得到风险
        List<CraneInspectReport> list=craneInspectReportService.showRiskRank(city,area);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);

    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF")
    @Path("/showRiskRankByValueRange")
    @POST
    public String showRiskRankByValueRange(@FormParam("value") String value,@FormParam("city") String city,@FormParam("area") String area){
        String[] values= value.split(";");
        float startValue = Float.parseFloat(values[0]);
        float endValue=Float.parseFloat(values[1]);
        List<CraneInspectReport> list = craneInspectReportService.showRiskRankByValueRange(startValue,endValue,city,area);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getOneUnitAddressInfo")
    public String getOneUnitAddressInfo(@FormParam("unitAddress") String unitAddress){
             //根据unitAddress得到一条craneinspectreport信息
            List<CraneInspectReport> list=new ArrayList<CraneInspectReport>();
            CraneInspectReport craneInspectReport=craneInspectReportService.getOneUnitAddressInfo(unitAddress);
            Long riskValue=craneInspectReportService.findReportNumberByUnitAddress(craneInspectReport.getUnitAddress());
            craneInspectReport.setRiskValue(riskValue);
            list.add(craneInspectReport);
            return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/imageupload")
    public String imageUpload(@Context HttpServletRequest request){
        int uploadMaxSize= Integer.parseInt(FundamentalConfigProvider.get("uploadMaxSize"));
        FileService fileService=new FileService("jpg");
        /*singlePicURL=request.getSession().getServletContext().getInitParameter("UPLOAD_IMAGE_PATH");*/
        String realPath=request.getSession().getServletContext().getRealPath("../imageupload");
        try{
            MultipartRequestResult mrr=multipartRequestParser.parse(request,realPath,uploadMaxSize,fileService);
            String path=mrr.getFileInfos().get(0).getPath();
            String name=mrr.getFileInfos().get(0).getName();
            singlePicURL="../imageupload"+path;
           /* System.out.println(name+"jjjjjjjjj"+path);
            System.out.println(singlePicURL);
            singlePicURL=singlePicURL.replaceAll("\\\\","/");*/

        }catch (Exception e){
            e.printStackTrace();
        }
       // return  JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        //return JsonResultUtils.getObjectResultByStringAsDefault(singlePicURL,JsonResultUtils.Code.SUCCESS);
        return  JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);

    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getLatLngByProvince")
    public String getLatLngByProvince(@FormParam("province") String province){
        //根据省得到经纬度
        String latLng=null;
        try{
           Map map=baiduMapUtil.getCoordinate(province);
           latLng=map.get("lng")+","+map.get("lat");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JsonResultUtils.getObjectStrResultByStringAsDefault(null,200,latLng);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getLatLngByCity")
    public String getLatLngByCity(@FormParam("province") String province,@FormParam("city") String city){
        //根据省城市得到经纬度
        String latLng=null;
        try{
            String address=province+""+city;
            Map map=baiduMapUtil.getCoordinate(address);
            latLng=map.get("lng")+","+map.get("lat");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JsonResultUtils.getObjectStrResultByStringAsDefault(null,200,latLng);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getLatLngByArea")
    public String getLatLngByArea(@FormParam("province") String province,@FormParam("city") String city,@FormParam("area") String area){
        //根据省市区得到经纬度
        String latLng=null;
        try{
            String address=province+""+city+""+area;
            Map map=baiduMapUtil.getCoordinate(address);
            latLng=map.get("lng")+","+map.get("lat");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JsonResultUtils.getObjectStrResultByStringAsDefault(null,200,latLng);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getCraneInspectReportInfoById")
    public String getCraneInspectReportInfoById(@FormParam("id") long id){
        //通过id得到craneinpectreport信息
         List<CraneInspectReport> list=craneInspectReportService.getCraneInspectReportInfoById(id);
         return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getCraneInspectReportInfoFromCircle")
    public String getCraneInspectReportInfoFromCircle(@FormParam("radius") String radius,@FormParam("centerlng") String centerlng,@FormParam("centerlat") String centerlat){
        Map map=baiduMapUtil.getAround(Double.parseDouble(centerlat), Double.parseDouble(centerlng), Double.parseDouble(radius));
        String maxLng=map.get("maxLng").toString();
        String maxLat=map.get("maxLat").toString();
        String minLng=map.get("minLng").toString();
        String minLat=map.get("minLat").toString();
        List<CraneInspectReport> list=craneInspectReportService.getCraneInspectReportInfoFromCircle(maxLng,maxLat,minLng,minLat);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);

    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("reportNumber") String reportNumber){
        CraneInspectReport craneInspectReport=craneInspectReportService.getCraneInspectReportByReportNumber(reportNumber);
        craneInspectReport.setSinglePicURL(singlePicURL);
        craneInspectReport.setTypePicURL("");
        craneInspectReportService.update(craneInspectReport);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getEquipmentVarietyList")
    @POST
    public String getEquipmentVarietyList()
    {
        List<CraneInspectReport> list=craneInspectReportService.getEquipmentVarietyList();
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getProvinceAvgRiskValue")
    @GET
    public String getProvinceAvgRiskValue(){
        List<Map<String,Float>> list=craneInspectReportService.getProvinceAvgRiskValue();
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getCityAvgRiskValueByProvince")
    @POST
    public String getCityAvgRiskValueByProvince(@FormParam("province") String province){
        List<Map<String,Float>> list=craneInspectReportService.getCityAvgRiskValueByProvince(province);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getAreaAvgRiskValueByProvinceAndCity")
    @POST
    public String getAreaAvgRiskValueByProvinceAndCity(@FormParam("province") String province,@FormParam("city") String city){
        List<Map<String,Float>> list=craneInspectReportService.getAreaAvgRiskValueByProvinceAndCity(province,city);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getSession")
    @GET
    public String getSession(){
        MyUserDetail myUserDetail=userService.getMyUserDetailFromSession() ;
        System.out.print(myUserDetail.getUsername()+"用户名");
        System.out.print(myUserDetail.getPassword()+"密码");
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/fuzzyQuery")
    @POST
    public String fuzzyQuery(@FormParam("city") String city,@FormParam("area") String area,@FormParam("require") String require){
        List<CraneInspectReport> list=craneInspectReportService.fuzzyQuery(city,area,require);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/fuzzyQueryByUnitAddress")
    @POST
    public String fuzzyQueryByUnitAddress(@FormParam("city") String city,@FormParam("area") String area,@FormParam("require") String require){
        List<CraneInspectReport> list=craneInspectReportService.fuzzyQueryByUnitAddress(city,area,require);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/fuzzyQueryByUserPoint")
    @POST
    public String fuzzyQueryByUserPoint(@FormParam("city") String city,@FormParam("area") String area,@FormParam("require") String require){
        List<CraneInspectReport> list=craneInspectReportService.fuzzyQueryByUserPoint(city, area, require);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/fuzzyQueryBySafeManager")
    @POST
    public String fuzzyQueryBySafeManager(@FormParam("city") String city,@FormParam("area") String area,@FormParam("require") String require){
        List<CraneInspectReport> list=craneInspectReportService.fuzzyQueryBySafeManager(city, area, require);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/fuzzyQueryByEquipmentVariety")
    @POST
    public String fuzzyQueryByEquipmentVariety(@FormParam("city") String city,@FormParam("area") String area,@FormParam("require") String require){
        List<CraneInspectReport> list=craneInspectReportService.fuzzyQueryByEquipmentVariety(city, area, require);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF")
    @Path("/showRiskRankByValueRange")
    @POST
    public String getProvinceRiskRankFormRiskRange(@FormParam("value") String value){
        String[] values= value.split(";");
        float startValue = Float.parseFloat(values[0]);
        float endValue=Float.parseFloat(values[1]);
        List<Map<String,Float>> list = craneInspectReportService.getProvinceRiskRankFormRiskRange(startValue,endValue);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF")
    @Path("/showRiskRankByValueRange")
    @POST
    public String getCityRiskRankFormRiskRange(@FormParam("value") String value,@FormParam("province") String province){
        String[] values= value.split(";");
        float startValue = Float.parseFloat(values[0]);
        float endValue=Float.parseFloat(values[1]);
        List<Map<String,Float>> list = craneInspectReportService.getCityRiskRankFormRiskRange(startValue,endValue,province);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

}
