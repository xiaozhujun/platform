package org.whut.rentManagement.business.deptAndEmployee.entity;

/**
 * Created with IntelliJ IDEA.
 * User: liubei1203
 * Date: 14-10-26
 * Time: 下午4:49
 * To change this template use File | Settings | File Templates.
 */
public class SubDepartment {
    private long id;
    private String name;
    private String description;
    private String createTime;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }
}
