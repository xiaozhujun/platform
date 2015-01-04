package org.whut.platform.business.cranetype.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.cranetype.entity.CraneType;
import org.whut.platform.business.cranetype.mapper.CraneTypeMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 14-12-30
 * Time: 下午4:13
 * To change this template use File | Settings | File Templates.
 */
public class CraneTypeService {
    @Autowired
    private CraneTypeMapper mapper;

    public List<CraneType> findByName(String name){
       Map<String,Object> map=new HashMap<String, Object>();
       map.put("name",name);
       return  mapper.findByCondition(map);
    }
    public void add(CraneType craneType){
       mapper.add(craneType);
    }
    public List<Map<String,String>>list(){
        return mapper.list();
    }
    public int update(CraneType craneType){
        return mapper.update(craneType);
    }
    public int deleteByCraneId(Long craneTypeId){
        return mapper.deleteByCraneId(craneTypeId);
    }
    public int delete(CraneType craneType){
        return mapper.delete(craneType);
    }
    public List<Map<String,String>> listModel(){
        return mapper.listModel();
    }
    public String findIdByModelName(String modelName){
       return mapper.findIdByModelName(modelName);
    }
}
