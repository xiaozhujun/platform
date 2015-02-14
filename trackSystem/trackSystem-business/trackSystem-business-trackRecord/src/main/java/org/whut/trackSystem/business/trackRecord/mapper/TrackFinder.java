package org.whut.trackSystem.business.trackRecord.mapper;

import com.mongodb.DBObject;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-2-14
 * Time: 下午2:54
 * To change this template use File | Settings | File Templates.
 */
public interface TrackFinder {
    public List<DBObject> findTrack(String startTime,String endTime,String devNum);
    public Map<String,List<String>> findTrackToMap(String startTime,String endTime,String devNum);
}
