package org.whut.inspectManagement.business.inspectResult.entity;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-8-15
 * Time: 下午7:18
 * To change this template use File | Settings | File Templates.
 */
public class ImageBean {
    private long itemId;
    private long itemRecordId;
    private long tableRecordId;

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getItemRecordId() {
        return itemRecordId;
    }

    public void setItemRecordId(long itemRecordId) {
        this.itemRecordId = itemRecordId;
    }

    public long getTableRecordId() {
        return tableRecordId;
    }

    public void setTableRecordId(long tableRecordId) {
        this.tableRecordId = tableRecordId;
    }
}
