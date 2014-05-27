package org.whut.inspectManagement.business.deptAndEmployee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.deptAndEmployee.entity.Department;
import org.whut.inspectManagement.business.deptAndEmployee.mapper.DepartmentMapper;

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

    public List<Department> list(long appId,String name,String createtime){
        return departmentMapper.findByCondition(appId,name,createtime);
    }

    public long getIdByName(String name,long appId)
    {
        return departmentMapper.getIdByName(name,appId);
    }

    public String getNameById(long id)
    {
        return departmentMapper.getNameById(id);
    }
}
