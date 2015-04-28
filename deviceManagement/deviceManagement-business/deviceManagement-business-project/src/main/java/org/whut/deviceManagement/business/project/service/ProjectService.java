package org.whut.deviceManagement.business.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.deviceManagement.business.project.entity.Project;
import org.whut.deviceManagement.business.project.mapper.ProjectMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-4-26
 * Time: 下午5:07
 * To change this template use File | Settings | File Templates.
 */
public class ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    public void add(Project project){
        projectMapper.add(project);
    }

    public int delete(Project project){
        return projectMapper.delete(project);
    }

    public int update(Project project){
        return projectMapper.update(project);
    }

    public int deleteById(long id){
        return projectMapper.deleteById(id);
    }

    public List<Map<String,String>> getListByAppId(long appId) {
        return projectMapper.getListByAppId(appId);
    }
}
