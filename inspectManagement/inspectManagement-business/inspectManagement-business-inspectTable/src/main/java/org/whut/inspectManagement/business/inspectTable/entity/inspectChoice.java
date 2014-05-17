package org.whut.inspectManagement.business.inspectTable.entity;

/**
 * Created with IntelliJ IDEA.
 * User: choumiaoer
 * Date: 14-5-17
 * Time: 下午8:45
 * To change this template use File | Settings | File Templates.
 */
public class InspectChoice {
    private long id;
    private String choiceValue;
    private int appId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChoiceValue() {
        return choiceValue;
    }

    public void setChoiceValue(String choiceValue) {
        this.choiceValue = choiceValue;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }
}
