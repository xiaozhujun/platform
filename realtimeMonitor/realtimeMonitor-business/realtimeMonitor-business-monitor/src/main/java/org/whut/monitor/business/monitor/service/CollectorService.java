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

    public void add(Collector collector) {
        collectorMapper.add(collector);
    }

    public List<Map<String,String>> getCollectorListByAppId(long appId) {
        return collectorMapper.getListByAppId(appId);
    }

    public int delete(Collector collector){
        return collectorMapper.delete(collector);
    }
    public int update(Collector collector){
        return collectorMapper.update(collector);
    }
    public List<Collector> getCollector(){
        return collectorMapper.getCollector();
    }
    public long getIdByNameAndAppId(String name,long appId) {
        return collectorMapper.getIdByNameAndAppId(name,appId);
    }
    public List<String> getCollectorNameListByAppId(long appId){
        return collectorMapper.getCollectorNameListByAppId(appId);
    }
    public List<String> getCollectorNames(long appId){
        return collectorMapper.getCollectorNames(appId);
    }
    public void deleteById(long Id){
        collectorMapper.deleteById(Id);
    }
    public List<Map<String,String>> getCollectorNameByAppId(long appId){
        return collectorMapper.getCollectorNameByAppId(appId);
    }
}