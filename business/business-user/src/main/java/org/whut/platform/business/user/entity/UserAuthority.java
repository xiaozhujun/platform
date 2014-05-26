package org.whut.platform.business.user.entity;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-3-16
 * Time: 下午8:11
 * To change this template use File | Settings | File Templates.
 */
public class UserAuthority {
    private long id;
    private long userId;
    private long authorityId;
    private String userName;
    private String authorityName;
    private long appId;

    public long getId() {
        return id;
    }

    public long getAppId() {

        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(long authorityId) {
        this.authorityId = authorityId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }
}
