package org.whut.inspectManagement.business.Department.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.inspectManagement.business.Department.entity.Department;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: chenqw
 * Date: 14-5-14
 * Time: 上午11:00
 * To change this template use File | Settings | File Templates.
 */
public interface DepartmentMapper extends AbstractMapper<Department> {
    public List<Department> findByCondition(@Param("appId") long appId,@Param("name") String name,@Param("createtime") String createtime);

    public long getIdByName(@Param("name") String name,@Param("appId") long appId);

    public String getNameById(long id);
}
