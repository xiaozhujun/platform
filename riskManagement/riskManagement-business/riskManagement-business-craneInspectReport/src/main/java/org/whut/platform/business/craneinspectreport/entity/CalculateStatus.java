package org.whut.platform.business.craneinspectreport.entity;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 15-1-13
 * Time: 下午4:33
 * To change this template use File | Settings | File Templates.
 */
public class CalculateStatus {
    private long id;
    private String status;
    private long taskId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }
}
