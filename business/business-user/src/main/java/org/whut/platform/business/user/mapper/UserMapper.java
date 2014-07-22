package org.whut.platform.business.user.mapper;

import org.whut.platform.business.user.entity.User;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-1-26
 * Time: 上午11:51
 * To change this template use File | Settings | File Templates.
 */
public interface UserMapper extends AbstractMapper<User>{
    public List<User> findByName(String name);
    public List<User> findByCondition(Map<String,Object> map);
    public Long getIdByName(String name);
    public User getById(long id);
    public void updateUserImage(User user);
}
