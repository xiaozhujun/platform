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
    public List<Map<String,String>>findEquipmentAndCraneType(String equipmentVariety,String craneType){
        return mapper.findEquipmentAndCraneType(equipmentVariety,craneType);
    }
    public  String findCraneTypeIdByName(String name){
        return mapper.findCraneTypeIdByName(name);
    }
    public void addCraneInspectReportCraneType(Map<String,String> map){
        mapper.addCraneInspectReportCraneType(map);

    }
    public List<Map<String,String>> listCraneInspectAndType(){
        return mapper.listCraneInspectAndType();
    }
    public Long findIdByName(String name){
        return mapper.findIdByName(name);
    }
    public int updateCraneInspectAndType(Map<String,String> map){
        return mapper.updateCraneInspectAndType(map);
    }
    public int deleteCraneInspectAndType(Map<String,String> map){
        return mapper.deleteCraneInspectAndType(map);
    }
}
