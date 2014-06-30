package org.whut.inspectManagement.business.menu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.menu.entity.Menu;
import org.whut.inspectManagement.business.menu.mapper.MenuMapper;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunhui
 * Date: 14-5-23
 * Time: 上午1:50
 * To change this template use File | Settings | File Templates.
 */
public class MenuService {
    @Autowired
    private MenuMapper menuMapper;

    public void add(Menu menu){
        menuMapper.add(menu);
    }
    public int delete(Menu menu){
        return menuMapper.delete(menu);
    }

    public int update(Menu menu){
        return menuMapper.update(menu);
    }
    public int fsupdate(Menu menu){
        return menuMapper.fsupdate(menu);
    }
    public List<Menu> list(){
        return menuMapper.findByCondition(new HashMap<String, Object>());
    }
    public Menu get(long id){
        return menuMapper.get(id);
    }
    public String getNameById(long id){
        return menuMapper.getNameById(id);
    }
    public List<Menu> getMenuByUserId(long userid)
    {
        return  menuMapper.getMenuByUserId(userid);
    }
}
