package org.whut.inspectManagement.business.inspectResult.entity;

import java.util.Date;
/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-5-13
 * Time: 上午10:17
 * To change this template use File | Settings | File Templates.
 */
public class InspectTableRecord {
    private long id;
    private long inspectTableId;
    private long userId;
    private Date inspectTime;
    private Date createTime;
    private long exceptionCount;
    private long appId;
    private String mongoId;
    private long deviceId;

    public long getId() {
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getInspectTableId() {
        return inspectTableId;
    }

    public void setInspectTableId(long inspectTableId){
        this.inspectTableId = inspectTableId;
    }

    public long getUseId() {
        return userId;
    }

    public void setUseId(long useId){
        this.userId = useId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public long getExceptionCount() {
        return exceptionCount;
    }

    public void setExceptionCount(long exceptionCount){
       this.exceptionCount = exceptionCount;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId){
        this.appId = appId;
    }

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }


    public Date getInspectTime() {
        return inspectTime;
    }

    public void setInspectTime(Date inspectTime) {
        this.inspectTime = inspectTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
