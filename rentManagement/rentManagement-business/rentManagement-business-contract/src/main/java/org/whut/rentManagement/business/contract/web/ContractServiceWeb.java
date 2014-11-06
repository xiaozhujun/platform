package org.whut.rentManagement.business.contract.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.contract.entity.Contract;
import org.whut.rentManagement.business.contract.service.ContractService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cww
 * Date: 14-10-13
 * Time: 下午1:21
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/contract")
public class ContractServiceWeb {
    @Autowired
    private ContractService contractService;
//    @Autowired
//    org.whut.rentManagement.business.customer.service.CustomerService customerService;

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("jsonString") String jsonString){
        long appId= UserContext.currentUserAppId();
//        Contract contract= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Contract.class);
        Map<String, String> map = JsonMapper.buildNonDefaultMapper().fromJson(jsonString, HashMap.class);
//        if(contract.getName()==null||contract.getName().equals("")||contract.getCustomerName()==null||contract.getCustomerName().equals("")
//                ||contract.getNumber()==null||contract.getNumber().equals("")||contract.getProjectLocation()==null||contract.getProjectLocation().equals("")
//                ||contract.getSignTime()==null||contract.getStartTime()==null
//                ||contract.getEndTime()==null||contract.getChargeMan()==null||contract.getChargeMan().equals("")
//                ){
//            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
//        }
        if(map.get("name")==null||map.get("name").equals("")||map.get("customerName")==null||map.get("customerName").equals("")
//                ||map.get("customerId")==null||map.get("customerId").equals("")
                ||map.get("number")==null||map.get("number").equals("")||map.get("projectLocation")==null||map.get("projectLocation").equals("")
                ||map.get("signTime")==null||map.get("startTime")==null
                ||map.get("endTime")==null||map.get("chargeMan")==null||map.get("chargeMan").equals("")
                ){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }

  /*
        Contract subContract= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Contract.class);
        Contract contract=new Contract();
        contract.setCustomerId(subContract.getCustomerId);
        contract.setCustomerName(subContract.getCustomerName);
        contract.setName(subContract.getName);
        contract.setStatus(subContract.getStatus);
        contract.setStartTime(subContract.getStartTime);
        contract.setEndTime(subContract.getEndTime);
        contract.setSignTime(subContract.getSignTime);
        contract.setProjectLocation(subContract.getProjectLocation);
        contract.setChargeMan(subContract.getChargeMan);
        contract.setPreBuryMan(subContract.getPreBuryMan);
        contract.setPreBuryTime(subContract.getPreBurtTime);
        contract.setPreBuryStatus(subContract.getPreBuryStatus);
        contract.setNeedInstallCount(subContract.getNeedInstallCount);
        contract.setInstallCount(subContract.getInstallCount);
        contract.setSelfInspectTime(subContract.getSelfInspectTime);
        contract.setSelfInspectStatus(subContract.getSelfInspectStatus);
        contract.setNetRegisterMan(subContract.getNetRegisterMan);
        contract.setNetRegisterTime(subContract.getNetRegisterTime);
        contract.setRemoveMan(subContract.getRemoveMan);
        contract.setRemoveStatus(subContract.RemoveStatus);
        contract.setRemoveTime(subContract.getRemoveTime);
        contract.setAppId(appId);
        contractService.add(contract);
    */
//        Long id;
//        try
//        {
//            id=customerService.getIdByName(map.get("customerName"),appId);
//        }
//        catch (Exception e){
//            id= null;
//        }
//        if(id!=null)
//        {

            Date startTime = null;
            Date endTime = null;
            Date signTime = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try{
                startTime = sdf.parse(map.get("startTime"));
                endTime = sdf.parse(map.get("endTime"));
                signTime = sdf.parse(map.get("signTime"));
            }catch (Exception e){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"日期格式错误！");
            }
            if(endTime.before(startTime)){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"结束时间早于开始时间，请确认！");
            }
            Contract contract=new Contract();
            contract.setAppId(appId);
            contract.setName(map.get("name"));
            contract.setNumber(map.get("number"));
            contract.setCustomerId(Long.parseLong(map.get("customerId")));
            contract.setCustomerName(map.get("customerName"));
            contract.setChargeMan(map.get("chargeMan"));
            contract.setProjectLocation(map.get("projectLocation"));
            contract.setEndTime(endTime);
            contract.setSignTime(signTime);
            contract.setStartTime(startTime);
            contractService.add(contract);
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
//        }
//        else
//        {
//            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"不存在此客户");
//        }
    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        Contract contract= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Contract.class);
        if(contract.getName()==null||contract.getName().equals("")||contract.getCustomerName()==null||contract.getCustomerName().equals("")
                ||contract.getNumber()==null||contract.getNumber().equals("")||contract.getProjectLocation()==null||contract.getProjectLocation().equals("")
                ||contract.getSignTime()==null||contract.getStartTime()==null
                ||contract.getEndTime()==null||contract.getChargeMan()==null||contract.getChargeMan().equals("")
                ){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }
        int result=contractService.update(contract);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS) ;
        }
        else {
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/delete")
    @POST
//    public  String delete(@FormParam("jsonString") String jsonString)
    public  String delete(@FormParam("id") long id){
        long appId=UserContext.currentUserAppId();
//        Contract contract=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Contract.class);
//        int result=contractService.deleteById(contract.getId(),appId);
        int result=contractService.deleteById(id,appId);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else {
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        long appId=UserContext.currentUserAppId();
        List<Contract> list=contractService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getContractById")
    @POST
    public String getContractById(@FormParam("id")long id){
        long appId=UserContext.currentUserAppId();
        Contract contract=contractService.getContractById(id,appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(contract,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getNumber")
    @GET
    public String getNumber()
    {   int a=0;
        return JsonResultUtils.getObjectResultByStringAsDefault(a, JsonResultUtils.Code.SUCCESS);
    }
}
