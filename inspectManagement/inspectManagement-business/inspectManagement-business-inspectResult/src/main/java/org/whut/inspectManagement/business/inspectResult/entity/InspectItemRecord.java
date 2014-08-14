package org.whut.inspectManagement.business.inspectResult.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-5-13
 * Time: 上午9:47
 * To change this template use File | Settings | File Templates.
 */
public class InspectItemRecord {
    private long id;
    private long inspectTableId;
    private long inspectTagId;
    private long inspectItemId;
    private long inspectChoiceId;
    private String inspectChoiceValue;
    private long inspectTableRecordId;
    private long userId;
    private long deviceId;
    private long appId;
    private String note;
    private Date createTime;
    private Date inspectTime;
    private long maintainId;
    private String maintainSuggest;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInspectTableId() {
        return inspectTableId;
    }

    public void setInspectTableId(long inspectTableId){
        this.inspectTableId = inspectTableId;
    }

    public long getInspectTagId() {
        return inspectTagId;
    }

    public void setInspectTagId(long inspectTagId){
       this.inspectTagId = inspectTagId;
    }

    public long getInspectItemId() {
        return inspectItemId;
    }

    public void setInspectItemId(long inspectItemId){
        this.inspectItemId = inspectItemId;
    }

    public long getInspectChoiceId() {
        return inspectChoiceId;
    }

    public void setInspectChoiceId(long inspectChoiceId){
        this.inspectChoiceId = inspectChoiceId;
    }

    public String getInspectChoiceValue() {
        return inspectChoiceValue;
    }

    public void setInspectChoiceValue(String inspectChoiceValue){
        this.inspectChoiceValue = inspectChoiceValue;
    }

    public long getInspectTableRecordId() {
        return inspectTableRecordId;
    }

    public void setInspectTableRecordId(long inspectTableRecordId){
        this.inspectTableRecordId = inspectTableRecordId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId){
        this.userId = userId;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId){
        this.deviceId = deviceId;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId){
        this.appId = appId;
    }
    public String getNote() {
        return note;
    }

    public void setNote(String note){
        this.note = note;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getInspectTime() {
        return inspectTime;
    }

    public void setInspectTime(Date inspectTime) {
        this.inspectTime = inspectTime;
    }
}
