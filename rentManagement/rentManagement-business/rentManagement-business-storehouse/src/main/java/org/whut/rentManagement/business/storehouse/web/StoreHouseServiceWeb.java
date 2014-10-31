package org.whut.rentManagement.business.storehouse.web;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.storehouse.entity.StoreHouse;
import org.whut.rentManagement.business.storehouse.entity.SubStoreHouse;
import org.whut.rentManagement.business.storehouse.service.StoreHouseService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Steven
 * Date: 14-10-10
 * Time: 下午1:05
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/storeHouse")
public class StoreHouseServiceWeb {
    @Autowired
    private StoreHouseService storeHouseService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("jsonString")String jsonStringList)throws JSONException, ParseException{
/*        long appId = UserContext.currentUserAppId();
        if(name.trim().equals("")) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
        }

        long id;
        try {
            id = storeHouseService.getIdByNameAndAppId(name,appId);
        } catch (Exception e) {
            id = 0;
        }

        if (id != 0) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"仓库已存在");
        }
        StoreHouse storeHouse = new StoreHouse();
        storeHouse.setAppId(appId);
        storeHouse.setName(name);
        storeHouse.setAddress(address);
        storeHouse.setLinkman(linkman);
        storeHouse.setTelephone(telephone);
        storeHouse.setCreateTime(createTime);
        storeHouseService.add(storeHouse);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);*/
        long appId = UserContext.currentUserAppId();
        try{
            JSONObject jsonObject=new JSONObject(jsonStringList);
            if(jsonObject==null){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能是空");
            }
            SubStoreHouse subStoreHouse = JsonMapper.buildNonDefaultMapper().fromJson(jsonStringList, SubStoreHouse.class);
            StoreHouse storeHouse = new StoreHouse();
            storeHouse.setName(subStoreHouse.getName());
            storeHouse.setAddress(subStoreHouse.getAddress());
            storeHouse.setLinkman(subStoreHouse.getLinkman());
            storeHouse.setTelephone(subStoreHouse.getTelephone());
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //定义时间格式
            storeHouse.setCreateTime(sdf.parse(subStoreHouse.getCreateTime()));  //String搞成date类型
            storeHouse.setAppId(appId);
            storeHouseService.add(storeHouse);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString")String jsonString)throws JSONException, ParseException {
        long appId= UserContext.currentUserAppId();
        try{
            JSONObject jsonObject=new JSONObject(jsonString);
            if(jsonObject==null){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能是空");
            }
        SubStoreHouse substoreHouse= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubStoreHouse.class);
        StoreHouse storeHouse=new StoreHouse();
        storeHouse.setName(substoreHouse.getName());
        storeHouse.setTelephone(substoreHouse.getTelephone());
        storeHouse.setLinkman(substoreHouse.getLinkman());
        storeHouse.setAddress(substoreHouse.getAddress());
        storeHouse.setDescription(substoreHouse.getDescription());
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        storeHouse.setCreateTime(format.parse(substoreHouse.getCreateTime()));
        storeHouse.setAppId(appId);
        storeHouseService.update(storeHouse);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/delete")
    @POST
    public  String delete(@FormParam("jsonString")String  jsonString){
        StoreHouse storeHouse= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,StoreHouse.class);
        int result=storeHouseService.delete(storeHouse);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else {
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/list")
    @POST
    public String List(){
        long appId= UserContext.currentUserAppId();
        List<StoreHouse> list=storeHouseService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getIdByNameAndAppId")
    @POST
    public String getIdByNameAndAppId(@FormParam("name")String name){
        long appId= UserContext.currentUserAppId();
        long id=storeHouseService.getIdByNameAndAppId(name, appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(id, JsonResultUtils.Code.SUCCESS);
    }
}
