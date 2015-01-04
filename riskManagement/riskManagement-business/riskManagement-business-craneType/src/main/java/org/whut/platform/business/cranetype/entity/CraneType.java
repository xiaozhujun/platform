package org.whut.platform.business.cranetype.entity;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 14-12-30
 * Time: 下午4:03
 * To change this template use File | Settings | File Templates.
 */
public class CraneType {
    private long id;

    private String name;

    private String model;

    private long riskmodelId;

    private String description;

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getRiskmodelId() {
        return riskmodelId;
    }

    public void setRiskmodelId(long riskmodelId) {
        this.riskmodelId = riskmodelId;
    }
}
