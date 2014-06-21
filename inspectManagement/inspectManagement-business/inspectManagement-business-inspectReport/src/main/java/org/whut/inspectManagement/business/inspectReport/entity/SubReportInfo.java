package org.whut.inspectManagement.business.inspectReport.entity;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-6-11
 * Time: 下午7:07
 * To change this template use File | Settings | File Templates.
 */
public class SubReportInfo {
     private String  itemName;

     private String  inspectChoiceValue;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getInspectChoiceValue() {
        return inspectChoiceValue;
    }

    public void setInspectChoiceValue(String inspectChoiceValue) {
        this.inspectChoiceValue = inspectChoiceValue;
    }
}
