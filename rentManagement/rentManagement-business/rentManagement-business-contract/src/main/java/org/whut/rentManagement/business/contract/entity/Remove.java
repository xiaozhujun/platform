package org.whut.rentManagement.business.contract.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-13
 * Time: 下午4:46
 * To change this template use File | Settings | File Templates.
 */
public class Remove {
    private long id;
    private long contractId;
    private long removeDeviceId;
    private String removeMan;
    private String  removeStatus;
    private Date  removeTime;
    private long  appId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getContractId() {
        return contractId;
    }

    public void setContractId(long contractId) {
        this.contractId = contractId;
    }

    public long getRemoveDeviceId() {
        return removeDeviceId;
    }

    public void setRemoveDeviceId(long removeDeviceId) {
        this.removeDeviceId = removeDeviceId;
    }

    public String getRemoveMan() {
        return removeMan;
    }

    public void setRemoveMan(String removeMan) {
        this.removeMan = removeMan;
    }

    public String getRemoveStatus() {
        return removeStatus;
    }

    public void setRemoveStatus(String removeStatus) {
        this.removeStatus = removeStatus;
    }

    public Date getRemoveTime() {
        return removeTime;
    }

    public void setRemoveTime(Date removeTime) {
        this.removeTime = removeTime;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }
}
