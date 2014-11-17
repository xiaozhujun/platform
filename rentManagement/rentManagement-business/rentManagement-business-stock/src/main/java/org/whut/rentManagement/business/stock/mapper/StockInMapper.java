package org.whut.rentManagement.business.stock.mapper;

import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.stock.entity.StockIn;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-16
 * Time: 上午11:46
 * To change this template use File | Settings | File Templates.
 */
public interface StockInMapper  extends AbstractMapper<StockIn> {
    public List<Map<String,String>> getListByAppId(long appId);
    public List<Map<String,String>> findByCondition(Map<String,Object> condition);
    public Map<String,Object> getInfo(Map<String,Object> condition);
}