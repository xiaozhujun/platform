package org.whut.rentManagement.business.deptAndEmployee.entity;


/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-22
 * Time: 下午1:02
 * To change this template use File | Settings | File Templates.
 */
public class SubEmployee {
    private long id;
    private String departmentId;
    private String name;
    private String sex;
    private String skillId;
    private String telephone;
    private String email;
    private String employedTime;
    private String position;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployedTime() {
        return employedTime;
    }

    public void setEmployedTime(String employedTime) {
        this.employedTime = employedTime;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    private String createTime;
    private long appId;
}
