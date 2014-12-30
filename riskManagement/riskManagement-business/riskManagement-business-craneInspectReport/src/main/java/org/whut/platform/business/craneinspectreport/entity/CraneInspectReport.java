package org.whut.platform.business.craneinspectreport.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: zhuzhenhua
 * Date: 14-3-17
 * Time: 下午10:38
 * To change this template use File | Settings | File Templates.
 */
public class CraneInspectReport {
    private long id;

    private String  reportNumber;              //报告书编号

    private String   unitAddress;               //使用单位地址

    private  long    addressId;                  //地址ID

    private  String  organizeCode;                 //组织机构代码

    private String    userPoint;                   // 使用地点

    private String    safeManager;                  //安全管理人员

    private String    contactPhone;                //联系电话

    private String    equipmentVariety;              //设备品种

    private String     unitNumber;                   //单位内部编号

    private String     manufactureUnit;               //制造单位

    private String     manufactureLicenseNumber;      //制造许可编号

    private Date     manufactureDate;               //制造日期

    private String     specification;                 //规格型号

    private String     pNumber;                       //产品编号

    private String     workLevel;                     //工作级别

    private String     lng;                            //经度

    private String     lat;                            //纬度

    private String     singlePicURL;                //单个起重机的图片路径

    private String     typePicURL;                   //每种类型起重器的图片路径

    private Long      riskValue;                     //风险值

    private String    ratedLiftWeight;                //额定起重量

    private long      craneNumber;                    //起重机台数

    private long       useTime;                       //使用年限

    private String      conclusion;                     //结论

    private Float      span;                            //跨度

    private Float      range;                           //幅度

    private Float      liftHeight;                      //起升高度

    private Float      liftSpeed;                       //起升速度

    private Float      runSpeed;                        //运行速度

    private Float      cartSpeed;                       //大车运行速度

    private Float      carSpeed;                         //小车运行速度

    private Float      maxLiftMoment;                    //最大起重力矩

    private Long      uploadedReportId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public String getOrganizeCode() {
        return organizeCode;
    }

    public void setOrganizeCode(String organizeCode) {
        this.organizeCode = organizeCode;
    }

    public String getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(String userPoint) {
        this.userPoint = userPoint;
    }

    public String getSafeManager() {
        return safeManager;
    }

    public void setSafeManager(String safeManager) {
        this.safeManager = safeManager;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getEquipmentVariety() {
        return equipmentVariety;
    }

    public void setEquipmentVariety(String equipmentVariety) {
        this.equipmentVariety = equipmentVariety;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getManufactureUnit() {
        return manufactureUnit;
    }

    public void setManufactureUnit(String manufactureUnit) {
        this.manufactureUnit = manufactureUnit;
    }

    public String getManufactureLicenseNumber() {
        return manufactureLicenseNumber;
    }

    public void setManufactureLicenseNumber(String manufactureLicenseNumber) {
        this.manufactureLicenseNumber = manufactureLicenseNumber;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getpNumber() {
        return pNumber;
    }

    public void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }

    public String getWorkLevel() {
        return workLevel;
    }

    public void setWorkLevel(String workLevel) {
        this.workLevel = workLevel;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getSinglePicURL() {
        return singlePicURL;
    }

    public void setSinglePicURL(String singlePicURL) {
        this.singlePicURL = singlePicURL;
    }

    public String getTypePicURL() {
        return typePicURL;
    }

    public void setTypePicURL(String typePicURL) {
        this.typePicURL = typePicURL;
    }

    public Long getRiskValue() {
        return riskValue;
    }

    public void setRiskValue(Long riskValue) {
        this.riskValue = riskValue;
    }

    public String getRatedLiftWeight() {
        return ratedLiftWeight;
    }

    public void setRatedLiftWeight(String ratedLiftWeight) {
        this.ratedLiftWeight = ratedLiftWeight;
    }

    public long getCraneNumber() {
        return craneNumber;
    }

    public void setCraneNumber(long craneNumber) {
        this.craneNumber = craneNumber;
    }

    public long getUseTime() {
        return useTime;
    }

    public void setUseTime(long useTime) {
        this.useTime = useTime;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public Float getSpan() {
        return span;
    }

    public void setSpan(Float span) {
        this.span = span;
    }

    public Float getRange() {
        return range;
    }

    public void setRange(Float range) {
        this.range = range;
    }

    public Float getLiftHeight() {
        return liftHeight;
    }

    public void setLiftHeight(Float liftHeight) {
        this.liftHeight = liftHeight;
    }

    public Float getLiftSpeed() {
        return liftSpeed;
    }

    public void setLiftSpeed(Float liftSpeed) {
        this.liftSpeed = liftSpeed;
    }

    public Float getRunSpeed() {
        return runSpeed;
    }

    public void setRunSpeed(Float runSpeed) {
        this.runSpeed = runSpeed;
    }

    public Float getCartSpeed() {
        return cartSpeed;
    }

    public void setCartSpeed(Float cartSpeed) {
        this.cartSpeed = cartSpeed;
    }

    public Float getCarSpeed() {
        return carSpeed;
    }

    public void setCarSpeed(Float carSpeed) {
        this.carSpeed = carSpeed;
    }

    public Float getMaxLiftMoment() {
        return maxLiftMoment;
    }

    public void setMaxLiftMoment(Float maxLiftMoment) {
        this.maxLiftMoment = maxLiftMoment;
    }

    public Long getUploadedReportId() {
        return uploadedReportId;
    }

    public void setUploadedReportId(Long uploadedReportId) {
        this.uploadedReportId = uploadedReportId;
    }
}
