package org.whut.platform.business.datarule.entity;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-4-21
 * Time: 下午2:04
 * To change this template use File | Settings | File Templates.
 */

public class DataRole {
    private long id;
    private String Name;
    private String description;
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
