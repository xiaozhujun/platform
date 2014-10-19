package org.whut.rentManagement.business.stock.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.stock.entity.Stock_out_device;
import org.whut.rentManagement.business.stock.service.Stock_out_deviceService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-12
 * Time: 下午3:16
 * To change this template use File | Settings | File Templates.
 */
public class Stock_out_deviceServiceWeb {
    @Component
    @Path("/stock_out_device")
    public class Stock_out_devicetServiceWeb {
        @Autowired
        private Stock_out_deviceService stock_out_deviceService;
        //    @Autowired
//    private Stock_out_deviceService stock_out_deviceService;
        @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
        @Path("/add")
        @POST
        public String add(@FormParam("stockOutId") long stockOutId,@FormParam("deviceId")long deviceId){
            long appId = UserContext.currentUserAppId();
            if(stockOutId==0||deviceId==0){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
            }
            long id;
            try {
                id=stock_out_deviceService.getIdByStockOutIdAndDeviceId(stockOutId, deviceId);
            }catch (Exception e){
                id=0;
            }
            if(id!=0){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "已存在");
            }

            Stock_out_device stock_out_device=new Stock_out_device();
            stock_out_device.setStockOutId(stockOutId);
            stock_out_device.setDeviceId(deviceId);
            stock_out_deviceService.add(stock_out_device);
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
            Stock_out_device stock_out_sheet = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Stock_out_device.class);
            if(stock_out_sheet.getStockOutId()==0||stock_out_sheet.getStockOutId()==0){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
            }
            long id;
            try {
                id=stock_out_deviceService.getIdByStockOutIdAndDeviceId(stock_out_sheet.getStockOutId(), stock_out_sheet.getDeviceId());
            }catch (Exception e){
                id=0;
            }
            if(id!=0){
                if(id!=stock_out_sheet.getId()){
                    return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "已存在");
                }
            }
            stock_out_deviceService.update(stock_out_sheet);
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }

//        @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
//        @Path("/list")
//        @POST
//        public String list(){
//            List<Stock_out_sheet> list = stock_out_sheetService.getListByAppId(UserContext.currentUserAppId());
//            return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
//        }

        @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
        @Path("/delete")
        @POST
        public String delete(@FormParam("jsonString") String jsonString){
            Stock_out_device stock_out_sheet= JsonMapper.buildNonDefaultMapper().fromJson(jsonString, Stock_out_device.class);
            stock_out_deviceService.delete(stock_out_sheet);
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }

    }
}
