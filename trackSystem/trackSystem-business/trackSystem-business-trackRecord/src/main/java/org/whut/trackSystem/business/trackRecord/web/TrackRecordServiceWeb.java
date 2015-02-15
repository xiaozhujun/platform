package org.whut.trackSystem.business.trackRecord.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.trackSystem.business.trackRecord.entity.TrackRecord;
import org.whut.trackSystem.business.trackRecord.mapper.TrackFinder;
import org.whut.trackSystem.business.trackRecord.service.TrackRecordService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-2-10
 * Time: 下午8:30
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/trackRecord")
public class TrackRecordServiceWeb {
    @Autowired
    private TrackRecordService trackRecordService;
    @Autowired
    private TrackFinder trackFinder;

    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String list() {
        Long appId = UserContext.currentUserAppId();
        List<TrackRecord> list = trackRecordService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Path("/getMongoDataByCondition")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String getMongoDataByCondition(@FormParam("sTime")String sTime,@FormParam("eTime")String eTime,
                                          @FormParam("deviceNum")String deviceNum) {
        Map<String,List<String>> map = trackFinder.findTrackToMap(sTime, eTime, deviceNum);
        return JsonResultUtils.getObjectResultByStringAsDefault(map, JsonResultUtils.Code.SUCCESS);
    }

    @Path("/getListByCondition")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String getListByCondition(@FormParam("sTime")String sTime,@FormParam("eTime")String eTime,@FormParam("deviceId")Long deviceId) {
        sTime=sTime+" "+" 00:00:00";
        eTime=eTime+" "+" 23:59:59";
        Long appId = UserContext.currentUserAppId();
        List<Map<String,String>> list = trackRecordService.getListByCondition(sTime,eTime,deviceId,appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
}
