package org.whut.platform.business.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.user.entity.AuthorityPower;
import org.whut.platform.business.user.mapper.AuthorityPowerMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-3-16
 * Time: 下午10:45
 * To change this template use File | Settings | File Templates.
 */
public class AuthorityPowerService {

    @Autowired
    private AuthorityPowerMapper mapper;

    public List<AuthorityPower> getAuthorityPowerList(){
        return mapper.getAuthorityPowerList();
    }

    public void add(AuthorityPower authorityPower){
        mapper.add(authorityPower);
    }

    public int deleteByAuthorityName(String name) {
        return mapper.deleteByAuthorityName(name);
    }

    public List<String> getResourcesByAuthorityName(String name){
        return mapper.getResourcesByAuthorityName(name);
    }

    public int deleteByPowerResource(String powerResource){
        return mapper.deleteByPowerResource(powerResource);
    }
}
