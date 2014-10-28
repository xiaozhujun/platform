package org.whut.monitor.business.monitor.web;

import org.codehaus.jettison.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.monitor.business.monitor.entity.Area;
import org.whut.monitor.business.monitor.entity.SubArea;
import org.whut.monitor.business.monitor.service.AreaService;
import org.whut.monitor.business.monitor.service.GroupService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.logger.PlatformLogger;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-15
 * Time: 下午3:23
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/area")
public class AreaServiceWeb {
    private static PlatformLogger logger = PlatformLogger.getLogger(SensorServiceWeb.class);

    @Autowired
    AreaService areaService;
    @Autowired
    GroupService groupService;

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        long appId= UserContext.currentUserAppId();
        List<Map<String,String>> areaList=areaService.getAreaListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(areaList,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("jsonStringList") String jsonStringList) {
        long appId=UserContext.currentUserAppId();
        List<SubArea> areaSuccessList = new ArrayList<SubArea>();
        List<SubArea> areaErrorList = new ArrayList<SubArea>();
        List<SubArea> areaRepeatList=new ArrayList<SubArea>();
        try {
            JSONArray jsonArray = new JSONArray(jsonStringList);
            if(jsonArray.length()==0){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
            }
            for(int i=0;i<jsonArray.length();i++){
                String jsonString= jsonArray.get(i).toString();
                SubArea subArea=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubArea.class);
                if(subArea.getAddStatus().equals("提交成功")){
                    areaSuccessList.add(subArea);
                }
                else {
                    if (subArea.getName().equals("") || subArea.getName()==null || subArea.getGroupName().equals("") || subArea.getGroupName()==null) {
                        subArea.setAddStatus("参数缺省");
                        areaErrorList.add(subArea);
                    }
                    else {
                        long tempId;
                        try {
                            tempId = areaService.getIdByNameAndGroupIdAndAppId(subArea.getName()
                                    ,groupService.getIdByNameAndAppId(subArea.getGroupName(),appId),appId);
                        }catch (Exception e) {
                            tempId = 0;
                        }

                        if(tempId != 0){
                            subArea.setAddStatus("参数重复");
                            areaRepeatList.add(subArea);
                        }
                        else {
                            Area area = new Area();
                            area.setName(subArea.getName());
                            area.setDescription(subArea.getDescription());
                            area.setGroupId(groupService.getIdByNameAndAppId(subArea.getGroupName(),appId));
                            Date date=new Date();
                            area.setCreatetime(date);
                            area.setAppId(appId);
                            areaService.add(area);
                            subArea.setAddStatus("提交成功");
                            areaSuccessList.add(subArea);
                        }
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        if (areaErrorList.size()!=0){
            areaErrorList.addAll(areaRepeatList);
            areaErrorList.addAll(areaSuccessList);
            return JsonResultUtils.getObjectResultByStringAsDefault(areaErrorList,JsonResultUtils.Code.ERROR);
        }else if(areaRepeatList.size()!=0){
            areaRepeatList.addAll(areaSuccessList);
            return JsonResultUtils.getObjectResultByStringAsDefault(areaRepeatList,JsonResultUtils.Code.DUPLICATE);
        }else {
            return JsonResultUtils.getObjectResultByStringAsDefault(areaSuccessList,JsonResultUtils.Code.SUCCESS);
        }
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString")String jsonString) throws ParseException {
        long appId = UserContext.currentUserAppId();
        SubArea subArea = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubArea.class);
        String groupName = subArea.getGroupName();
        long groupId = groupService.getIdByNameAndAppId(groupName,appId);
        Area area = new Area();
        area.setAppId(appId);
        area.setDescription(subArea.getDescription());
        area.setGroupId(groupId);
        area.setId(subArea.getId());
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        area.setCreatetime(format.parse(subArea.getCreatetime()));
        area.setName(subArea.getName());
        String areaName = area.getName();
        if (areaName.trim().equals("")) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }

        long id;
        try {
             id = areaService.getIdByNameAndGroupIdAndAppId(subArea.getName()
                    ,groupService.getIdByNameAndAppId(subArea.getGroupName(),appId),appId);
        } catch (Exception e) {
            id = 0;
        }
        if (id != 0) {
             if (id != area.getId()) {
                 return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"区域已存在");
             }
        }
        areaService.update(area);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getAreaNameListByAppId")
    @POST
    public String getAreaNameListByAppId(){
        long appId=UserContext.currentUserAppId();
        List<Map<String,String>> list=areaService.getAreaNameListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getAreaByGroupId")
    @POST
    public String getAreaByGroupId(@FormParam("groupId")long groupId){
        long appId=UserContext.currentUserAppId();
        List<Area>list =areaService.getAreaByGroupId(groupId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getAreaListByGroupName")
    @POST
    public String getAreaListByGroupName(@FormParam("groupName")String groupName){
        logger.info("现在的数据："+groupName);
        long appId=UserContext.currentUserAppId();
        List<String> listByGroupName = areaService.getAreaListByGroupName(appId,groupName);
        return JsonResultUtils.getObjectResultByStringAsDefault(listByGroupName, JsonResultUtils.Code.SUCCESS);
    }
}
