package org.whut.rentManagement.business.rentReport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.rentReport.entity.SearchReportBean;
import org.whut.rentManagement.business.rentReport.mapper.RentReportMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cwb
 * Date: 14-11-21
 * Time: 下午3:36
 * To change this template use File | Settings | File Templates.
 */
public class RentReportService {
    @Autowired
    private RentReportMapper rentReportMapper;

    public List<Map<String,String>> getRentTableRecordList(String deviceType,String deviceStatus,String sTime,String eTime,long appId){
        return rentReportMapper.getRentTableRecordList(deviceType,deviceStatus,sTime,eTime,appId);
    }

    public List<SearchReportBean> getRentTableRecordByBean(String deviceType,String deviceStatus,String sTime,String eTime,long appId){
        return rentReportMapper.getRentTableRecordByBean(deviceType,deviceStatus,sTime,eTime,appId);
    }

   /* public Map aaa(long appId){
        return rentReportMapper.aaa(appId);
    }*/
}
