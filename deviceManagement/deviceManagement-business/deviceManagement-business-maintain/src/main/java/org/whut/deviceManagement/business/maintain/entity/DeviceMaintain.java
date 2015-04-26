package org.whut.deviceManagement.business.maintain.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-4-26
 * Time: 下午3:27
 * To change this template use File | Settings | File Templates.
 */
public class DeviceMaintain {
    private Long id;
    private Long deviceId;
    private Long maintainRuleId;
    private Date lastMaintainTime;

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

    public Long getMaintainRuleId() {
        return maintainRuleId;
    }

    public void setMaintainRuleId(Long maintainRuleId) {
        this.maintainRuleId = maintainRuleId;
    }

    public Date getLastMaintainTime() {
        return lastMaintainTime;
    }

    public void setLastMaintainTime(Date lastMaintainTime) {
        this.lastMaintainTime = lastMaintainTime;
    }
}
