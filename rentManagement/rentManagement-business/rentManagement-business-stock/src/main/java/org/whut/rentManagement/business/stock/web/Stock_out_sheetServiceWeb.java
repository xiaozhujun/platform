package org.whut.rentManagement.business.stock.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.stock.entity.Stock_out_sheet;
import org.whut.rentManagement.business.stock.service.Stock_out_sheetService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-12
 * Time: 下午3:16
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/stock_out_sheet")
public class Stock_out_sheetServiceWeb {
    @Autowired
    private Stock_out_sheetService stock_out_sheetService;
//    @Autowired
//    private Stock_out_deviceService stock_out_deviceService;
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("number") String number,@FormParam("carNumber")String carNumber,@FormParam("customerId")long customerId,@FormParam("contractId")long contractId,@FormParam("handler")String handler,@FormParam("storehouseId")long storehouseId,@FormParam("description")String description,
                      @FormParam("createTime")String createTime,@FormParam("creator")String creator){
       long appId = UserContext.currentUserAppId();
        if(customerId==0||contractId==0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
        }
        long id;
        try {
            id=stock_out_sheetService.getIdByCustomerIdAndContractId(customerId, contractId);
        }catch (Exception e){
            id=0;
        }
        if(id!=0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "已存在");
        }

        Stock_out_sheet stock_out_sheet=new Stock_out_sheet();
        stock_out_sheet.setNumber(number);
        stock_out_sheet.setCarNumber(carNumber);
        stock_out_sheet.setCustomerId(customerId);
        stock_out_sheet.setContractId(contractId);
        stock_out_sheet.setHandler(handler);
        stock_out_sheet.setCreator(creator);
        stock_out_sheet.setStorehouseId(storehouseId);
        stock_out_sheet.setDescription(description);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date  date = null;
        try {
            date = sdf.parse(createTime);
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "时间格式错误");
        }
        stock_out_sheet.setCreateTime(date);
        stock_out_sheet.setAppId(appId);
        stock_out_sheetService.add(stock_out_sheet);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

//    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
//    @Path("/getList")
//    @POST
//    public String getList(){
//        long appId=UserContext.currentUserAppId();
//        List<InspectChoice> list=inspectChoiceService.getList(appId);
//        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
//    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        long appId= UserContext.currentUserAppId();
        Stock_out_sheet stock_out_sheet = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Stock_out_sheet.class);
        if(stock_out_sheet.getNumber()==null||stock_out_sheet.getNumber().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
        }
        long id;
        try {
            id=stock_out_sheetService.getIdByCustomerIdAndContractId(stock_out_sheet.getCustomerId(),stock_out_sheet.getContractId());
        }catch (Exception e){
            id=0;
        }
        if(id!=0){
            if(id!=stock_out_sheet.getId()){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "已存在");
            }
        }
        stock_out_sheetService.update(stock_out_sheet);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        List<Stock_out_sheet> list = stock_out_sheetService.getListByAppId(UserContext.currentUserAppId());
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString){
        Stock_out_sheet stock_out_sheet= JsonMapper.buildNonDefaultMapper().fromJson(jsonString, Stock_out_sheet.class);
        stock_out_sheetService.delete(stock_out_sheet);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
//    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
//    @Path("/getListByCondition")
//    @POST
//    public String getId(@Param("appId") long appId, @Param("customerId") long customerId, @Param("contractId") long contractId, @Param("storehouseId") long storehouseId){
//        long addId= UserContext.currentUserAppId();
//        if((appId=0&&customerId=0&&(tagName==null||tagName.equals(""))) {
//            deviceType=null;
//            deviceNumber=null;
//            tagName=null;
//        }
//        else if((deviceType!=null||!deviceType.equals(""))&&(deviceNumber==null||deviceNumber.equals(""))&&(tagName==null||tagName.equals(""))){
//            deviceNumber=null;
//            tagName=null;
//        }
//        else if((deviceType==null||deviceType.equals(""))&&(deviceNumber!=null||!deviceNumber.equals(""))&&(tagName==null||tagName.equals(""))){
//            deviceType=null;
//            tagName=null;
//        }
//        else if((deviceType==null||deviceType.equals(""))&&(deviceNumber==null||deviceNumber.equals(""))&&(tagName!=null||!tagName.equals(""))){
//            deviceType=null;
//            deviceNumber=null;
//        }
//        else if((deviceType!=null||!deviceType.equals(""))&&(deviceNumber!=null||!deviceNumber.equals(""))&&(tagName==null||tagName.equals(""))){
//            tagName=null;
//        }
//        else if((deviceType!=null||!deviceType.equals(""))&&(deviceNumber==null||deviceNumber.equals(""))&&(tagName!=null||!tagName.equals(""))){
//            deviceNumber=null;
//        }
//        else if((deviceType==null||deviceType.equals(""))&&(deviceNumber!=null||!deviceNumber.equals(""))&&(tagName!=null||!tagName.equals(""))){
//            deviceType=null;
//        }
//        List<Map<String,String>> mapList = stock_out_sheetService.getListByCondition(appId,customerId,contractId,storehouseId);
//        return JsonResultUtils.getObjectResultByStringAsDefault(mapList, JsonResultUtils.Code.SUCCESS);
//    }
//    @Produces(MediaType.APPLICATION_JSON+";+charset=UTF-8")
//    @Path("/getChoiceValues")
//    @POST
//    public String getChoiceValues(){
//        String choices=inspectChoiceService.getInspectChoicesList();
//        return JsonResultUtils.getObjectResultByStringAsDefault(choices,JsonResultUtils.Code.SUCCESS);
//    }

}
