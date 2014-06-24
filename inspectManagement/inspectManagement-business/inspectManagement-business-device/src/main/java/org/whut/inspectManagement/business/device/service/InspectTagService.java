package org.whut.inspectManagement.business.device.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.device.entity.InspectTag;
import org.whut.inspectManagement.business.device.mapper.InspectTagMapper;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-19
 * Time: 上午11:43
 * To change this template use File | Settings | File Templates.
 */
public class InspectTagService {
    @Autowired
    private InspectTagMapper inspectTagMapper;
    public  void add(InspectTag inspectArea){
        inspectTagMapper.add(inspectArea);
    }

    public int delete(InspectTag inspectArea){
        return inspectTagMapper.delete(inspectArea);
    }

    public int update(InspectTag inspectArea){
        return  inspectTagMapper.update(inspectArea);
    }

    public List<InspectTag> list(){
        return inspectTagMapper.findByCondition(new HashMap<String, Object>());
    }

    public List<InspectTag> getListByAppId(long appId)
    {
        return inspectTagMapper.getListByAppId(appId);
    }

    public long getIdByNumber(String number,long appId){
        return inspectTagMapper.getIdByNumber(number,appId);
    }
}
