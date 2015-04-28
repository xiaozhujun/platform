package org.whut.deviceManagement.business.project.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.deviceManagement.business.project.entity.ProjectSegment;
import org.whut.deviceManagement.business.project.mapper.ProjectSegmentMapper;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-4-27
 * Time: 下午3:03
 * To change this template use File | Settings | File Templates.
 */
public class ProjectSegmentService {
    @Autowired
    private ProjectSegmentMapper projectSegmentMapper;

    public void add(ProjectSegment projectSegment){
        projectSegmentMapper.add(projectSegment);
    }

    public int delete(ProjectSegment projectSegment){
        return projectSegmentMapper.delete(projectSegment);
    }

    public int update(ProjectSegment projectSegment){
        return projectSegmentMapper.update(projectSegment);
    }

    public int deleteById(long id){
        return projectSegmentMapper.deleteById(id);
    }

    public List<Map<String,String>> getListByAppId(long appId) {
        return projectSegmentMapper.getListByAppId(appId);
    }

    public List<Map<String,String>> getListByProjectId(Long projectId,Long appId){
        return projectSegmentMapper.getListByProjectId(projectId,appId);
    }
}