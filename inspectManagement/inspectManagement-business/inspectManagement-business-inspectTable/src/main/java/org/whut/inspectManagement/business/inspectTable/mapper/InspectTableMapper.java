package org.whut.inspectManagement.business.inspectTable.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.inspectManagement.business.inspectTable.entity.InspectTable;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;
/**
 * Created with IntelliJ IDEA.
 * User: choumiaoer
 * Date: 14-5-10
 * Time: 下午1:59
 * To change this template use File | Settings | File Templates.
 */
public interface InspectTableMapper extends AbstractMapper<InspectTable>{
    public List<InspectTable> findByCondition(Map<String,Object> map);
    public List<InspectTable> getListByAppId(long appId);
    public long getIdByNameAndAppId(@Param("name")String name,@Param("appId") long appId);
    public String getNameById(long id);
}
