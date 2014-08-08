package org.whut.monitor.business.algorithm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.monitor.business.monitor.service.SensorService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-8-2
 * Time: 上午11:51
 * To change this template use File | Settings | File Templates.
 */
public class AlgorithmService {
//    @Autowired
//    private SensorService sensorService;

    public double meanVariance(ArrayList sensorDataArray) {
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
        return sqrt(temp);
    }

    public boolean compare(double curValue,double warnValue) {
        if (curValue > warnValue) {
            return true;
        }
        else {
            return false;
        }
    }

    public int getNum(String warnType) {
        if (warnType.equals("均方差")) {
            return 0;
        }
        else if (warnType.equals("最大值")) {
            return 1;
        }
        else if (warnType.equals("最小值")){
            return 2;
        }
        return -1;
    }

    public double calculate(String type,ArrayList data) {
        int warnType;
        warnType = getNum(type);
        switch (warnType) {
            case -1: return 0;
            case 0: return meanVariance(data);
            case 1:System.out.println("计算最大值");break;
            case 2:System.out.println("计算最大值");break;
        }
        return 0;
    }
}
