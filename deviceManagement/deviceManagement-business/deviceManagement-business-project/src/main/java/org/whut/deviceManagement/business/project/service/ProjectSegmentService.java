package org.whut.deviceManagement.business.project.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.deviceManagement.business.device.entity.Device;
import org.whut.deviceManagement.business.device.mapper.DeviceMapper;
import org.whut.deviceManagement.business.project.entity.ProjectSegment;
import org.whut.deviceManagement.business.project.mapper.ProjectSegmentMapper;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;

import java.util.HashMap;
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
    public static PlatformLogger logger = PlatformLogger.getLogger(ProjectSegmentService.class);

    @Autowired
    private ProjectSegmentMapper projectSegmentMapper;
    @Autowired
    private DeviceMapper deviceMapper;

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
        return projectSegmentMapper.getListByProjectId(projectId, appId);
    }

    public void resolveMessage(String message){
        HashMap<String,Object> object = JsonMapper.buildNonDefaultMapper().fromJson(message, HashMap.class);
        String deviceNumber = (String)object.get("number");
        Device device = deviceMapper.getByNumber(deviceNumber);
        ProjectSegment projectSegment = projectSegmentMapper.getByDeviceId(device.getId());
        if(projectSegment.getFulfillCount()<projectSegment.getTotalCount()){
            projectSegment.setFulfillCount(projectSegment.getFulfillCount() + 1);
            projectSegmentMapper.update(projectSegment);
        }
        logger.info("ProjectSegmentService resolveMessage: "+message);
    }
}