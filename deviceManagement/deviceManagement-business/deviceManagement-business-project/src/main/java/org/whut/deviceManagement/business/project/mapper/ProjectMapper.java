package org.whut.deviceManagement.business.project.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.deviceManagement.business.project.entity.Project;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-4-26
 * Time: 下午5:06
 * To change this template use File | Settings | File Templates.
 */
public interface ProjectMapper  extends AbstractMapper<Project> {
    public List<Map<String,String>> getListByAppId(long appId);
    public int deleteById(@Param("id") long id);

}