package org.whut.platform.business.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.app.entity.App;
import org.whut.platform.business.app.mapper.AppMapper;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunhui
 * Date: 14-5-12
 * Time: 上午9:31
 * To change this template use File | Settings | File Templates.
 */
public class AppService {
    @Autowired
    private AppMapper appMapper;

    public void add(App app){
        appMapper.add(app);
    }
    public List<App> list(){
        return appMapper.findByCondition(new HashMap<String, Object>());
    }
    public int delete(App app){
        return appMapper.delete(app);
    }
    public int update(App app){
        return appMapper.update(app);
    }
    public long getIdByName(String name){
        return appMapper.getIdByName(name);
    }
    public String getNameById(long id){
        return appMapper.getNameById(id);
    }
}
