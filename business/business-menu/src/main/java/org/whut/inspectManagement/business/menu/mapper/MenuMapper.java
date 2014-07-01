package org.whut.inspectManagement.business.menu.mapper;

import org.whut.inspectManagement.business.menu.entity.Menu;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: sunhui
 * Date: 14-5-23
 * Time: 上午1:50
 * To change this template use File | Settings | File Templates.
 */
public interface MenuMapper extends AbstractMapper{
    public List<Menu> findByCondition(Map<String,Object> map);
    public Menu get(long id);
    public String getNameById(long id);
    public List<Menu> getMenuByUserId(long userid);
    public  int fsupdate(Menu menu);
}
