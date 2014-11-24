package org.whut.rentManagement.business.device.web;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.device.entity.Batch;
import org.whut.rentManagement.business.device.service.BatchService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-20
 * Time: 下午5:00
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/batch")
public class BatchServiceWeb {
    @Autowired
    private BatchService batchService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("jsonString") String jsonString)throws ParseException {
        if(jsonString==null||jsonString.trim().equals("")) {
            return  JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }
        Batch batch= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Batch.class);
        if(batch==null||batch.getNumber()==null||batch.getNumber().trim().equals("")){
            return  JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }
        batch.setCreateTime(new Date());
        batch.setAppId(UserContext.currentUserAppId());
        if(batchService.getIdByNumber(batch.getNumber(),batch.getAppId())!=null){
            return  JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，该编号已经存在!");
        }

        batchService.add(batch);
        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "添加成功！");
    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString)  throws JSONException {
        long appId = UserContext.currentUserAppId();
        Batch batch= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Batch.class);
        if(batch==null||batch.getId()==0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能是空");
        }
        batch.setAppId(UserContext.currentUserAppId());
        batchService.update(batch);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/delete")
    @POST
    public  String delete(@FormParam("jsonString") String jsonString){
        Batch batch= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Batch.class);
        batch.setAppId(UserContext.currentUserAppId());
        int result=batchService.delete(batch);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else {
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        List<Batch> list = batchService.getListByAppId(UserContext.currentUserAppId());
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getIdByNumber")
    @POST
    public String getIdByNumber(@FormParam("number")String number){
        long appId= UserContext.currentUserAppId();
        long id=batchService.getIdByNumber(number,appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(id, JsonResultUtils.Code.SUCCESS);
    }
}