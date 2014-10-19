package org.whut.rentManagement.business.contract.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-17
 * Time: 下午7:51
 * To change this template use File | Settings | File Templates.
 */
public class SubRemove {
    private long id;
    private String contractId;
    private String removeDeviceId;
    private String removeMan;
    private String  removeStatus;
    private String removeTime;
    private String addStatus;

    public String getAddStatus() {
        return addStatus;
    }

    public void setAddStatus(String addStatus) {
        this.addStatus = addStatus;
    }

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

    public String getRemoveDeviceId() {
        return removeDeviceId;
    }

    public void setRemoveDeviceId(String removeDeviceId) {
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

    public String getRemoveTime() {
        return removeTime;
    }

    public void setRemoveTime(String removeTime) {
        this.removeTime = removeTime;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }
}
