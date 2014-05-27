package org.whut.inspectManagement.business.deptAndEmployee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.deptAndEmployee.entity.EmployeeRoleInspectTable;
import org.whut.inspectManagement.business.deptAndEmployee.mapper.EmployeeRoleInspectTableMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenqw
 * Date: 14-5-26
 * Time: 下午7:35
 * To change this template use File | Settings | File Templates.
 */
public class EmployeeRoleInspectTableService {

    @Autowired
    private EmployeeRoleInspectTableMapper employeeRoleInspectTableMapper;
    public void add(EmployeeRoleInspectTable employeeRoleInspectTable)
    {
        employeeRoleInspectTableMapper.add(employeeRoleInspectTable);
    }

    public int delete(EmployeeRoleInspectTable employeeRoleInspectTable)
    {
        return employeeRoleInspectTableMapper.delete(employeeRoleInspectTable);
    }

    public int update(EmployeeRoleInspectTable employeeRoleInspectTable)
    {
        return employeeRoleInspectTableMapper.update(employeeRoleInspectTable);
    }

    public List<EmployeeRoleInspectTable> findByEmployeeRoleName(String employeeRoleName)
    {
        return employeeRoleInspectTableMapper.findByEmployeeRoleName(employeeRoleName);
    }

    public int deleteByEmployeeRoleId(long employeeRoleId)
    {
        return employeeRoleInspectTableMapper.deleteByEmployeeRoleId(employeeRoleId);
    }

    public List<EmployeeRoleInspectTable> getByEmployeeRoleId(long employeeRoleId)
    {
        return employeeRoleInspectTableMapper.getByEmployeeRoleId(employeeRoleId);
    }

    public List<EmployeeRoleInspectTable> findByInspectTableName(String insepctTableName)
    {
        return employeeRoleInspectTableMapper.findByInspectTableName(insepctTableName);
    }
}
