package org.whut.platform.business.address.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.address.entity.Address;
import org.whut.platform.business.address.service.AddressService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-2-6
 * Time: 下午9:32
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/address")
public class AddressServiceWeb {

    private static PlatformLogger logger = PlatformLogger.getLogger(AddressServiceWeb.class);

    @Autowired
    private AddressService addressService;

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("findByID/{id}")
    @GET
    public String findById(@PathParam("id") String id){
        if (id == null) {
            return JsonResultUtils
                    .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
       Address address;
        try {
            address = addressService.findById(Long.parseLong(id));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
        // 新增操作时，返回操作状态和状态码给客户端，数据区是为空的
        return JsonResultUtils.getObjectResultByStringAsDefault(address,JsonResultUtils.Code.SUCCESS);
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("findByArea/{area}")
    @GET
    public String findByArea(@PathParam("area") String area){
        if (area == null) {
            return JsonResultUtils
                    .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
        Address address;
        try {
            address = addressService.findByArea(area);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
        // 新增操作时，返回操作状态和状态码给客户端，数据区是为空的
        return JsonResultUtils.getObjectResultByStringAsDefault(address,JsonResultUtils.Code.SUCCESS);
    }
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("findByArea/{province},{city},{area}")
    @GET
    public String findIdByArea(@PathParam("province") String province,@PathParam("city") String city,@PathParam("area") String area){
        if (area == null) {
            return JsonResultUtils
                    .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
        Long addressId=addressService.findIdByArea(province,city,area);
        System.out.print(addressId+"......");
        return JsonResultUtils
                .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }


}
