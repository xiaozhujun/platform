package org.whut.platform.business.map.entity;

/**
 * Created with IntelliJ IDEA.
 * User: zhuzhenhua
 * Date: 14-3-24
 * Time: 下午3:45
 * To change this template use File | Settings | File Templates.
 */
public class Device {
    private long id;

    private String name;

    private String description;

    private String  picurl;

    private long riskvalue;

    private long cid;

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

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public long getRiskvalue() {
        return riskvalue;
    }

    public void setRiskvalue(long riskvalue) {
        this.riskvalue = riskvalue;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }
}
