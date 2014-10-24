package org.whut.rentManagement.business.badDebt.entity;

/**
 * Created with IntelliJ IDEA.
 * User: cwb
 * Date: 14-10-9
 * Time: 下午4:32
 * To change this template use File | Settings | File Templates.
 */
public class BadDebtDevice {
    private long id;
    private long badDebtId;
    private long deviceId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBadDebtId() {
        return badDebtId;
    }

    public void setBadDebtId(long badDebtId) {
        this.badDebtId = badDebtId;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }
}
