package org.whut.inspectManagement.business.task.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-6-30
 * Time: 下午4:14
 * To change this template use File | Settings | File Templates.
 */
public class UserInspectPlan {
    private long id;
    private long userId;
    private long inspectPlanId;
    private Date createtime;
    private int status;
    private long deviceId;
    private long appId;

    private String userName;
    private String inspectTableName;
    private String deviceName;
    private String inspectPlanName;

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

    public long getInspectPlanId() {
        return inspectPlanId;
    }

    public void setInspectPlanId(long inspectPlanId) {
        this.inspectPlanId = inspectPlanId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInspectTableName() {
        return inspectTableName;
    }

    public void setInspectTableName(String inspectTableName) {
        this.inspectTableName = inspectTableName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getInspectPlanName() {
        return inspectPlanName;
    }

    public void setInspectPlanName(String inspectPlanName) {
        this.inspectPlanName = inspectPlanName;
    }
}
