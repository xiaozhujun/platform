package org.whut.inspectManagement.business.user.bean;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-7-12
 * Time: 下午4:58
 * To change this template use File | Settings | File Templates.
 */
public class InspectUser {
    private long number;
    private String role;
    private long roleNum;
    private String name;

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
}
