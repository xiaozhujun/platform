package org.whut.rentManagement.business.deptAndEmployee.entity;

/**
 * Created with IntelliJ IDEA.
 * User: liubei1203
 * Date: 14-10-26
 * Time: 下午4:50
 * To change this template use File | Settings | File Templates.
 */
public class SubCarDriver {
    private long id;
    private String name;
    private long appId;
    private String createTime;
    private String carNumber;
    private String carType;


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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
}
