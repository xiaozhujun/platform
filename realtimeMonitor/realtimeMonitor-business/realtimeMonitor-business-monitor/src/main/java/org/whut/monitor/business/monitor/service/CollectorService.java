package org.whut.monitor.business.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.monitor.business.monitor.entity.Collector;
import org.whut.monitor.business.monitor.mapper.CollectorMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-15
 * Time: 下午9:22
 * To change this template use File | Settings | File Templates.
 */
public class CollectorService {
    @Autowired
    private CollectorMapper collectorMapper;

    public void add(Collector collector){
        collectorMapper.add(collector);
    }
    public List<Map<String,String>> getListByAppId(long appId){
        return collectorMapper.getListByAppId(appId);
    }
    public int update(Collector collector){
        return collectorMapper.update(collector);
    }
    public int delete(Collector collector){
        return collectorMapper.delete(collector);
    }
    public long getCollectorId(String name,String number,long appId){
        return collectorMapper.getCollectorId(name,number,appId);
    }
    public List<Collector> getCollectorByAreaId(long areaId){
        return collectorMapper.getCollectorByAreaId(areaId);
    }
    public long getIdByNameAndAppId(String groupName,String areaName,String collectorName,long appId){
        return collectorMapper.getIdByNameAndAppId(groupName,areaName,collectorName,appId);
    }

    public long getIdByNameAndGroupIdAndAreaIdAndAppId(String name,long groupId,long areaId,long appId) {
        return collectorMapper.getIdByNameAndGroupIdAndAreaIdAndAppId(name,groupId,areaId,appId);
    }
}