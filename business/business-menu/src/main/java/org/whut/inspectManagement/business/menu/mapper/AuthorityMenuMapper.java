package org.whut.inspectManagement.business.menu.mapper;

import org.whut.inspectManagement.business.menu.entity.AuthorityMenu;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: LJXia
 * Date: 14-5-27
 * Time: 上午10:28
 * To change this template use File | Settings | File Templates.
 */
public interface AuthorityMenuMapper extends AbstractMapper {
    public List<AuthorityMenu> findByCondition(Map<String,Object> map);
    public AuthorityMenu get(long id);
    public void addList(List<AuthorityMenu> list);
    public int deleteByAuthorityId(long authorityId);
}
