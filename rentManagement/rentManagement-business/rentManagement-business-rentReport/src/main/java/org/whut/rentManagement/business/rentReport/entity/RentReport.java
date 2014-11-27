package org.whut.rentManagement.business.rentReport.entity;

/**
 * Created with IntelliJ IDEA.
 * User: cwb
 * Date: 14-11-21
 * Time: 下午2:48
 * To change this template use File | Settings | File Templates.
 */
public class RentReport {
    private String sTime;
    private String eTime;
    private String deviceType;
    private String deviceStatus;

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public String geteTime() {
        return eTime;
    }

    public void seteTime(String eTime) {
        this.eTime = eTime;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }
}
