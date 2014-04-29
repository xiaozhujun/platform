package org.whut.platform.business.datarule.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.address.entity.Address;
import org.whut.platform.business.address.service.AddressService;
import org.whut.platform.business.datarule.service.DataRoleAddressService;
import org.whut.platform.business.user.service.UserService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-4-23
 * Time: 下午2:26
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/dataRuleAddress")
public class DataRoleAddressServiceWeb {
    private static PlatformLogger logger = PlatformLogger.getLogger(DataRoleAddressServiceWeb.class);

    @Autowired
    private DataRoleAddressService dataRoleAddressService;
    @Autowired
    private UserService userService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getProvinceAndColorWithDataRole")
    public String getProvinceAndColorWithDataRole(){
        String userName=userService.getMyUserDetailFromSession().getUsername();
        List<Map<String,String>> list=dataRoleAddressService.getProvinceAndColorWithDataRole(userName);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getCityAndColorWithDataRole")
    public String getCityAndColorWithDataRole(@FormParam("province") String province){
        String userName=userService.getMyUserDetailFromSession().getUsername();
        List<Map<String,String>> list=dataRoleAddressService.getCityAndColorWithDataRole(province,userName);
        return JsonResultUtils.getObjectStrResultByStringAsDefault(list,200,province);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getAreaAndColorWithDataRole")
    public String getAreaAndColorWithDataRole(@FormParam("province") String province,@FormParam("city")String city){
        String userName=userService.getMyUserDetailFromSession().getUsername();
        List<Map<String,String>> list=dataRoleAddressService.getAreaAndColorWithDataRole(province,city,userName);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
}
