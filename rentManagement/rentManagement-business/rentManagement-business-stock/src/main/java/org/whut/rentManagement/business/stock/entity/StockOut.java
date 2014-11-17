package org.whut.rentManagement.business.stock.entity;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.whut.platform.fundamental.util.json.CustomDateDeserialize;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-16
 * Time: 上午11:37
 * To change this template use File | Settings | File Templates.
 */
public class StockOut {
    private long id;
    private String number;
    private String driver;
    private String carNumber;
    private long contractId;
    private String handler;
    private long storehouseId;
    private String description;
    @JsonDeserialize(using=CustomDateDeserialize.class)
    private Date createTime;
    private long transportId;
    private String image;
    private long appId;

    private String deviceId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public long getContractId() {
        return contractId;
    }

    public void setContractId(long contractId) {
        this.contractId = contractId;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public long getStorehouseId() {
        return storehouseId;
    }

    public void setStorehouseId(long storehouseId) {
        this.storehouseId = storehouseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getTransportId() {
        return transportId;
    }

    public void setTransportId(long transportId) {
        this.transportId = transportId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
