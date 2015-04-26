package org.whut.deviceManagement.business.maintain.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-4-25
 * Time: 下午11:58
 * To change this template use File | Settings | File Templates.
 */
public interface MaintainRecordMapper {

    public List<Map<String,String>> getListByAppId(long appId);
    public int deleteById(@Param("id") long id);
}
