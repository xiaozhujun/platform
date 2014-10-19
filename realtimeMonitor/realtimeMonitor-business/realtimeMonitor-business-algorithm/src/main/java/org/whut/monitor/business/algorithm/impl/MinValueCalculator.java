package org.whut.monitor.business.algorithm.impl;

import org.whut.monitor.business.algorithm.api.Calculator;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-10-19
 * Time: 上午9:38
 * To change this template use File | Settings | File Templates.
 */
public class MinValueCalculator implements Calculator {
    private double minValue;

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    @Override
    public boolean calculate(ArrayList sensorDataArray,double warnValue) {
        Double[] sensorData = new Double[sensorDataArray.size()];
        for(int i=0;i<sensorDataArray.size();i++){
            sensorData[i] = Double.parseDouble(sensorDataArray.get(i).toString());
        }
        double curMinValue=sensorData[0];
        for(int i=0; i<sensorDataArray.size(); i++) {
            if(curMinValue>sensorData[i])  curMinValue=sensorData[i];
        }
        setMinValue(curMinValue);

        if (warnValue > getMinValue()) {
            return true;
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
