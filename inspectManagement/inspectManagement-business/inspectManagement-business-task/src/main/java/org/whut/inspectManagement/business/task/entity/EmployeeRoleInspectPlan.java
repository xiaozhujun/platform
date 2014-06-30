package org.whut.inspectManagement.business.task.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-6-30
 * Time: 下午4:16
 * To change this template use File | Settings | File Templates.
 */
public class EmployeeRoleInspectPlan {
    private long id;
    private long employeeRoleId;
    private long planId;
    private Date createtime;
    private int status;
    private long deviceId;
    private long appId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEmployeeRoleId() {
        return employeeRoleId;
    }

    public void setEmployeeRoleId(long employeeRoleId) {
        this.employeeRoleId = employeeRoleId;
    }

    public long getPlanId() {
        return planId;
    }

    public void setPlanId(long planId) {
        this.planId = planId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }
}
