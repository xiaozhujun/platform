package org.whut.platform.business.user.entity;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-1-26
 * Time: 上午11:28
 * To change this template use File | Settings | File Templates.
 */
public class User {
    private long id;
    private String name;
    private String password;
    private String sex;
    private String role;
<<<<<<< HEAD

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
=======
>>>>>>> 23dd51744e660700d6196a2d52cb2394d49b9f1c

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex(){
         return sex;
    }

    public void setSex(String sex){
        this.sex = sex;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }
}
