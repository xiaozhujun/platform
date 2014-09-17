package org.whut.inspectManagement.business.inspectTable.entity;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-9-10
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */
public class InspectVersion {
    private long id;
    private long versionCode;
    private String downloadAddress;
    private long appId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(long versionCode) {
        this.versionCode = versionCode;
    }

    public String getDownloadAddress() {
        return downloadAddress;
    }

    public void setDownloadAddress(String downloadAddress) {
        this.downloadAddress = downloadAddress;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }
}
