package org.whut.inspectManagement.business.inspectLocate.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-7-11
 * Time: 上午10:16
 * To change this template use File | Settings | File Templates.
 */
public interface InspectLocateMapper extends AbstractMapper{
    public List<Map<String,String>> findByCondition(Map<String,Object> map);
    public Long validateIsExistRecord(@Param("appId")long appId,@Param("userId")long userId);

}
