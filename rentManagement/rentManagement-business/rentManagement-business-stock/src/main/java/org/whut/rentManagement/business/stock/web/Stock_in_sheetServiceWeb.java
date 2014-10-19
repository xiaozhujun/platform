package org.whut.rentManagement.business.stock.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.stock.entity.Stock_in_sheet;
import org.whut.rentManagement.business.stock.service.Stock_in_sheetService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public String add(@FormParam("number") String number,@FormParam("carNumber")String carNumber,@FormParam("handler")String handler,
                      @FormParam("description")String description,@FormParam("createTime")String createTime,@FormParam("creator")String creator) {
        long appId= UserContext.currentUserAppId();
        Stock_in_sheet stockInSheet = new Stock_in_sheet();
        stockInSheet.setNumber(number);
        stockInSheet.setCarNumber(carNumber);
        stockInSheet.setCustomerId(0);
        stockInSheet.setContractId(0);
        stockInSheet.setHandler(handler);
        stockInSheet.setStorehouseId(0);
        stockInSheet.setDescription(description);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date time= null;
        try {
            time = sdf.parse(createTime);
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        stockInSheet.setCreateTime(time);
        stockInSheet.setCreator(creator);
        stockInSheet.setAppId(appId);
        stockInSheetService.add(stockInSheet);

        return JsonResultUtils.getObjectResultByStringAsDefault(stockInSheet, JsonResultUtils.Code.SUCCESS);


    }
}
