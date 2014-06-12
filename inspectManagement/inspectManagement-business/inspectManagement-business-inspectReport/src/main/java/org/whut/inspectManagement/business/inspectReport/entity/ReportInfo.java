package org.whut.inspectManagement.business.inspectReport.entity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-6-11
 * Time: 下午7:01
 * To change this template use File | Settings | File Templates.
 */
public class ReportInfo {
    private String tName;

    private String devName;

    private String userName;

    private String createTime;

    private String tagName;

    private List<SubReportInfo> subReportInfoList;

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

    public List<SubReportInfo> getSubReportInfoList() {
        return subReportInfoList;
    }

    public void setSubReportInfoList(List<SubReportInfo> subReportInfoList) {
        this.subReportInfoList = subReportInfoList;
    }
}
