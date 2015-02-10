package org.whut.trackSystem.business.trackRecord.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.trackSystem.business.trackRecord.entity.TrackRecord;
import org.whut.trackSystem.business.trackRecord.service.TrackRecordService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

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

    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String list() {
        Long appId = UserContext.currentUserAppId();
        List<TrackRecord> list = trackRecordService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
}
