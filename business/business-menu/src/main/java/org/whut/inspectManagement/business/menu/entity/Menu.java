package org.whut.inspectManagement.business.menu.entity;

/**
 * Created with IntelliJ IDEA.
 * User: sunhui
 * Date: 14-5-23
 * Time: 上午1:48
 * To change this template use File | Settings | File Templates.
 */
public class Menu {
    long id;
    String name;
    long level;
    String url;
    String parentname;
    long pid;

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

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

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }
}
