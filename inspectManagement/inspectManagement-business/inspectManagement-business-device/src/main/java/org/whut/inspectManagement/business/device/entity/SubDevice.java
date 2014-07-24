package org.whut.inspectManagement.business.device.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-5
 * Time: 下午1:58
 * To change this template use File | Settings | File Templates.
 */
public class SubDevice {

    private long id;
    private String name;
    private String number;
    private String description;
    private long appId;
    private String deviceTypeName;
    private String image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
