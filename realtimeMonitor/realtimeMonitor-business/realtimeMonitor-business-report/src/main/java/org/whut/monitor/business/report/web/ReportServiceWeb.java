package org.whut.monitor.business.report.web;

import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.monitor.business.report.service.ReportService;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-5-31
 * Time: 下午2:53
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/report")
public class ReportServiceWeb {

    PlatformLogger logger = PlatformLogger.getLogger(ReportServiceWeb.class);

    @Autowired
    ReportService reportService;

    @Path("/query/{startTime}/{endTime}/{sensorNum}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @GET
    public String sensorReport(@Context HttpServletResponse response,@PathParam("startTime") String startTime,@PathParam("endTime") String endTime,@PathParam("sensorNum") String sensorNum){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start = null;
        String end = null;
        try {
           // start = format.parse(startTime);
           // end = format.parse(endTime);
             start= startTime+" "+"00:00:00";
            end= endTime+" "+"23:59:59";
            List<DBObject> list =  reportService.queryDocuments(start, end, sensorNum);
            StringBuffer buffer =  new StringBuffer("");
            for(DBObject object:list){

                buffer.append(object.get(FundamentalConfigProvider.get("monitor.mongo.field.sensor.time")));
                buffer.append(" ");
                buffer.append(object.get(FundamentalConfigProvider.get("monitor.mongo.field.sensor.id")));

                for(Object o:(List)object.get(FundamentalConfigProvider.get("monitor.mongo.field.sensor.data"))){
                    buffer.append(" ");
                    buffer.append(o);
                }
                buffer.append("\r\n");
            }
            response.setHeader("Content-Disposition", "attachment;filename=" + sensorNum + ".txt");
            return  buffer.toString();
        } catch (Exception e) {
           logger.error(e.getMessage());
           return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }
}
