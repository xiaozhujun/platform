package org.whut.rentManagement.business.badDebt.entity;

/**
 * Created with IntelliJ IDEA.
 * User: cwb
 * Date: 14-10-9
 * Time: 下午4:32
 * To change this template use File | Settings | File Templates.
 */
public class BadDebtDevice {
    private int id;
    private int badDebtId;
    private int deviceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBadDebtId() {
        return badDebtId;
    }

    public void setBadDebtId(int badDebtId) {
        this.badDebtId = badDebtId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }
}
