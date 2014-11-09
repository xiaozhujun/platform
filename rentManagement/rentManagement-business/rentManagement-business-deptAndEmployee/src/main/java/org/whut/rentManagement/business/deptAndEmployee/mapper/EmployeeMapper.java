package org.whut.rentManagement.business.deptAndEmployee.mapper;

import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.deptAndEmployee.entity.Employee;

import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-10
 * Time: 下午1:42
 * To change this template use File | Settings | File Templates.
 */
public interface EmployeeMapper extends AbstractMapper<Employee> {
   public Employee getById(long id);
    public List<Map<String,String>> getListByAppId(long appId);
    public long getIdByName(String name);
}
