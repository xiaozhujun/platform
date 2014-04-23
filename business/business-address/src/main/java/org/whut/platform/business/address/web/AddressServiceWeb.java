package org.whut.platform.business.address.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.address.entity.Address;
import org.whut.platform.business.address.service.AddressService;
import org.whut.platform.business.user.service.UserService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
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
    @Autowired
    private UserService userService;

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

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/list")
    @POST
    public String list()
    {
        List<Address> list=addressService.list();
        List<Address> provinceList=addressService.getProvinceList();
        List<List<Address>> List =new ArrayList<List<Address>>();
        List.add(list);
        List.add(provinceList);
        return JsonResultUtils.getObjectResultByStringAsDefault(List,JsonResultUtils.Code.SUCCESS);
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/getCityByProvince")
    @POST
    public String getCityByProvince(@FormParam("province") String province)
    {
        List<Address> list=addressService.getCityByProvince(province);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/getAreaByCity")
    @POST
    public String getAreaByCity(@FormParam("city") String city)
    {
        List<Address> list=addressService.getAreaByCity(city);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getAreaByProvinceAndCity")
    public String getAreaByProvinceAndCity(@FormParam("province") String province,@FormParam("city") String city){
        List<Address> list=addressService.getAreaByProvinceAndCity(province,city);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }


    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getProvinceList")
    public String getProvinceList(){
         List<Address> proviceList=addressService.getProvinceList();
         return JsonResultUtils.getObjectResultByStringAsDefault(proviceList,JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getProvinceWithDataRule")
    public String getProvinceWithDataRule(){
          String userName=userService.getMyUserDetailFromSession().getUsername();
          List<Address> list=addressService.getProvinceWithDataRule(userName);
          return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
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
