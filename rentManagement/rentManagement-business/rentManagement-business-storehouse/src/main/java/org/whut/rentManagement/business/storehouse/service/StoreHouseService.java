package org.whut.rentManagement.business.storehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.storehouse.entity.StoreHouse;
import org.whut.rentManagement.business.storehouse.mapper.StoreHouseMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Steven
 * Date: 14-10-13
 * Time: 上午11:20
 * To change this template use File | Settings | File Templates.
 */
public class StoreHouseService {
    @Autowired
    private StoreHouseMapper storeHouseMapper;

    public void add(StoreHouse storeHouse){
        storeHouseMapper.add(storeHouse);
    }

    public int delete(StoreHouse storeHouse){
        return storeHouseMapper.delete(storeHouse);
    }
    public int deleteById(long id){
        return storeHouseMapper.deleteById(id);
    }

    public int update(StoreHouse storeHouse){
        return storeHouseMapper.update(storeHouse);
    }

    public long getIdByNameAndAppId(String name,long appId){
        return storeHouseMapper.getIdByNameAndAppId(name, appId);
    }

    public List<StoreHouse> getListByAppId(long appId){
        return storeHouseMapper.getListByAppId(appId);
    }
}
