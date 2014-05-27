package org.whut.inspectManagement.business.deptAndEmployee.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: chenqw
 * Date: 14-5-14
 * Time: 上午10:59
 * To change this template use File | Settings | File Templates.
 */
public class Department {
    private long id;



    private String name;
    private long appId;
    private String description;
    private Date createtime;
    private String status;

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
    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
