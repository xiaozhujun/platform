package org.whut.inspectManagement.business.inspectResult.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.inspectManagement.business.inspectResult.entity.ImageUpload;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-8-12
 * Time: 上午9:49
 * To change this template use File | Settings | File Templates.
 */
public interface ImageUploadMapper extends AbstractMapper<ImageUpload> {
    public String getImageByNames(@Param("userName") String userName, @Param("deviceName") String deviceName, @Param("itemRecordId") long itemRecordId, @Param("itemId") long itemId, @Param("appId") long appId);
    public Long getIdByItemIdAndItemRecordIdAndTableRecordIdAndAppIdAndPath(@Param("itemId")long itemId,@Param("itemRecordId")long itemRecordId,@Param("tableRecordId")long tableRecordId,@Param("appId")long appId,@Param("imagePath")String imagePath);
}
