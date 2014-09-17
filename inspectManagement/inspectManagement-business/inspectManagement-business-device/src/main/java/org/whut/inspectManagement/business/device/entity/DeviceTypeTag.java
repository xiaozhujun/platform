package org.whut.inspectManagement.business.device.entity;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-5-28
 * Time: 下午7:50
 * To change this template use File | Settings | File Templates.
 */

public class DeviceTypeTag {
    private String deviceType;
    private String deviceTypeNumber;
    private String deviceNumber;
    private String tagName;
    private long tagId;
    private String tagNumber;
    private String tagAreaName;
    private String tagAreaId;

    public String getTagAreaId() {
        return tagAreaId;
    }

    public void setTagAreaId(String tagAreaId) {
        this.tagAreaId = tagAreaId;
    }

    public String getTagAreaName() {
        return tagAreaName;
    }

    public void setTagAreaName(String tagAreaName) {
        this.tagAreaName = tagAreaName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceTypeNumber() {
        return deviceTypeNumber;
    }

    public void setDeviceTypeNumber(String deviceTypeNumber) {
        this.deviceTypeNumber = deviceTypeNumber;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    public String getTagNumber() {
        return tagNumber;
    }

    public void setTagNumber(String tagNumber) {
        this.tagNumber = tagNumber;
    }
}
