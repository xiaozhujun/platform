package org.whut.inspectManagement.business.task.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.inspectManagement.business.task.entity.InspectPlan;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-6-30
 * Time: 下午4:20
 * To change this template use File | Settings | File Templates.
 */
public interface InspectPlanMapper extends AbstractMapper<InspectPlan> {
    public List<InspectPlan> findByCondition(@Param("appId") long appId, @Param("name") String name, @Param("createtime") String createtime);
    public long getIdByName(@Param("name") String name, @Param("appId") long appId);
    public String getNameById(long id);
    public List<InspectPlan> getListByAppId(long appId);
    public List<InspectPlan> findByCondition(Map<String,Object> map);
}
