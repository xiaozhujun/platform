package org.whut.platform.business.craneinspectreport.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 15-1-13
 * Time: 下午4:30
 * To change this template use File | Settings | File Templates.
 */
public class CalculateTask {
    private long id;
    private Date startTime;
    private Date endTime;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
