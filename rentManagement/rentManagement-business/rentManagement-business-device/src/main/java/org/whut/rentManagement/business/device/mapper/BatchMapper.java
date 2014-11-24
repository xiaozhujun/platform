package org.whut.rentManagement.business.device.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.device.entity.Batch;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-20
 * Time: 下午4:57
 * To change this template use File | Settings | File Templates.
 */
public interface BatchMapper  extends AbstractMapper<Batch> {
    public Long getIdByNumber(@Param("number")String number, @Param("appId")long appId);
    public  int deleteById(long id);
    public List<Batch> getListByAppId(Long appId);
}
