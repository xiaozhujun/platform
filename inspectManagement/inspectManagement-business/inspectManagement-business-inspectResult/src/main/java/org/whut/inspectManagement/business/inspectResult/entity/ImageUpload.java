package org.whut.inspectManagement.business.inspectResult.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-8-12
 * Time: 上午8:49
 * To change this template use File | Settings | File Templates.
 */
public class ImageUpload {
    long id;
    long tableRecordId;
    long itemRecordId;
    long itemId;
    Date createTime;
    long appId;
    String image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTableRecordId() {
        return tableRecordId;
    }

    public void setTableRecordId(long tableRecordId) {
        this.tableRecordId = tableRecordId;
    }

    public long getItemRecordId() {
        return itemRecordId;
    }

    public void setItemRecordId(long itemRecordId) {
        this.itemRecordId = itemRecordId;
    }

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

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
