package org.whut.inspectManagement.business.inspectTable.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.inspectTable.entity.InspectChoice;
import org.whut.inspectManagement.business.inspectTable.mapper.InspectChoiceMapper;

import java.util.HashMap;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * User: xjie
 * Date: 14-5-15
 * Time: 下午3:12
 * To change this template use File | Settings | File Templates.
 */
public class InspectChoiceService {
    @Autowired
    private InspectChoiceMapper inspectChoiceMapper;

    public List<InspectChoice> getList(){
         return inspectChoiceMapper.getList();
    }
    public void add(InspectChoice inspectChoice){
         inspectChoiceMapper.add(inspectChoice);
    }
    public int delete(InspectChoice inspectChoice){
        return inspectChoiceMapper.delete(inspectChoice);
    }
    public int update(InspectChoice inspectChoice){
        return inspectChoiceMapper.update(inspectChoice);
    }
    public long getIdByChoiceValue(String choiceValue){
        return inspectChoiceMapper.getIdByChoiceValue(choiceValue);
    }
}
