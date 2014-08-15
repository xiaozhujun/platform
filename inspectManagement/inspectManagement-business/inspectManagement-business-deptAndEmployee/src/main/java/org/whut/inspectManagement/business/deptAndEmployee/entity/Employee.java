package org.whut.inspectManagement.business.deptAndEmployee.entity;

/**
 * Created by Administrator on 2014/5/19.
 */
public class Employee {
    long id;
    String name;
    String password;
    String sex;
    String employeeRoleName;
    String status;
    long appId;
    long departmentId;
    long userId;
    long tel;

    public String getStatus() {
        return status;
    }

    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getEmployeeRoleName() {
        return employeeRoleName;
    }

    public long getAppId() {
        return appId;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public long getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmployeeRoleName(String employeeRoleName) {
        this.employeeRoleName = employeeRoleName;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }
}
