package org.whut.inspectManagement.business.user.bean;

import org.whut.inspectManagement.business.deptAndEmployee.entity.EmployeeRole;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-7-12
 * Time: 下午4:58
 * To change this template use File | Settings | File Templates.
 */
public class InspectUser {
    private long number;
    private List<EmployeeRole> employeeRoleList;
    private String role;
    private long roleNum;
    private String name;
    private String userName;
    private long id;
    private String image;
    private String sex;
    private String userRole;
    private String appName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getRoleNum() {
        return roleNum;
    }

    public void setRoleNum(long roleNum) {
        this.roleNum = roleNum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public List<EmployeeRole> getEmployeeRoleList() {
        return employeeRoleList;
    }

    public void setEmployeeRoleList(List<EmployeeRole> employeeRoleList) {
        this.employeeRoleList = employeeRoleList;
    }


}
