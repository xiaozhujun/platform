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
    private Date createtime;
    private long inspectAreaId;
    private String number;
    private String inspectTableName;
    private String isInputName;
    private String choiceValue;

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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }


    public long getInspectAreaId() {
        return inspectAreaId;
    }

    public void setInspectAreaId(long inspectAreaId) {
        this.inspectAreaId = inspectAreaId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getInspectTableName() {
        return inspectTableName;
    }

    public void setInspectTableName(String inspectTableName) {
        this.inspectTableName = inspectTableName;
    }

    public String getInputName() {
        return isInputName;
    }

    public void setInputName(String inputName) {
        isInputName = inputName;
    }

    public String getChoiceValue() {
        return choiceValue;
    }

    public void setChoiceValue(String choiceValue) {
        this.choiceValue = choiceValue;
    }
}
