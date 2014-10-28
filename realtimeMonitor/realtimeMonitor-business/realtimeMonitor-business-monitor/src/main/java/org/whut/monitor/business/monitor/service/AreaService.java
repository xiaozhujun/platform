package org.whut.monitor.business.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.monitor.business.monitor.entity.Area;
import org.whut.monitor.business.monitor.mapper.AreaMapper;
import org.whut.monitor.business.monitor.mapper.GroupMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-15
 * Time: 下午3:20
 * To change this template use File | Settings | File Templates.
 */
public class AreaService {
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private GroupMapper groupMapper;

    public void add(Area area){
         areaMapper.add(area);
    }
    public List<Map<String,String>> getAreaListByAppId(long appId){
        return areaMapper.getListByAppId(appId);
    }
    public int delete(Area area){
        return areaMapper.delete(area);
    }
    public int update(Area area){
        return areaMapper.update(area);
    }
    public List<Area> getAreaByGroupId(long groupId){
        return areaMapper.getAreaByGroupId(groupId);
    }
    public long getIdByNameAndGroupIdAndAppId(String name,long groupId,long appId) {
        return areaMapper.getIdByNameAndGroupIdAndAppId(name,groupId,appId);
    }
    public List<Map<String,String>> getAreaNameListByAppId(long appId){
        return areaMapper.getAreaNameListByAppId(appId);
    }
    public List<String> getAreaNames(long groupId){
        return areaMapper.getAreaNames(groupId);
    }
    public List<String> getAreaListByGroupName(Long appId,String group){
        Long groupId=groupMapper.getIdByNameAndAppId(group,appId);
        return areaMapper.getAreaNames(groupId);
    }
}
