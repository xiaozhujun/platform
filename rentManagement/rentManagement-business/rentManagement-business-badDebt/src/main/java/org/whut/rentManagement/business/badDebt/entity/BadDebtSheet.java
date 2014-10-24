package org.whut.rentManagement.business.badDebt.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: cwb
 * Date: 14-10-9
 * Time: 下午4:19
 * To change this template use File | Settings | File Templates.
 */
public class BadDebtSheet {
    private long id;
    private String number;
    private String carNumber;
    private long customerId;
    private long contractId;
    private String handler;
    private long storehouseId;
    private String description;
    private Date createTime;
    private String creator;
    private long appId;

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

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }
}
