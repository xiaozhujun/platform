package org.whut.platform.business.app.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sunhui
 * Date: 14-5-12
 * Time: 上午9:29
 * To change this template use File | Settings | File Templates.
 */
public class App {

    private Long id;
    private String name;
    private String description;
    private String status;
    private Date createtime;

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}

