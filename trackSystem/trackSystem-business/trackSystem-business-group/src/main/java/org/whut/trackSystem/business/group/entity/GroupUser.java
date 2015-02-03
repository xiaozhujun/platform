package org.whut.trackSystem.business.group.entity;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-2-3
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */
public class GroupUser {
    private Long id;
    private Long groupId;
    private Long userId;
    private Long deviceId;
    private String description;
    private Long appId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
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
