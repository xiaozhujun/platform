package org.whut.inspectManagement.business.device.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-15
 * Time: 下午3:41
 * To change this template use File | Settings | File Templates.
 */
public class Device {
    private long id;
    private String name;
    private long number;
    private String description;
    private long appId;
    private long deviceTypeId;

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

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public long getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(long deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }
}
