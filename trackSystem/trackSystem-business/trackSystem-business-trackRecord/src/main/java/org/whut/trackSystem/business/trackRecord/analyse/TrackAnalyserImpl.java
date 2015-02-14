package org.whut.trackSystem.business.trackRecord.analyse;

import com.mongodb.DBObject;
import org.whut.trackSystem.business.trackRecord.mapper.TrackAnalyser;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-2-14
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */
public class TrackAnalyserImpl implements TrackAnalyser {
    /**
     * 通过分析曲率半径的变化，抽取绘图必要数据，以减少向前台发送的数据量
     * @param list
     */
    @Override
    public void analyseTrack(List<DBObject> list) {
        //To change body of implemented methods use File | Settings | File Templates.
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
