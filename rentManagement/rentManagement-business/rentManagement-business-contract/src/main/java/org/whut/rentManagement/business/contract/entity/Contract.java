package org.whut.rentManagement.business.contract.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: cww
 * Date: 14-10-13
 * Time: 下午1:18
 * To change this template use File | Settings | File Templates.
 */
public class Contract {
    private long id;
    private long customerId;
    private String customerName;
    private String name;
    private String number;
    private String status;
    private Date startTime;
    private Date endTime;
    private Date signTime;
    private String projectLocation;
    private String chargeMan;
    private String preBuryMan;
    private Date preBuryTime;
    private String preBuryStatus;
    private long needInstallCount;
    private long installCount;
    private String netRegisterMan;
    private Date netRegisterTime;
    private String selfInspectStatus;
    private Date selfInspectTime;
    private String removeMan;
    private String removeStatus;
    private Date removeTime;
    private long appId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public String getProjectLocation() {
        return projectLocation;
    }

    public void setProjectLocation(String projectLocation) {
        this.projectLocation = projectLocation;
    }

    public String getChargeMan() {
        return chargeMan;
    }

    public void setChargeMan(String chargeMan) {
        this.chargeMan = chargeMan;
    }

    public String getPreBuryMan() {
        return preBuryMan;
    }

    public void setPreBuryMan(String preBuryMan) {
        this.preBuryMan = preBuryMan;
    }

    public Date getPreBuryTime() {
        return preBuryTime;
    }

    public void setPreBuryTime(Date preBuryTime) {
        this.preBuryTime = preBuryTime;
    }

    public String getPreBuryStatus() {
        return preBuryStatus;
    }

    public void setPreBuryStatus(String preBuryStatus) {
        this.preBuryStatus = preBuryStatus;
    }

    public long getNeedInstallCount() {
        return needInstallCount;
    }

    public void setNeedInstallCount(long needInstallCount) {
        this.needInstallCount = needInstallCount;
    }

    public long getInstallCount() {
        return installCount;
    }

    public void setInstallCount(long installCount) {
        this.installCount = installCount;
    }

    public String getNetRegisterMan() {
        return netRegisterMan;
    }

    public void setNetRegisterMan(String netRegisterMan) {
        this.netRegisterMan = netRegisterMan;
    }

    public Date getNetRegisterTime() {
        return netRegisterTime;
    }

    public void setNetRegisterTime(Date netRegisterTime) {
        this.netRegisterTime = netRegisterTime;
    }

    public String getSelfInspectStatus() {
        return selfInspectStatus;
    }

    public void setSelfInspectStatus(String selfInspectStatus) {
        this.selfInspectStatus = selfInspectStatus;
    }

    public Date getSelfInspectTime() {
        return selfInspectTime;
    }

    public void setSelfInspectTime(Date selfInspectTime) {
        this.selfInspectTime = selfInspectTime;
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
