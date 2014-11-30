package org.whut.rentManagement.business.transport.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.transport.entity.Transport;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-9
 * Time: 下午10:56
 * To change this template use File | Settings | File Templates.
 */
public interface TransportMapper extends AbstractMapper<Transport> {
    public List<Map<String,String>> getListByAppId(long appId);
    public List<Map<String,String>> findByCondition(Map<String,Object> condition);
    public Map<String,Object> getInfo(Map<String,Object> condition);
}