package org.whut.inspectManagement.business.inspectReport.entity;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-6-30
 * Time: 下午6:19
 * To change this template use File | Settings | File Templates.
 */
public class SearchReportBean {
    private String devname;

    private String createtime;

    private long exceptioncount;

    private String tagName;

    private String itemName;

    private String userName;

    private String inspectChoiceValue;

    private String mongoId;

    private String zhouqi;

    private String shijian;

    private String note;

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public long getExceptioncount() {
        return exceptioncount;
    }

    public void setExceptioncount(long exceptioncount) {
        this.exceptioncount = exceptioncount;
    }

    public String getDevname() {
        return devname;
    }

    public void setDevname(String devname) {
        this.devname = devname;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInspectChoiceValue() {
        return inspectChoiceValue;
    }

    public void setInspectChoiceValue(String inspectChoiceValue) {
        this.inspectChoiceValue = inspectChoiceValue;
    }

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public String getZhouqi() {
        return zhouqi;
    }

    public void setZhouqi(String zhouqi) {
        this.zhouqi = zhouqi;
    }

    public String getShijian() {
        return shijian;
    }

    public void setShijian(String shijian) {
        this.shijian = shijian;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
