package org.whut.inspectManagement.business.inspectTable.entity;

/**
 * Created with IntelliJ IDEA.
 * User: choumiaoer
 * Date: 14-5-17
 * Time: 下午9:10
 * To change this template use File | Settings | File Templates.
 */
public class InspectItemChoice {
    private long id;
    private long inspectItemId;
    private long inspectChoiceId;
    private long appId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInspectItemId() {
        return inspectItemId;
    }

    public void setInspectItemId(long inspectItemId) {
        this.inspectItemId = inspectItemId;
    }

    public long getInspectChoiceId() {
        return inspectChoiceId;
    }

    public void setInspectChoiceId(long inspectChoiceId) {
        this.inspectChoiceId = inspectChoiceId;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }
}
