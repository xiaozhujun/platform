package org.whut.trackSystem.business.trackRecord.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-2-14
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */
public interface TrackAnalyser {
    public void analyseTrack(Map<String, List<String>> map);
}
