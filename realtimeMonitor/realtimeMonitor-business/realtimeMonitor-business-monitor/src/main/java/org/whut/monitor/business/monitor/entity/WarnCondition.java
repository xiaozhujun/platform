package org.whut.monitor.business.monitor.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-7-31
 * Time: 上午9:11
 * To change this template use File | Settings | File Templates.
 */
public class WarnCondition {
    long id;
    String groupName;
    String areaName;
    String collectorName;
    String sensorName;
    Date warnTime;
    String number;
    double curWarnValue;
    long appId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCollectorName() {
        return collectorName;
    }

    public void setCollectorName(String collectorName) {
        this.collectorName = collectorName;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public Date getWarnTime() {
        return warnTime;
    }

    public void setWarnTime(Date warnTime) {
        this.warnTime = warnTime;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getCurWarnValue() {
        return curWarnValue;
    }

    public void setCurWarnValue(double curWarnValue) {
        this.curWarnValue = curWarnValue;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }
}
