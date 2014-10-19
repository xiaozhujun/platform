package org.whut.rentManagement.business.deptAndEmployee.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.deptAndEmployee.entity.Department;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liubei1203
 * Date: 14-10-12
 * Time: 下午8:46
 * To change this template use File | Settings | File Templates.
 */
public interface DepartmentMapper extends AbstractMapper<Department> {
    public List<Department> findByCondition(@Param("appId") long appId, @Param("name") String name, @Param("createTime") String createTime);
    public long getIdByName(@Param("name") String name, @Param("appId") long appId);
    public String getNameById(long id);
}
