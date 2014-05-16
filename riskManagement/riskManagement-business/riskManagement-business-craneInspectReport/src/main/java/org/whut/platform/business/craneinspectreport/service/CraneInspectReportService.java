package org.whut.platform.business.craneinspectreport.service;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.address.entity.Address;
import org.whut.platform.business.address.service.AddressService;
import org.whut.platform.business.craneinspectreport.entity.CraneInspectReport;
import org.whut.platform.business.craneinspectreport.mapper.CraneInspectReportMapper;
import org.whut.platform.business.craneinspectreport.riskcalculate.CalculateTools;
import org.whut.platform.business.craneinspectreport.riskcalculate.WeightFactor;
import org.whut.platform.fundamental.jxl.model.ExcelMap;
import org.whut.platform.fundamental.jxl.utils.JxlExportImportUtils;
import org.whut.platform.fundamental.map.BaiduMapUtil;
import org.whut.platform.fundamental.mongo.connector.MongoConnector;
import org.whut.platform.fundamental.util.tool.ToolUtil;

import java.io.InputStream;
import java.util.*;
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
    private  ExcelMap excelMap=new ExcelMap();
    private JxlExportImportUtils jxlExportImportUtils;
    private CraneInspectReport craneInspectReport;
    private ToolUtil toolUtil=new ToolUtil();
    private List<CraneInspectReport> listRepeat=new ArrayList<CraneInspectReport>();
    private BaiduMapUtil baiduMapUtil=new BaiduMapUtil();
    private MongoConnector mongoConnector=new MongoConnector("craneInspectReportDB","craneInspectReportCollection");
    private CalculateTools calculateTools=new CalculateTools();
    public void upload(InputStream inputStream,String fileName){
      String documentJson=getMongoStringFromRequest(inputStream,fileName);
      mongoConnector.insertDocument(documentJson);
    }
    //返回MongoString
    public String getMongoStringFromRequest(InputStream inputStream,String fileName){
             String mString;
             excelMap=jxlExportImportUtils.analysisExcel(inputStream);
             List<List<String>> listContents=new ArrayList<List<String>>();
             List<CraneInspectReport> craneInspectReportList=new ArrayList<CraneInspectReport>();
             listRepeat.clear();
             for(int i=0;i<excelMap.getContents().size();i++){
                 Address address=new Address();
                 address=getAddressFromExcel(excelMap,i);
                 if(address==null){
                 listContents.add(excelMap.getContents().get(i));
                 }else{
                     Long addressId=addressService.findIdByArea(address.getProvince(),address.getCity(),address.getArea());
                     if(addressId==null){
                         //addressId查不到
                     }else{
                         craneInspectReport=transferExcelMapToCraneInspectReportObject(excelMap,i,addressId);
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
                documentJson1+=excelMap.getHeads().get(j)+":'"+excelMap.getContents().get(i).get(j)+"',";
                if(j+1==excelMap.getContents().get(i).size()-1){
                    documentJson1+=excelMap.getHeads().get(j+1)+":'"+excelMap.getContents().get(i).get(j+1)+"'";
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
    public CraneInspectReport transferExcelMapToCraneInspectReportObject(ExcelMap excelMap,int i,Long addressId){
             Date d=toolUtil.transferStringToDate(excelMap.getContents().get(i).get(10));
             craneInspectReport=new CraneInspectReport();
             craneInspectReport.setReportNumber(excelMap.getContents().get(i).get(0));
             craneInspectReport.setUnitAddress(excelMap.getContents().get(i).get(1));
             craneInspectReport.setAddressId(addressId);
             craneInspectReport.setOrganizeCode(excelMap.getContents().get(i).get(2));
             craneInspectReport.setUserPoint(excelMap.getContents().get(i).get(3));
             craneInspectReport.setSafeManager(excelMap.getContents().get(i).get(4));
             craneInspectReport.setContactPhone(excelMap.getContents().get(i).get(5));
             craneInspectReport.setEquipmentVariety(excelMap.getContents().get(i).get(6));
             craneInspectReport.setUnitNumber(excelMap.getContents().get(i).get(7));
             craneInspectReport.setManufactureUnit(excelMap.getContents().get(i).get(8));
             craneInspectReport.setManufactureLicenseNumber(excelMap.getContents().get(i).get(9));
             craneInspectReport.setManufactureDate(d);
             craneInspectReport.setSpecification(excelMap.getContents().get(i).get(11));
             craneInspectReport.setpNumber(excelMap.getContents().get(i).get(12));
             craneInspectReport.setWorkLevel(excelMap.getContents().get(i).get(13));
             craneInspectReport.setRatedLiftWeight(excelMap.getContents().get(i).get(14));
             Map map=getCoordinate(craneInspectReport.getUnitAddress());
             craneInspectReport.setLng(map.get("lng").toString());
             craneInspectReport.setLat(map.get("lat").toString());
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
        if(toolUtil.parseAddress(excelMap.getContents().get(i).get(1)).equals("0")){
            return null;
        }else{
        String str[]=toolUtil.parseAddress(excelMap.getContents().get(i).get(1)).split(",");
        Address address=new Address();
        address.setProvince(str[0]);
        address.setCity(str[1]);
        address.setArea(str[2]);
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
     public List<CraneInspectReport> showRiskRank(String city,String area){
         return mapper.showRiskRank(city,area);
     }
    public List<CraneInspectReport> showRiskRankByValueRange(float startValue,float endValue,String city,String area){
        return mapper.showRiskRankByValueRange(startValue,endValue,city,area);
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
    public List<CraneInspectReport> getAreaInfo(String city,String area){
        return mapper.getAreaInfo(city,area);
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
    public  List<Map<String,Float>>getAreaInfoByCondition(String province,String city,String equipmentVariety,String sTime,String eTime,float startValue,float endValue){
          return mapper.getAreaInfoByCondition(province,city,equipmentVariety,sTime,eTime,startValue,endValue);
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
            maxUseTime=String.valueOf(calculateTools.getMaxUseTime(equipmentVariety));
            maxRatedLiftWeight=String.valueOf(calculateTools.getMaxRatedLiftWeight(equipmentVariety));
            maxSpan=String.valueOf(calculateTools.getMaxSpan(equipmentVariety));
            maxRange=String.valueOf(calculateTools.getMaxRange(equipmentVariety));
            maxLiftHeight=String.valueOf(calculateTools.getMaxLiftHeight(equipmentVariety));
            maxLiftSpeed=String.valueOf(calculateTools.getMaxLiftSpeed(equipmentVariety));
            maxRunSpeed=String.valueOf(calculateTools.getMaxRunSpeed(equipmentVariety));
            maxCartSpeed=String.valueOf(calculateTools.getMaxCartSpeed(equipmentVariety));
            maxCarSpeed=String.valueOf(calculateTools.getMaxCarSpeed(equipmentVariety));
            maxLiftMoment=String.valueOf(calculateTools.getMaxLiftMoment(equipmentVariety));
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
    public void insertToCraneInspectReportMaxValueCollection(){
        MongoConnector mongo=new MongoConnector("craneInspectReportDB","craneInspectReportMaxValue");
        mongo.insertDocument(getCraneInspectReportMaxValue());
    }
    public CraneInspectReport getCraneInfoFromMongoByReportNumber(String reportNumber){
        DBObject d=mongoConnector.getDBObjectByReportNumber(reportNumber);
        CraneInspectReport craneInspectReport=new CraneInspectReport();
        craneInspectReport.setReportNumber(reportNumber);
        craneInspectReport.setRatedLiftWeight((String)d.get(WeightFactor.ratedLiftWeight));
        craneInspectReport.setWorkLevel((String)d.get(WeightFactor.workLevel));
        craneInspectReport.setConclusion((String)d.get(WeightFactor.conclusion));
        craneInspectReport.setMaxLiftMoment((Float)d.get(WeightFactor.maxLiftMoment));
        craneInspectReport.setLiftHeight((Float)d.get(WeightFactor.liftHeight));
        craneInspectReport.setLiftSpeed((Float)d.get(WeightFactor.liftSpeed));
        craneInspectReport.setRunSpeed((Float)d.get(WeightFactor.runSpeed));
        craneInspectReport.setRange((Float)d.get(WeightFactor.range));
        craneInspectReport.setSpan((Float)d.get(WeightFactor.span));
        craneInspectReport.setCartSpeed((Float)d.get(WeightFactor.cartSpeed));
        craneInspectReport.setCarSpeed((Float)d.get(WeightFactor.carSpeed));
        return craneInspectReport;
    }
    public void batchInsertToRiskValue(List<Map<String,String>> mapList){
        mapper.batchInsertToRiskValue(mapList);
    }
}
