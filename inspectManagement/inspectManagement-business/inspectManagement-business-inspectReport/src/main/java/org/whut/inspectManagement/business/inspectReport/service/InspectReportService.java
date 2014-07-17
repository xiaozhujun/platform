package org.whut.inspectManagement.business.inspectReport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.inspectReport.entity.SearchReportBean;
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

    public List<Map<String,String>> getInspectTableRecordList(String userId,String deviceId,String sTime,String eTime,long appId){
        return mapper.getInspectTableRecordList(userId,deviceId,sTime,eTime,appId);
    }
    public Map<String,String>getInfoByMongoDbObject(Map<String,String> map){
        return mapper.getInfoByMongoDbObject(map);
    }
    public List<SearchReportBean>getInspectTableRecordListByBean(String userId,String deviceId,String sTime,String eTime,long appId){
        return mapper.getInspectTableRecordListByBean(userId,deviceId,sTime,eTime,appId);
    }
    public List<SearchReportBean>getDeviceHistoryData(String sTime,String eTime,String deviceId,long appId){
        return mapper.getDeviceHistoryData(sTime,eTime,deviceId,appId);
    }
}
