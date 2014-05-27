package org.whut.inspectManagement.business.deptAndEmployee.mapper;

import org.whut.inspectManagement.business.deptAndEmployee.entity.EmployeeRoleInspectTable;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenqw
 * Date: 14-5-26
 * Time: 下午7:32
 * To change this template use File | Settings | File Templates.
 */
public interface EmployeeRoleInspectTableMapper extends AbstractMapper{
    public int deleteByEmployeeRoleId(long EmployeeRoleId);
    public List<EmployeeRoleInspectTable> findByInspectTableName(String InspectTableName);

    public List<EmployeeRoleInspectTable> findByEmployeeRoleName(String EmployeeRoleName);

    public List<EmployeeRoleInspectTable> getByEmployeeRoleId(long employeeRoleId);
}
