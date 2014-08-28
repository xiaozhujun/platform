package org.whut.inspectManagement.business.inspectResult.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-7-21
 * Time: 下午6:15
 * To change this template use File | Settings | File Templates.
 */
public class SearchBean {
    private long id;
    private String userName;
    private String itemName;
    private long itemId;
    private String tableName;
    private String deviceName;
    private String inspectChoiceValue;
    private String note;
    private Date createTime;
    private Date inspectTime;
    private long maintainId;
    private String maintainSuggest;
    private String image;



    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInspectChoiceValue() {
        return inspectChoiceValue;
    }

    public void setInspectChoiceValue(String inspectChoiceValue) {
        this.inspectChoiceValue = inspectChoiceValue;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getInspectTime() {
        return inspectTime;
    }

    public void setInspectTime(Date inspectTime) {
        this.inspectTime = inspectTime;
    }

    public long getMaintainId() {
        return maintainId;
    }

    public void setMaintainId(long maintainId) {
        this.maintainId = maintainId;
    }

    public String getMaintainSuggest() {
        return maintainSuggest;
    }

    public void setMaintainSuggest(String maintainSuggest) {
        this.maintainSuggest = maintainSuggest;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
