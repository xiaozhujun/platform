package org.whut.monitor.business.report.service;

import com.mongodb.DBObject;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-5-31
 * Time: 上午11:22
 * To change this template use File | Settings | File Templates.
 */
public class ReportServiceTest {
    @Test
    public void testGetDocument() throws Exception {

    }

    @Test
    public void testGetDocumentDataById() throws Exception {

    }

    @Test
    public void testQueryDocumentsByParams() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = dateFormat.parse("2014-05-31 14:00:00");
        System.out.println(startDate.getTime());
        Date endDate = dateFormat.parse("2014-05-31 23:55:00");
        System.out.println(endDate.getTime());
        ReportService reportService = new ReportService();
        List<DBObject> list =  reportService.queryDocuments(startDate.getTime(), endDate.getTime(), "101");
        System.out.println(list.size());

    }

    @Test
       public void testQueryDocumentsByJson() throws Exception {
        String json = "{$and:[{sensorNum:'101'},{time:{ $gte: 1401516000000}},{time:{ $lte: 1401551700000}}]}";
        ReportService reportService = new ReportService();
        List<DBObject> list =  reportService.queryDocuments(json);
        System.out.println(list.size());
    }
}
