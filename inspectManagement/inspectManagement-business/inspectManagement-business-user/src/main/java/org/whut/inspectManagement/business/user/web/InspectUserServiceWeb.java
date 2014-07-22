package org.whut.inspectManagement.business.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.deptAndEmployee.entity.Employee;
import org.whut.inspectManagement.business.deptAndEmployee.entity.EmployeeRole;
import org.whut.inspectManagement.business.deptAndEmployee.service.EmployeeRoleService;
import org.whut.inspectManagement.business.deptAndEmployee.service.EmployeeService;
import org.whut.inspectManagement.business.user.bean.InspectUser;
import org.whut.platform.business.user.entity.User;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.business.user.service.UserService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-7-12
 * Time: 下午4:41
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/inspectUser")
public class InspectUserServiceWeb {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRoleService employeeRoleService;

    private static final PlatformLogger logger = PlatformLogger.getLogger(InspectUserServiceWeb.class);

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/currentUser")
    @GET
    public String  currentUser(){
        String username = null;
        Object credential = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if(credential instanceof UserDetails){
            UserDetails userDetails = (UserDetails) credential;
            username = userDetails.getUsername();
        }else{
            username = (String)credential;
        }
        logger.info("current user is "+username);
        User user;
        Employee employee;
        EmployeeRole employeeRole;
        InspectUser inspectUser = new InspectUser();
        try {
            user = userService.findByName(username);
            employee = employeeService.getByUserId(user.getId());
            if(employee!=null){

                employeeRole = employeeRoleService.getByName(employee.getEmployeeRoleName(), UserContext.currentUserAppId());
                inspectUser.setRoleNum(employeeRole.getId());
                inspectUser.setName(employee.getName());
                inspectUser.setRole(employee.getEmployeeRoleName());
            }
            inspectUser.setNumber(user.getId());
            inspectUser.setUserName(user.getName());
            inspectUser.setUserRole(user.getRole());
            inspectUser.setId(user.getId());
            inspectUser.setImage(user.getImage());

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(inspectUser,JsonResultUtils.Code.SUCCESS);
    }
}
