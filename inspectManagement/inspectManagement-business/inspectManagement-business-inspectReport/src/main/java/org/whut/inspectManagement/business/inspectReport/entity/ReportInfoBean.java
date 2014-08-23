package org.whut.inspectManagement.business.inspectReport.entity;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-6-29
 * Time: 下午3:58
 * To change this template use File | Settings | File Templates.
 */
public class ReportInfoBean {
    private String tName;

    private String devName;

    private String userName;

    private String createTime;

    private String tagName;

    private String itemName;

    private String inspectChoiceValue;

    private String note;

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getInspectChoiceValue() {
        return inspectChoiceValue;
    }

    public void setInspectChoiceValue(String inspectChoiceValue) {
        this.inspectChoiceValue = inspectChoiceValue;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
