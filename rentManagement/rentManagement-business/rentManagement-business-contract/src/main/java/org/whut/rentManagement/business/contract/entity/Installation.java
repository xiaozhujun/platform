package org.whut.rentManagement.business.contract.entity;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.whut.platform.fundamental.util.json.CustomDateDeserialize;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Aaron
 * Date: 14-10-12
 * Time: 下午4:31
 * To change this template use File | Settings | File Templates.
 */
public class Installation {
    private long id;
    private long contractId;
    private String type;
    private String installMan;
    @JsonDeserialize(using=CustomDateDeserialize.class)
    private Date installTime;
    private String installStatus;
    private String image;
    private long appId;

    private String deviceId;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInstallMan() {
        return installMan;
    }

    public void setInstallMan(String installMan) {
        this.installMan = installMan;
    }

    public Date getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    public String getInstallStatus() {
        return installStatus;
    }

    public void setInstallStatus(String installStatus) {
        this.installStatus = installStatus;
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
