package org.whut.inspectManagement.business.inspectReport.entity;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-6-10
 * Time: 上午9:46
 * To change this template use File | Settings | File Templates.
 */
public class ReportSearch {
    private String sTime;

    private String eTime;

    private String deviceNum;

    private String userId;

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

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
