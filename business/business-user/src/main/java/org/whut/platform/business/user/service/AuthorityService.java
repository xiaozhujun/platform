package org.whut.platform.business.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.user.entity.Authority;
import org.whut.platform.business.user.mapper.AuthorityMapper;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-3-16
 * Time: 下午8:16
 * To change this template use File | Settings | File Templates.
 */
public class AuthorityService {
    @Autowired
    private AuthorityMapper authorityMapper;

    public void add(Authority authority){
        authorityMapper.add(authority);
    }
    public int delete(Authority authority){
        return authorityMapper.delete(authority);
    }
    public int update(Authority authority){
        return authorityMapper.update(authority);
    }
    public List<Authority> list(){
        return authorityMapper.findByCondition(new HashMap<String, Object>());
    }
}
