package org.whut.rentManagement.business.device.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Steven
 * Date: 14-10-30
 * Time: 下午4:12
 * To change this template use File | Settings | File Templates.
 */
public class SubDeviceType {
    private long id;
    private String name;
    private String description;
    private String createTime;
    private String unit;
    private String mainDevice;
    private String warnTime;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getWarnTime() {
        return warnTime;
    }

    public void setWarnTime(String warnTime) {
        this.warnTime = warnTime;
    }

    public String getMainDevice() {
        return mainDevice;
    }

    public void setMainDevice(String mainDevice) {
        this.mainDevice = mainDevice;
    }
}
