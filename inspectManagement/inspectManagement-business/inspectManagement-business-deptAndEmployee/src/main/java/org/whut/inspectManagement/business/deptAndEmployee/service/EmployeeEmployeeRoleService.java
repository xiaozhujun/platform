package org.whut.inspectManagement.business.deptAndEmployee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.deptAndEmployee.entity.EmployeeEmployeeRole;
import org.whut.inspectManagement.business.deptAndEmployee.mapper.EmployeeEmployeeRoleMapper;

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

    public int deleteByEmployeeId(long employeeId)
    {
        return employeeEmployeeRoleMapper.deleteByEmployeeId(employeeId);
    }

    public List<EmployeeEmployeeRole> findByEmployeeRoleName(String employeeRoleName)
    {
        return employeeEmployeeRoleMapper.findByEmployeeRoleName(employeeRoleName);
    }

    public List<EmployeeEmployeeRole> getByEmployeeId(long employeeId)
    {
        return employeeEmployeeRoleMapper.getByEmployeeId(employeeId);
    }
    public List<EmployeeEmployeeRole> getByEmployeeRoleId(long employeeRoleId)
    {
        return employeeEmployeeRoleMapper.getByEmployeeRoleId(employeeRoleId);
    }
    public List<EmployeeEmployeeRole> getByEmployeeNameAndRole(String employeeName,String EmployeeRole,long appId){
        return employeeEmployeeRoleMapper.getByEmployeeNameAndRole(employeeName, EmployeeRole, appId);
    }

    public int deleteByEmployeeRoleId(long employeeRoleId)
    {
        return employeeEmployeeRoleMapper.deleteByEmployeeRoleId(employeeRoleId);
    }

    public EmployeeEmployeeRole getById(long id){
       return employeeEmployeeRoleMapper.getById(id);
    }
}
