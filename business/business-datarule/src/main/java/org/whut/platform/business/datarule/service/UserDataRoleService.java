package org.whut.platform.business.datarule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.datarule.entity.UserDataRole;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-4-21
 * Time: 下午2:40
 * To change this template use File | Settings | File Templates.
 */

public class UserDataRoleService {
    @Autowired
    private org.whut.platform.business.datarule.mapper.UserDataRoleMapper userDataRoleMapper;

    public void add(UserDataRole userDataRole){
       userDataRoleMapper.add(userDataRole);
    }

    public List<String> findDataRoleByUserName(String userName){
       return userDataRoleMapper.findDataRoleByUserName(userName);
    }

    public int deleteByUserName(String userName){
        return userDataRoleMapper.deleteByUserName(userName);
    }
}
