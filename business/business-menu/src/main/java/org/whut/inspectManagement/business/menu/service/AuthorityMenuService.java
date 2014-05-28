package org.whut.inspectManagement.business.menu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.menu.entity.AuthorityMenu;
import org.whut.inspectManagement.business.menu.mapper.AuthorityMenuMapper;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: LJXia
 * Date: 14-5-27
 * Time: 上午10:30
 * To change this template use File | Settings | File Templates.
 */
public class AuthorityMenuService {
    @Autowired
    private AuthorityMenuMapper authorityMenuMapper;

    public void add(AuthorityMenu authorityMenu){
        authorityMenuMapper.add(authorityMenu);
    }
    public void addList(List<AuthorityMenu> list){
        authorityMenuMapper.addList(list);
    }
    public int delete(AuthorityMenu authorityMenu){
        return authorityMenuMapper.delete(authorityMenu);
    }
    public int deleteByAuthorityId(long authorityId){
        return authorityMenuMapper.deleteByAuthorityId(authorityId);
    }
    public int update(AuthorityMenu authorityMenu){
        return authorityMenuMapper.update(authorityMenu);
    }
    public List<AuthorityMenu> list(){
        return authorityMenuMapper.findByCondition(new HashMap<String, Object>());
    }
    public AuthorityMenu get(long id){
        return authorityMenuMapper.get(id);
    }
}
