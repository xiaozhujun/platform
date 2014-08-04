package org.whut.monitor.business.algorithm.factory;

import org.whut.monitor.business.algorithm.service.AlgorithmService;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-8-2
 * Time: 下午2:29
 * To change this template use File | Settings | File Templates.
 */
public class AlgorithmServiceFactory {
    public static AlgorithmService create() {
        return new AlgorithmService();
    }
}
