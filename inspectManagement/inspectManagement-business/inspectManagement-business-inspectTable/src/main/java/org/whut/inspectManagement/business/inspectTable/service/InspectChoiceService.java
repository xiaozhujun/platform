package org.whut.inspectManagement.business.inspectTable.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.inspectTable.entity.InspectChoice;
import org.whut.inspectManagement.business.inspectTable.mapper.InspectChoiceMapper;
import org.whut.platform.business.user.security.UserContext;

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

    public List<InspectChoice> getList(long appId){
         return inspectChoiceMapper.getListByAppId(appId);
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
    public long getIdByChoiceValueAndAppId(String choiceValue,long appId){
        return inspectChoiceMapper.getIdByChoiceValueAndAppId(choiceValue, appId);
    }
    public String getChoiceValueById(long id){
        return inspectChoiceMapper.getChoiceValueById(id);
    }
    public String getInspectChoicesList(){
        long appId= UserContext.currentUserAppId();
        List<InspectChoice> inspectChoiceList=inspectChoiceMapper.getListByAppId(appId);
        String choices="";
        if(inspectChoiceList.size()!=0){
            for (int i=0;i<inspectChoiceList.size()-1;i++){
                String temp=inspectChoiceList.get(i).getChoiceValue()+";";
                choices+=temp;
            }
            choices+=inspectChoiceList.get(inspectChoiceList.size()-1).getChoiceValue();
        }
        return choices;
    }
}
