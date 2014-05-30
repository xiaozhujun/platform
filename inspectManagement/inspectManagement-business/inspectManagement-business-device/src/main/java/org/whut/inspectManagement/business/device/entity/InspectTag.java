package org.whut.inspectManagement.business.device.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-17
 * Time: 下午10:02
 * To change this template use File | Settings | File Templates.
 */
public class InspectTag {
    private long id;
    private String name;
    private String description;
    private Date createtime;
    private String number;
    private long inspectAreaId;
    private long deviceId;
    private long appId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getInspectAreaId() {
        return inspectAreaId;
    }

    public void setInspectAreaId(long inspectAreaId) {
        this.inspectAreaId = inspectAreaId;
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
