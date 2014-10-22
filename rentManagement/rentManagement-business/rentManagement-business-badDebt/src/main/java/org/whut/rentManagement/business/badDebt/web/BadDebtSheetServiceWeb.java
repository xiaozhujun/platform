package org.whut.rentManagement.business.badDebt.web;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.badDebt.entity.BadDebtSheet;
import org.whut.rentManagement.business.badDebt.entity.SubBadDebtSheet;
import org.whut.rentManagement.business.badDebt.service.BadDebtSheetService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: cwb
 * Date: 14-10-9
 * Time: 下午7:05
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/bad_Debt_Sheet")
public class BadDebtSheetServiceWeb {
    @Autowired
    BadDebtSheetService badDebtSheetService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    /*public String add(@FormParam("number") String number,@FormParam("carNumber") String carNumber,@FormParam("customerId")long customerId,
                      @FormParam("contractId")long contractId,@FormParam("handler")String handler,@FormParam("storehouseId")long storehouseId,
                      @FormParam("description")String description,@FormParam("creator")String creator){*/
    public String add(@FormParam("jsonStringList") String jsonStringList) throws JSONException, ParseException{
        long appId = UserContext.currentUserAppId();
        try {
            //JSONArray jsonArray = new JSONArray(jsonStringList);
            JSONObject jsonObject=new JSONObject(jsonStringList);
            if(jsonObject==null){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能是空");
            }
                SubBadDebtSheet subBadDebtSheet = JsonMapper.buildNonDefaultMapper().fromJson(jsonStringList, SubBadDebtSheet.class);
                BadDebtSheet badDebtSheet = new BadDebtSheet();
                badDebtSheet.setNumber(subBadDebtSheet.getNumber());
                badDebtSheet.setCarNumber(subBadDebtSheet.getCarNumber());
                badDebtSheet.setCustomerId(subBadDebtSheet.getCustomerId());
                badDebtSheet.setContractId(subBadDebtSheet.getContractId());
                badDebtSheet.setHandler(subBadDebtSheet.getHandler());
                badDebtSheet.setStorehouseId(subBadDebtSheet.getStorehouseId());
                badDebtSheet.setDescription(subBadDebtSheet.getDescription());
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //定义时间格式
                badDebtSheet.setCreateTime(format.parse(subBadDebtSheet.getCreateTime()));
                badDebtSheet.setCreator(subBadDebtSheet.getCreator());
                badDebtSheet.setAppId(appId);
                badDebtSheetService.add(badDebtSheet);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        BadDebtSheet badDebtSheet = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,BadDebtSheet.class);
        badDebtSheetService.update(badDebtSheet);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString){
        BadDebtSheet badDebtSheet = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,BadDebtSheet.class);
        badDebtSheetService.delete(badDebtSheet);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        long appId = UserContext.currentUserAppId();
        List<BadDebtSheet> list = badDebtSheetService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    /*@Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getBadDebtSheetId")
    @POST
    public String getBadDebtSheetId(@FormParam("number") String number,@FormParam("carNumber") String carNumber,@FormParam("customerId") String customerId){
       return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }*/
}
