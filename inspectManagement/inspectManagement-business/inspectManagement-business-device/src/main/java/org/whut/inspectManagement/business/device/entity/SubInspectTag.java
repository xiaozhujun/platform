package org.whut.inspectManagement.business.device.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-5
 * Time: 上午9:56
 * To change this template use File | Settings | File Templates.
 */
public class SubInspectTag {

    private long id;
    private String name;
    private String description;
    private Date createtime;
    private String number;
    private String inspectAreaName;
    private String deviceName;
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

    public String getInspectAreaName() {
        return inspectAreaName;
    }

    public void setInspectAreaName(String inspectAreaName) {
        this.inspectAreaName = inspectAreaName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }
}
