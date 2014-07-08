package org.whut.inspectManagement.business.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.task.entity.InspectTask;
import org.whut.inspectManagement.business.task.mapper.InspectTaskMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-6-30
 * Time: 下午4:26
 * To change this template use File | Settings | File Templates.
 */
public class InspectTaskService {
    @Autowired
    private InspectTaskMapper mapper;


    public List<InspectTask> getListByAppId(long appId){
        return mapper.getListByAppId(appId);
    }

    public List<InspectTask> findByCondition(InspectTask inspectTask){
        return mapper.findByCondition(inspectTask);
    }

    public List<InspectTask> getUserLastTask(long userId,long appId){
        InspectTask condition = new InspectTask();
        condition.setUserId(userId);
        condition.setAppId(appId);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date today = null;
        try {
            today =  format.parse(format.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        condition.setTaskDate(today);
        return mapper.findByCondition(condition);
    }

    public HashMap<String,List<InspectTask>> getLastTaskByDeviceGroup(long appId){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date today = null;
        try {
            today =  format.parse(format.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        InspectTask condition = new InspectTask();
        condition.setAppId(appId);
        condition.setTaskDate(today);
        List<InspectTask> list = mapper.findByCondition(condition);
        HashMap<String,List<InspectTask>> resultMap = new LinkedHashMap<String, List<InspectTask>>();
        for(InspectTask task:list){
            if(resultMap.containsKey(task.getDeviceName())){
                 resultMap.get(task.getDeviceName()).add(task);
            }else{
                List<InspectTask> temp = new ArrayList<InspectTask>();
                temp.add(task);
                resultMap.put(task.getDeviceName(),temp);
            }
        }
        return resultMap;
    }
}
