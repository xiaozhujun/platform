package org.whut.monitor.business.monitor.web;

import com.mongodb.DBObject;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.monitor.business.monitor.entity.Sensor;
import org.whut.monitor.business.monitor.entity.SubSensor;
import org.whut.monitor.business.monitor.service.AreaService;
import org.whut.monitor.business.monitor.service.CollectorService;
import org.whut.monitor.business.monitor.service.GroupService;
import org.whut.monitor.business.monitor.service.SensorService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.mongo.connector.MongoConnector;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-7-16
 * Time: 下午3:07
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/sensor")
public class SensorServiceWeb {
    private static PlatformLogger logger = PlatformLogger.getLogger(SensorServiceWeb.class);

    @Autowired
    private SensorService sensorService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private CollectorService collectorService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("jsonString") String jsonString){
        long appId= UserContext.currentUserAppId();
        List<SubSensor> repeatList=new ArrayList<SubSensor>();
        List<SubSensor> successList=new ArrayList<SubSensor>();
        List<SubSensor> errorList=new ArrayList<SubSensor>();
        try {
            JSONArray jsonArray=new JSONArray(jsonString);
            if(jsonArray.length()==0){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
            }
            for(int i=0;i<jsonArray.length();i++){
                String js=jsonArray.get(i).toString();
                SubSensor subSensor= JsonMapper.buildNonDefaultMapper().fromJson(js,SubSensor.class);
                if(subSensor.getAddStatus().equals("提交成功")){
                    successList.add(subSensor);
                }else{
                    if(subSensor.getShouldWarn().equals("是")&&(subSensor.getName().equals("")||subSensor.getNumber().equals("")||subSensor.getMaxFrequency().equals("")||
                        subSensor.getMinFrequency().equals("")||subSensor.getWorkFrequency().equals("")||subSensor.getWarnType().equals("")
                        ||subSensor.getWarnValue().equals(""))){
                        subSensor.setAddStatus("参数缺省");
                        errorList.add(subSensor);
                    }
                    else if(subSensor.getShouldWarn().equals("否")&&(subSensor.getName().equals("")||subSensor.getNumber().equals("")||subSensor.getMaxFrequency().equals("")||
                            subSensor.getMinFrequency().equals("")||subSensor.getWorkFrequency().equals(""))){
                        subSensor.setAddStatus("参数缺省");
                        errorList.add(subSensor);
                    }
                    else{
                        long tempId;
                        try{
                            tempId=sensorService.getSensorId(subSensor.getGroupName(),subSensor.getAreaName(),subSensor.getCollectorName(),subSensor.getName(), subSensor.getNumber(), appId);
                        }catch (Exception e){
                            tempId=0;
                        }
                        if(tempId!=0){
                            subSensor.setAddStatus("参数重复");
                            repeatList.add(subSensor);
                        }else{
                            Sensor sensor= new Sensor();
                            sensor.setName(subSensor.getName());
                            sensor.setDescription(subSensor.getDescription());
                            sensor.setNumber(subSensor.getNumber());
                            sensor.setAppId(appId);
                            long groupId = groupService.getIdByNameAndAppId(subSensor.getGroupName(),appId);
                            sensor.setGroupId(groupId);
                            long areaId = areaService.getIdByNameAndGroupIdAndAppId(subSensor.getAreaName(),groupId,appId);
                            sensor.setAreaId(areaId);
                            sensor.setCollectorId(collectorService.getIdByNameAndAppId(subSensor.getGroupName(),subSensor.getAreaName(),subSensor.getCollectorName(),appId));
                            sensor.setMaxFrequency(subSensor.getMaxFrequency());
                            sensor.setMinFrequency(subSensor.getMinFrequency());
                            sensor.setWorkFrequency(subSensor.getWorkFrequency());
                            if(subSensor.getShouldWarn().equals("否")){
                                sensor.setShouldWarn("否");
                            }
                            else{
                                sensor.setShouldWarn("是");
                                sensor.setWarnType(subSensor.getWarnType());
                                sensor.setWarnValue(subSensor.getWarnValue());
                            }
                            sensorService.add(sensor);
                            subSensor.setAddStatus("提交成功");
                            successList.add(subSensor);
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (errorList.size()!=0){
            errorList.addAll(successList);
            errorList.addAll(repeatList);
            return JsonResultUtils.getObjectResultByStringAsDefault(errorList,JsonResultUtils.Code.ERROR);
        }else if(repeatList.size()!=0){
            repeatList.addAll(successList);
            return JsonResultUtils.getObjectResultByStringAsDefault(repeatList,JsonResultUtils.Code.DUPLICATE);
        }else {
            return JsonResultUtils.getObjectResultByStringAsDefault(successList,JsonResultUtils.Code.SUCCESS);
        }
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("list")
    @POST
    public String list(){
        long appId=UserContext.currentUserAppId();
        List<Map<String,String>> list = sensorService.list(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString){
        SubSensor subSensor = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubSensor.class);
        long id = subSensor.getId();
        int deleted = sensorService.deleteById(id);
        if(deleted>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        SubSensor subSensor = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubSensor.class);
        if(subSensor.getShouldWarn().equals("是")&&(subSensor.getName().equals("")||subSensor.getNumber().equals("")||subSensor.getMaxFrequency().equals("")||
                subSensor.getMinFrequency().equals("")||subSensor.getWorkFrequency().equals("")||subSensor.getWarnType().equals("")
                ||subSensor.getWarnValue().equals(""))){
           return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数缺省！");
        }
        else if(subSensor.getShouldWarn().equals("否")&&(subSensor.getName().equals("")||subSensor.getNumber().equals("")||subSensor.getMaxFrequency().equals("")||
                subSensor.getMinFrequency().equals("")||subSensor.getWorkFrequency().equals(""))){
           return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数缺省！");
        }
        long appId=UserContext.currentUserAppId();
        long existId = 0;
        try{
            existId=sensorService.getSensorId(subSensor.getGroupName(),subSensor.getAreaName(),subSensor.getCollectorName(),subSensor.getName(), subSensor.getNumber(), appId);
        }catch (Exception e){
            existId=0;
        }
        if(existId!=0&&existId!=subSensor.getId()){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"传感器已存在！");
        }
        Sensor sensor = new Sensor();
        long groupId = 0;
        long areaId = 0 ;
        long collectorId = 0;
        try{
            groupId = groupService.getIdByNameAndAppId(subSensor.getGroupName(), appId);
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        if(groupId==0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"监控组不存在！");
        }
        try{
            areaId = areaService.getIdByNameAndGroupIdAndAppId(subSensor.getAreaName(),groupId,appId);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        if(areaId==0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"监控组中不存在该监控区域！");
        }
        try{
            collectorId = collectorService.getIdByNameAndAppId(subSensor.getGroupName(),subSensor.getAreaName(),subSensor.getCollectorName(),appId);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        if(collectorId==0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"监控区域中不存在该采集仪！");
        }
        sensor.setId(subSensor.getId());
        sensor.setAppId(appId);
        sensor.setName(subSensor.getName());
        sensor.setNumber(subSensor.getNumber());
        sensor.setDescription(subSensor.getDescription());
        sensor.setGroupId(groupId);
        sensor.setAreaId(areaId);
        sensor.setCollectorId(collectorId);
        sensor.setMaxFrequency(subSensor.getMaxFrequency());
        sensor.setMinFrequency(subSensor.getMinFrequency());
        sensor.setWorkFrequency(subSensor.getWorkFrequency());
        String shouldWarn = subSensor.getShouldWarn();
        if(shouldWarn.equals("否")){
            sensor.setShouldWarn("否");
            sensor.setWarnType("");
            sensor.setWarnValue("");
        }
        else{
            sensor.setShouldWarn("是");
            sensor.setWarnType(subSensor.getWarnType());
            sensor.setWarnValue(subSensor.getWarnValue());
        }
        int updated =0;
        if(collectorId>0){
           try{
              updated = sensorService.update(sensor);
           }catch(Exception e){
               e.printStackTrace();
               logger.error(e.getMessage());
           }
        }
        if(updated>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/homePageList")
    @POST
    public String homePageList() {
        long appId = UserContext.currentUserAppId();
        List<Map<String,String>> homePageList = sensorService.homePageList(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(homePageList, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/updateWarnCount")
    @POST
    public String updateWarnCount(@FormParam("count") long count,@FormParam("groupName") String groupName,@FormParam("areaName")String areaName,
                                  @FormParam("name")String name,@FormParam("collectorName")String collectorName,@FormParam("number")String number){
        long sensorWarnCount =count;
        long appId = UserContext.currentUserAppId();
        long id;
        try {
            id = sensorService.getSensorId(groupName,areaName,collectorName,name,number,appId);
        } catch (Exception e) {
            id = 0;
        }
        if (id != 0) {
            sensorService.updateWarnCount(sensorWarnCount,id,appId);
        }
        else {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"采集仪不存在");
        }
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getSensorsByCollectorId")
    @POST
    public String getSensorsByCollectorId(@FormParam("collectorId") long collectorId){
        long appId=UserContext.currentUserAppId();
        List<Sensor> list=sensorService.getSensorsByCollectorId(collectorId,appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }


    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getMongoDataList")
    @POST
    public String getMongoDataList(@FormParam("sTime")String sTime,@FormParam("eTime")String eTime,@FormParam("number")String number){
       System.out.println(sTime+"dddddddddddddddddddddddddddddd"+eTime+"sssssssssssss"+number);
        MongoConnector mongoConnector=new MongoConnector("sensorDB","sensorCollection");
        List<List<DBObject>> getList=new ArrayList<List<DBObject>>();
        getList=mongoConnector.getDbArrayListFromMongo2(sTime,eTime,number);
        List a=new ArrayList();
        int data2=0,p=0;Object data;
        for(int i=0;i<getList.size();i++){
            for(int j=0;j<getList.get(i).size();j++){
                data=getList.get(i).get(j);
                data2=data2+Integer.parseInt(data.toString());
                p++;
            }
            if ((i+1)%30==0)         //取一个小时的数据
            {
              data2=data2/p;
              System.out.println(data2);
              a.add(data2);
              data2=0; p=0;
            }
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(a, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getMongoDataList2")
    @POST
    public String getMongoDataList2(@FormParam("sTime")String sTime,@FormParam("eTime")String eTime,@FormParam("number")String number){
        System.out.println(sTime+"dddddddddddddddddddddddddddddd"+eTime+"sssssssssssss"+number);
        MongoConnector mongoConnector=new MongoConnector("sensorDB","sensorCollection");
        List<List<DBObject>> getList=new ArrayList<List<DBObject>>();
        getList=mongoConnector.getDbArrayListFromMongo3(sTime,eTime,number);
        List a=new ArrayList();
        for(int i=0;i<getList.size();i=i+30){
             Object b=getList.get(i);
             a.add(b);
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(a, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getListByGroupCollectionAndMonitor")
    @POST
    public String getListByGroupCollectionAndMonitor(@FormParam("groupName")String groupName,@FormParam("collectorName")String collectorName,@FormParam("monitorName")String monitorName){
        //System.out.println(groupName+"dddddddddddddddddddddddddddddd"+collectorName+"jjjjjjjjjjjjjjjjjjjjjj"+monitorName);
        long appId=UserContext.currentUserAppId();
        List<Map<String,String>> listByGroupCollectionAndMonitor = sensorService.listByGroupCollectionAndMonitor(appId,groupName,collectorName,monitorName);
        return JsonResultUtils.getObjectResultByStringAsDefault(listByGroupCollectionAndMonitor, JsonResultUtils.Code.SUCCESS);
    }
}

