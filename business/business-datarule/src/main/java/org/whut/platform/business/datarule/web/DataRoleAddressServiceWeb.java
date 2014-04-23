package org.whut.platform.business.datarule.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.address.entity.Address;
import org.whut.platform.business.address.service.AddressService;
import org.whut.platform.business.user.service.UserService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-4-23
 * Time: 下午2:26
 * To change this template use File | Settings | File Templates.
 */
public class DataRoleAddressServiceWeb {
    private static PlatformLogger logger = PlatformLogger.getLogger(DataRoleAddressServiceWeb.class);

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getProvinceWithDataRule")
    public String getProvinceWithDataRule(){
        String userName=userService.getMyUserDetailFromSession().getUsername();
        List<Address> list=addressService.getProvinceWithDataRule(userName);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getCityWithDataRule")
    public String getCityWithDataRule(@FormParam("province") String province){
        String userName=userService.getMyUserDetailFromSession().getUsername();
        List<Address> list=addressService.getCityWithDataRule(province,userName);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getAreaWithDataRule")
    public String getAreaWithDataRule(@FormParam("province") String province,@FormParam("city")String city){
        String userName=userService.getMyUserDetailFromSession().getUsername();
        List<Address> list=addressService.getAreaWithDataRule(province,city,userName);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
}
