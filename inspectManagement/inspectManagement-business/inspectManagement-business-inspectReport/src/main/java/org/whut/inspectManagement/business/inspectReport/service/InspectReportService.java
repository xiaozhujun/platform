package org.whut.inspectManagement.business.inspectReport.service;

import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.inspectReport.mapper.InspectReportMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-6-10
 * Time: 下午10:01
 * To change this template use File | Settings | File Templates.
 */
public class InspectReportService {
    @Autowired
    private InspectReportMapper mapper;

    public List<Map<String,String>> getInspectTableRecordList(String createTime){
        return mapper.getInspectTableRecordList(createTime);
    }
    public Map<String,String>getInfoByMongoDbObject(Map<String,String> map){
        return mapper.getInfoByMongoDbObject(map);
    }
}
