package org.whut.rentManagement.business.stock.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.stock.entity.Stock_in_sheet;
import org.whut.rentManagement.business.stock.entity.Stock_in_sheetP;
import org.whut.rentManagement.business.stock.service.Stock_in_sheetService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Jiaju
 * Date: 14-10-14
 * Time: 下午4:28
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/stock_in_sheet")
public class Stock_in_sheetServiceWeb {
    @Autowired
    Stock_in_sheetService stockInSheetService;


    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("number") String number,@FormParam("carNumber")String carNumber,
                      @FormParam("customerId")long customerId,@FormParam("contractId")long contractId,@FormParam("handler")String handler,
                      @FormParam("storehouseId")long storehouseId,@FormParam("description")String description,
                      @FormParam("creator")String creator,@FormParam("image")String image) {
        if(number==null||"".equals(number.trim())||carNumber==null||"".equals(carNumber.trim())
                ||0==customerId||0==contractId||0==storehouseId
                ||handler==null||"".equals(handler.trim())
                ||description==null||"".equals(description.trim())
                ||creator==null||"".equals(creator.trim())
                ||image==null||"".equals(image.trim())){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }

//        long id;
//        try {
//            id=stock_out_sheetService.getIdByCustomerIdAndContractId(customerId, contractId);
//        }catch (Exception e){
//            id=0;
//        }
//        if(id!=0){
//            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "已存在");
//        }

        long appId= UserContext.currentUserAppId();
        Stock_in_sheet stockInSheet = new Stock_in_sheet();
        stockInSheet.setNumber(number);
        stockInSheet.setCarNumber(carNumber);
        stockInSheet.setCustomerId(customerId);
        stockInSheet.setContractId(contractId);
        stockInSheet.setHandler(handler);
        stockInSheet.setStorehouseId(storehouseId);
        stockInSheet.setDescription(description);
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        Date time= null;
//        try {
//            time = sdf.parse(createTime);
//        } catch (ParseException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
        Date time=new Date();
        stockInSheet.setCreateTime(time);
        stockInSheet.setCreator(creator);
        stockInSheet.setImage(image);
        stockInSheet.setAppId(appId);
        stockInSheetService.add(stockInSheet);

        return JsonResultUtils.getObjectResultByStringAsDefault(stockInSheet, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/list")
    @POST
    public String getList(){
        long appId=UserContext.currentUserAppId();
        List<Stock_in_sheet> list=stockInSheetService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
//        long appId= UserContext.currentUserAppId();
        Stock_in_sheet stockInSheet=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Stock_in_sheet.class);

        if(stockInSheet.getNumber()==null||stockInSheet.getNumber().equals("")
                ||stockInSheet.getCarNumber()==null||stockInSheet.getCarNumber().equals("")
                ||stockInSheet.getHandler()==null||stockInSheet.getHandler().equals("")
                ||stockInSheet.getDescription()==null||stockInSheet.getDescription().equals("")
                ||stockInSheet.getCreator()==null||stockInSheet.getCreator().equals("")
                ||stockInSheet.getImage()==null||stockInSheet.getImage().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }

        stockInSheetService.update(stockInSheet);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
//        Stock_in_sheetP stockInSheetp = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Stock_in_sheetP.class);
//
//        if(stockInSheetp.getNumber()==null||stockInSheetp.getNumber().equals("")
//                ||stockInSheetp.getCarNumber()==null||stockInSheetp.getCarNumber().equals("")
//                ||stockInSheetp.getHandler()==null||stockInSheetp.getHandler().equals("")
//                ||stockInSheetp.getDescription()==null||stockInSheetp.getDescription().equals("")
//                ||stockInSheetp.getCreator()==null||stockInSheetp.getCreator().equals("")){
//            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
//        }
//
//        Stock_in_sheet stockInSheet=new Stock_in_sheet();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String s=stockInSheetp.getCreateTime() ;
//        if(s==null){
//            stockInSheet.setId(stockInSheetp.getId());
//            stockInSheet.setNumber(stockInSheetp.getNumber());
//            stockInSheet.setCarNumber(stockInSheetp.getCarNumber());
//            stockInSheet.setCustomerId(stockInSheetp.getCustomerId());
//            stockInSheet.setContractId(stockInSheetp.getContractId());
//            stockInSheet.setHandler(stockInSheetp.getHandler());
//            stockInSheet.setCreator(stockInSheetp.getCreator());
//            stockInSheet.setStorehouseId(stockInSheetp.getStorehouseId());
//            stockInSheet.setDescription(stockInSheetp.getDescription());
//            stockInSheet.setAppId(appId);
//            stockInSheetService.update(stockInSheet);
//            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "更新成功!");
//        }   else {
//            Date date = null;
//
//            try {
//                date = sdf.parse(stockInSheetp.getCreateTime());
//            } catch (ParseException e) {
//                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "日期格式错误");
//            }
//
//            long oneDayTime = 1000*3600*24;
//            Date Time = new Date(date.getTime() + oneDayTime);
//
//            stockInSheet.setId(stockInSheetp.getId());
//            stockInSheet.setNumber(stockInSheetp.getNumber());
//            stockInSheet.setCarNumber(stockInSheetp.getCarNumber());
//            stockInSheet.setCustomerId(stockInSheetp.getCustomerId());
//            stockInSheet.setContractId(stockInSheetp.getContractId());
//            stockInSheet.setHandler(stockInSheetp.getHandler());
//            stockInSheet.setCreator(stockInSheetp.getCreator());
//            stockInSheet.setStorehouseId(stockInSheetp.getStorehouseId());
//            stockInSheet.setDescription(stockInSheetp.getDescription());
//            stockInSheet.setCreateTime(Time);
//            stockInSheet.setAppId(appId);
//
//            stockInSheetService.update(stockInSheet);
//            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
//        }


    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString){
        Stock_in_sheet stockInSheet = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Stock_in_sheet.class);
        stockInSheetService.delete(stockInSheet);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/getInStockList")
    @POST
    public String getInStockList(@FormParam("user")String user,@FormParam("device")String device,@FormParam("sTime")String sTime,@FormParam("eTime")String eTime){
        Map<String,Object> condition = new HashMap<String, Object>();
        if(user!=null&&!user.equals("")){
            condition.put("user",user);
        }
        if(device!=null&&!device.equals("")){
            condition.put("device",device);
        }
        if(sTime!=null&&!sTime.equals("")){
            condition.put("startTime",sTime+" 00:00:00");
        }
        if(eTime!=null&&!eTime.equals("")){
            condition.put("endTime",eTime+" 59:59:59");
        }
        condition.put("appId", UserContext.currentUserAppId());
        List<Map<String,String>> list=stockInSheetService.getInStockList(condition);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
}
