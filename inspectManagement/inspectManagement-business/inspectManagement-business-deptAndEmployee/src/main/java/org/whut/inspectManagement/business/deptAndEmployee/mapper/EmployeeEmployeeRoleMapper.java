package org.whut.inspectManagement.business.deptAndEmployee.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.inspectManagement.business.deptAndEmployee.entity.EmployeeEmployeeRole;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenqw
 * Date: 14-5-21
 * Time: 下午4:00
 * To change this template use File | Settings | File Templates.
 */
public interface EmployeeEmployeeRoleMapper extends AbstractMapper {
    public List<EmployeeEmployeeRole> findByEmployeeName(String employeeName);
    public int deleteByEmployeeId(long employeeId);
    public int deleteByEmployeeName(String employeeName);
    public List<EmployeeEmployeeRole> findByEmployeeRoleName(String EmployeeRoleName);
    public List<EmployeeEmployeeRole> getByEmployeeId(long employeeId);
    public List<EmployeeEmployeeRole> getByEmployeeRoleId(long employeeRoleId);
    public List<EmployeeEmployeeRole> getByEmployeeNameAndRole(@Param("employeeName") String employeeName,@Param("employeeRole") String employeeRole,@Param("appId") long appId);
    public int deleteByEmployeeRoleId(long employeeRoleId);
    public EmployeeEmployeeRole getById(long id);
}
