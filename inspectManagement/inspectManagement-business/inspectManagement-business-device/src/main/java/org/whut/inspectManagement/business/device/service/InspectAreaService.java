package org.whut.inspectManagement.business.device.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.device.entity.InspectArea;
import org.whut.inspectManagement.business.device.mapper.InspectAreaMapper;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-19
 * Time: 上午11:43
 * To change this template use File | Settings | File Templates.
 */
public class InspectAreaService {
    @Autowired
    private InspectAreaMapper inspectAreaMapper;
    public  void add(InspectArea inspectArea){
        inspectAreaMapper.add(inspectArea);
    }

    public int delete(InspectArea inspectArea){
        return inspectAreaMapper.delete(inspectArea);
    }

    public int update(InspectArea inspectArea){
        return  inspectAreaMapper.update(inspectArea);
    }

    public List<InspectArea> list(){
        return inspectAreaMapper.findByCondition(new HashMap<String, Object>());
    }

    public long findIdByName(String name){
        return inspectAreaMapper.findIdByName(name);
    }

    public long getTypeIdByAreaId(long id){
        return inspectAreaMapper.getTypeIdByAreaId(id);
    }
}