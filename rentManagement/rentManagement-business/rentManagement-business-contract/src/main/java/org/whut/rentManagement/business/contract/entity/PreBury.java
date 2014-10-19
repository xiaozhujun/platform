package org.whut.rentManagement.business.contract.entity;

import java.util.Date;
/**
 * Created with IntelliJ IDEA.
 * User: Aaron
 * Date: 14-10-18
 * Time: 下午4:09
 * To change this template use File | Settings | File Templates.
 */
public class PreBury {
    private long id;
    private long contractId;
    private String preBuryMan;
    private Date preBuryTime;
    private String preBuryStatus;
    private long appId;

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

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }
}
