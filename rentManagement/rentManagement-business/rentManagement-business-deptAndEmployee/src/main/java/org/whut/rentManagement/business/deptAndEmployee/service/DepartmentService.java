package org.whut.rentManagement.business.deptAndEmployee.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.deptAndEmployee.entity.Department;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liubei1203
 * Date: 14-10-12
 * Time: 下午8:58
 * To change this template use File | Settings | File Templates.
 */
public class DepartmentService {
    @Autowired
    private org.whut.rentManagement.business.deptAndEmployee.mapper.DepartmentMapper departmentMapper;

    public void add(Department department){
        departmentMapper.add(department);
    }
    public void delete(Department department){
        departmentMapper.delete(department);
    }
    public void update(Department department){
        departmentMapper.update(department);
    }
    public List<Department> list(Long appId,String name, String createTime){
        return departmentMapper.findByCondition(appId, name, createTime);
    }
    public long getIdByName(String name,long appId){
        return departmentMapper.getIdByName(name, appId);
    }
    public String getNameById(long id){
        return departmentMapper.getNameById(id);
    }

}
