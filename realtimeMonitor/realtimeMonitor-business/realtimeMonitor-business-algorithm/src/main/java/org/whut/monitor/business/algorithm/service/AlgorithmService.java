package org.whut.monitor.business.algorithm.service;

import org.whut.monitor.business.algorithm.impl.MaxValueCalculator;
import org.whut.monitor.business.algorithm.impl.MeanVarianceCalculator;
import org.whut.monitor.business.algorithm.impl.MinValueCalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-8-2
 * Time: 上午11:51
 * To change this template use File | Settings | File Templates.
 */
public class AlgorithmService {
    private MaxValueCalculator maxValueCalculator = new MaxValueCalculator();
    private MinValueCalculator minValueCalculator = new MinValueCalculator();
    private MeanVarianceCalculator meanVarianceCalculator = new MeanVarianceCalculator();
    private Map curData = new HashMap();
    private boolean isWarn;

    public Map getCurData() {
        return curData;
    }

    public boolean calculate(String warnType,ArrayList sensorDataArray,double warnValue) {
        String warnTypeList = "最大值   最小值   均方差";
        switch (warnTypeList.indexOf(warnType)/6) {
            case 0:
                isWarn = maxValueCalculator.calculate(sensorDataArray, warnValue);
                this.curData.put("最大值",maxValueCalculator.getMaxValue());
                return isWarn;
            case 1:
                isWarn = minValueCalculator.calculate(sensorDataArray, warnValue);
                this.curData.put("最小值",minValueCalculator.getMinValue());
                return isWarn;
            case 2:
                isWarn = meanVarianceCalculator.calculate(sensorDataArray, warnValue);
                this.curData.put("均方差",meanVarianceCalculator.getMeanVariance());
                return isWarn;
        }
        return false;
    }
}
