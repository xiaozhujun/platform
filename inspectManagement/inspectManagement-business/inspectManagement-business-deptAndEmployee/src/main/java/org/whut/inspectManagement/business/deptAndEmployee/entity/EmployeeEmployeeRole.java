package org.whut.inspectManagement.business.deptAndEmployee.entity;

/**
 * Created with IntelliJ IDEA.
 * User: chenqw
 * Date: 14-5-21
 * Time: 下午3:40
 * To change this template use File | Settings | File Templates.
 */
public class EmployeeEmployeeRole {
    private long id;
    private long employeeId;
    private long employeeRoleId;
    private long appId;
    private String employeeName;
    private String employeeRoleName;

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getEmployeeRoleId() {
        return employeeRoleId;
    }

    public void setEmployeeRoleId(long employeeRoleId) {
        this.employeeRoleId = employeeRoleId;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public String getEmployeeRoleName() {
        return employeeRoleName;
    }

    public void setEmployeeRoleName(String employeeRoleName) {
        this.employeeRoleName = employeeRoleName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

}
