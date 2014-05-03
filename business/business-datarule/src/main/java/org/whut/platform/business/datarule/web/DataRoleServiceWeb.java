package org.whut.platform.business.datarule.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.address.entity.Address;
import org.whut.platform.business.address.service.AddressService;
import org.whut.platform.business.datarule.entity.DataRole;
import org.whut.platform.business.datarule.entity.DataRoleAddress;
import org.whut.platform.business.datarule.entity.SubDataRole;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
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
    private static PlatformLogger logger = PlatformLogger.getLogger(DataRoleServiceWeb.class);

    @Autowired
    private org.whut.platform.business.datarule.service.DataRoleService dataRoleService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private org.whut.platform.business.datarule.service.DataRoleAddressService dataRoleAddressService;
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/addDataRole")
    public String addDataRole(@FormParam("dRoleName") String dRoleName,@FormParam("dRoleDescription") String dRoleDescription,@FormParam("dRoleStatus") String dRoleStatus,@FormParam("addressIds") String addressIds){
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
        if(addressIds==null||addressIds.trim().equals("")){
            return  JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"未选中区域！");
        }
        else{
          addressIds =addressIds.substring(0,addressIds.length()-1);
          String[] idArray = addressIds.split(",");
          for(int i=0;i<idArray.length;i++){
              System.out.println(">>>>>"+idArray[i]);
              DataRoleAddress dataRoleAddress =new DataRoleAddress();
              dataRoleAddress.setDRoleId(dRoleId);
              dataRoleAddress.setDRoleName(dRoleName);
              dataRoleAddress.setAddressId(Integer.parseInt(idArray[i]));
              dataRoleAddressService.add(dataRoleAddress);
          }
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        /*List<Long> addressIdList = new ArrayList<Long>();
        if(province!=null&&!province.trim().equals("")&&city!=null&&!city.trim().equals("")&&area!=null&&!area.trim().equals("")){
            String[] areaArray = area.split(";");
            for(int i=0;i<areaArray.length;i++){
                long addressId = addressService.findIdByArea(province,city,areaArray[i]);
                addressIdList.add(addressId);
            }
        }
        else if(province!=null&&!province.trim().equals("")&&city!=null&&!city.trim().equals("")){
            String[] cityArray = city.split(";");
            for(int j=0;j<cityArray.length;j++){
                List<Long> list = addressService.findIdByProvinceCity(province,cityArray[j]);
                addressIdList.addAll(list);
            }
        }
        else if(province!=null&&!province.trim().equals("")){
            String[] provinceArray = province.split(";");
            for(int k=0;k<provinceArray.length;k++){
               List<Long>  list = addressService.findIdByProvince(province);
               addressIdList.addAll(list);
            }
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
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);*/
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/list")
    @GET
    public String list(){
       List<DataRole> list = dataRoleService.list();
       return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/listAddress")
    @GET
    public String listAddress(){
        List<DataRole> list = dataRoleService.list();
        List<SubDataRole>listNew=new ArrayList<SubDataRole>();
        for(DataRole a:list) {
            SubDataRole  subDataRole=new SubDataRole();
            long id=a.getId();
            subDataRole.setId(a.getId());
            subDataRole.setName(a.getName());
            subDataRole.setDescription(a.getDescription());
            subDataRole.setStatus(a.getStatus());
            List<Long> addressIDList=dataRoleAddressService.findAddressIdById(id);
            String province="";
            String city="";
            String area="";
            String temp1="";
            String temp2="";
            String temp3="";
            for(long b:addressIDList){
                if(!temp1.equals(addressService.findProvinceById(b))){
                    temp1=addressService.findProvinceById(b);
                    province+=addressService.findProvinceById(b)+";";
                }
                if(!temp2.equals(addressService.findCityById(b))){
                    temp2= addressService.findCityById(b);
                    city+=addressService.findCityById(b)+";";
                }
                if(!temp3.equals(addressService.findAreaById(b)) ){
                    temp3=addressService.findAreaById(b);
                    area+=addressService.findAreaById(b)+";";
                }
            }
            province=province.substring(0,province.length()-1);
            city=city.substring(0,city.length()-1);
            area=area.substring(0,area.length()-1);
            subDataRole.setProvince(province);
            subDataRole.setCity(city);
            subDataRole.setArea(area);
            listNew.add(subDataRole);
        }

        return JsonResultUtils.getObjectResultByStringAsDefault(listNew,JsonResultUtils.Code.SUCCESS);
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/listProvince")
    @GET
    public String listProvince(){
        List<Address> provinceList=addressService.list();
        return JsonResultUtils.getObjectResultByStringAsDefault(provinceList,JsonResultUtils.Code.SUCCESS);
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        SubDataRole  subDataRole= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubDataRole.class);
        DataRole dataRole = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,DataRole.class);
        try {
            dataRoleService.update(dataRole);
            List<DataRoleAddress> dataRoleAddressesList=dataRoleAddressService.findByCondition(dataRole.getId());
            for(DataRoleAddress a:dataRoleAddressesList){
                dataRoleAddressService.delete(a);
            }
            /*String province =  subDataRole.getProvince();
            String city = subDataRole.getCity();
            String area = subDataRole.getArea();
            List<Long> addressIdList = new ArrayList<Long>();
            if(province!=null&&!province.trim().equals("")&&city!=null&&!city.trim().equals("")&&area!=null&&!area.trim().equals("")){
                  long addressId = addressService.findIdByArea(province,city,area);
                  addressIdList.add(addressId);
            }
            else if(province!=null&&!province.trim().equals("")&&city!=null&&!city.trim().equals("")){
                addressIdList = addressService.findIdByProvinceCity(province,city);
            }
            else if(province!=null&&!province.trim().equals("")){
                String[] provinceArray=subDataRole.getProvince().split(";");
                for(int i=0;i<provinceArray.length;i++){
                  addressIdList = addressService.findIdByProvince(provinceArray[i]);
                }
            }
            else{
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"未选中区域！");
            }
            for(Long addressId:addressIdList){
                DataRoleAddress dataRoleAddress =new DataRoleAddress();
                dataRoleAddress.setDRoleId(dataRole.getId());
                dataRoleAddress.setDRoleName(dataRole.getName());
                dataRoleAddress.setAddressId(addressId);
                dataRoleAddressService.add(dataRoleAddress);
            }*/
            String[] provinceArray=subDataRole.getProvince().split(";");
            String[] cityArray=subDataRole.getCity().split(";");
            String[] areaArray=subDataRole.getArea().split(";");
            for(int i=0;i<provinceArray.length;i++)
            {
                for(int j=0;j< cityArray.length;j++)
                {
                    for(int k=0;k<areaArray.length;k++)
                    {
                        Long addressId=addressService.findIdByArea(provinceArray[i],cityArray[j],areaArray[k]);
                        DataRoleAddress dataRoleAddress = new DataRoleAddress();
                        dataRoleAddress.setDRoleId(dataRole.getId());
                        dataRoleAddress.setDRoleName(dataRole.getName());
                        dataRoleAddress.setAddressId(addressId);
                        dataRoleAddressService.add(dataRoleAddress);
                    }
                }
            }
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "修改成功!");
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "修改失败!");
        }
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/updateDataRole")
    @POST
    public String updateDataRole(@FormParam("dRoleName") String dRoleName,@FormParam("dRoleDescription") String dRoleDescription,@FormParam("dRoleStatus") String dRoleStatus,@FormParam("addressIds") String addressIds){
        try {
            DataRole dataRole =new DataRole();
            dataRole.setName(dRoleName);
            dataRole.setDescription(dRoleDescription);
            dataRole.setStatus(Integer.parseInt(dRoleStatus));
            dataRoleService.updateByName(dataRole);
            Long dRoleId = dataRoleService.getIdByName(dRoleName);
            List<DataRoleAddress> dataRoleAddressesList=dataRoleAddressService.findByDataRoleName(dataRole.getName());
            for(DataRoleAddress a:dataRoleAddressesList){
                dataRoleAddressService.delete(a);
            }
            addressIds =addressIds.substring(0,addressIds.length()-1);
            String[] idArray = addressIds.split(",");
            for(int i=0;i<idArray.length;i++){
                System.out.println(">>>>>"+idArray[i]);
                DataRoleAddress dataRoleAddress =new DataRoleAddress();
                dataRoleAddress.setDRoleId(dRoleId);
                dataRoleAddress.setDRoleName(dRoleName);
                dataRoleAddress.setAddressId(Integer.parseInt(idArray[i]));
                dataRoleAddressService.add(dataRoleAddress);
            }
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "修改成功!");
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "修改失败!");
        }
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString){
       DataRole dataRole= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,DataRole.class);
        List<DataRoleAddress> dataRoleAddressesList=dataRoleAddressService.findByCondition(dataRole.getId());
        try {
             dataRoleService.delete(dataRole);
             for(DataRoleAddress a:dataRoleAddressesList){
               dataRoleAddressService.delete(a);
            }
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "删除成功!");
        }
        catch (Exception e){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "删除失败!");
        }

    }


}
