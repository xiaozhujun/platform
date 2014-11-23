package org.whut.rentManagement.business.device.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.device.entity.Batch;
import org.whut.rentManagement.business.device.mapper.BatchMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-20
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */
public class BatchService {

    @Autowired
    private BatchMapper batchMapper;

    public void add(Batch batch){
        batchMapper.add(batch);
    }

    public int delete(Batch batch){
        return batchMapper.delete(batch);
    }

    public int update(Batch batch){
        return batchMapper.update(batch);
    }

    public int deleteById(long id){
        return  batchMapper.deleteById(id);
    }
    public Long getIdByNumber(String name,long appId) {
        return batchMapper.getIdByNumber(name,appId);
    }

    public List<Batch> getListByAppId(Long appId) {
        return batchMapper.getListByAppId(appId);
    }
}