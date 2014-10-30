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
import java.util.ArrayList;
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
        if(name==null||name.equals("")||telephone==null||telephone.equals("")||address==null||address.equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }
        if(!"".equals(email.trim())){
            if(!email.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "邮箱格式不正确!");
            }
        }
        if(!"".equals(qq.trim())){
            if(!qq.matches("^[0-9]*$")){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "QQ必须是数字!");
            }
        }
        if(!"".equals(telephone.trim())){
            if(!telephone.matches("^[0-9]*$")){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "联系电话必须是数字!");
            }
        }
        if(!"".equals(bankAccount.trim())){
            if(!bankAccount.matches("^[0-9]*$")){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "银行账户必须是数字!");
            }
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
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "添加成功!");

    }else {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "此客户已存在");
        }
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        long appId= UserContext.currentUserAppId();
        Customer customer = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Customer.class);
        if(customer.getName()==null||customer.getName().equals("")||customer.getAddress()==null||customer.getAddress().equals("")||customer.getTelephone()==null||customer.getTelephone().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
        }
        if(!"".equals(customer.getEmail().trim())){
            if(!customer.getEmail().matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "邮箱格式不正确!");
            }
        }
        if(!"".equals(customer.getQq().trim())){
            if(!customer.getQq().matches("^[0-9]*$")){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "QQ必须是数字!");
            }
        }
        if(!"".equals(customer.getTelephone().trim())){
            if(!customer.getTelephone().matches("^[0-9]*$")){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "联系电话必须是数字!");
            }
        }
        if(!"".equals(customer.getBankAccount().trim())){
            if(!customer.getBankAccount().matches("^[0-9]*$")){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "银行账户必须是数字!");
            }
        }
       customer.setName(customer.getName().replaceAll("\\s*",""));
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
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "此客户已存在");
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
        long appId= UserContext.currentUserAppId();

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


    @Path("/listCustomerByNameAndLinkman")
    @POST
    public String listCustomerByNameAndLinkman(@FormParam("name") String name,@FormParam("linkman") String linkman){
        if((name==null||name.equals(""))&&(linkman==null||linkman.equals(""))){
            name="";
            linkman="";
        }
        else if((name!=null||!name.equals(""))&&(linkman==null||linkman.equals(""))){
            linkman="";
        }
        else if((name==null||name.equals(""))&&(linkman!=null||!linkman.equals(""))){
            name="";
        }
        long appId = UserContext.currentUserAppId();
        name="%"+name+"%";
        linkman="%"+linkman+"%";
        List<Customer> list = customerService.getByNameAndLinkman(name,linkman,appId);
        List<Customer> customerList=new ArrayList<Customer>();
        for(Customer customer:list){
            Customer subCustomer = new Customer();
            subCustomer.setName(customer.getName());
            subCustomer.setDescription(customer.getDescription());
            subCustomer.setAddress(customer.getAddress());
            subCustomer.setLinkman(customer.getLinkman());
            subCustomer.setTelephone(customer.getTelephone());
            subCustomer.setEmail(customer.getEmail());
            subCustomer.setQq(customer.getQq());
            subCustomer.setBank(customer.getBank());
            subCustomer.setBankAccount(customer.getBankAccount());
            subCustomer.setCreateTime(customer.getCreateTime());
            customerList.add(subCustomer);
        }
        if (customerList.toArray().length==0)  {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "查询不到结果!");
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(customerList, JsonResultUtils.Code.SUCCESS);


    }
}

