package org.whut.platform.business.craneinspectreport.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.address.service.AddressService;
import org.whut.platform.business.craneinspectreport.entity.CraneInspectReport;
import org.whut.platform.business.craneinspectreport.service.CraneInspectReportService;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.fileupload.FileInfo;
import org.whut.platform.fundamental.fileupload.FileService;
import org.whut.platform.fundamental.fileupload.MultipartRequestParser;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.map.BaiduMapUtil;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
    private BaiduMapUtil baiduMapUtil=new BaiduMapUtil();
    private MultipartRequestParser multipartRequestParser=new MultipartRequestParser();
    @Produces(MediaType.MULTIPART_FORM_DATA)
    @Path("/upload")
    @POST
    public String upload(@Context HttpServletRequest request){
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

    @Path("/getAreaInfo")
    @POST
    public String getAreaInfo(@FormParam("city") String city,@FormParam("pname") String pname){
        if(city==null||city.trim().equals("")||pname==null||pname.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
        Long addressId=addressService.findIdByCityArea(city,pname);
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
        }
    }
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/getAreaInfoByUnitAddress")
    public String  getAreaInfoByUnitAddress(@FormParam("name") String name){
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
        if(address_equipmentvariety==null||address_equipmentvariety.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
           String[] str=address_equipmentvariety.split(",");
           List<CraneInspectReport> list=craneInspectReportService.getCraneInspectReportInfoByAddressAndEquipment(str[0],str[1]);
           return JsonResultUtils.getObjectStrResultByStringAsDefault(list,200,itemInfo);
    }

    @Produces(MediaType.MULTIPART_FORM_DATA)
    @Path("/getUnitaddressByArea")
    @POST
    public String getUnitaddressByArea(@FormParam("province") String province,@FormParam("city") String city,@FormParam("area") String area)
    {
        List<CraneInspectReport> list=craneInspectReportService.getUnitaddressByArea(province,city,area);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);

    }
    @Produces(MediaType.MULTIPART_FORM_DATA)
    @Path("/showRiskRank")
    @POST
    public String showRiskRank(@FormParam("city") String city,@FormParam("pname") String area)
    {
        List<CraneInspectReport> list=craneInspectReportService.showRiskRank(city,area);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);

    }
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/getOneUnitAddressInfo")
    public String getOneUnitAddressInfo(@FormParam("unitAddress") String unitAddress){
            List<CraneInspectReport> list=new ArrayList<CraneInspectReport>();
            CraneInspectReport craneInspectReport=craneInspectReportService.getOneUnitAddressInfo(unitAddress);
            Long riskValue=craneInspectReportService.findReportNumberByUnitAddress(craneInspectReport.getUnitAddress());
            craneInspectReport.setRiskValue(riskValue);
            list.add(craneInspectReport);
            return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/imageupload")
    public String imageUpload(@Context HttpServletRequest request){
        int uploadMaxSize= Integer.parseInt(FundamentalConfigProvider.get("uploadMaxSize"));
        FileService fileService=new FileService("jpg");
        String path=request.getSession().getServletContext().getRealPath("/imageupload");
        try{
        multipartRequestParser.parse(request,path,uploadMaxSize,fileService);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);

    }
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/getLatLngByProvince")
    public String getLatLngByProvince(@FormParam("province") String province){
        String latLng=null;
        try{
           Map map=baiduMapUtil.getCoordinate(province);
           latLng=map.get("lng")+","+map.get("lat");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JsonResultUtils.getObjectStrResultByStringAsDefault(null,200,latLng);
    }
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/getLatLngByCity")
    public String getLatLngByCity(@FormParam("province") String province,@FormParam("city") String city){
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
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/getLatLngByArea")
    public String getLatLngByArea(@FormParam("province") String province,@FormParam("city") String city,@FormParam("area") String area){
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
}
