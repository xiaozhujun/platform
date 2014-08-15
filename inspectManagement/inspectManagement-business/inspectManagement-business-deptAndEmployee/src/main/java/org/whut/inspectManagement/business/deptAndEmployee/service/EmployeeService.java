package org.whut.inspectManagement.business.deptAndEmployee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.deptAndEmployee.entity.Employee;
import org.whut.inspectManagement.business.deptAndEmployee.mapper.EmployeeMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/5/19.
 */
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    public void add(Employee employee){ employeeMapper.add(employee);}
    public void delete(Employee employee){
        employeeMapper.delete(employee);
    }
    public void update(Employee employee){
        employeeMapper.update(employee);
    }
    public long getIdByName(String name){ return employeeMapper.getIdByName(name);}
    public List<Employee> list(){
        return employeeMapper.findByCondition(new HashMap<String, Object>());
    }

    public Employee getById(long id)
    {
        return employeeMapper.getById(id);
    }

    public Employee getByUserId(long id)
    {
        return employeeMapper.getByUserId(id);
    }

    public List<Map<String,Object>> getListByAppId(long appId)
    {
        return employeeMapper.getListByAppId(appId);
    }
    public List<Employee> getByNameDepartmentAndRole(String name,String departmentId,String employeeRoleName,long appId){
        return  employeeMapper.getByNameDepartmentAndRole(name,departmentId,employeeRoleName,appId);
    }

    public List<Map<String,String>> getNameAndTelListByAppId(long appId){
        return employeeMapper.getNameAndTelListByAppId(appId);
    }

    public long getTelById(long Id){
        return employeeMapper.getTelById(Id);
    }
}
