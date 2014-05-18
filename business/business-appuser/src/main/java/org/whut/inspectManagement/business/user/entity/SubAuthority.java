package org.whut.inspectManagement.business.user.entity;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-3-30
 * Time: 下午8:00
 * To change this template use File | Settings | File Templates.
 */
public class SubAuthority{
    private long id;
    private String name;
    private String description;
    private String status;
    private String resource;

    public void setId(long id){
        this.id = id;
    }

    public long getId() {
        return id;
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

    public void setResource(String resource){
        this.resource= resource;
    }

    public String getResource(){
        return resource;
    }
}
