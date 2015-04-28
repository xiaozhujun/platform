package org.whut.deviceManagement.business.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.deviceManagement.business.project.entity.ProjectUnit;
import org.whut.deviceManagement.business.project.mapper.ProjectUnitMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-4-27
 * Time: 下午3:03
 * To change this template use File | Settings | File Templates.
 */
public class ProjectUnitService {
    @Autowired
    private ProjectUnitMapper projectUnitMapper;

    public void add(ProjectUnit projectUnit){
        projectUnitMapper.add(projectUnit);
    }

    public int delete(ProjectUnit projectUnit){
        return projectUnitMapper.delete(projectUnit);
    }

    public int update(ProjectUnit projectUnit){
        return projectUnitMapper.update(projectUnit);
    }

    public int deleteById(long id){
        return projectUnitMapper.deleteById(id);
    }

    public List<Map<String,String>> getListByAppId(long appId) {
        return projectUnitMapper.getListByAppId(appId);
    }
}