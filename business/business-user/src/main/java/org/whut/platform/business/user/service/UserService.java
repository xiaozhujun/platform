package org.whut.platform.business.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.user.entity.User;
import org.whut.platform.business.user.mapper.UserMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-1-26
 * Time: 上午11:39
 * To change this template use File | Settings | File Templates.
 */
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void add(User user){
         userMapper.add(user);
    }

    public List<User> findByName(String name){
        return userMapper.findByName(name);
    }
}
