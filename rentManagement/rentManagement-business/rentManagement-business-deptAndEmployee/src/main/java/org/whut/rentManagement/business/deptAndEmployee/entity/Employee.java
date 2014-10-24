package org.whut.rentManagement.business.deptAndEmployee.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-10
 * Time: 下午12:50
 * To change this template use File | Settings | File Templates.
 */
public class Employee {
    private long id;
    private long departmentId;
    private String name;
    private String sex;
    private long skillId;
    private String telephone;
    private String email;
    private Date employedTime;
    private String position;
    private Date createTime;
    private long appId;

    public long getId() {
        return id;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public long getSkillId() {
        return skillId;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public Date getEmployedTime() {
        return employedTime;
    }

    public String getPosition() {
        return position;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public long getAppId() {
        return appId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setSkillId(long skillId) {
        this.skillId = skillId;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmployedTime(Date employedTime) {
        this.employedTime = employedTime;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }
}
