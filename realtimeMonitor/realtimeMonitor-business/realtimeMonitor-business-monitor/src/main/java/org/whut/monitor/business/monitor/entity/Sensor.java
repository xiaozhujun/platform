package org.whut.monitor.business.monitor.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-13
 * Time: 下午4:58
 * To change this template use File | Settings | File Templates.
 */
public class Sensor {
    private long id;
    private String name;
    private String number;
    private String description;
    private long groupId;
    private long areaId;
    private long collectorId;
    private String maxFrequency;
    private String minFrequency;
    private String workFrequency;
    private String shouldWarn;
    private String warnType;
    private String warnValue;
    private long warnCount;
    private String warnStatus;
    private long appId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public long getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(long collectorId) {
        this.collectorId = collectorId;
    }

    public String getMaxFrequency() {
        return maxFrequency;
    }

    public void setMaxFrequency(String maxFrequency) {
        this.maxFrequency = maxFrequency;
    }

    public String getMinFrequency() {
        return minFrequency;
    }

    public void setMinFrequency(String minFrequency) {
        this.minFrequency = minFrequency;
    }

    public String getWorkFrequency() {
        return workFrequency;
    }

    public void setWorkFrequency(String workFrequency) {
        this.workFrequency = workFrequency;
    }

    public String getShouldWarn() {
        return shouldWarn;
    }

    public void setShouldWarn(String shouldWarn) {
        this.shouldWarn = shouldWarn;
    }

    public String getWarnType() {
        return warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }

    public String getWarnValue() {
        return warnValue;
    }

    public void setWarnValue(String warnValue) {
        this.warnValue = warnValue;
    }

    public String getWarnStatus() {
        return warnStatus;
    }

    public void setWarnStatus(String warnStatus) {
        this.warnStatus = warnStatus;
    }

    public long getWarnCount() {
        return warnCount;
    }

    public void setWarnCount(long warnCount) {
        this.warnCount = warnCount;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }
}
