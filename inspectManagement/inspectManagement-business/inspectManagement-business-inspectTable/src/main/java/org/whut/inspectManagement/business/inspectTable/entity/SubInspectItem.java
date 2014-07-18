package org.whut.inspectManagement.business.inspectTable.entity;

import java.util.Date;
/**
 * Created with IntelliJ IDEA.
 * User: xjie
 * Date: 14-5-21
 * Time: 下午9:12
 * To change this template use File | Settings | File Templates.
 */
public class SubInspectItem {
    private long id;
    private String name;
    private String description;
    private String createtime;
    private String inspectArea;
    private String number;
    private String inspectTable;
    private String input;
    private String choiceValue;
    private String deviceType;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getInspectArea() {
        return inspectArea;
    }

    public void setInspectArea(String inspectArea) {
        this.inspectArea = inspectArea;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getInspectTable() {
        return inspectTable;
    }

    public void setInspectTable(String inspectTable) {
        this.inspectTable = inspectTable;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getChoiceValue() {
        return choiceValue;
    }

    public void setChoiceValue(String choiceValue) {
        this.choiceValue = choiceValue;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
