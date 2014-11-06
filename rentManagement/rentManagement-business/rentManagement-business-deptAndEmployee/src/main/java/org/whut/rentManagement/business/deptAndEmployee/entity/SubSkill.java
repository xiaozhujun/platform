package org.whut.rentManagement.business.deptAndEmployee.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-30
 * Time: 下午5:00
 * To change this template use File | Settings | File Templates.
 */
public class SubSkill {
    private long id;
    private String name;
    private String description;
    private String createTime;

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
}
