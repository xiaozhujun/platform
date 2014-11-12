package org.whut.rentManagement.business.contract.entity;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-12
 * Time: 下午3:46
 * To change this template use File | Settings | File Templates.
 */
public class RemoveDevice {
    private long id;
    private long removeId;
    private long deviceId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRemoveId() {
        return removeId;
    }

    public void setRemoveId(long removeId) {
        this.removeId = removeId;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }
}
