package org.whut.platform.business.craneinspectreport.thread;
import org.whut.platform.business.address.entity.Address;
import org.whut.platform.business.address.service.AddressService;
import org.whut.platform.business.craneinspectreport.entity.CalculateStatus;
import org.whut.platform.business.craneinspectreport.entity.CalculateTask;
import org.whut.platform.business.craneinspectreport.entity.CraneInspectReport;
import org.whut.platform.business.craneinspectreport.riskcalculate.CalculateTools;
import org.whut.platform.business.craneinspectreport.riskcalculate.ICalculateRisk;
import org.whut.platform.business.craneinspectreport.service.CraneInspectReportService;
import org.whut.platform.business.datarule.service.DataRoleAddressService;
import org.whut.platform.business.user.service.UserService;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 14-12-18
 * Time: 下午3:05
 * To change this template use File | Settings | File Templates.
 */
public class CraneInspectReportThread implements Runnable{
    private CraneInspectReportService craneInspectReportService;
    private AddressService addressService;
    private DataRoleAddressService dataRoleAddressService;
    private UserService userService;
    //private static List<Map<String,String>> mList=new ArrayList<Map<String, String>>();
    private String userName;
    //缓存查出的所有地址
    private String reportId;
    private String SUCCESS="success";
    private static CraneInspectReportThread singleton;

    public CraneInspectReportThread(String reportId,CraneInspectReportService craneInspectReportService,AddressService addressService,UserService userService,DataRoleAddressService dataRoleAddressService,String userName){
        this.reportId=reportId;
        this.craneInspectReportService=craneInspectReportService;
        this.addressService=addressService;
        this.userService=userService;
        this.dataRoleAddressService=dataRoleAddressService;
        this.userName=userName;
    }
    public void run(){
        calculateRiskThread();
    }
    public static synchronized void start(String reportId,CraneInspectReportService craneInspectReportService,AddressService addressService,UserService userService,DataRoleAddressService dataRoleAddressService,String userName){
        if(singleton==null){
            singleton=new CraneInspectReportThread(reportId,craneInspectReportService,addressService,userService,dataRoleAddressService,userName);
            new Thread(singleton).start();
        }
    }
    public synchronized void stop(){
        if(singleton!=null){
            singleton=null;
        }else{
            System.out.println("singleton==null");
        }
    }
    public void calculateRiskThread(){
        //先插入calculate_task表
        CalculateTask calculateTask=new CalculateTask();
        calculateTask.setStartTime(new Date());
        calculateTask.setStatus("当前任务");
        craneInspectReportService.insertToCalculateTask(calculateTask);
        long taskId=calculateTask.getId();
        if(taskId>0){
        CalculateStatus calculateStatus=new CalculateStatus();
        calculateStatus.setStatus("1");
        calculateStatus.setTaskId(taskId);
        Map<String,String> m=craneInspectReportService.validateIsExistCalculateStatus(calculateStatus);
        if(m!=null){
        craneInspectReportService.deleteCalculateStatus(calculateStatus);
        }
        craneInspectReportService.insertToCalculateStatus(calculateStatus);
        long statusId=calculateStatus.getId();
        //取出taskId，插入状态表和uploadedreport表
        String[] str=reportId.split(",");
        for(int i=0;i<str.length;i++){
            craneInspectReportService.updateTaskIdUploadedReportByReportId(Long.parseLong(str[i]), taskId);
        }
        if(statusId>0){
        //先计算最大值,计算完毕后将status更新为1
        if(calculateMaxValue().equals(SUCCESS)){
          if(craneInspectReportService.updateRiskCalculateStatus("1",statusId,taskId)>0){
             if(calculateRiskValue().equals(SUCCESS)){
                 if(craneInspectReportService.updateRiskCalculateStatus("2",statusId,taskId)>0){
                     if(calculateProvinceCityArea().equals(SUCCESS)){
                         if(craneInspectReportService.updateRiskCalculateStatus("3",statusId,taskId)>0){
                            if(dumpData().equals(SUCCESS)){
                                if(craneInspectReportService.updateRiskCalculateStatus("4",statusId,taskId)>0){
                                    System.out.println("计算完毕!");
                                    craneInspectReportService.updateRiskCalculateStatus("5",statusId,taskId);
                                    CalculateTask task=new CalculateTask();
                                    task.setEndTime(new Date());
                                    task.setStatus("任务完成");
                                    task.setId(taskId);
                                    craneInspectReportService.updateCalculateTask(task);
                                    this.stop();
                                }
                            }
                         }
                     }
                 }
             }
          }
        }
        }
        }
        //再计算勾起来的文档,计算完毕后将status更新为2


        //计算省市区的风险值,然后将status更新为3

        //复制数据,将status更新为4
    }
    public String calculateMaxValue(){
        String r=craneInspectReportService.insertToCraneInspectReportMaxValueCollection();
        if(r.equals("0")){

        }else if(r.equals("1")){
            return SUCCESS;
        }
        return null;
    }
    public String calculateRiskValue(){
        //计算风险值，传过来的是uploaded_reportId,通过这些reportId找到对应的起重机，
        // 将相应的信息分装到craneInspectReport对象中，然后根据equipmentVariety
        //来查找craneTypeId，从而找到相应的riskModelId,然后找到className,动态的
        //选择class类来进行计算
        String[] str=reportId.split(",");
        List<Map<String,String>> calculatedReportList=new ArrayList<Map<String, String>>();
        for(int i=0;i<str.length;i++){
            String className=null;
            List<CraneInspectReport> craneList=craneInspectReportService.getCraneListByUploadReportId(Long.parseLong(str[i]));
            List<CraneInspectReport> craneInspectReportList=new ArrayList<CraneInspectReport>();
            List<Map<String,String>>mapList=new ArrayList<Map<String, String>>();
            craneInspectReportService.getDbArrayListFromMongo();
            for(CraneInspectReport craneInspectReport:craneList){
                //根据reportnumber从mongodb中拿出数据封装到craneinspectreport中
                className=craneInspectReportService.getClassNameByEquipmentVariety(craneInspectReport.getEquipmentVariety());
                //通过每个reportnumber从mongodb中拿出数据封装成craneinspectreport对象，然后加载
                CraneInspectReport craneReport=new CraneInspectReport();
                craneReport=craneInspectReportService.getCraneInfoFromMongoByReportNumber(craneInspectReport.getReportNumber(),craneInspectReport.getEquipmentVariety());
                craneReport.setId(craneInspectReport.getId());
                craneInspectReportList.add(craneReport);
            }
            for(CraneInspectReport cr:craneInspectReportList){
                Long craneTypeId=craneInspectReportService.getCraneTypeIdByCraneEquipment(cr.getEquipmentVariety());
                if(craneTypeId!=null){
                    Float r=calculateRisk(className,cr,String.valueOf(craneTypeId));
                    int riskValue=Math.round(r);
                    Map<String,String> m=new HashMap<String,String>();
                    if(cr!=null){
                        m.put("reportNumber",cr.getReportNumber());
                        m.put("riskvalue",String.valueOf(riskValue));
                        m.put("reportId",String.valueOf(cr.getId()));
                        mapList.add(m);
                        //mList.add(m);
                    }
                }
            }
            Map<String,String> uploadReport=craneInspectReportService.validateReportIsCalculated(Long.parseLong(str[i]));
            if(uploadReport.get("status").equals("未计算")){
                //批量插入riskValue
                if(craneInspectReportService.InsertToRiskValue(mapList)){
                    //更新
                    craneInspectReportService.updateUploadedReportByReportId(Long.parseLong(str[i]),"已计算");
                };
            }else{
                //将重复的记录保存到list中
                calculatedReportList.add(uploadReport);
            }
        }
        return SUCCESS;
    }
    public Float calculateRisk(String className,CraneInspectReport craneInspectReport,String craneType){
        Float riskValue=0f;
        try{
            Class c=Class.forName(className);
            ICalculateRisk iCalculateRisk=(ICalculateRisk)c.newInstance();
            riskValue=iCalculateRisk.calculateRisk(craneInspectReport,craneType);
        }catch (Exception e){
            e.printStackTrace();
        }
        return riskValue;
    }
    public String calculateProvinceCityArea(){
          if(calculateAreaRisk().equals(SUCCESS)){
              if(calculateCityRisk().equals(SUCCESS)){
                  if(calculateProvinceRisk().equals(SUCCESS)){
                      return SUCCESS;
                  }
              }
          }
        return null;
    }
    public String calculateCityRisk(){
        //通过区查出有多少unitAddress,然后根据每家unitAddress求出区域风险平均值
        //查出所有的区
        List<Map<String,Float>> cityList=new ArrayList<Map<String, Float>>();
        List<Address> addresses=getProvinceCity();
        for(Address address:addresses){
            Map<String,String> cityRiskValueMap=craneInspectReportService.validateCityRiskValueIsExistByProvinceAndCity(address.getProvince(),address.getCity());
            if(cityRiskValueMap==null){

            }else{
                craneInspectReportService.deleteCityRiskValue(cityRiskValueMap.get("province"), cityRiskValueMap.get("city"));
            }
            Map<String,Float> map=craneInspectReportService.getCityInfoByCondition0(address.getProvince(),address.getCity());
            cityList.add(map);
        }
        if(cityList!=null&&cityList.size()!=0){
            craneInspectReportService.batchInsertToCityRiskValue(cityList);
        }
        return SUCCESS;
    }
    //查出相关联的省市
    public List<Address> getProvinceCity(){
        List<Address> list=addressService.getProvinceCity();
        return list;
    }
    public String calculateAreaRisk(){
        //通过区查出有多少unitAddress,然后根据每家unitAddress求出区域风险平均值
        //查出所有的区
        List<Address> addressList=new ArrayList<Address>();
        List<Address> idList=new ArrayList<Address>();
        addressList=getAllAddress();
        List<Map<String,Float>> areaList=new ArrayList<Map<String, Float>>();
        idList=getId();
        //根据省市
        for(Address address:idList){
            Map<String,String> addressRiskValue=craneInspectReportService.validateAddressRiskValueIsExistByAddressId(address.getId());
            if(addressRiskValue==null){
            }else{
                craneInspectReportService.deleteAreaRiskValue(Long.parseLong(String.valueOf(addressRiskValue.get("addressid"))));
            }
        }
        for(Address address:addressList){
            areaList=craneInspectReportService.getAreaInfoByCondition0(address.getProvince(),address.getCity(),"0","0","0",0f,0f);
            if(areaList!=null&&areaList.size()!=0){
                craneInspectReportService.batchInsertToAddressRiskValue(areaList);
            }
        }
        return SUCCESS;
    }
    //获取所有的地址
    public List<Address> getAllAddress(){
        List<Address> list=addressService.getProvinceCity();
        return list;
    }
    public List<Address> getId(){
        return addressService.getId();
    }
    public String calculateProvinceRisk(){
        long userId=userService.getIdByName(userName);
        List<Map<String,String>> provinceList=dataRoleAddressService.getProvinceInfoWithDataRuleByCondition0(userId,"0","0","0",0f,0f);
        for(Map<String,String> addressMap:provinceList){
            Address address=new Address();
            address.setProvince(addressMap.get("province"));
            Map<String,String> repeatProvinceMap=dataRoleAddressService.validateProvinceRiskValueIsExistByProvince(address.getProvince());
            if(repeatProvinceMap==null){

            }else{
                //如果存在,则删掉
                dataRoleAddressService.deleteProvinceRiskValue(repeatProvinceMap.get("province"));
            }
        }
        if(provinceList!=null&&provinceList.size()!=0){
            dataRoleAddressService.batchInsertToProvinceRiskValue(provinceList);
        }
        return SUCCESS;
    }
    public String dumpData(){
        craneInspectReportService.deleteProvinceRiskTempTable();
        craneInspectReportService.deleteCityRiskTempTable();
        craneInspectReportService.deleteAreaRiskTempTable();
        int a=craneInspectReportService.dumpDataToProvinceRisk();
        if(a!=0){
            int b=craneInspectReportService.dumpDataToCityRisk();
            if(b!=0){
                int c=craneInspectReportService.dumpDataToAreaRisk();
                if(c!=0){
                    return SUCCESS;
                }
            }
        }
        return null;
    }
}
