package org.whut.platform.business.craneinspectreport.service;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.address.entity.Address;
import org.whut.platform.business.address.service.AddressService;
import org.whut.platform.business.craneinspectreport.entity.CalculateStatus;
import org.whut.platform.business.craneinspectreport.entity.CalculateTask;
import org.whut.platform.business.craneinspectreport.entity.CraneInspectReport;
import org.whut.platform.business.craneinspectreport.mapper.CraneInspectReportMapper;
import org.whut.platform.business.craneinspectreport.riskcalculate.CalculateTools;
import org.whut.platform.business.craneinspectreport.riskcalculate.WeightFactor;
import org.whut.platform.business.user.entity.User;
import org.whut.platform.business.user.security.MyUserDetail;
import org.whut.platform.business.user.service.UserService;
import org.whut.platform.fundamental.jxl.model.ExcelMap;
import org.whut.platform.fundamental.jxl.utils.JxlExportImportUtils;
import org.whut.platform.fundamental.map.BaiduMapUtil;
import org.whut.platform.fundamental.mongo.connector.MongoConnector;
import org.whut.platform.fundamental.util.string.StringUtil;
import org.whut.platform.fundamental.util.tool.ToolUtil;

import java.io.InputStream;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: zhuzhenhua
 * Date: 14-3-17
 * Time: 下午10:39
 * To change this template use File | Settings | File Templates.
 */
public class CraneInspectReportService {
    @Autowired
    private CraneInspectReportMapper mapper;
    @Autowired
    private  AddressService addressService;
    @Autowired
    private UserService userService;
    private  ExcelMap excelMap=new ExcelMap();
    private JxlExportImportUtils jxlExportImportUtils;
    private CraneInspectReport craneInspectReport;
    private ToolUtil toolUtil=new ToolUtil();
    private List<CraneInspectReport> listRepeat=new ArrayList<CraneInspectReport>();
    private BaiduMapUtil baiduMapUtil=new BaiduMapUtil();
    private MongoConnector mongoConnector=new MongoConnector("craneInspectReportDB","craneInspectReportCollection");
    private CalculateTools calculateTools=new CalculateTools();
    private static List<List<DBObject>> dbObjectList=new ArrayList<List<DBObject>>();
    public void getDbArrayListFromMongo(){
        dbObjectList= mongoConnector.getDbArrayListFromMongo();
    }
    public void upload(ExcelMap excelMap,String fileName){
      String documentJson=getMongoStringFromRequest(excelMap,fileName);
      String monResult=mongoConnector.insertDocument(documentJson);
    }
    //返回MongoString
    public String getMongoStringFromRequest(ExcelMap excelMap,String fileName){
             String mString;
             //excelMap=new ExcelMap();
            // excelMap=jxlExportImportUtils.analysisExcel(inputStream);
             List<List<String>> listContents=new ArrayList<List<String>>();
             List<CraneInspectReport> craneInspectReportList=new ArrayList<CraneInspectReport>();
             listRepeat.clear();
             insertToUploadedReport(fileName);
             long reportId=findIdFromUploadedReportByName(fileName);
             for(int i=0;i<excelMap.getContents().size();i++){
                 Address address=new Address();
                 address=getAddressFromExcel(excelMap,i);
                 if(address==null){
                 listContents.add(excelMap.getContents().get(i));
                 }else{
                     Long addressId=addressService.findIdByArea(address.getProvince(),address.getCity(),address.getArea());
                     if(addressId==null||addressId==0){
                         //addressId查不到
                     }else{
                         craneInspectReport=transferExcelMapToCraneInspectReportObject(excelMap,i,addressId,reportId);
                         if(craneInspectReport!=null){
                         String s=craneInspectReport.getReportNumber();
                         String  reportNumber=mapper.getReportNumber(s);
                         if(reportNumber==null){
                             craneInspectReportList.add(craneInspectReport);
                             /*mapper.insert(craneInspectReport);*/
                         }else{
                             listRepeat.add(craneInspectReport);
                         }
                         }
                    }
                 }
             }
             if(craneInspectReportList!=null&&craneInspectReportList.size()!=0){
                 System.out.print("jj");
                 mapper.batchInsert(craneInspectReportList);
             }
               JxlExportImportUtils.createExcel(excelMap.getHeads(),listContents,fileName);
               mString=getDocumentJson(excelMap);
               return mString;
    }

    //获取键值对
    public String getDocumentJson(ExcelMap excelMap){
        String documentJson="{craneinspectreports:[";
        for(int i=0;i<excelMap.getContents().size()-1;i++){
            String documentJson1="{";
            for(int j=0;j<excelMap.getContents().get(i).size()-1;j++){
                if(StringUtil.removeN(excelMap.getContents().get(i).get(j))!=null){
                documentJson1+=excelMap.getHeads().get(j)+":'"+StringUtil.removeN(excelMap.getContents().get(i).get(j))+"',";
                }
                if(j+1==excelMap.getContents().get(i).size()-1){
                   if(StringUtil.removeN(excelMap.getContents().get(i).get(j+1))!=null){
                    documentJson1+=excelMap.getHeads().get(j+1)+":'"+StringUtil.removeN(excelMap.getContents().get(i).get(j+1))+"'";
                   }
                   }
            }
            documentJson+=documentJson1+"},";
            if(i+1==excelMap.getContents().size()-1){
                documentJson+=documentJson1+"}";
            }
        }
        documentJson+="]}";
        return documentJson;
    }
    //将execl的中数据转化成CraneInspectReport对象
    public CraneInspectReport transferExcelMapToCraneInspectReportObject(ExcelMap excelMap,int i,Long addressId,Long reportId){
             Date d=toolUtil.transferStringToDate(excelMap.getContents().get(i).get(10));
             craneInspectReport=new CraneInspectReport();
             if(!excelMap.getContents().get(i).get(0).equals("")&&StringUtil.removeN(excelMap.getContents().get(i).get(6))!=null){
             craneInspectReport.setReportNumber(excelMap.getContents().get(i).get(0));
             craneInspectReport.setUnitAddress(excelMap.getContents().get(i).get(1));
             craneInspectReport.setAddressId(addressId);
             craneInspectReport.setOrganizeCode(excelMap.getContents().get(i).get(2));
             craneInspectReport.setUserPoint(excelMap.getContents().get(i).get(3));
             craneInspectReport.setSafeManager(excelMap.getContents().get(i).get(4));
             craneInspectReport.setContactPhone(excelMap.getContents().get(i).get(5));
             craneInspectReport.setEquipmentVariety(StringUtil.removeN(excelMap.getContents().get(i).get(6)));
             craneInspectReport.setUnitNumber(excelMap.getContents().get(i).get(7));
             craneInspectReport.setManufactureUnit(excelMap.getContents().get(i).get(8));
             craneInspectReport.setManufactureLicenseNumber(excelMap.getContents().get(i).get(9));
             craneInspectReport.setManufactureDate(d);
             craneInspectReport.setSpecification(excelMap.getContents().get(i).get(11));
             craneInspectReport.setpNumber(excelMap.getContents().get(i).get(12));
             craneInspectReport.setWorkLevel(excelMap.getContents().get(i).get(13));
             craneInspectReport.setRatedLiftWeight(excelMap.getContents().get(i).get(14));
             Map map=getCoordinate(craneInspectReport.getUnitAddress());
             if(map.get("lng")!=null&&map.get("lat")!=null){
             craneInspectReport.setLng(map.get("lng").toString());
             craneInspectReport.setLat(map.get("lat").toString());
             }else{
              return null;
             }
             craneInspectReport.setUploadedReportId(reportId);
             }else{
             System.out.println("reportNumber为空!");
             return null;
             }
             return craneInspectReport;
    }
    //从execl中获取地址信息
    private Map<String,String> getCoordinate(String unitAddress){
        Map map=new HashMap();
        try{
        map=baiduMapUtil.getCoordinate(unitAddress);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
        }
    private Address getAddressFromExcel(ExcelMap excelMap,int i){
        if(baiduMapUtil.parseAddToProCityArea(excelMap.getContents().get(i).get(1))==null){
            return null;
        }else{
        Map<String,String> m=baiduMapUtil.parseAddToProCityArea(excelMap.getContents().get(i).get(1));
        Address address=new Address();
        if(m!=null&&m.get("province")!=null&&m.get("city")!=null&&m.get("area")!=null){
        address.setProvince(m.get("province"));
        address.setCity(m.get("city"));
        address.setArea(m.get("area"));
        }
        return address;
        }
    }
    public List<CraneInspectReport> list(){
        return mapper.findByCondition(new HashMap<String, Object>());
    }
    //获取重复的记录
    public List<CraneInspectReport> getRepeatList(){
        return listRepeat;
    }
    public int update(CraneInspectReport craneInspectReport){
        return mapper.update(craneInspectReport);
    }
    public int delete(CraneInspectReport craneInspectReport){
        return mapper.delete(craneInspectReport);
    }
    public CraneInspectReport getCraneInspectReportByReportNumber(String reportNumber){
        return mapper.getCraneInspectReportByReportNumber(reportNumber);
    }
    public void updateURL(String singlePicURL) {
        mapper.updateURL(singlePicURL);
    }
    public List<CraneInspectReport> getInfoByAddressId(Long id){
        return mapper.getInfoByAddressId(id);
    }
    public List<CraneInspectReport> getInfoByUnitAddress(String name){
        return mapper.getInfoByUnitAddress(name);
    }
    public List<CraneInspectReport> getCraneInspectReportInfoByAddressAndEquipment(String unitAddress,String equipmentVariety){
        return mapper.getCraneInspectReportInfoByAddressAndEquipment(unitAddress,equipmentVariety);
    }
    public List<CraneInspectReport> getUnitaddressByArea(String province,String city,String area)
    {
        return mapper.getUnitaddressByArea(province,city,area);
    }
    public Long findReportNumberByUnitAddress(String unitAddress){
          return mapper.findReportNumberByUnitAddress(unitAddress);
    }
     public List<CraneInspectReport> showRiskRank(Long addressId){
         return mapper.showRiskRank(addressId);
     }
    public List<CraneInspectReport> showRiskRankByValueRange(float startValue,float endValue,Long addressId){
        return mapper.showRiskRankByValueRange(startValue,endValue,addressId);
    }
    public CraneInspectReport getOneUnitAddressInfo(String unitAddress){
        return mapper.getOneUnitAddressInfo(unitAddress);
    }
    public List<CraneInspectReport> getCraneInspectReportInfoById(long id){
        return mapper.getCraneInspectReportInfoById(id);
    }
    public List<CraneInspectReport> getCraneInspectReportInfoFromCircle(String maxLng,String maxLat,String minLng,String minLat){
        return mapper.getCraneInspectReportInfoFromCircle(maxLng,maxLat,minLng,minLat);
    }
    public float getAvgRiskValueByProvince(String province){
        return mapper.getAvgRiskValueByProvince(province);
    }
    public float getAvgRiskValueByProvinceAndCity(String province,String city){
        return mapper.getAvgRiskValueByProvinceAndCity(province,city);
    }
    public List<Map<String,Float>> getProvinceAvgRiskValue(){
        return  mapper.getProvinceAvgRiskValue();
    }
    public List<Map<String,Float>> getCityAvgRiskValueByProvince(String province){
        return mapper.getCityAvgRiskValueByProvince(province);
    }
    public List<Map<String,Float>> getAreaAvgRiskValueByProvinceAndCity(String province,String city){
        return mapper.getAreaAvgRiskValueByProvinceAndCity(province,city);
    }
    public List<CraneInspectReport> fuzzyQuery(String city,String area,String require)
    {
        return mapper.fuzzyQuery(city,area,require);
    }
    public List<CraneInspectReport> fuzzyQueryByUnitAddress(String city,String area,String require){
        return mapper.fuzzyQueryByUnitAddress(city, area, require);
    }
    public List<CraneInspectReport> fuzzyQueryByUserPoint(String city,String area,String require){
        return mapper.fuzzyQueryByUserPoint(city, area, require);
    }
    public List<CraneInspectReport> fuzzyQueryBySafeManager(String city,String area,String require){
        return mapper.fuzzyQueryBySafeManager(city, area, require);
    }
    public List<CraneInspectReport> fuzzyQueryByEquipmentVariety(String city,String area,String require){
        return mapper.fuzzyQueryByEquipmentVariety(city, area, require);
    }
    public List<CraneInspectReport> fuzzyQueryByManufactureUnit(String city,String area,String require){
        return mapper.fuzzyQueryByManufactureUnit(city, area, require);
    }
    public List<String> getCraneInfoByFuzzyUnitAddress(String city,String area,String require){
        List<String> list=mapper.getCraneInfoByFuzzyUnitAddress(city, area, require);
        return list;
    }
    public List<String> getCraneInfoByFuzzyUsePoint(String city,String area,String require){
        List<String> list=mapper.getCraneInfoByFuzzyUsePoint(city, area, require);
        return list;
    }
    public List<String> getCraneInfoByFuzzySafeManager(String city,String area,String require){
        List<String> list=mapper.getCraneInfoByFuzzySafeManager(city, area, require);
        return list;
    }
    public List<String> getCraneInfoByFuzzyEquipmentVariety(String city,String area,String require){
        List<String> list=mapper.getCraneInfoByFuzzyEquipmentVariety(city, area, require);
        return list;
    }
    public List<String> getCraneInfoByFuzzyManufactureunit(String city,String area,String require){
        List<String> list=mapper.getCraneInfoByFuzzyManufactureunit(city, area, require);
        return list;
    }
    public List<String> getCraneInfoByFuzzyQuery(String city,String area,String require){
        List<String> list=mapper.getCraneInfoByFuzzyUnitAddress(city, area, require);
        List<String> list1=mapper.getCraneInfoByFuzzyUsePoint(city, area, require);
        List<String> list2=mapper.getCraneInfoByFuzzySafeManager(city, area, require);
        List<String> list3=mapper.getCraneInfoByFuzzyEquipmentVariety(city, area, require);
        List<String> list4=mapper.getCraneInfoByFuzzyManufactureunit(city, area, require);
        list.addAll(list1);
        list.addAll(list2);
        list.addAll(list3);
        list.addAll(list4);
        return list;
    }
    /*
        新加入的，Sunhui
    */
    public List<CraneInspectReport> getEquipmentVarietyList()
    {
        return  mapper.getEquipmentVarietyList();
    }
    public List<CraneInspectReport> getAreaInfo(Long id){
        return mapper.getAreaInfo(id);
    }
    public List<Map<String,Float>> getProvinceRiskRankFormRiskRange(float startValue,float endValue){
          return mapper.getProvinceRiskRankFormRiskRange(startValue,endValue);
    }
    public List<Map<String,Float>>getCityRiskRankFormRiskRange(float startValue,float endValue,String province){
          return mapper.getCityRiskRankFormRiskRange(startValue,endValue,province);
    }
    public List<Map<String,Float>>getAreaRiskRankFormRiskRange(float startValue,float endValue,String province,String city){
        return mapper.getAreaRiskRankFormRiskRange(startValue,endValue,province,city);
    }
    public long getCraneNumberByUnitAddress(String unitAddress){
        return mapper.getCraneNumberByUnitAddress(unitAddress);
    }
    public CraneInspectReport transferCraneInspectReportObject(CraneInspectReport object1,CraneInspectReport object2){
        object1.setId(object2.getId());
        object1.setReportNumber(object2.getReportNumber());
        object1.setUnitAddress(object2.getUnitAddress());
        object1.setAddressId(object2.getAddressId());
        object1.setOrganizeCode(object2.getOrganizeCode());
        object1.setUserPoint(object2.getUserPoint());
        object1.setSafeManager(object2.getSafeManager());
        object1.setContactPhone(object2.getContactPhone());
        object1.setEquipmentVariety(object2.getEquipmentVariety());
        object1.setUnitNumber(object2.getUnitNumber());
        object1.setManufactureUnit(object2.getManufactureUnit());
        object1.setManufactureLicenseNumber(object2.getManufactureLicenseNumber());
        object1.setManufactureDate(object2.getManufactureDate());
        object1.setSpecification(object2.getSpecification());
        object1.setpNumber(object2.getpNumber());
        object1.setWorkLevel(object2.getWorkLevel());
        object1.setRatedLiftWeight(object2.getRatedLiftWeight());
        object1.setSinglePicURL(object2.getSinglePicURL());
        object1.setTypePicURL(object2.getTypePicURL());
        object1.setRiskValue(object2.getRiskValue());
        object1.setLat(object2.getLat());
        object1.setLng(object2.getLng());
        return object1;
    }
    public List<String>getUseTimeList(){
        return mapper.getUseTimeList();
    }
    public List<CraneInspectReport>getCraneInfoByCondition(String province,String city,String area,String equipmentVariety,String sTime,String eTime,float startValue,float endValue){
        return mapper.getCraneInfoByCondition(province,city,area,equipmentVariety,sTime,eTime,startValue,endValue);
    }
    public List<Map<String,Float>> getCityInfoByCondition(String province,String equipmentVariety,String sTime,String eTime,float startValue,float endValue){
         return mapper.getCityInfoByCondition(province,equipmentVariety,sTime,eTime,startValue,endValue);
    }
    public Map<String,Float> getCityInfoByCondition0(String province,String city){
        return mapper.getCityInfoByCondition0(province,city);
    }
    public  List<Map<String,Float>>getAreaInfoByCondition(String province,String city,String equipmentVariety,String sTime,String eTime,float startValue,float endValue){
          return mapper.getAreaInfoByCondition(province,city,equipmentVariety,sTime,eTime,startValue,endValue);
    }
    public  List<Map<String,Float>>getAreaInfoByCondition0(String province,String city,String equipmentVariety,String sTime,String eTime,float startValue,float endValue){
        return mapper.getAreaInfoByCondition0(province,city,equipmentVariety,sTime,eTime,startValue,endValue);
    }
    public List<CraneInspectReport> getCraneListByUploadReportId(long reportId){
        return  mapper.getCraneListByUploadReportId(reportId);
    }
    public String getClassNameByEquipmentVariety(String equipmentVariety){
        return mapper.getClassNameByEquipmentVariety(equipmentVariety);
    }
    //根据某一大类来查出所有的小类
    //一点击按钮之后，应该先根据数据库中的值来将每一项的最大值算出来，存到数据库中
    public List<Long> getCraneTypeByCraneInspectReportInfo(){
        return mapper.getCraneTypeByCraneInspectReportInfo();
    }
    public List<String>getEquipmentVarietyByCraneType(long craneTypeId){
        return mapper.getEquipmentVarietyByCraneType(craneTypeId);
    }
    //求最大值的也应该动态生成
    public String getCraneInspectReportMaxValue(){
        List<Long> craneTypeIdList=getCraneTypeByCraneInspectReportInfo();
        //documentJson={maxValue:[{"typeId":"1","maxusetime":"maxusetime",...},{"typeId":"2","maxusetime":"maxusetime",...}]}
        String documentJson="{maxValue:[";
        int i=0;
        for(Long typeId:craneTypeIdList){
            List<String> equipmentVariety=getEquipmentVarietyByCraneType(typeId);
            documentJson+="{typeId:'"+typeId+"',";
            String maxUseTime=null;
            String maxRatedLiftWeight=null;
            String maxSpan=null;
            String maxRange=null;
            String maxLiftHeight=null;
            String maxLiftSpeed=null;
            String maxRunSpeed=null;
            String maxCartSpeed=null;
            String maxCarSpeed=null;
            String maxLiftMoment=null;
            //计算之前先初始化数据
            calculateTools.getDbArrayListFromMongo();
            if(calculateTools.getMaxUseTime(equipmentVariety)!=null){
            maxUseTime=String.valueOf(calculateTools.getMaxUseTime(equipmentVariety));
            }
            if(calculateTools.getMaxRatedLiftWeight(equipmentVariety)!=null){
            maxRatedLiftWeight=String.valueOf(calculateTools.getMaxRatedLiftWeight(equipmentVariety));
            }
            if(calculateTools.getMaxSpan(equipmentVariety)!=null){
            maxSpan=String.valueOf(calculateTools.getMaxSpan(equipmentVariety));
            }
            if(calculateTools.getMaxRange(equipmentVariety)!=null){
            maxRange=String.valueOf(calculateTools.getMaxRange(equipmentVariety));
            }
            if(calculateTools.getMaxLiftHeight(equipmentVariety)!=null){
            maxLiftHeight=String.valueOf(calculateTools.getMaxLiftHeight(equipmentVariety));
            }
            if(calculateTools.getMaxLiftSpeed(equipmentVariety)!=null){
            maxLiftSpeed=String.valueOf(calculateTools.getMaxLiftSpeed(equipmentVariety));
            }
            if(calculateTools.getMaxRunSpeed(equipmentVariety)!=null){
            maxRunSpeed=String.valueOf(calculateTools.getMaxRunSpeed(equipmentVariety));
            }
            if(calculateTools.getMaxCartSpeed(equipmentVariety)!=null){
            maxCartSpeed=String.valueOf(calculateTools.getMaxCartSpeed(equipmentVariety));
            }
            if(calculateTools.getMaxCarSpeed(equipmentVariety)!=null){
            maxCarSpeed=String.valueOf(calculateTools.getMaxCarSpeed(equipmentVariety));
            }
            if(calculateTools.getMaxLiftMoment(equipmentVariety)!=null){
            maxLiftMoment=String.valueOf(calculateTools.getMaxLiftMoment(equipmentVariety));
            }
            if(i<craneTypeIdList.size()-1){
                documentJson+="maxUseTime:'"+maxUseTime+"',maxRatedLiftWeight:'"+maxRatedLiftWeight+"',maxSpan:'"+maxSpan+"',maxRange:'"+maxRange+"',maxLiftHeight:'"+maxLiftHeight+"',maxLiftSpeed:'"+maxLiftSpeed+"',maxRunSpeed:'"+maxRunSpeed+"',maxCartSpeed:'"+maxCartSpeed+"',maxCarSpeed:'"+maxCarSpeed+"',maxLiftMoment:'"+maxLiftMoment+"'},";
            }
            if(i==craneTypeIdList.size()-1){
                documentJson+="maxUseTime:'"+maxUseTime+"',maxRatedLiftWeight:'"+maxRatedLiftWeight+"',maxSpan:'"+maxSpan+"',maxRange:'"+maxRange+"',maxLiftHeight:'"+maxLiftHeight+"',maxLiftSpeed:'"+maxLiftSpeed+"',maxRunSpeed:'"+maxRunSpeed+"',maxCartSpeed:'"+maxCartSpeed+"',maxCarSpeed:'"+maxCarSpeed+"',maxLiftMoment:'"+maxLiftMoment+"'}";
            }
            i++;
        }
        return documentJson+"]}";
    }
    public String insertToCraneInspectReportMaxValueCollection(){
        MongoConnector mongo=new MongoConnector("craneInspectReportDB","craneInspectReportMaxValue");
        //在插入之前先删除表
        String result=null;
        if(mongo!=null){
            mongo.dropCollection();
            String r=mongo.insertDocument(getCraneInspectReportMaxValue());
            if(r!=null){
            result="1";
            }
        }else{
           //没有连接
           result="0";
        }
        return result;
    }
    public DBObject getDBObjectByReportNumber(String reportNumber){
        if(reportNumber==null)
            return null;
        for(List<DBObject> dd:dbObjectList){
            for(DBObject ddd:dd){
                if(ddd.get("reportnumber")!=null){
                if(ddd.get("reportnumber").equals(reportNumber)){
                    return ddd;
                }
                }
            }
        }
        return null;
    }
    public CraneInspectReport getCraneInfoFromMongoByReportNumber(String reportNumber,String equipmentVariety){
        DBObject d=getDBObjectByReportNumber(reportNumber);
        if(d!=null){
        craneInspectReport=new CraneInspectReport();
        if(reportNumber!=null){
            craneInspectReport.setReportNumber(reportNumber);
        }
        if(equipmentVariety!=null){
            craneInspectReport.setEquipmentVariety(equipmentVariety);
        }
        if((String)d.get(WeightFactor.manufacturedate)!=null){
            long useTime=calculateTools.getUseTime((String)d.get(WeightFactor.manufacturedate));
            craneInspectReport.setUseTime(useTime);
        }
        if((String)d.get(WeightFactor.ratedLiftWeight)!=null&&filter((String)d.get(WeightFactor.ratedLiftWeight))){
            craneInspectReport.setRatedLiftWeight((String)d.get(WeightFactor.ratedLiftWeight));
        }
        else if((String)d.get(WeightFactor.ratedLiftWeight)==null||((String)d.get(WeightFactor.ratedLiftWeight)).equals("/")){
            craneInspectReport.setRatedLiftWeight("0");
        }
        if((String)d.get(WeightFactor.workLevel)!=null){
            craneInspectReport.setWorkLevel((String)d.get(WeightFactor.workLevel));
        }else if((String)d.get(WeightFactor.workLevel)==null||((String)d.get(WeightFactor.workLevel)).equals("/")){
            craneInspectReport.setWorkLevel("A1");
        }
        if((String)d.get(WeightFactor.conclusion)!=null){
            craneInspectReport.setConclusion((String)d.get(WeightFactor.conclusion));
        }else if((String)d.get(WeightFactor.conclusion)==null||((String)d.get(WeightFactor.conclusion)).equals("/")){
            craneInspectReport.setConclusion("不合格");
        }
        if(filter((String)d.get(WeightFactor.maxLiftMoment))){
        craneInspectReport.setMaxLiftMoment(Float.parseFloat((String)d.get(WeightFactor.maxLiftMoment)));
        }
        else if((String)d.get(WeightFactor.maxLiftMoment)==null||((String)d.get(WeightFactor.maxLiftMoment)).equals("/")){
        craneInspectReport.setMaxLiftMoment(0f);
        }
        if(filter((String)d.get(WeightFactor.liftHeight))){
        craneInspectReport.setLiftHeight(Float.parseFloat((String)d.get(WeightFactor.liftHeight)));
        }
        else if((String)d.get(WeightFactor.liftHeight)==null||((String)d.get(WeightFactor.liftHeight)).equals("/")){
        craneInspectReport.setLiftHeight(0f);
        }
        if(filter((String)d.get(WeightFactor.liftSpeed))){
        craneInspectReport.setLiftSpeed(Float.parseFloat((String)d.get(WeightFactor.liftSpeed)));
        }
        else if((String)d.get(WeightFactor.liftSpeed)==null||((String)d.get(WeightFactor.liftSpeed)).equals("/")){
            craneInspectReport.setLiftSpeed(0f);
        }
        if(filter((String)d.get(WeightFactor.runSpeed))){
            craneInspectReport.setRunSpeed(Float.parseFloat((String)d.get(WeightFactor.runSpeed)));
        }
        else if((String)d.get(WeightFactor.runSpeed)==null||((String)d.get(WeightFactor.runSpeed)).equals("/")){
            craneInspectReport.setRunSpeed(0f);
        }
        if(filter((String)d.get(WeightFactor.range))){
            craneInspectReport.setRange(Float.parseFloat((String)d.get(WeightFactor.range)));
        }
        else if((String)d.get(WeightFactor.range)==null||((String)d.get(WeightFactor.range)).equals("/")){
            craneInspectReport.setRange(0f);
        }
        if(filter((String)d.get(WeightFactor.span))){
            craneInspectReport.setSpan(Float.parseFloat((String)d.get(WeightFactor.span)));
        }
        else if((String)d.get(WeightFactor.span)==null||((String)d.get(WeightFactor.span)).equals("/")){
            craneInspectReport.setSpan(0f);
        }
        if(filter((String)d.get(WeightFactor.cartSpeed))){
            craneInspectReport.setCartSpeed(Float.parseFloat((String)d.get(WeightFactor.cartSpeed)));
        }
        if((String)d.get(WeightFactor.cartSpeed)==null||((String)d.get(WeightFactor.cartSpeed)).equals("/")){
            craneInspectReport.setCartSpeed(0f);
        }
        if(filter((String)d.get(WeightFactor.carSpeed))){
            craneInspectReport.setCarSpeed(Float.parseFloat((String)d.get(WeightFactor.carSpeed)));
        }
        if((String)d.get(WeightFactor.carSpeed)==null||((String)d.get(WeightFactor.carSpeed)).equals("/")){
            craneInspectReport.setCarSpeed(0f);
        }
        }else{

        }
        return craneInspectReport;
    }
    public boolean InsertToRiskValue(List<Map<String,String>> list){
        //去重复
        for(Map<String,String> m:list){
            String reportNumber=getReportNumberFromRiskValue(m.get("reportNumber").toString());
            if(reportNumber==null){
            }else{
            deleteRiskValueByReportNumber(reportNumber);
            }
        }
        mapper.batchInsertToRiskValue(list);
        return true;
    }
    public List<Map<String,String>>listUploadedReport(){
        return mapper.listUploadedReport();
    }
    public boolean filter(String condition){
         boolean f=false;
         if(calculateTools.filter(condition,"^[+-]?([0-9]*\\.?[0-9]+|[0-9]+\\.?[0-9]*)([eE][+-]?[0-9]+)?$")){
             f=true;
         }
        return f;
    }
    public void updateUploadedReportByReportId(long reportId,String status){
        mapper.updateUploadedReportByReportId(reportId,status);
    }
    public void updateTaskIdUploadedReportByReportId(long reportId,long taskId){
        mapper.updateTaskIdUploadedReportByReportId(reportId,taskId);
    }
    public Map<String,String> validateReportIsCalculated(long reportId){
        return mapper.validateReportIsCalculated(reportId);
    }
    public void updateRiskValueByChooseReport(String reportNumber,String riskvalue){
        mapper.updateRiskValueByChooseReport(reportNumber,riskvalue);
    }
    public void insertToUploadedReport(String reportName){
        Date d=new Date();
        MyUserDetail myUserDetail=userService.getMyUserDetailFromSession();
        String userName=myUserDetail.getUsername();
        User user=userService.findByName(userName);
        //去重复
        Map<String,String> map=mapper.validateUploadedReport(reportName);
        if(map==null){
        mapper.insertToUploadedReport(reportName,d,user.getId(),userName,"","未计算");
        }else{
        Long id=Long.parseLong(String.valueOf(map.get("id")));
        mapper.updateUploadedReport(reportName,d,user.getId(),userName,"","未计算",id);
        }
    }
    public long findIdFromUploadedReportByName(String reportName){
        return mapper.findIdFromUploadedReportByName(reportName);
    }
    public Long getCraneTypeIdByCraneEquipment(String equipmentVariety){
        return mapper.getCraneTypeIdByCraneEquipment(equipmentVariety);
    }
    public void batchInsertToAddressRiskValue(List<Map<String,Float>> mapList){
         mapper.batchInsertToAddressRiskValue(mapList);
    }
    public void batchInsertToCityRiskValue(List<Map<String,Float>> mapList){
         mapper.batchInsertToCityRiskValue(mapList);
    }
    public Map<String,String> validateAddressRiskValueIsExistByAddressId(long addressId){
        return mapper.validateAddressRiskValueIsExistByAddressId(addressId);
    }
    public void updateAreaRiskValue(Long addressId,Long riskValue){
        mapper.updateAreaRiskValue(addressId,riskValue);
    }
    public Map<String,String> validateCityRiskValueIsExistByProvinceAndCity(String province,String city){
        return mapper.validateCityRiskValueIsExistByProvinceAndCity(province,city);
    }
    public void updateCityRiskValue(String province,String city,Float riskValue){
        mapper.updateCityRiskValue(province,city,riskValue);
    }
    public void updateProvinceRiskValue(String province,Long riskValue){
       mapper.updateProvinceRiskValue(province,riskValue);
    }
    public int deleteCityRiskValue(String province,String city){
        return mapper.deleteCityRiskValue(province,city);
    }
    public int deleteAreaRiskValue(Long addressId){
        return mapper.deleteAreaRiskValue(addressId);
    }
    public void dropCraneInspectReportCollection(){
        MongoConnector mongo=new MongoConnector("craneInspectReportDB","craneInspectReportCollection");
        mongo.dropCollection();
    }
    public int dumpDataToProvinceRiskTemp(List<Map<String,String>> list){   //将计算风险值插入到省风险的临时表中
        return mapper.dumpDataToProvinceRiskTemp(list);
    }
    public int dumpDataToProvinceRisk(){
        return mapper.dumpDataToProvinceRisk();
    }
    public int dumpDataToCityRiskTemp(List<Map<String,String>> list){       //将计算风险值插入到市风险的临时表中
        return mapper.dumpDataToCityRiskTemp(list);
    }
    public int dumpDataToCityRisk(){
        return mapper.dumpDataToCityRisk();
    }
    public int dumpDataToAreaRiskTemp(List<Map<String,String>> list){        //将计算风险值插入到区域风险的临时表中
        return mapper.dumpDataToAreaRiskTemp(list);
    }
    public int dumpDataToAreaRisk(){
        return mapper.dumpDataToAreaRisk();
    }
    public List<Map<String,String>>selectProvinceRiskTemp(){
        return mapper.selectProvinceRiskTemp();
    }
    public List<Map<String,String>>selectCityRiskTemp(){
        return mapper.selectCityRiskTemp();
    }
    public List<Map<String,String>>selectAreaRiskTemp(){
        return mapper.selectAreaRiskTemp();
    }
    public String getReportNumberFromRiskValue(String reportNumber){
        return mapper.getReportNumberFromRiskValue(reportNumber);
    }
    public int deleteRiskValueByReportNumber(String reportNumber){
        return mapper.deleteRiskValueByReportNumber(reportNumber);
    }
    public int deleteProvinceRiskTempTable(){
        return mapper.deleteProvinceRiskTempTable();
    }
    public int deleteCityRiskTempTable(){
        return mapper.deleteCityRiskTempTable();
    }
    public int deleteAreaRiskTempTable(){
        return mapper.deleteAreaRiskTempTable();
    }
    public int updateUpdatedReport(Map<String,String> map){
        return mapper.updateUpdatedReport(map);
    }
    public int deleteByReportId(Long reportId){
        return mapper.deleteByReportId(reportId);
    }
    public int deleteUploadedReport(Map<String,String> map){
        return mapper.deleteUploadedReport(map);
    }
    public List<CraneInspectReport>getEquipmentVarietyByUploadedReportId(Long reportId){
        return mapper.getEquipmentVarietyByUploadedReportId(reportId);
    }
    public String findEquipmentVarietyFromCraneType(String equipmentVariety){
        return mapper.findEquipmentVarietyFromCraneType(equipmentVariety);
    }
    public List<String> getUnExistInCraneType(InputStream inputStream,String fileName){
        excelMap=jxlExportImportUtils.analysisExcel(inputStream);
        List<String> list=new ArrayList<String>();
        List<String> unExistEquipmentVarietyInCraneType=new ArrayList<String>();
        for(int i=0;i<excelMap.getContents().size();i++){
        String equipmentVar=excelMap.getContents().get(i).get(6);
        String equipmentVariety= null;
        if(StringUtil.removeN(equipmentVar)!=null){
            equipmentVariety=StringUtil.removeN(equipmentVar);
            list.add(equipmentVariety);
        }
        }
        if(list!=null||list.size()!=0){
        for(String s:list){
            String equipmentVariety=findEquipmentVarietyFromCraneType(s);
            if(equipmentVariety==null){
                if(!unExistEquipmentVarietyInCraneType.contains(s)){
                unExistEquipmentVarietyInCraneType.add(s);
                }
            }
        }
        }
        if(unExistEquipmentVarietyInCraneType!=null||unExistEquipmentVarietyInCraneType.size()!=0){
            upload(excelMap,fileName);
        }
        return unExistEquipmentVarietyInCraneType;
    }
    public String getCalculateStatus(String status){
        return mapper.getCalculateStatus(status);
    }
    public int updateRiskCalculateStatus(String status,long id,long taskId){
        return mapper.updateRiskCalculateStatus(status,id,taskId);
    }
    public int insertToCalculateTask(CalculateTask calculateTask){
        return  mapper.insertToCalculateTask(calculateTask);
    }
    public int insertToCalculateStatus(CalculateStatus calculateStatus){
        return mapper.insertToCalculateStatus(calculateStatus);
    }
    public List<Map<String,String>> getCalculateTaskInfo(String status){
        return mapper.getCalculateTaskInfo(status);
    }
    public void updateCalculateTask(CalculateTask calculateTask){
        mapper.updateCalculateTask(calculateTask);
    }
    public Map<String,String>validateIsExistCalculateStatus(CalculateStatus calculateStatus){
        return mapper.validateIsExistCalculateStatus(calculateStatus);
    }
    public int deleteCalculateStatus(CalculateStatus calculateStatus){
        return mapper.deleteCalculateStatus(calculateStatus);
    }
}
