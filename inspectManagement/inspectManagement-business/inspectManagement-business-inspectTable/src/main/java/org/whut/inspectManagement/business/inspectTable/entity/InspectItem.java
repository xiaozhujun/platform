package org.whut.inspectManagement.business.inspectTable.entity;

import java.util.Date;
/**
 * Created with IntelliJ IDEA.
 * User: choumiaoer
 * Date: 14-5-11
 * Time: 上午10:41
 * To change this template use File | Settings | File Templates.
 */
public class InspectItem {
    private long id;
    private String name;
    private String description;
    private Date createtime;
    private long inspectTableId;
    private long inspectAreaId;
    private String number;
    private int isInput;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    private long appId;

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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public long getInspectTableId() {
        return inspectTableId;
    }

    public void setInspectTableId(long inspectTableId) {
        this.inspectTableId = inspectTableId;
    }

    public long getInspectAreaId() {
        return inspectAreaId;
    }

    public void setInspectAreaId(long inspectAreaId) {
        this.inspectAreaId = inspectAreaId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getInput() {
        return isInput;
    }

    public void setInput(int input) {
        isInput = input;
    }



    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }
}
