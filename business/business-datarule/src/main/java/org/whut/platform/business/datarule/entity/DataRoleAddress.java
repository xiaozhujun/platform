package org.whut.platform.business.datarule.entity;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-4-21
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */

public class DataRoleAddress {
    private long id;
    private long dRoleId;
    private long addressId;
    private String dRoleName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDRoleId() {
        return dRoleId;
    }

    public void setDRoleId(long dRoleId) {
        this.dRoleId = dRoleId;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public String getDRoleName() {
        return dRoleName;
    }

    public void setDRoleName(String dRoleName) {
        this.dRoleName = dRoleName;
    }
}
