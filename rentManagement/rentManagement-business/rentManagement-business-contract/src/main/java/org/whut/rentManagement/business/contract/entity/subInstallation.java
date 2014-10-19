package org.whut.rentManagement.business.contract.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Aaron
 * Date: 14-10-17
 * Time: 下午7:02
 * To change this template use File | Settings | File Templates.
 */
public class subInstallation {
    private long id;
    private String contractId;
    private String type;
    private String installDeviceId;
    private String installMan;
    private String installTime;
    private String installStatus;
    private long appId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInstallDeviceId() {
        return installDeviceId;
    }

    public void setInstallDeviceId(String installDeviceId) {
        this.installDeviceId = installDeviceId;
    }

    public String getInstallMan() {
        return installMan;
    }

    public void setInstallMan(String installMan) {
        this.installMan = installMan;
    }

    public String getInstallTime() {
        return installTime;
    }

    public void setInstallTime(String installTime) {
        this.installTime = installTime;
    }

    public String getInstallStatus() {
        return installStatus;
    }

    public void setInstallStatus(String installStatus) {
        this.installStatus = installStatus;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }
}
