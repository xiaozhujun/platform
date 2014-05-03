package org.whut.platform.business.datarule.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.datarule.entity.DataRole;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-4-21
 * Time: 下午2:10
 * To change this template use File | Settings | File Templates.
 */

public class DataRoleService {
    @Autowired
    private org.whut.platform.business.datarule.mapper.DataRoleMapper dataRoleMapper;

    public void add(DataRole dataRole){
       dataRoleMapper.add(dataRole);
    }

    public long getIdByName(String name){
        return  dataRoleMapper.getIdByName(name);
    }

    public List<DataRole> list(){
        return dataRoleMapper.list();
    }

    public DataRole get(long id){
        return dataRoleMapper.get();
    }

    public int update(DataRole dataRole){
        return dataRoleMapper.update(dataRole);
    }

    public int updateByName(DataRole dataRole){
        return dataRoleMapper.updateByName(dataRole);
    }
    public int delete(DataRole dataRole){
        return  dataRoleMapper.delete(dataRole);
    }
}
