package org.whut.trackSystem.business.trackRecord.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-2-10
 * Time: 下午8:24
 * To change this template use File | Settings | File Templates.
 */
public class TrackRecord {
    private Long id;
    private Long deviceId;
    private Date startTime;
    private Date endTime;
    private Long appId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }
}
