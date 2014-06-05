package org.whut.inspectManagement.business.device.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-5
 * Time: 上午9:52
 * To change this template use File | Settings | File Templates.
 */
public class SubInspectArea {
    private long id;
    private String name;
    private String description;
    private Date createtime;
    private String number;
    private String deviceTypeName;
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

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }
}
