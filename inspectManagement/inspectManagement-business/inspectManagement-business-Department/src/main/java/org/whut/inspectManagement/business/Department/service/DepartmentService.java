package org.whut.inspectManagement.business.Department.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.Department.entity.Department;
import org.whut.inspectManagement.business.Department.mapper.DepartmentMapper;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenqw
 * Date: 14-5-14
 * Time: 上午11:00
 * To change this template use File | Settings | File Templates.
 */
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public void add(Department department)
    {
        departmentMapper.add(department);
    }

    public void delete(Department department){
        departmentMapper.delete(department);
    }

    public void update(Department department){
        departmentMapper.update(department);
    }
    public long getIdByName(String name){ return departmentMapper.getIdByName(name);}

    public List<Department> list(){
        return departmentMapper.findByCondition(new HashMap<String, Object>());
    }

    public long getIdByName(String name,long appId)
    {
        return departmentMapper.getIdByName(name,appId);
    }
}
