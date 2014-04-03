package org.whut.platform.business.user.mapper;

import org.whut.platform.business.user.entity.Authority;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

<<<<<<< HEAD
import java.util.List;
import java.util.Map;

=======
import java.util.Map;
import java.util.List;
>>>>>>> 23dd51744e660700d6196a2d52cb2394d49b9f1c
/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-3-16
 * Time: 下午8:14
 * To change this template use File | Settings | File Templates.
 */
public interface AuthorityMapper extends AbstractMapper<Authority> {
<<<<<<< HEAD
    public List<Authority> findByCondition(Map<String,Object> map);
=======
      public List<Authority> findByCondition(Map<String,Object> map);
      public long getIdByName(String name);
>>>>>>> 23dd51744e660700d6196a2d52cb2394d49b9f1c
}
