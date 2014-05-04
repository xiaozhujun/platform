package org.whut.platform.business.map.entity;

/**
 * Created with IntelliJ IDEA.
 * User: zhuzhenhua
 * Date: 14-3-24
 * Time: 下午3:42
 * To change this template use File | Settings | File Templates.
 */
public class Company {

    private long id;

    private String name;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
