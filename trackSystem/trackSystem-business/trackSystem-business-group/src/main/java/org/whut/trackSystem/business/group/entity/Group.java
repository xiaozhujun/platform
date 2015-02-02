package org.whut.trackSystem.business.group.entity;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-1-31
 * Time: 下午4:16
 * To change this template use File | Settings | File Templates.
 */
public class Group {
    private Long id;
    private String name;
    private String number;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
