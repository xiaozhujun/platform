package org.whut.inspectManagement.business.inspectTable.entity;

/**
 * Created with IntelliJ IDEA.
 * User: choumiaoer
 * Date: 14-5-17
 * Time: 下午9:10
 * To change this template use File | Settings | File Templates.
 */
public class InspectItem_choice {
    private int id;
    private int inspectItemId;
    private int inspectChoiceId;
    private int appId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInspectItemId() {
        return inspectItemId;
    }

    public void setInspectItemId(int inspectItemId) {
        this.inspectItemId = inspectItemId;
    }

    public int getInspectChoiceId() {
        return inspectChoiceId;
    }

    public void setInspectChoiceId(int inspectChoiceId) {
        this.inspectChoiceId = inspectChoiceId;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }
}
