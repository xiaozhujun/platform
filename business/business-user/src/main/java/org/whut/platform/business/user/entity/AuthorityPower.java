package org.whut.platform.business.user.entity;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-3-16
 * Time: 下午10:57
 * To change this template use File | Settings | File Templates.
 */
public class AuthorityPower {
    private long id;
    private long authorityId;
    private long powerId;
    private String authorityName;
    private String powerResource;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(long authorityId) {
        this.authorityId = authorityId;
    }

    public long getPowerId() {
        return powerId;
    }

    public void setPowerId(long powerId) {
        this.powerId = powerId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getPowerResource() {
        return powerResource;
    }

    public void setPowerResource(String powerResource) {
        this.powerResource = powerResource;
    }
}
