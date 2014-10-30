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
import java.util.List;

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
    @Autowired
    org.whut.rentManagement.business.customer.service.CustomerService customerService;

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("jsonString") String jsonString){
        long appId= UserContext.currentUserAppId();
        Contract contract= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Contract.class);

        if(contract.getName()==null||contract.getName().equals("")||contract.getCustomerName()==null||contract.getCustomerName().equals("")
                ||contract.getNumber()==null||contract.getNumber().equals("")||contract.getProjectLocation()==null||contract.getProjectLocation().equals("")
                ||contract.getSignTime()==null||contract.getStartTime()==null
                ||contract.getEndTime()==null||contract.getChargeMan()==null||contract.getChargeMan().equals("")
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
        Long id;
        try
        {
            id=customerService.getIdByName(contract.getCustomerName(),appId);
        }
        catch (Exception e){
            id= null;
        }
        if(id==null){
        contract.setAppId(appId);
        contract.setCustomerId(id);
        contractService.add(contract);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else{
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"不存在此客户");
        }
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
