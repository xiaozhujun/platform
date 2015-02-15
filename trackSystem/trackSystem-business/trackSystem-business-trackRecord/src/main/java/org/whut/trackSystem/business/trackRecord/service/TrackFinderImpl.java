package org.whut.trackSystem.business.trackRecord.service;

import com.mongodb.*;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.trackSystem.business.trackRecord.mapper.TrackFinder;
import org.whut.trackSystem.business.trackRecord.mongo.MongoConnectorAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-2-14
 * Time: 下午2:56
 * To change this template use File | Settings | File Templates.
 */
public class TrackFinderImpl implements TrackFinder {
    private static MongoConnectorAgent connectorAgent = new MongoConnectorAgent(
            FundamentalConfigProvider.get("device.mongo.deviceDB"),
            FundamentalConfigProvider.get("device.mongo.deviceCollection"));

    @Override
    public List<DBObject> findTrack(String startTime, String endTime, String devNum) {
        BasicDBObject query = createQuery(startTime, endTime, devNum);
        List<DBObject> l = connectorAgent.getDocumentList(query);
        return l;
    }

    private BasicDBObject createQuery(String startTime,String endTime,String devNum) {
        BasicDBObject query =new BasicDBObject();
        query.put("time", new BasicDBObject("$gte", startTime).append("$lte", endTime));
        query.put("deviceNum", devNum);
        return query;
    }

    @Override
    public Map<String, List<String>> findTrackToMap(String startTime, String endTime, String devNum) {
        BasicDBObject query = createQuery(startTime, endTime, devNum);
        DBCursor cursor = connectorAgent.getCursor(query);

        return convertResult2Map(cursor);
    }

    private Map<String,List<String>> convertResult2Map(DBCursor cursor) {
        List<String> l1 = new ArrayList<String>();
        List<String> l2 = new ArrayList<String>();
        boolean exists = cursor.hasNext();
        while (exists) {
            DBObject object = cursor.next();
            l1.add(object.get("lng").toString());
            l2.add(object.get("lat").toString());
            exists = cursor.hasNext();
        }
        Map<String,List<String>> map = new HashMap<String, List<String>>();
        map.put("lng",l1);
        map.put("lat",l2);
        return map;
    }


    public static void main(String[] args) {
        TrackFinder trackCalculator = new TrackFinderImpl();
        List<DBObject> list = trackCalculator.findTrack("2015-02-08 22:23:49","2015-02-08 22:23:51","001");

        for (int i=0;i<list.size();i++) {
            System.out.println(list.get(i).get("time") + " " + list.get(i).get("lng") + " "
                    + list.get(i).get("lat"));
        }
    }

}
