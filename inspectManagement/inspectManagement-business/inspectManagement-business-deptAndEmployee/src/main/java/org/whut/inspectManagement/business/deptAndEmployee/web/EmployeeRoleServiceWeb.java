package org.whut.inspectManagement.business.deptAndEmployee.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.deptAndEmployee.entity.*;
import org.whut.inspectManagement.business.deptAndEmployee.service.EmployeeEmployeeRoleService;
import org.whut.inspectManagement.business.deptAndEmployee.service.EmployeeRoleInspectTableService;
import org.whut.inspectManagement.business.deptAndEmployee.service.EmployeeRoleService;
import org.whut.inspectManagement.business.deptAndEmployee.service.EmployeeService;
import org.whut.inspectManagement.business.inspectTable.service.InspectTableService;
import org.whut.platform.business.user.entity.User;
import org.whut.platform.business.user.entity.UserAuthority;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.business.user.service.AuthorityService;
import org.whut.platform.business.user.service.UserAuthorityService;
import org.whut.platform.business.user.service.UserService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenqw
 * Date: 14-5-15
 * Time: 下午3:52
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/employeeRole")
public class EmployeeRoleServiceWeb {

    private static PlatformLogger logger = PlatformLogger.getLogger(EmployeeRoleServiceWeb.class);

    @Autowired
    private EmployeeRoleService employeeRoleService;
    @Autowired
    private EmployeeEmployeeRoleService employeeEmployeeRoleService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private EmployeeRoleInspectTableService employeeRoleInspectTableService;
    @Autowired
    private InspectTableService inspectTableService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserAuthorityService userAuthorityService;



    @Produces( MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,@FormParam("status") String status,@FormParam("description") String description,@FormParam("authority") String authority,@FormParam("inspectTable") String inspectTable)
    {
        if(name==null||name.trim().equals("")||authority==null||inspectTable==null||inspectTable.equals(""))
        {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }
        long appid=UserContext.currentUserAppId();
        long id;
        try
        {
            id=employeeRoleService.getIdByName(name,appid);
        }
        catch (Exception e)
        {
            id=0;
        }
        if(id==0)
        {
            EmployeeRole employeeRole=new EmployeeRole();
            long authorityid = authorityService.getIdByName(authority);
            employeeRole.setAppId(appid);
            employeeRole.setDescription(description);
            employeeRole.setStatus(status);
            employeeRole.setAuthorityId(authorityid);
            employeeRole.setName(name);
            employeeRoleService.add(employeeRole);
            long employeeRoleId=employeeRoleService.getIdByName(name,appid);
            String [] inspectTableArray=inspectTable.split(";");
            for(int i=0;i<inspectTableArray.length;i++)
            {
                EmployeeRoleInspectTable employeeRoleInspectTable=new EmployeeRoleInspectTable();
                employeeRoleInspectTable.setAppId(appid);
                employeeRoleInspectTable.setEmployeeRoleName(name);
                employeeRoleInspectTable.setEmployeeRoleId(employeeRoleId);
                employeeRoleInspectTable.setInspectTableId(inspectTableService.getIdByName(inspectTableArray[i],appid));
                employeeRoleInspectTable.setInspectTableName(inspectTableArray[i]);
                employeeRoleInspectTableService.add(employeeRoleInspectTable);
            }
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"员工角色名已存在！");
    }

    @Produces( MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString)
    {
        SubEmployeeRole subEmployeeRole = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubEmployeeRole.class);
        if(subEmployeeRole.getName()==null||subEmployeeRole.getAppId()==0||subEmployeeRole.getName().equals("")||subEmployeeRole.getStatus()==null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }
        long appId=UserContext.currentUserAppId();
        long id;
        try {
            id =employeeRoleService.getIdByName(subEmployeeRole.getName(),appId);
        } catch (Exception ex) {
            id = 0;
        }
        if (id == 0||id==subEmployeeRole.getId())
        {
            employeeRoleInspectTableService.deleteByEmployeeRoleId(subEmployeeRole.getId());
            String[] inspectTableNameArray=subEmployeeRole.getInspectTable().split(";");
            for(int i=0;i<inspectTableNameArray.length;i++){
                long inspectTableId = inspectTableService.getIdByName(inspectTableNameArray[i],appId);
                EmployeeRoleInspectTable employeeRoleInspectTable=new EmployeeRoleInspectTable();
                employeeRoleInspectTable.setEmployeeRoleName(subEmployeeRole.getName());
                employeeRoleInspectTable.setEmployeeRoleId(subEmployeeRole.getId());
                employeeRoleInspectTable.setAppId(appId);
                employeeRoleInspectTable.setInspectTableId(inspectTableId);
                employeeRoleInspectTable.setInspectTableName(inspectTableNameArray[i]);
                employeeRoleInspectTableService.add(employeeRoleInspectTable);
            }
            EmployeeRole employeeRole= employeeRoleService.getById(subEmployeeRole.getId());
            //判断角色的权限是否更改
            boolean isAuthorityChanged=false;
            if(subEmployeeRole.getAuthority()!=authorityService.getNameById(employeeRole.getAuthorityId()))
            {
                isAuthorityChanged=true;
            }
            employeeRole.setAuthorityId(authorityService.getIdByName(subEmployeeRole.getAuthority()));
            employeeRole.setName(subEmployeeRole.getName());
            employeeRole.setStatus(subEmployeeRole.getStatus());
            employeeRole.setDescription(subEmployeeRole.getDescription());
            employeeRoleService.update(employeeRole);

            List<EmployeeEmployeeRole> employeeEmployeeRoleList=employeeEmployeeRoleService.getByEmployeeRoleId(subEmployeeRole.getId());
            for(EmployeeEmployeeRole employeeEmployeeRole:employeeEmployeeRoleList){
                if(employeeEmployeeRole.getEmployeeRoleName()!=subEmployeeRole.getName())
                {
                    Employee employee=employeeService.getById(employeeEmployeeRole.getEmployeeId());
                    employeeEmployeeRole.setEmployeeRoleName(subEmployeeRole.getName());
                    employeeEmployeeRoleService.update(employeeEmployeeRole);

                    List<EmployeeEmployeeRole>employeeEmployeeRoleByIdList=employeeEmployeeRoleService.getByEmployeeId(employeeEmployeeRole.getEmployeeId());
                    String employeeRoleName="";
                    for(int j=0;j<employeeEmployeeRoleByIdList.toArray().length;j++)
                    {
                        if(j==0)
                        {
                            employeeRoleName=employeeEmployeeRoleByIdList.get(j).getEmployeeRoleName();
                        }
                        else
                        {
                            employeeRoleName+=";"+employeeEmployeeRoleByIdList.get(j).getEmployeeRoleName();
                        }
                    }
                    employee.setEmployeeRoleName(employeeRoleName);
                    employeeService.update(employee);
                }
            }
            //角色的权限如果更改就要更改对应user的权限
            if(isAuthorityChanged)
            {
                List<Long> employeeIdList=new ArrayList<Long>();
                //获取所有拥有该角色的员工编号
                for(EmployeeEmployeeRole employeeEmployeeRole:employeeEmployeeRoleList)
                {
                    boolean isEmployeeIdExist=false;
                    for(int i=0;i<employeeIdList.toArray().length;i++)
                    {
                        if(employeeIdList.get(i)==employeeEmployeeRole.getEmployeeId()){
                            isEmployeeIdExist=true;
                        }
                    }
                    if(!isEmployeeIdExist) {
                        employeeIdList.add(employeeEmployeeRole.getEmployeeId());
                    }
                }

                for(int i=0;i<employeeIdList.toArray().length;i++)
                {
                    long userId=employeeService.getById(employeeIdList.get(i)).getUserId();
                    //原先user的权限列表
                    List<UserAuthority> userAuthorityList=userAuthorityService.findByUserId(userId);
                    String role="";
                    String[] employeeRoleList=employeeService.getById(employeeIdList.get(i)).getEmployeeRoleName().split(";");
                    //现在user的应该有的权限列表
                    ArrayList<Long> authorityIdList=new ArrayList<Long>();
                    for(int j=0;j<employeeRoleList.length;j++)
                    {
                        boolean isExist=false;
                        employeeRole= employeeRoleService.getByName(employeeRoleList[j],appId);
                        for(long aid:authorityIdList)
                        {
                            if(aid==employeeRole.getAuthorityId()) {
                                isExist=true;
                            }
                        }
                        if(!isExist) {
                            authorityIdList.add(employeeRole.getAuthorityId());
                        }
                    }


                    if (userAuthorityList.toArray().length==0){
                        UserAuthority userAuthority=userAuthorityList.get(0);
                        role=authorityService.getNameById(authorityIdList.get(0));
                        userAuthority.setAuthorityId(authorityIdList.get(0));
                        userAuthority.setAuthorityName(role);
                        userAuthorityService.update(userAuthority);
                        User user =userService.getById(userId);
                        user.setRole(role);
                        userService.update(user);
                    }
                    else {
                        for(int j=0;j<authorityIdList.toArray().length;j++){
                            if(j==0)  {
                                role=authorityService.getNameById(authorityIdList.get(j));
                            }
                            else{
                                role+=";"+authorityService.getNameById(authorityIdList.get(j));
                            }
                        }
                        User user=userService.getById(userId);
                        user.setRole(role);
                        userService.update(user);

                        userAuthorityService.deleteByUserName(user.getName());

                        String[] roleArray = role.split(";");
                        for (int k= 0;k < roleArray.length;k++) {
                            long authorityId = authorityService.getIdByName(roleArray[k]);
                            UserAuthority userAuthority = new UserAuthority();
                            userAuthority.setUserId(userId);
                            userAuthority.setAuthorityId(authorityId);
                            userAuthority.setUserName(user.getName());
                            userAuthority.setAuthorityName(roleArray[k]);
                            userAuthorityService.add(userAuthority);
                        }
                    }
                }
            }
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else
        {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"修改的员工角色名已存在！");
        }
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString)
    {
        long appId=UserContext.currentUserAppId();
        SubEmployeeRole subEmployeeRole=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubEmployeeRole.class);
        EmployeeRole employeeRole=employeeRoleService.getById(subEmployeeRole.getId());

        int result2=employeeRoleInspectTableService.deleteByEmployeeRoleId(subEmployeeRole.getId());
        int result1=employeeRoleService.delete(employeeRole);
        List<EmployeeEmployeeRole> employeeEmployeeRoleList=employeeEmployeeRoleService.getByEmployeeRoleId(subEmployeeRole.getId());
        List<Long> employeeIdlist=new ArrayList<Long>();
        //获取所有拥有该角色的员工编号
        for(EmployeeEmployeeRole employeeEmployeeRole:employeeEmployeeRoleList)
        {
            boolean isEmployeeIdExist=false;
            for(int i=0;i<employeeIdlist.toArray().length;i++)
            {
                if(employeeIdlist.get(i)==employeeEmployeeRole.getEmployeeId())
                    isEmployeeIdExist=true;
            }
            if(!isEmployeeIdExist)
                employeeIdlist.add(employeeEmployeeRole.getEmployeeId());
        }
        employeeEmployeeRoleService.deleteByEmployeeRoleId(subEmployeeRole.getId());
        for(int i=0;i<employeeIdlist.toArray().length;i++)
        {
            long userId=employeeService.getById(employeeIdlist.get(i)).getUserId();
            //原先user的权限列表
            List<UserAuthority> userAuthorityList=userAuthorityService.findByUserId(userId);
            String role="";
            String employeeRoleString="";
            List<EmployeeEmployeeRole> empEmpRoleList=employeeEmployeeRoleService.getByEmployeeId(subEmployeeRole.getId());
            //现在user的应该有的权限列表
            ArrayList<Long> authorityIdList=new ArrayList<Long>();
            for(int j=0;j<empEmpRoleList.toArray().length;j++)
            {
                if(j==0)
                    employeeRoleString=empEmpRoleList.get(j).getEmployeeRoleName();
                else
                    employeeRoleString+=";"+empEmpRoleList.get(j).getEmployeeRoleName();
                boolean isExist=false;
                employeeRole= employeeRoleService.getById(empEmpRoleList.get(j).getEmployeeRoleId());
                for(long aid:authorityIdList)
                {
                    if(aid==employeeRole.getAuthorityId())
                        isExist=true;
                }
                if(!isExist)
                    authorityIdList.add(employeeRole.getAuthorityId());
            }
            //判断旧的权限在新的中不存在，如果不存在就删除
            for(UserAuthority userAuthority:userAuthorityList)
            {
                boolean isAuthorityExist=false;
                for(int j=0;j<authorityIdList.toArray().length;j++)
                {
                    if(userAuthority.getAuthorityId()==authorityIdList.get(j))
                        isAuthorityExist=true;
                }
                if(!isAuthorityExist)
                    userAuthorityService.delete(userAuthority);
            }
            //修改user里面的role
            for(int j=0;j<authorityIdList.toArray().length;j++)
            {
                if(j==0)
                    role=authorityService.getNameById(authorityIdList.get(j));
                else
                    role+=";"+authorityService.getNameById(authorityIdList.get(j));
            }
            User user =userService.getById(userId);
            user.setRole(role);
            userService.update(user);
            Employee employee=employeeService.getById(employeeIdlist.get(i));
            employee.setEmployeeRoleName(employeeRoleString);
            employeeService.update(employee);
        }
        if(result1>0&&result2>0)
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        else
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @POST
    public String list()
    {
        long appId= UserContext.currentUserAppId();
        List<EmployeeRole> employeeRoleList=employeeRoleService.getListByAppId(appId);
        List<SubEmployeeRole> subEmployeeRoleList=new ArrayList<SubEmployeeRole>();
        for(EmployeeRole e:employeeRoleList)
        {
            List<EmployeeRoleInspectTable> employeeRoleInspectTablesList=employeeRoleInspectTableService.getByEmployeeRoleId(e.getId());
            String inspectTableName="";
            for(int i=0;i<employeeRoleInspectTablesList.toArray().length;i++)
            {
                if(i==0)
                {
                    inspectTableName=employeeRoleInspectTablesList.get(i).getInspectTableName();
                }
                else
                {
                    inspectTableName+=";"+employeeRoleInspectTablesList.get(i).getInspectTableName();
                }
            }
            SubEmployeeRole subEmployeeRole=new SubEmployeeRole();
            subEmployeeRole.setName(e.getName());
            subEmployeeRole.setStatus(e.getStatus());
            subEmployeeRole.setDescription(e.getDescription());
            subEmployeeRole.setId(e.getId());
            subEmployeeRole.setAppId(e.getAppId());
            subEmployeeRole.setInspectTable(inspectTableName);
            String authority=authorityService.getNameById(e.getAuthorityId());
            subEmployeeRole.setAuthority(authority);
            subEmployeeRoleList.add(subEmployeeRole);
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(subEmployeeRoleList,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/canUseList")
    @POST
    public String canUseList() {
        long appId = UserContext.currentUserAppId();
        List<EmployeeRole> list=employeeRoleService.getListByAppId(appId);
        List<EmployeeRole> canUseList=new ArrayList<EmployeeRole>();
        for(EmployeeRole employeeRole:list){
            if(employeeRole.getStatus().equals("启用")){
                canUseList.add(employeeRole);
            }
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(canUseList,JsonResultUtils.Code.SUCCESS);
    }

}
