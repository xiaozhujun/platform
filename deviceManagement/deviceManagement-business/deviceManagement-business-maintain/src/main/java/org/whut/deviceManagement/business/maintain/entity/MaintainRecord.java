package org.whut.deviceManagement.business.maintain.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-4-25
 * Time: 下午11:57
 * To change this template use File | Settings | File Templates.
 */
public class MaintainRecord {
    private Long id;
    private Long deviceId;
    private Long employedId;
    private Date maintainTime;
    private Date createTime;

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

    public Long getEmployedId() {
        return employedId;
    }

    public void setEmployedId(Long employedId) {
        this.employedId = employedId;
    }

    public Date getMaintainTime() {
        return maintainTime;
    }

    public void setMaintainTime(Date maintainTime) {
        this.maintainTime = maintainTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
