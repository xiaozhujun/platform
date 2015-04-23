package org.whut.deviceManagement.business.device.entity.report;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.whut.platform.fundamental.util.json.CustomDateDeserialize;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-12-2
 * Time: 下午4:19
 * To change this template use File | Settings | File Templates.
 */
public class DeviceAccount {

    private Long id;
    private Long mainDeviceId;
    private Long batchId;
    private String batchNumber;
    private Long supplierId;
    private String supplierName;
    private Long typeId;
    private String deviceType;
    private Long storehouseId;
    private Long storehouseName;
    private Long contractId;
    private String contractName;
    private String optionType;
    private String address;
    private String status;
    private String name;
    private String number;
    private String mainDeviceName;
    private String mainDeviceNumber;
    private String price;
    @JsonDeserialize(using=CustomDateDeserialize.class)
    private Date produceTime;

    @JsonDeserialize(using=CustomDateDeserialize.class)
    private Date createTime;
    private String priceUnit;

    private String lng;
    private String lat;
    private Long appId;

    private String deviceAge;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceAge() {
        return deviceAge;
    }

    public void setDeviceAge(String deviceAge) {
        this.deviceAge = deviceAge;
    }

    public Long getMainDeviceId() {
        return mainDeviceId;
    }

    public void setMainDeviceId(Long mainDeviceId) {
        this.mainDeviceId = mainDeviceId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Long getStorehouseId() {
        return storehouseId;
    }

    public void setStorehouseId(Long storehouseId) {
        this.storehouseId = storehouseId;
    }

    public Long getStorehouseName() {
        return storehouseName;
    }

    public void setStorehouseName(Long storehouseName) {
        this.storehouseName = storehouseName;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMainDeviceName() {
        return mainDeviceName;
    }

    public void setMainDeviceName(String mainDeviceName) {
        this.mainDeviceName = mainDeviceName;
    }

    public String getMainDeviceNumber() {
        return mainDeviceNumber;
    }

    public void setMainDeviceNumber(String mainDeviceNumber) {
        this.mainDeviceNumber = mainDeviceNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(Date produceTime) {
        this.produceTime = produceTime;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
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

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

}
