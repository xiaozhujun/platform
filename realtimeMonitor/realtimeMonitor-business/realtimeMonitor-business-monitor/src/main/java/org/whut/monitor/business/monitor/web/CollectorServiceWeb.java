package org.whut.monitor.business.monitor.web;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public String add(@FormParam("jsonStringList") String jsonStringList) throws ParseException {
        //long appId= UserContext.currentUserAppId();
        long appId=1;
        List<SubCollector> repeatList=new ArrayList<SubCollector>();
        List<SubCollector> successList=new ArrayList<SubCollector>();
        List<SubCollector> errorList=new ArrayList<SubCollector>();
        try {
            JSONArray jsonArray=new JSONArray(jsonStringList);
            if(jsonArray.length()==0){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
            }
            for(int i=0;i<jsonArray.length();i++){
                String jsonString=jsonArray.get(i).toString();
                SubCollector subCollector=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubCollector.class);
                if(subCollector.getAddStatus().equals("已提交")){
                    successList.add(subCollector);
                }else{
                    if(subCollector.getName()==null||subCollector.getNumber()==null||subCollector.getStatus()==null||
                            subCollector.getMaxFrequency()==null||subCollector.getMinFrequency()==null||subCollector.getWorkFrequency()==null){
                        subCollector.setAddStatus("参数缺省");
                        errorList.add(subCollector);
                    }else{
                        long tempId;
                        try{
                           tempId=collectorService.getCollectorId(subCollector.getName(),subCollector.getNumber(),appId);
                        }catch (Exception e){
                            tempId=0;
                        }
                        if(tempId!=0){
                            subCollector.setAddStatus("参数重复");
                            repeatList.add(subCollector);
                        }else{
                            Collector collector=new Collector();
                            collector.setName(subCollector.getName());
                            collector.setNumber(subCollector.getNumber());
                            collector.setDescription(subCollector.getDescription());
                            collector.setAreaId(1);
                            collector.setGroupId(1);
                            collector.setAppId(appId);
                            collector.setStatus(subCollector.getStatus());
                            collector.setMaxFrequency(subCollector.getMaxFrequency());
                            collector.setMinFrequency(subCollector.getMinFrequency());
                            collector.setWorkFrequency(subCollector.getWorkFrequency());
                            DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
                            collector.setLastMessageTime(format.parse(subCollector.getLastMessageTime()));
                            collectorService.add(collector);
                            subCollector.setAddStatus("提交成功");
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (errorList.size()!=0){
            errorList.addAll(repeatList);
            errorList.addAll(successList);
            return JsonResultUtils.getObjectResultByStringAsDefault(errorList,JsonResultUtils.Code.ERROR);
        }else if(repeatList.size()!=0){
            repeatList.addAll(successList);
            return JsonResultUtils.getObjectResultByStringAsDefault(repeatList,JsonResultUtils.Code.DUPLICATE);
        }else {
            return JsonResultUtils.getObjectResultByStringAsDefault(successList,JsonResultUtils.Code.SUCCESS);
        }
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        long appId=1;
        List<Map<String,String>> list=collectorService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString) throws ParseException {
        long appId=1;
        SubCollector subCollector=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubCollector.class);
        if(subCollector.getName()==null||subCollector.getNumber()==null||subCollector.getStatus()==null||
                subCollector.getMaxFrequency()==null||subCollector.getMinFrequency()==null||subCollector.getWorkFrequency()==null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }
        long tempId;
        try{
            tempId=collectorService.getCollectorId(subCollector.getName(),subCollector.getNumber(),appId);
        }catch (Exception e){
            tempId=0;
        }
        if(tempId!=0){
            if(tempId!=subCollector.getId()){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"采集仪已存在");
            }
        }
        //判断group和area能否对应得上
        Collector collector=new Collector();
        collector.setName(subCollector.getName());
        collector.setNumber(subCollector.getNumber());
        collector.setAppId(appId);
        collector.setAreaId(1);
        collector.setGroupId(1);
        collector.setMaxFrequency(subCollector.getMaxFrequency());
        collector.setMinFrequency(subCollector.getMinFrequency());
        collector.setWorkFrequency(subCollector.getWorkFrequency());
        collector.setStatus(subCollector.getStatus());
        collector.setDescription(subCollector.getDescription());
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        collector.setLastMessageTime(format.parse(subCollector.getLastMessageTime()));
        collectorService.update(collector);
        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"修改成功");
    }
    public String delete(@FormParam("jsonString") String jsonString){
       return null;
    }
}
