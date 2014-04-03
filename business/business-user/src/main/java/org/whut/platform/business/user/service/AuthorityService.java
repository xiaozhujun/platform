package org.whut.platform.business.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.user.entity.Authority;
import org.whut.platform.business.user.mapper.AuthorityMapper;
<<<<<<< HEAD

import java.util.HashMap;
import java.util.List;

=======
import java.util.HashMap;
import java.util.List;
>>>>>>> 23dd51744e660700d6196a2d52cb2394d49b9f1c
/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-3-16
 * Time: 下午8:16
 * To change this template use File | Settings | File Templates.
 */
public class AuthorityService {
<<<<<<< HEAD
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
=======
     @Autowired
     private AuthorityMapper mapper;

     public void add(Authority authority){
         mapper.add(authority);
     }

     public int update(Authority authority){
         return mapper.update(authority);
     }

     public int delete(Authority authority){
         return mapper.delete(authority);
     }

     public List<Authority> list(){
         return mapper.findByCondition(new HashMap<String, Object>());
     }

     public long getIdByName(String name){
         return mapper.getIdByName(name);
     }
>>>>>>> 23dd51744e660700d6196a2d52cb2394d49b9f1c
}
