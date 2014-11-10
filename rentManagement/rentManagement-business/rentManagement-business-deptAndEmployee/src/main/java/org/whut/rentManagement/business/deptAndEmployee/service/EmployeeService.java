package org.whut.rentManagement.business.deptAndEmployee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.deptAndEmployee.entity.Employee;
import org.whut.rentManagement.business.deptAndEmployee.mapper.EmployeeMapper;
import org.whut.rentManagement.business.deptAndEmployee.mapper.SkillMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-10
 * Time: 下午6:55
 * To change this template use File | Settings | File Templates.
 */
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    SkillMapper skillMapper;
    public void add(Employee employee){ employeeMapper.add(employee);}
    public void delete(Employee employee){
        employeeMapper.delete(employee);
    }
    public void update(Employee employee){
        employeeMapper.update(employee);
    }
    public Employee getById(long id)
    {
        return employeeMapper.getById(id);
    }
    public List<Map<String,String>> getListByAppId(long appId)
    {
        return employeeMapper.getListByAppId(appId);
    }
    public long getIdByName(String name){
        return employeeMapper.getIdByName(name);
    }
}
