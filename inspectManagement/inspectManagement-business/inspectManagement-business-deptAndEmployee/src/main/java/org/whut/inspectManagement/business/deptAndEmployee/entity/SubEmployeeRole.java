package org.whut.inspectManagement.business.deptAndEmployee.entity;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-5-20
 * Time: 下午7:33
 * To change this template use File | Settings | File Templates.
 */
public class SubEmployeeRole {
    private long id;

    private String name;

    private long appId;

    private String authority;

    private String inspectTable;

    private String status;

    private String description;

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

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getInspectTable() {
        return inspectTable;
    }

    public void setInspectTable(String inspectTable) {
        this.inspectTable = inspectTable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
