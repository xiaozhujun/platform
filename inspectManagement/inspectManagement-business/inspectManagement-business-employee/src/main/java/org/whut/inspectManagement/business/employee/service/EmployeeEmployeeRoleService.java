package org.whut.inspectManagement.business.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.employee.entity.EmployeeEmployeeRole;
import org.whut.inspectManagement.business.employee.mapper.EmployeeEmployeeRoleMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenqw
 * Date: 14-5-21
 * Time: 下午4:02
 * To change this template use File | Settings | File Templates.
 */
public class EmployeeEmployeeRoleService {
    @Autowired
    private EmployeeEmployeeRoleMapper employeeEmployeeRoleMapper;

    public void add(EmployeeEmployeeRole employeeEmployeeRole)
    {
        employeeEmployeeRoleMapper.add(employeeEmployeeRole);
    }

    public int delete(EmployeeEmployeeRole employeeEmployeeRole)
    {
        return employeeEmployeeRoleMapper.delete(employeeEmployeeRole);
    }

    public int update(EmployeeEmployeeRole employeeEmployeeRole)
    {
        return employeeEmployeeRoleMapper.update(employeeEmployeeRole);
    }

    public List<EmployeeEmployeeRole> findByEmployeeName(String employeeName)
    {
        return employeeEmployeeRoleMapper.findByEmployeeName(employeeName);
    }

    public int deleteByEmployeeName(String employeeName)
    {
        return employeeEmployeeRoleMapper.deleteByEmployeeName(employeeName);
    }

    public List<EmployeeEmployeeRole> findByEmployeeRoleName(String employeeRoleName)
    {
        return employeeEmployeeRoleMapper.findByEmployeeRoleName(employeeRoleName);
    }
}
