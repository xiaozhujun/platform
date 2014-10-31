package org.whut.rentManagement.business.stock.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.customer.service.CustomerService;
import org.whut.rentManagement.business.device.service.DeviceService;
import org.whut.rentManagement.business.stock.entity.Stock_out_device;
import org.whut.rentManagement.business.stock.entity.Stock_out_sheet;
import org.whut.rentManagement.business.stock.entity.Stock_out_sheetP;
import org.whut.rentManagement.business.stock.service.Stock_out_deviceService;
import org.whut.rentManagement.business.stock.service.Stock_out_sheetService;

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
    @Autowired
    private Stock_out_deviceService stock_out_deviceService;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private DeviceService deviceService;


    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("number") String number,@FormParam("carNumber")String carNumber,@FormParam("customerId")String customerId,@FormParam("contractId")String contractId,
                      @FormParam("handler")String handler,@FormParam("storehouseId")String storehouseId,@FormParam("description")String description,
                      @FormParam("createTime")String createTime,@FormParam("creator")String creator,
                      @FormParam("deviceId")long deviceId){
       long appId = UserContext.currentUserAppId();
        if(customerId==null||contractId.equals(null)){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
        }
        Stock_out_sheet stock_out_sheet=new Stock_out_sheet();
        String s1=number;
        String s2=customerId;
        String s3=contractId;
        String s4=storehouseId;
        if(!"".equals(s1.trim())||!"".equals(s2.trim())||!"".equals(s3.trim())||!"".equals(s4.trim())){
            if(!s1.matches("^[0-9]*$")||!s2.matches("^[0-9]*$")||!s3.matches("^[0-9]*$")||!s4.matches("^[0-9]*$")){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "格式错误");
            }
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
        Date now = new Date();
        DateFormat d1 = DateFormat.getDateInstance(); //默认语言（汉语）下的默认风格（MEDIUM风格，比如：2008-6-16 20:54:53）
        String str1 = d1.format(now);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date  date = null;
        try {
            date = sdf.parse(str1);
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.

        }
   // long StockOutId = stock_out_sheetService.getStockOutId(stock_out_sheet.getNumber(),stock_out_sheet.getCarNumber(),Long.parseLong(stock_out_sheet.getCustomerId()),Long.parseLong(stock_out_sheet.getContractId()),Long.parseLong(stock_out_sheet.getStorehouseId()),appId);

        Stock_out_device stock_out_device = new Stock_out_device();
        stock_out_device.setStockOutId(id);
        stock_out_device.setDeviceId(deviceId);
        stock_out_deviceService.add(stock_out_device);   //对关联表进行插入操作

        try{

        stock_out_sheet.setNumber(number);
        stock_out_sheet.setCarNumber(carNumber);
        stock_out_sheet.setCustomerId(customerId);
        stock_out_sheet.setContractId(contractId);
        stock_out_sheet.setHandler(handler);
        stock_out_sheet.setCreator(creator);
        stock_out_sheet.setStorehouseId(storehouseId);
        stock_out_sheet.setDescription(description);
        stock_out_sheet.setCreateTime(date);
        stock_out_sheet.setAppId(appId);
        stock_out_sheetService.add(stock_out_sheet);

            //long device = deviceService.getIdByNumber(stock_out_sheet.getDeviceNumber(),appId);

        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "格式错误");
        }


    }



    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        long appId= UserContext.currentUserAppId();
        Stock_out_sheetP stock_out_sheetp = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Stock_out_sheetP.class);
        if(stock_out_sheetp.getNumber()==null||stock_out_sheetp.getNumber().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
        }
        Stock_out_sheet stock_out_sheet=new Stock_out_sheet();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s=stock_out_sheetp.getCreateTime() ;
        if(s==null){
            stock_out_sheet.setId(stock_out_sheetp.getId());
            stock_out_sheet.setNumber(stock_out_sheetp.getNumber());
            stock_out_sheet.setCarNumber(stock_out_sheetp.getCarNumber());
            stock_out_sheet.setCustomerId(stock_out_sheetp.getCustomerId());
            stock_out_sheet.setContractId(stock_out_sheetp.getContractId());
            stock_out_sheet.setHandler(stock_out_sheetp.getHandler());
            stock_out_sheet.setCreator(stock_out_sheetp.getCreator());
            stock_out_sheet.setStorehouseId(stock_out_sheetp.getStorehouseId());
            stock_out_sheet.setDescription(stock_out_sheetp.getDescription());
            stock_out_sheet.setAppId(appId);
            stock_out_sheetService.update(stock_out_sheet);
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "更新成功!");
        }  else {
        Date date = null;
        try {
            date = sdf.parse(stock_out_sheetp.getCreateTime());
        } catch (ParseException e) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "时间格式错误");
        }
        long oneDayTime = 1000*3600*24;
        Date Time = new Date(date.getTime() + oneDayTime);

        stock_out_sheet.setId(stock_out_sheetp.getId());
        stock_out_sheet.setNumber(stock_out_sheetp.getNumber());
        stock_out_sheet.setCarNumber(stock_out_sheetp.getCarNumber());
        stock_out_sheet.setCustomerId(stock_out_sheetp.getCustomerId());
        stock_out_sheet.setContractId(stock_out_sheetp.getContractId());
        stock_out_sheet.setHandler(stock_out_sheetp.getHandler());
        stock_out_sheet.setCreator(stock_out_sheetp.getCreator());
        stock_out_sheet.setStorehouseId(stock_out_sheetp.getStorehouseId());
        stock_out_sheet.setDescription(stock_out_sheetp.getDescription());
        stock_out_sheet.setCreateTime(Time);
        stock_out_sheet.setAppId(appId);
        stock_out_sheetService.update(stock_out_sheet);
        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "更新成功!");
        }
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


 }
