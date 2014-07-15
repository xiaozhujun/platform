package org.whut.inspectManagement.business.task.mapper;

import org.whut.inspectManagement.business.task.entity.InspectTask;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-6-30
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */
public interface InspectTaskMapper extends AbstractMapper<InspectTask>{
    public List<InspectTask> getListByAppId(long appId);
    public List<InspectTask> findByCondition(InspectTask inspectTask);
    public List<InspectTask> getDispatchTaskList(List<String> ruleCondition);
    public void dispatchTask(List<InspectTask> list);
    public List<Map<String,String>>getInspectTaskInfo(String appId);
    public void completeTask(InspectTask inspectTask);
}
