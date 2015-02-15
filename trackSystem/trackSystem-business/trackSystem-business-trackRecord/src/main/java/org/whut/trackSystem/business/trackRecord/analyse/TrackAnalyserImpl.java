package org.whut.trackSystem.business.trackRecord.analyse;

import org.whut.trackSystem.business.trackRecord.mapper.TrackAnalyser;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-2-14
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */
public class TrackAnalyserImpl implements TrackAnalyser {
    /**
     *
     * @param map
     */
    @Override
    public void analyseTrack(Map<String, List<String>> map) {

    }

    /**
     * 求一阶导数
     * @return
     */
    private List<Double> getFirstDerivative() {
        return null;
    }

    /**
     * 求二阶导数
     * @return
     */
    private List<Double> getSecondDerivative() {
        return null;
    }

    /**
     * 求曲率半径
     * @return
     */
    private List<Double> getCurvature() {
        return null;
    }
}
