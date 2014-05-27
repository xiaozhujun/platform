package org.whut.inspectManagement.business.menu.entity;

/**
 * Created with IntelliJ IDEA.
 * User: LJXia
 * Date: 14-5-27
 * Time: 上午10:26
 * To change this template use File | Settings | File Templates.
 */
public class AuthorityMenu {
    private long id;
    private String authorityName;
    private long authorityId;
    private long menuId;
    private String menuName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(long authorityId) {
        this.authorityId = authorityId;
    }

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
