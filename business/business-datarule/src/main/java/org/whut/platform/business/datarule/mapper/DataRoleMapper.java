package org.whut.platform.business.datarule.mapper;

import org.whut.platform.business.datarule.entity.DataRole;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-4-21
 * Time: 下午2:08
 * To change this template use File | Settings | File Templates.
 */

public interface DataRoleMapper extends AbstractMapper<DataRole>{
    public long getIdByName(String name);
    public List<DataRole> list();
    public DataRole get();
    public int delete(DataRole dataRole);
    public DataRole update(String name,String description,long status);
    public int updateByName(DataRole dataRole);
}
