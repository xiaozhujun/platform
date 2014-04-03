package org.whut.platform.business.user.entity;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-3-16
 * Time: 下午8:00
 * To change this template use File | Settings | File Templates.
 */
public class Authority {
    private long id;
    private String name;
    private String description;
    private int status;

<<<<<<< HEAD
=======
    public void setId(long id){
        this.id = id;
    }

>>>>>>> 23dd51744e660700d6196a2d52cb2394d49b9f1c
    public long getId() {
        return id;
    }

<<<<<<< HEAD
    public void setId(long id) {
        this.id = id;
    }
=======
>>>>>>> 23dd51744e660700d6196a2d52cb2394d49b9f1c
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
