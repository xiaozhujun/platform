package org.whut.inspectManagement.business.inspectTable.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.inspectTable.entity.InspectItemChoice;
import org.whut.inspectManagement.business.inspectTable.mapper.InspectItemChoiceMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: choumiaoer
 * Date: 14-5-20
 * Time: 下午1:32
 * To change this template use File | Settings | File Templates.
 */
public class InspectItemChoiceService {
    @Autowired
    private InspectItemChoiceMapper inspectItemChoiceMapper;

    public void addList(List<InspectItemChoice> inspectItemChoiceList){
         inspectItemChoiceMapper.addList(inspectItemChoiceList);
    }
}
