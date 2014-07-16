package org.whut.monitor.business.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.monitor.business.monitor.entity.Area;
import org.whut.monitor.business.monitor.mapper.AreaMapper;

import java.util.List;

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

    public void add(Area area){
         areaMapper.add(area);
    }
    public List<Area> getAreaListByAppId(long appId){
        return areaMapper.getListByAppId(appId);
    }
    public int delete(Area area){
        return areaMapper.delete(area);
    }
    public int update(Area area){
        return areaMapper.update(area);
    }
    public long getIDByNameAndAppId(String name,long appId){
        return areaMapper.getIdByNameAndAppId(name, appId);
    }
    public List<String> getAreaByGroupId(long groupId){
        return areaMapper.getAreaByGroupId(groupId);
    }
}
