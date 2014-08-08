package org.whut.inspectManagement.business.inspectLocate.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-7-11
 * Time: 下午6:09
 * To change this template use File | Settings | File Templates.
 */
public class InspectLocate {
    private long id;

    private long userId;

    private String userName;

    private String inspectStartTime;

    private String inspectEndTime;

    private long deviceId;

    private String devName;

    private String deviceNum;

    private long inspectTableId;

    private String inspectTableName;

    private String lng;

    private String lat;

    private long appId;

    private String status;

    private String address;

    private Date updateTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getInspectStartTime() {
        return inspectStartTime;
    }

    public void setInspectStartTime(String inspectStartTime) {
        this.inspectStartTime = inspectStartTime;
    }

    public String getInspectEndTime() {
        return inspectEndTime;
    }

    public void setInspectEndTime(String inspectEndTime) {
        this.inspectEndTime = inspectEndTime;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public long getInspectTableId() {
        return inspectTableId;
    }

    public void setInspectTableId(long inspectTableId) {
        this.inspectTableId = inspectTableId;
    }

    public String getInspectTableName() {
        return inspectTableName;
    }

    public void setInspectTableName(String inspectTableName) {
        this.inspectTableName = inspectTableName;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }
}
