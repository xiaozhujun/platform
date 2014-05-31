package org.whut.inspectManagement.business.deptAndEmployee.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.inspectManagement.business.deptAndEmployee.entity.Employee;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/5/19.
 */
public interface EmployeeMapper extends AbstractMapper<Employee> {
    public long getIdByName(String name);
    public List<Employee> findByCondition(Map<String, Object> map);
    public Employee getById(long id);
    public List<Employee> getListByAppId(long appId);
    public List<Employee> getByNameDepartmentAndRole(@Param("name")String name,@Param("departmentId")String departmentId,@Param("employeeRoleName")String employeeRoleName,@Param("appId")long appId);
}
