package org.whut.inspectManagement.business.inspectTable.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.inspectManagement.business.inspectTable.entity.InspectVersion;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-9-10
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */
public interface InspectVersionMapper extends AbstractMapper<InspectVersion>{
    public Long getVersionCodeByIdAndAppId(@Param("id")long id,@Param("appId")long appId);
    public String getAddressByIdAndAppId(@Param("id")long id,@Param("appId")long appId);
}
