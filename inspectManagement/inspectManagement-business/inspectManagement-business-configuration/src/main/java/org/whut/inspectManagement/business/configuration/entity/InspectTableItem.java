package org.whut.inspectManagement.business.configuration.entity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-5-21
 * Time: 上午10:13
 * To change this template use File | Settings | File Templates.
 */
public class InspectTableItem {
    private String inspectType;
    private String deviceType;
    private String area;
    private long areaId;
    private String name;
    private long id;
    private String isInput;
    private String description;
    private List<String> values;

    public String getInspectType() {
        return inspectType;
    }

    public void setInspectType(String inspectType) {
        this.inspectType = inspectType;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInput() {
        return isInput;
    }

    public void setInput(String input) {
        isInput = input;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
