package org.whut.monitor.business.monitor.web;

import org.codehaus.jettison.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.monitor.business.monitor.entity.Collector;
import org.whut.monitor.business.monitor.entity.SubCollector;
import org.whut.monitor.business.monitor.service.CollectorService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-15
 * Time: 下午9:27
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/collector")
public class CollectorServiceWeb {
    @Autowired
    private CollectorService collectorService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("jsonStringList")String jsonStringList) {
        long appId= UserContext.currentUserAppId();
        List<SubCollector> collectorSuccessList = new ArrayList<SubCollector>();
        List<SubCollector> collectorErrorList = new ArrayList<SubCollector>();
        List<SubCollector> collectorRepeatList=new ArrayList<SubCollector>();

        try {
            JSONArray jsonArray = new JSONArray(jsonStringList);
            if(jsonArray.length()==0){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
            }
            for(int i=0;i<jsonArray.length();i++){
                String jsonString= jsonArray.get(i).toString();
                SubCollector subCollector = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubCollector.class);
                if(subCollector.getAddStatus().equals("提交成功")){
                    collectorSuccessList.add(subCollector);
                }
                else {
                    if(subCollector.getName()==""||subCollector.getNumber()==""||subCollector.getStatus()==""||
                            subCollector.getMaxFrequency()==""||subCollector.getMinFrequency()==""||subCollector.getWorkFrequency()==""){
                        subCollector.setAddStatus("参数缺省");
                        collectorErrorList.add(subCollector);
                    }else{


                    long tempId;
                    try {
                        tempId = collectorService.getIdByNumberAndAppId(subCollector.getNumber(),appId);
                    }catch (Exception e) {
                        tempId = 0;
                    }
                    if (tempId != 0) {
                        subCollector.setAddStatus("参数重复");
                        collectorRepeatList.add(subCollector);
                    }
                    else {
                        Collector collector = new Collector();
                        collector.setAppId(appId);
                        collector.setName(subCollector.getName());
                        if (subCollector.getDescription()=="") {collector.setDescription("无");}
                            else{collector.setDescription(subCollector.getDescription());}
                        collector.setStatus(subCollector.getStatus());
                        Date date = new Date();
                        collector.setLastMessageTime(date);
                        collector.setMaxFrequency(subCollector.getMaxFrequency());
                        collector.setMinFrequency(subCollector.getMinFrequency());
                        collector.setWorkFrequency(subCollector.getWorkFrequency());
                        collector.setNumber(subCollector.getNumber());
                        collectorService.add(collector);
                        subCollector.setAddStatus("提交成功");
                        collectorSuccessList.add(subCollector);
                    }
                }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        if (collectorErrorList.size()!=0) {
            collectorErrorList.addAll(collectorRepeatList);
            collectorErrorList.addAll(collectorSuccessList);
            return JsonResultUtils.getObjectResultByStringAsDefault(collectorErrorList,JsonResultUtils.Code.ERROR);
        }
        else if (collectorRepeatList.size()!=0) {
            collectorRepeatList.addAll(collectorSuccessList);
            return JsonResultUtils.getObjectResultByStringAsDefault(collectorRepeatList, JsonResultUtils.Code.DUPLICATE);
        }
        else {
            return JsonResultUtils.getObjectResultByStringAsDefault(collectorSuccessList,JsonResultUtils.Code.SUCCESS);
        }
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString")String jsonString) {
        SubCollector subCollector = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubCollector.class);
        long collectorId=subCollector.getId();
        collectorService.deleteById(collectorId);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }


    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/list")
    @POST
    public String list() {
        long appId= UserContext.currentUserAppId();
        List<Map<String,String>> collectorList = collectorService.getCollectorListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(collectorList, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString")String jsonString) throws ParseException {
        long appId = UserContext.currentUserAppId();
        SubCollector subCollector = JsonMapper.buildNonDefaultMapper().fromJson(jsonString, SubCollector.class);
        if(subCollector.getName()==""||subCollector.getNumber()==""||subCollector.getStatus()==""||
                subCollector.getMaxFrequency()==""||subCollector.getMinFrequency()==""||subCollector.getWorkFrequency()==""){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }
        long tempId;
        try{
            tempId = collectorService.getIdByNumberAndAppId(subCollector.getNumber(),appId);
        }catch (Exception e){
            tempId=0;
        }
        if(tempId!=0){
            if(tempId!=subCollector.getId()){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"采集仪已存在");
            }
        }
        Collector collector = new Collector();
        collector.setAppId(appId);
        collector.setDescription(subCollector.getDescription());
        collector.setId(subCollector.getId());
        collector.setStatus(subCollector.getStatus());
        Date date=new Date();
        collector.setLastMessageTime(date);

        collector.setName(subCollector.getName());
        collector.setNumber(subCollector.getNumber());
        collector.setMaxFrequency(subCollector.getMaxFrequency());
        collector.setMinFrequency(subCollector.getMinFrequency());
        collector.setWorkFrequency(subCollector.getWorkFrequency());

        collectorService.update(collector);

        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getCollectorNameByAppId")
    @POST
    public String getCollectorNameByAppId() {
        long appId=UserContext.currentUserAppId();
        List<Map<String,String>> list=collectorService.getCollectorNameByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
}