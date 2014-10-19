package org.whut.rentManagement.business.contract.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-17
 * Time: 下午7:48
 * To change this template use File | Settings | File Templates.
 */
public class subSelfInspect {
    private long id;
    private String contractId;
    private String selfInspectDeviceId;
    private String selfInspectMan;
    private String selfInspectTime;
    private String selfInspectStatus;
    private long  appId;

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

    public String getSelfInspectDeviceId() {
        return selfInspectDeviceId;
    }

    public void setSelfInspectDeviceId(String selfInspectDeviceId) {
        this.selfInspectDeviceId = selfInspectDeviceId;
    }

    public String getSelfInspectMan() {
        return selfInspectMan;
    }

    public void setSelfInspectMan(String selfInspectMan) {
        this.selfInspectMan = selfInspectMan;
    }

    public String getSelfInspectTime() {
        return selfInspectTime;
    }

    public void setSelfInspectTime(String selfInspectTime) {
        this.selfInspectTime = selfInspectTime;
    }

    public String getSelfInspectStatus() {
        return selfInspectStatus;
    }

    public void setSelfInspectStatus(String selfInspectStatus) {
        this.selfInspectStatus = selfInspectStatus;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }
}
