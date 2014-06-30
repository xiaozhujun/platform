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
}
