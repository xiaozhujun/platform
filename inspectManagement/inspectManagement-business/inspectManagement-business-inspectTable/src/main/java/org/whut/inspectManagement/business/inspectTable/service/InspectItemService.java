package org.whut.inspectManagement.business.inspectTable.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.inspectTable.entity.InspectItem;
import org.whut.inspectManagement.business.inspectTable.mapper.InspectItemChoiceMapper;
import org.whut.inspectManagement.business.inspectTable.mapper.InspectItemMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: choumiaoer
 * Date: 14-5-11
 * Time: 上午11:01
 * To change this template use File | Settings | File Templates.
 */
public class InspectItemService {
    @Autowired
    private InspectItemMapper inspectItemMapper;

    public void add(InspectItem inspectItem){
        inspectItemMapper.add(inspectItem);
    }
    public int delete(InspectItem inspectItem){
        return inspectItemMapper.delete(inspectItem);
    }
    public int update(InspectItem inspectItem){
        return inspectItemMapper.update(inspectItem);
    }
    public List<InspectItem> list(){
        return inspectItemMapper.findByCondition(new HashMap<String, Object>());
    }
    public long getInspectItemId(String name,String number,long tableId,long areaId,long appId){
        return inspectItemMapper.getInspectItemId(name,number,tableId,areaId,appId);
    }
    public void addList(List<InspectItem> inspectItemList){
        inspectItemMapper.addList(inspectItemList);
    }
    public List<InspectItem> getInspectItemListByAppId(long appId){
        return inspectItemMapper.getInspectItemListByAppId(appId);
    }
    public List<Map<String,String>> getInspectItemList(Long appId){
        return inspectItemMapper.getInspectItemList(appId);
    }
}
