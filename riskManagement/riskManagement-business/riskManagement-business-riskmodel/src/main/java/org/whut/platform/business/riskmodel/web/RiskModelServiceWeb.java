package org.whut.platform.business.riskmodel.web;
import com.google.common.io.Files;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.stereotype.Component;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.riskmodel.entity.RiskModel;
import org.whut.platform.business.riskmodel.service.RiskModelService;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-5-15
 * Time: 下午12:51
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/riskModel")
public class RiskModelServiceWeb {
   @Autowired
   private RiskModelService riskModelService;

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,@FormParam("className") String className,@FormParam("description") String description){
        if(name==null || className=="" || name.trim().equals("") || className.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }
        List<RiskModel> modelList = riskModelService.findByName(name);
        if(modelList.size()>0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"模型已存在！");
        }
        else{
            RiskModel riskModel = new RiskModel();
            riskModel.setName(name);
            riskModel.setClassName(className);
            riskModel.setDescription(description);
            riskModelService.add(riskModel);
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/list")
    @GET
    public String list(){
        List<RiskModel> list = riskModelService.list();
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        RiskModel riskModel = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,RiskModel.class);
        int result = riskModelService.update(riskModel);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }else{
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }

    @Produces( MediaType.APPLICATION_JSON+ ";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString){
        RiskModel riskModel = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,RiskModel.class);
        int result = riskModelService.delete(riskModel);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }else{
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getClassNameFromPackage")
    public String getClassNameFromPackage(){
        List<Map<String,String>> list=new ArrayList<Map<String, String>>();
        List<String>clist=findClassFromPackage();
        for(String cl:clist){
            Map<String,String>map=new HashMap<String, String>();
            map.put("name",cl);
            list.add(map);
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    public List<String> findClassFromPackage(){
          String packageName= FundamentalConfigProvider.get("calculateClassPackage");
          String classNameString=FundamentalConfigProvider.get("className");
          String[] classNameArr=classNameString.split(",");
          List<String> list=new ArrayList<String>();
          for(int i=0;i<classNameArr.length;i++){
             String className=packageName+"."+classNameArr[i];
             list.add(className);
          }
        return list;
    }
}
