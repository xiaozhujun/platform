package org.whut.rentManagement.business.contract.entity;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.whut.platform.fundamental.util.json.CustomDateDeserialize;

import java.util.Date;
/**
 * Created with IntelliJ IDEA.
 * User: Aaron
 * Date: 14-10-18
 * Time: 下午4:09
 * To change this template use File | Settings | File Templates.
 */
public class PreBury {
    private Long id;
    private Long contractId;
    private String preBuryMan;
    private Integer preBuryCount;
    @JsonDeserialize(using=CustomDateDeserialize.class)
    private Date preBuryTime;
    private String preBuryStatus;
    private String image;
    private Long appId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getPreBuryMan() {
        return preBuryMan;
    }

    public void setPreBuryMan(String preBuryMan) {
        this.preBuryMan = preBuryMan;
    }

    public Integer getPreBuryCount() {
        return preBuryCount;
    }

    public void setPreBuryCount(Integer preBuryCount) {
        this.preBuryCount = preBuryCount;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }
}
