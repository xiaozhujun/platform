package org.whut.platform.business.datarule.entity;
/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-4-23
 * Time: 下午6:44
 * To change this template use File | Settings | File Templates.
 */
public class SubDataRole {
    private long id;
    private String Name;
    private String description;
    private int status;
    private String province;
    private String city;
    private String area;

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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
