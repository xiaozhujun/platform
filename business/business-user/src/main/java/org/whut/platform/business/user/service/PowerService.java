package org.whut.platform.business.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.user.entity.Power;
import org.whut.platform.business.user.mapper.PowerMapper;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-3-23
 * Time: 下午6:24
 * To change this template use File | Settings | File Templates.
 */
public class PowerService {

    @Autowired
    private PowerMapper mapper;

    public void add(Power power){
        mapper.add(power);
    }

    public int update(Power power){
        return mapper.update(power);
    }

    public int delete(Power power){
        return mapper.delete(power);
    }

    public List<Power> list(){
        return mapper.findByCondition(new HashMap<String, Object>());
    }

    public long getIdByResource(String name){
        return mapper.getIdByResource(name);
    }

    public List<Power> findByResource(String resource){
        return mapper.findByResource(resource);
    }
}
