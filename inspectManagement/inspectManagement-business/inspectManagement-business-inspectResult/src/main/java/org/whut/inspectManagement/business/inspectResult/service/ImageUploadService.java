package org.whut.inspectManagement.business.inspectResult.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.inspectResult.entity.ImageUpload;
import org.whut.inspectManagement.business.inspectResult.mapper.ImageUploadMapper;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-8-12
 * Time: 上午9:49
 * To change this template use File | Settings | File Templates.
 */
public class ImageUploadService {
    @Autowired
    private ImageUploadMapper imageUploadMapper;

    public void add(ImageUpload imageUpload) {
        imageUploadMapper.add(imageUpload);
    }

    public String getImageByNames(String userName,String deviceName,long itemRecordId,long itemId,long appId) {
        return imageUploadMapper.getImageByNames(userName,deviceName,itemRecordId,itemId,appId);
    }

    public Long getIdByItemIdAndItemRecordIdAndTableRecordIdAndAppIdAndPath(long itemId,long itemRecordId,long tableRecordId,long appId,String imagePath) {
        return imageUploadMapper.getIdByItemIdAndItemRecordIdAndTableRecordIdAndAppIdAndPath(itemId, itemRecordId, tableRecordId, appId,imagePath);
    }
}
