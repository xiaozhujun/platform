package org.whut.rentManagement.business.storehouse.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Steven
 * Date: 14-10-10
 * Time: 下午1:04
 * To change this template use File | Settings | File Templates.
 */
public class StoreHouse {
    private long id;
    private String name;
    private String description;
    private String address;
    private String linkman;
    private String telephone;
    private Date createTime;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }
}
