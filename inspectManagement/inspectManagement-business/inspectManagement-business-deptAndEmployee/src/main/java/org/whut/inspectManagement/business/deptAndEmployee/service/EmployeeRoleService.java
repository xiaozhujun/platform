package org.whut.inspectManagement.business.deptAndEmployee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.deptAndEmployee.entity.EmployeeRole;
import org.whut.inspectManagement.business.deptAndEmployee.mapper.EmployeeRoleMapper;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenqw
 * Date: 14-5-15
 * Time: 下午3:45
 * To change this template use File | Settings | File Templates.
 */
public class EmployeeRoleService {
    @Autowired
    private EmployeeRoleMapper employeeRoleMapper;

    public void add(EmployeeRole employeeRole)
    {
        employeeRoleMapper.add(employeeRole);
    }

    public int update(EmployeeRole employeeRole)
    {
       return employeeRoleMapper.update(employeeRole);
    }

    public int delete(EmployeeRole employeeRole)
    {
        return employeeRoleMapper.delete(employeeRole);
    }

    public List<EmployeeRole> list()
    {
        return employeeRoleMapper.findByCondition(new HashMap<String, Object>());
    }

    public long getIdByName(String name,long appId)
    {
        return employeeRoleMapper.getIdByName(name,appId);
    }

    public EmployeeRole getById(long id){
        return employeeRoleMapper.getById(id);
    }

    public EmployeeRole getByName(String name,long appId){
        return employeeRoleMapper.getByName(name,appId);
    }

    public List<EmployeeRole> getListByAppId(long appId)
    {
        return employeeRoleMapper.getListByAppId(appId);
    }
}
