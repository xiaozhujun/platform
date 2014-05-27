package org.whut.inspectManagement.business.inspectTable.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.inspectTable.entity.InspectTable;
import org.whut.inspectManagement.business.inspectTable.mapper.InspectTableMapper;

import java.util.HashMap;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * User: choumiaoer
 * Date: 14-5-10
 * Time: 下午2:17
 * To change this template use File | Settings | File Templates.
 */
public class InspectTableService {
    @Autowired
    private InspectTableMapper inspectTableMapper;

    public void add(InspectTable inspectTable){
        inspectTableMapper.add(inspectTable);
    }
    public List<InspectTable> getListByAppId(long appId){
        return  inspectTableMapper.getListByAppId(appId);
    }
    public void update(InspectTable inspectTable){
        inspectTableMapper.update(inspectTable);
    }
    public void delete(InspectTable inspectTable){
        inspectTableMapper.delete(inspectTable );
    }
    public long getIdByName(String name,long appId){
        return inspectTableMapper.getIdByNameAndAppId(name, appId);
    }
    public String getNameById(long id){
        return inspectTableMapper.getNameById(id);
    }

}
