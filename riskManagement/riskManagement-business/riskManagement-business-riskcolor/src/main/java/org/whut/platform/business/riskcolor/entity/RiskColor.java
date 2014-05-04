package org.whut.platform.business.riskcolor.entity;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-4-14
 * Time: 上午9:24
 * To change this template use File | Settings | File Templates.
 */
public class RiskColor {
    private long id;
    private float riskValue;
    private String riskColor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getRiskValue() {
        return riskValue;
    }

    public void setRiskValue(float riskValue) {
        this.riskValue = riskValue;
    }

    public String getRiskColor() {
        return riskColor;
    }

    public void setRiskColor(String riskColor) {
        this.riskColor = riskColor;
    }
}
