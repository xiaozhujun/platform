package org.whut.platform.business.craneinspectreport.mapper;
import org.apache.ibatis.annotations.Param;
import org.whut.platform.business.address.entity.Address;
import org.whut.platform.business.craneinspectreport.entity.CalculateStatus;
import org.whut.platform.business.craneinspectreport.entity.CalculateTask;
import org.whut.platform.business.craneinspectreport.entity.CraneInspectReport;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-3-17
 * Time: 下午10:39
 * To change this template use File | Settings | File Templates.
 */
public interface CraneInspectReportMapper extends AbstractMapper<CraneInspectReport> {
    public void insert(CraneInspectReport craneInspectReport);
    public void batchInsert(List<CraneInspectReport> craneInspectReports);
    public void updateURL(String singlePicURL);
    public List<CraneInspectReport> findByCondition(Map<String,Object> map);
    public String getReportNumber(String reportNumber);
    public List<CraneInspectReport> getInfoByAddressId(Long addressId);
    public List<CraneInspectReport> getInfoByUnitAddress(String unitAddress);
    public CraneInspectReport getCraneInspectReportByReportNumber(@Param("reportNumber") String reportNumber);
    public List<CraneInspectReport> getCraneInspectReportInfoByAddressAndEquipment(@Param("unitAddress") String unitAddress,@Param("equipmentVariety") String equipmentVariety);
    public List<CraneInspectReport> getUnitaddressByArea(@Param("province") String province,@Param("city") String city,@Param("area") String area);
    public Long findReportNumberByUnitAddress(String unitAddress);
    public List<CraneInspectReport>showRiskRank(@Param("addressId") Long addressId);
    public List<CraneInspectReport> showRiskRankByValueRange(@Param("startValue") float startValue,@Param("endValue") float endValue, @Param("addressId") Long addressId);
    public CraneInspectReport getOneUnitAddressInfo(String unitAddress);
    public List<CraneInspectReport> getCraneInspectReportInfoById(long id);
    public List<CraneInspectReport> getCraneInspectReportInfoFromCircle(@Param("maxLng") String maxLng,@Param("maxLat") String maxLat,@Param("minLng")String minLng,@Param("minLat") String minLat);
    /*
       新加入的，Sunhui
    */
    public List<CraneInspectReport> getEquipmentVarietyList();

    public float getAvgRiskValueByProvince(@Param("province") String province);
    public float getAvgRiskValueByProvinceAndCity(@Param("province") String province,@Param("city") String city);
    public List<Map<String,Float>> getProvinceAvgRiskValue();
    public List<Map<String,Float>> getCityAvgRiskValueByProvince(@Param("province") String province);
    public List<Map<String,Float>> getAreaAvgRiskValueByProvinceAndCity(@Param("province") String province,@Param("city") String city);
    public List<CraneInspectReport> fuzzyQuery(@Param("city") String city,@Param("area") String area,@Param("require") String require);
    public List<CraneInspectReport> fuzzyQueryByUnitAddress(@Param("city") String city,@Param("area") String area,@Param("require") String require);
    public List<CraneInspectReport> fuzzyQueryByUserPoint(@Param("city") String city,@Param("area") String area,@Param("require") String require);
    public List<CraneInspectReport> fuzzyQueryBySafeManager(@Param("city") String city,@Param("area") String area,@Param("require") String require);
    public List<CraneInspectReport> fuzzyQueryByEquipmentVariety(@Param("city") String city,@Param("area") String area,@Param("require") String require);
    public List<CraneInspectReport> fuzzyQueryByManufactureUnit(@Param("city") String city,@Param("area") String area,@Param("require") String require);
    public List<CraneInspectReport> getAreaInfo(@Param("id")Long id);
    public List<Map<String,Float>>getProvinceRiskRankFormRiskRange(@Param("startValue") float startValue,@Param("endValue")float endValue);
    public List<Map<String,Float>>getCityRiskRankFormRiskRange(@Param("startValue") float startValue,@Param("endValue")float endValue,@Param("province")String province);
    public List<Map<String,Float>>getAreaRiskRankFormRiskRange(@Param("startValue") float startValue,@Param("endValue")float endValue,@Param("province")String province,@Param("city")String city);

    public List<String> getCraneInfoByFuzzyUnitAddress(@Param("city") String city,@Param("area") String area,@Param("require") String require);
    public List<String> getCraneInfoByFuzzyUsePoint(@Param("city") String city,@Param("area") String area,@Param("require") String require);
    public List<String> getCraneInfoByFuzzySafeManager(@Param("city") String city,@Param("area") String area,@Param("require") String require);
    public List<String> getCraneInfoByFuzzyEquipmentVariety(@Param("city") String city,@Param("area") String area,@Param("require") String require);
    public List<String> getCraneInfoByFuzzyManufactureunit(@Param("city") String city,@Param("area") String area,@Param("require") String require);

    public long getCraneNumberByUnitAddress(String unitAddress);
    public List<String>getUseTimeList();
    public List<CraneInspectReport>getCraneInfoByCondition(@Param("province")String province,@Param("city")String city,@Param("area")String area,@Param("equipmentVariety")String equipmentVariety,@Param("sTime")String sTime,@Param("eTime")String eTime,@Param("startValue") float startValue,@Param("endValue")float endValue);
    public List<Map<String,Float>> getCityInfoByCondition(@Param("province")String province,@Param("equipmentVariety")String equipmentVariety,@Param("sTime")String sTime,@Param("eTime")String eTime,@Param("startValue") float startValue,@Param("endValue")float endValue);
    public  List<Map<String,Float>>getAreaInfoByCondition(@Param("province")String province,@Param("city")String city,@Param("equipmentVariety")String equipmentVariety,@Param("sTime")String sTime,@Param("eTime")String eTime,@Param("startValue") float startValue,@Param("endValue")float endValue);
    public  List<Map<String,Float>>getAreaInfoByCondition0(@Param("province")String province,@Param("city")String city,@Param("equipmentVariety")String equipmentVariety,@Param("sTime")String sTime,@Param("eTime")String eTime,@Param("startValue") float startValue,@Param("endValue")float endValue);
    public Map<String,Float> getCityInfoByCondition0(@Param("province")String province,@Param("city")String city);
    public List<CraneInspectReport>getCraneListByUploadReportId(@Param("reportId")long reportId);
    public String getClassNameByEquipmentVariety(@Param("equipmentVariety")String equipmentVariety);

    public List<Long>getCraneTypeByCraneInspectReportInfo();
    public List<String>getEquipmentVarietyByCraneType(long craneType);
    public void batchInsertToRiskValue(List<Map<String,String>> list);
    public List<Map<String,String>>listUploadedReport();
    public void updateUploadedReportByReportId(@Param("reportId")long reportId,@Param("status")String status);
    public void updateTaskIdUploadedReportByReportId(@Param("reportId")long reportId,@Param("taskId")long taskId);
    public Map<String,String>validateReportIsCalculated(long reportId);
    public void updateRiskValueByChooseReport(@Param("reportNumber")String reportNumber,@Param("riskvalue")String riskvalue);
    public void insertToUploadedReport(@Param("name")String name,@Param("uploadtime")Date time,@Param("userId")long userId,@Param("userName")String userName,@Param("path")String path,@Param("status")String status);
    public void updateUploadedReport(@Param("name")String name,@Param("uploadtime")Date time,@Param("userId")long userId,@Param("userName")String userName,@Param("path")String path,@Param("status")String status,@Param("reportId")long reportId);
    public long findIdFromUploadedReportByName(@Param("reportName") String reportName);
    public Long getCraneTypeIdByCraneEquipment(@Param("equipmentVariety")String equipmentVariety);
    public Map<String,String>validateUploadedReport(@Param("reportName") String reportName);
    public void batchInsertToAddressRiskValue(List<Map<String,Float>> list);
    public void batchInsertToCityRiskValue(List<Map<String,Float>> list);
    public Map<String,String>validateAddressRiskValueIsExistByAddressId(long addressId);
    public void updateAreaRiskValue(@Param("addressId")Long addressId,@Param("riskValue")Long riskValue);
    public Map<String,String>validateCityRiskValueIsExistByProvinceAndCity(@Param("province")String province,@Param("city")String city);
    public void updateCityRiskValue(@Param("province")String province,@Param("city")String city,@Param("riskValue")Float riskValue);
    public void updateProvinceRiskValue(@Param("province")String province,@Param("riskValue")Long riskValue);
    public int deleteCityRiskValue(@Param("province")String province,@Param("city")String city);
    public int deleteAreaRiskValue(@Param("addressId")Long addressId);
    public int dumpDataToProvinceRiskTemp(List<Map<String,String>> list);
    public int dumpDataToCityRiskTemp(List<Map<String,String>> list);
    public int dumpDataToAreaRiskTemp(List<Map<String,String>> list);
    public int dumpDataToAreaRisk();
    public int dumpDataToCityRisk();
    public int dumpDataToProvinceRisk();
    public List<Map<String,String>> selectProvinceRiskTemp();
    public List<Map<String,String>> selectCityRiskTemp();
    public List<Map<String,String>> selectAreaRiskTemp();
    public String getReportNumberFromRiskValue(@Param("reportNumber") String reportNumber);
    public int deleteRiskValueByReportNumber(@Param("reportNumber") String reportNumber);
    public int deleteProvinceRiskTempTable();
    public int deleteCityRiskTempTable();
    public int deleteAreaRiskTempTable();
    public int updateUpdatedReport(Map<String,String> map);
    public int deleteByReportId(@Param("reportId")Long reportId);
    public int deleteUploadedReport(Map<String,String> map);
    public List<CraneInspectReport>getEquipmentVarietyByUploadedReportId(@Param("reportId") Long reportId);
    public String findEquipmentVarietyFromCraneType(@Param("equipmentVariety")String equipmentVariety);
    public String getCalculateStatus(@Param("status")String status);
    public int updateRiskCalculateStatus(@Param("status")String status,@Param("id")long id,@Param("taskId")long taskId);
    public int insertToCalculateTask(CalculateTask calculateTask);
    public int insertToCalculateStatus(CalculateStatus calculateStatus);
    public List<Map<String,String>>getCalculateTaskInfo(@Param("status")String status);
    public void updateCalculateTask(CalculateTask calculateTask);
    public Map<String,String>validateIsExistCalculateStatus(CalculateStatus calculateStatus);
    public int deleteCalculateStatus(CalculateStatus calculateStatus);
}
