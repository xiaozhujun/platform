package org.whut.monitor.business.algorithm.api;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-10-19
 * Time: 上午9:36
 * To change this template use File | Settings | File Templates.
 */
public interface Calculator {
    public boolean calculate(ArrayList sensorDataArray,double warnValue);
}
