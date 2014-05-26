package org.whut.inspectManagement.business.inspectTable.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.inspectTable.entity.InspectItem;
import org.whut.inspectManagement.business.inspectTable.mapper.InspectItemChoiceMapper;
import org.whut.inspectManagement.business.inspectTable.mapper.InspectItemMapper;

import java.util.HashMap;
import java.util.List;
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


    public int delete(InspectItem inspectItem){
        return inspectItemMapper.delete(inspectItem);
    }
    public int update(InspectItem inspectItem){
        return inspectItemMapper.update(inspectItem);
    }
    public List<InspectItem> list(){
        return inspectItemMapper.findByCondition(new HashMap<String, Object>());
    }
    public long getInspectItemIdByNameAndNumber(String name,String number){
        return inspectItemMapper.getInspectItemIdByNameAndNumber(name,number);
    }

    public void addList(List<InspectItem> inspectItemList){
        inspectItemMapper.addList(inspectItemList);
    }
}
