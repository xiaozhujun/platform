package org.whut.monitor.business.monitor.web;

import org.codehaus.jettison.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.monitor.business.monitor.entity.Area;
import org.whut.monitor.business.monitor.entity.SubArea;
import org.whut.monitor.business.monitor.service.AreaService;
import org.whut.monitor.business.monitor.service.GroupService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    AreaService areaService;
    @Autowired
    GroupService groupService;

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        //long appId= UserContext.currentUserAppId();
        long appId=1;
        List<Area> list=areaService.getAreaListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/add")
    @POST
//    public String add(@FormParam("jsonString")String jsonString,@FormParam("name")String name,@FormParam("description")String description) {
//        Group group = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Group.class);
//        String groupName = group.getName();
//        long groupAppId = group.getAppId();
//        long appId = UserContext.currentUserAppId();
//        long groupId = groupService.getIdByNameAndAppId(groupName,groupAppId);
//        Date date = new Date();
//        if (name.trim().equals("")) {
//            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
//        }
//
//        long id;
//        try {
//            id = areaService.getIDByNameAndAppId(name,appId);
//        } catch (Exception e) {
//            id = 0;
//        }
//
//        if (id != 0) {
//            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"区域已存在");
//        }
//
//        Area area = new Area();
//        area.setAppId(appId);
//        area.setCreatetime(date);
//        area.setDescription(description);
//        area.setGroupId(groupId);
//        areaService.add(area);
//        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
//    }
    public String add(@FormParam("jsonStringList") String jsonStringList) {
        long appId=UserContext.currentUserAppId();
        List<Area> areaList = new ArrayList<Area>();
        List<SubArea> subAreaList = new ArrayList<SubArea>();
        List<SubArea> areaSuccessList = new ArrayList<SubArea>();
        List<SubArea> areaErrorList = new ArrayList<SubArea>();
        Date date=new Date();
        try {
            JSONArray jsonArray=new JSONArray(jsonStringList);
            if(jsonArray.length()==0){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
            }
            for(int i=0;i<jsonArray.length();i++){
                String jsonString= jsonArray.get(i).toString();
                SubArea subarea=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubArea.class);
                Area area = new Area();
                String name = subarea.getName();
                String description = subarea.getDescription();
                String groupName = subarea.getGroupName();
                long key = subarea.getId();
                }
            }catch (Exception e) {

        }
        return null;
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString")String jsonString) {
//        long appId = UserContext.currentUserAppId();
        long appId = 1;
        SubArea subArea = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubArea.class);
        String groupName = subArea.getGroupName();
        long groupId = groupService.getIdByNameAndAppId(groupName,appId);
        Area area = new Area();
        area.setAppId(subArea.getAppId());
        area.setDescription(subArea.getDescription());
        area.setGroupId(groupId);
        area.setId(subArea.getId());
        area.setCreatetime(subArea.getCreatetime());
        area.setName(subArea.getName());
        String areaName = area.getName();
        if (areaName.trim().equals("")) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }

        long id;
        try {
            id = areaService.getIDByNameAndAppId(areaName,appId);
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
}
