package org.whut.platform.business.datarule.entity;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-4-21
 * Time: 下午2:35
 * To change this template use File | Settings | File Templates.
 */

public class UserDataRole {
    private long id;
    private long userId;
    private long dRoleId;
    private String userName;
    private String dRoleName;

    public long getDRoleId() {
        return dRoleId;
    }

    public void setDRoleId(long dRoleId) {
        this.dRoleId = dRoleId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDRoleName() {
        return dRoleName;
    }

    public void setDRoleName(String dRoleName) {
        this.dRoleName = dRoleName;
    }
}
