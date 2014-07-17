package org.whut.monitor.business.monitor.web;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.monitor.business.monitor.entity.Sensor;
import org.whut.monitor.business.monitor.entity.SubSensor;
import org.whut.monitor.business.monitor.service.AreaService;
import org.whut.monitor.business.monitor.service.CollectorService;
import org.whut.monitor.business.monitor.service.GroupService;
import org.whut.monitor.business.monitor.service.SensorService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

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
        long appId=1;
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
                if(subSensor.getAddStatus().equals("已提交")){
                    successList.add(subSensor);
                }else{
                    if(subSensor.getShouldWarn().equals("1")&&subSensor.getName()==null||subSensor.getNumber()==null||subSensor.getMaxFrequency()==null||
                        subSensor.getMinFrequency()==null||subSensor.getWorkFrequency()==null||subSensor.getWarnType()==null
                        ||subSensor.getWarnValue()==null||subSensor.getWarnStatus()==null){
                        subSensor.setAddStatus("参数缺省");
                        errorList.add(subSensor);
                    }
                    else if(subSensor.getShouldWarn().equals("0")&&subSensor.getName()==null||subSensor.getNumber()==null||subSensor.getMaxFrequency()==null||
                            subSensor.getMinFrequency()==null||subSensor.getWorkFrequency()==null){
                        subSensor.setAddStatus("参数缺省");
                        errorList.add(subSensor);
                    }
                    else{
                        long tempId;
                        try{
                            tempId=sensorService.getSensorIdByNameAndNumber(subSensor.getName(), subSensor.getNumber(), appId);
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
                            sensor.setGroupId(groupService.getIdByNameAndAppId(subSensor.getGroup(),appId));
                            sensor.setAreaId(areaService.getIDByNameAndAppId(subSensor.getArea(),appId));
                            sensor.setCollectorId(collectorService.getIdByNameAndAppId(subSensor.getCollector(),appId));
                            sensor.setMaxFrequency(subSensor.getMaxFrequency());
                            sensor.setMinFrequency(subSensor.getMinFrequency());
                            sensor.setWorkFrequency(subSensor.getWorkFrequency());
                            if(subSensor.getShouldWarn().equals("0")){
                                sensor.setShouldWarn("否");
                            }
                            else{
                                sensor.setShouldWarn("是");
                                sensor.setWarnType(subSensor.getWarnType());
                                sensor.setWarnValue(subSensor.getWarnValue());
                                sensor.setWarnCount(subSensor.getWarnCount());
                                sensor.setWarnStatus(subSensor.getWarnStatus());
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
            errorList.addAll(errorList);
            errorList.addAll(successList);
            return JsonResultUtils.getObjectResultByStringAsDefault(errorList,JsonResultUtils.Code.ERROR);
        }else if(repeatList.size()!=0){
            repeatList.addAll(repeatList);
            return JsonResultUtils.getObjectResultByStringAsDefault(repeatList,JsonResultUtils.Code.DUPLICATE);
        }else {
            return JsonResultUtils.getObjectResultByStringAsDefault(successList,JsonResultUtils.Code.SUCCESS);
        }
    }

}
