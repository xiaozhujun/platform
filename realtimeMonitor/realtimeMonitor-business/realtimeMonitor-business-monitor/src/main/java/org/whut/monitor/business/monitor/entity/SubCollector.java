package org.whut.monitor.business.monitor.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-15
 * Time: 下午11:25
 * To change this template use File | Settings | File Templates.
 */
public class SubCollector {
    private long id;
    private String name;
    private String number;
    private String description;
    private String status;
    private String maxFrequency;
    private String minFrequency;
    private String workFrequency;
    private String lastMessageTime;
    private long appId;
    private String addStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(String lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public String getAddStatus() {
        return addStatus;
    }

    public void setAddStatus(String addStatus) {
        this.addStatus = addStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
