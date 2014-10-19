package org.whut.rentManagement.business.customer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.customer.entity.Customer;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoshou
 * Date: 14-10-10
 * Time: 下午6:29
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/customer")
public class CustomerServiceWeb {
    @Autowired
    org.whut.rentManagement.business.customer.service.CustomerService customerService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,
                      @FormParam("description") String description,
                      @FormParam("address") String address,
                      @FormParam("linkman") String linkman,
                      @FormParam("telephone") String telephone,
                      @FormParam("email") String email,
                      @FormParam("qq") String qq,
                      @FormParam("bank") String bank,
                      @FormParam("bankAccount") String bankAccount
                      ){
        long appId= UserContext.currentUserAppId();
        if(name==null||name.equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }
        name = name.replaceAll("\\s*","");
        long id;
        try {
            //id=customerService.getIdByName(name,appId);
            id=customerService.getIdByTelephone(telephone);
        }catch (Exception e){
            id=0;
        }


        if(id==0) {
            Customer customer=new Customer();
            customer.setName(name);
            customer.setDescription(description);
            customer.setAddress(address);
            customer.setLinkman(linkman);
            customer.setTelephone(telephone);
            customer.setEmail(email);
            customer.setQq(qq);
            customer.setBank(bank);
            customer.setBankAccount(bankAccount);
            Date now = new Date();
            customer.setCreateTime(now);
            customer.setAppId(appId);
            customerService.add(customer);
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"添加成功!");

    }else {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"此客户已存在");
        }
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        long appId=UserContext.currentUserAppId();
        Customer customer = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Customer.class);
        if(customer.getName()==null||customer.getName().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }
        Date now=new Date();
        customer.setCreateTime(now);
        long id;
        try {
            //id=customerService.getIdByName(customer.getName(),appId);
            id=customerService.getIdByTelephone(customer.getTelephone());
        }catch (Exception e){
            id=0;
        }
        if(id!=0){
            if(id!=customer.getId()){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"此客户已存在");
            }
        }
        customerService.update(customer);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    /*添加saas化*/
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        long appId=UserContext.currentUserAppId();

        List<Customer> list=customerService.list(appId,null,null,null,null,null,null,null,null,null,null);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString){
        Customer customer = JsonMapper.buildNonDefaultMapper().fromJson(jsonString, Customer.class);
        customerService.delete(customer);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
}

