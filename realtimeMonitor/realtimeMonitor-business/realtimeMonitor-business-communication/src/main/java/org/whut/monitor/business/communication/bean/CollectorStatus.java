package org.whut.monitor.business.communication.bean;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-9-19
 * Time: 下午7:17
 * To change this template use File | Settings | File Templates.
 */
public class CollectorStatus {

    private static Map<String,List<String>> collectorMap;

    public static Map<String, List<String>> getMap() {
        return collectorMap;
    }

    public static void setMap(Map<String, List<String>> map) {
        collectorMap = map;
    }
}
