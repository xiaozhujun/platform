package org.whut.rentManagement.business.contract.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Aaron
 * Date: 14-10-21
 * Time: 下午5:20
 * To change this template use File | Settings | File Templates.
 */
public class subPreBury {
    private long id;
    private String contractName;
    private String preBuryMan;
    private String preBuryTime;
    private String preBuryStatus;
    private long appId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getPreBuryMan() {
        return preBuryMan;
    }

    public void setPreBuryMan(String preBuryMan) {
        this.preBuryMan = preBuryMan;
    }

    public String getPreBuryTime() {
        return preBuryTime;
    }

    public void setPreBuryTime(String preBuryTime) {
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
