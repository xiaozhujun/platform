package org.whut.trackSystem.business.device.entity;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-12-15
 * Time: 下午4:19
 * To change this template use File | Settings | File Templates.
 */
public class DeviceType {
    private Long id;
    private String name;
    private String description;
    private Long appId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }
}
