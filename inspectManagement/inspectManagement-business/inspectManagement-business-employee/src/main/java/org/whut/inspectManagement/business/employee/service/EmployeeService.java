package org.whut.inspectManagement.business.employee.service;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.whut.inspectManagement.business.employee.entity.Employee;
        import org.whut.inspectManagement.business.employee.mapper.EmployeeMapper;

        import java.util.HashMap;
        import java.util.List;

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
}
