package org.whut.platform.business.datarule.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.address.service.AddressService;
import org.whut.platform.business.datarule.entity.DataRole;
import org.whut.platform.business.datarule.entity.DataRoleAddress;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-4-21
 * Time: 下午2:13
 * To change this template use File | Settings | File Templates.
 */

@Component
@Path("/dataRule")
public class DataRoleServiceWeb {
    @Autowired
    private org.whut.platform.business.datarule.service.DataRoleService dataRoleService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private org.whut.platform.business.datarule.service.DataRoleAddressService dataRoleAddressService;
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF8")
    @POST
    @Path("/addDataRole")
    public String addDataRole(@FormParam("dRoleName") String dRoleName,@FormParam("dRoleDescription") String dRoleDescription,@FormParam("dRoleStatus") String dRoleStatus,@FormParam("province") String province,@FormParam("city") String city,@FormParam("area") String area){
        /*if(dRoleName==null||dRoleName.trim().equals("")||dRoleDescription==null||dRoleDescription.trim().equals("")||dRoleStatus==null||dRoleStatus.equals("")){
               return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        } */
        long existId;
        try{
            existId = dataRoleService.getIdByName(dRoleName);
        }
        catch(Exception e){
            existId = 0;
            e.printStackTrace();
        }
        if(existId!=0){
          return  JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"用户名已存在！");
        }
        DataRole dataRole = new DataRole();
        dataRole.setName(dRoleName);
        dataRole.setDescription(dRoleDescription);
        dataRole.setStatus(Integer.parseInt(dRoleStatus));
        dataRoleService.add(dataRole);
        long dRoleId = dataRoleService.getIdByName(dRoleName);
        List<Long> addressIdList = new ArrayList<Long>();
        if(province!=null&&!province.trim().equals("")&&city!=null&&!city.trim().equals("")&&area!=null&&!area.trim().equals("")){
            long addressId = addressService.findIdByArea(province,city,area);
            addressIdList.add(addressId);
        }
        else if(province!=null&&!province.trim().equals("")&&city!=null&&!city.trim().equals("")){
            addressIdList = addressService.findIdByProvinceCity(province,city);
        }
        else if(province!=null&&!province.trim().equals("")){
            addressIdList = addressService.findIdByProvince(province);
        }
        else{
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"未选中区域！");
        }
        for(Long addressId:addressIdList){
            DataRoleAddress dataRoleAddress =new DataRoleAddress();
            dataRoleAddress.setDRoleId(dRoleId);
            dataRoleAddress.setDRoleName(dRoleName);
            dataRoleAddress.setAddressId(addressId);
           dataRoleAddressService.add(dataRoleAddress);
        }
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
}
