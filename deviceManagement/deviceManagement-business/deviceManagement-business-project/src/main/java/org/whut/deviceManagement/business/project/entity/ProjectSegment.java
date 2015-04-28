package org.whut.deviceManagement.business.project.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-4-27
 * Time: 下午3:02
 * To change this template use File | Settings | File Templates.
 */
public class ProjectSegment {
    private Long id;
    private Integer sequence;
    private Long projectUnitId;
    private Integer totalCount;
    private Integer fulfillCount;
    private Long deviceId;
    private Date createTime;
    private Long appId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Long getProjectUnitId() {
        return projectUnitId;
    }

    public void setProjectUnitId(Long projectUnitId) {
        this.projectUnitId = projectUnitId;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getFulfillCount() {
        return fulfillCount;
    }

    public void setFulfillCount(Integer fulfillCount) {
        this.fulfillCount = fulfillCount;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }
}
