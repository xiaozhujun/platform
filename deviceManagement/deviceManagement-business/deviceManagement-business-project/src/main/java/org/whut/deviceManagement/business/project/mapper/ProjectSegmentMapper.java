package org.whut.deviceManagement.business.project.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.deviceManagement.business.project.entity.ProjectSegment;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-4-27
 * Time: 下午3:02
 * To change this template use File | Settings | File Templates.
 */
public interface ProjectSegmentMapper extends AbstractMapper<ProjectSegment> {
    public List<Map<String,String>> getListByAppId(long appId);
    public List<Map<String,String>> getListByProjectId(@Param("projectId") long projectId,@Param("appId") long appId);
    public int deleteById(@Param("id") long id);
    public ProjectSegment getByDeviceId(@Param("deviceId") long deviceId);


}