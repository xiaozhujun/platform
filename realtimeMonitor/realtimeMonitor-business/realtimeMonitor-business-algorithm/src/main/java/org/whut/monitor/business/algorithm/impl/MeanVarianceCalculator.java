package org.whut.monitor.business.algorithm.impl;

import org.whut.monitor.business.algorithm.api.Calculator;

import java.util.ArrayList;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-10-19
 * Time: 上午9:37
 * To change this template use File | Settings | File Templates.
 */
public class MeanVarianceCalculator implements Calculator {
    private double meanVariance;

    public double getMeanVariance() {
        return meanVariance;
    }

    public void setMeanVariance(double meanVariance) {
        this.meanVariance = meanVariance;
    }

    @Override
    public boolean calculate(ArrayList sensorDataArray,double warnValue) {
        Double[] sensorData = new Double[sensorDataArray.size()];
        for(int i=0;i<sensorDataArray.size();i++){
            sensorData[i] = Double.parseDouble(sensorDataArray.get(i).toString());
        }
        double sum = 0;
        for (int i=0;i<sensorDataArray.size();i++) {
            sum += sensorData[i];
        }
        double average = sum/(sensorDataArray.size());
        double temp = 0;
        for (int i=0;i<sensorDataArray.size();i++) {
            temp += pow((sensorData[i]-average),2);
        }
        temp /= sensorDataArray.size();
        setMeanVariance(sqrt(temp));

        if (warnValue < getMeanVariance()) {
            return true;
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
