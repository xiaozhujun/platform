package org.whut.monitor.business.algorithm.impl;

import org.whut.monitor.business.algorithm.api.Calculator;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-10-19
 * Time: 上午9:37
 * To change this template use File | Settings | File Templates.
 */
public class MaxValueCalculator implements Calculator {
    private double maxValue;

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public boolean calculate(ArrayList sensorDataArray,double warnValue) {
        Double[] sensorData = new Double[sensorDataArray.size()];
        for(int i=0;i<sensorDataArray.size();i++){
            sensorData[i] = Double.parseDouble(sensorDataArray.get(i).toString());
        }
        double curMaxValue=sensorData[0];
        for(int i=0; i<sensorDataArray.size(); i++) {
            if(curMaxValue<sensorData[i])  curMaxValue=sensorData[i];
        }
        setMaxValue(curMaxValue);

        if (warnValue < getMaxValue()) {
            return true;
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
