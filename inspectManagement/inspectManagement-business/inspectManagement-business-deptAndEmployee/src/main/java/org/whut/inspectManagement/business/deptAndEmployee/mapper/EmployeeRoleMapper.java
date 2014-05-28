package org.whut.inspectManagement.business.deptAndEmployee.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.inspectManagement.business.deptAndEmployee.entity.EmployeeRole;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: chenqw
 * Date: 14-5-15
 * Time: 下午3:41
 * To change this template use File | Settings | File Templates.
 */
public interface EmployeeRoleMapper extends AbstractMapper{
    public List<EmployeeRole> findByCondition(Map<String, Object> map);

    public long getIdByName(@Param("name") String name, @Param("appId") long appId);

    public EmployeeRole getById(long id);

    public EmployeeRole getByName(@Param("name") String name, @Param("appId") long appId);

    public List<EmployeeRole> getListByAppId(long appId);
}
