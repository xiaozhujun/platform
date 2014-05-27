package org.whut.inspectManagement.business.deptAndEmployee.entity;

/**
 * Created with IntelliJ IDEA.
 * User: chenqw
 * Date: 14-5-26
 * Time: 下午7:03
 * To change this template use File | Settings | File Templates.
 */
public class EmployeeRoleInspectTable {
    private long id;
    private long employeeRoleId;
    private long inspectTableId;
    private String employeeRoleName;
    private String inspectTableName;
    private long appId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEmployeeRoleId() {
        return employeeRoleId;
    }

    public void setEmployeeRoleId(long employeeRoleId) {
        this.employeeRoleId = employeeRoleId;
    }

    public long getInspectTableId() {
        return inspectTableId;
    }

    public void setInspectTableId(long inspectTableId) {
        this.inspectTableId = inspectTableId;
    }

    public String getEmployeeRoleName() {
        return employeeRoleName;
    }

    public void setEmployeeRoleName(String employeeRoleName) {
        this.employeeRoleName = employeeRoleName;
    }

    public String getInspectTableName() {
        return inspectTableName;
    }

    public void setInspectTableName(String inspectTableName) {
        this.inspectTableName = inspectTableName;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }


}
