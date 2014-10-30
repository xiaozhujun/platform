package org.whut.rentManagement.business.supplier.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.supplier.entity.Supplier;
import org.whut.rentManagement.business.supplier.service.SupplierService;

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
 * Date: 14-10-18
 * Time: 下午8:19
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/supplier")
public class SupplierServiceWeb {
    @Autowired
    SupplierService supplierService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,
                      @FormParam("description") String description,
                      @FormParam("address") String address,
                      @FormParam("linkman") String linkman,
                      @FormParam("telephone") String telephone,
                      @FormParam("email") String email,
                      @FormParam("qq") String qq
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
        name = name.replaceAll("\\s*","");
        long id;
        try {
            //id=customerService.getIdByName(name,appId);
            id=supplierService.getIdByTelephone(telephone);
        }catch (Exception e){
            id=0;
        }


        if(id==0) {
            Supplier supplier=new Supplier();
            supplier.setName(name);
            supplier.setDescription(description);
            supplier.setAddress(address);
            supplier.setLinkman(linkman);
            supplier.setTelephone(telephone);
            supplier.setEmail(email);
            supplier.setQq(qq);
            Date now = new Date();
            supplier.setCreateTime(now);
            supplier.setAppId(appId);
            supplierService.add(supplier);
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "添加成功!");

        }else {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "此提供商已存在");
        }
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        long appId= UserContext.currentUserAppId();
        Supplier supplier = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Supplier.class);
        if(supplier.getName()==null||supplier.getName().equals("")||supplier.getAddress()==null||supplier.getAddress().equals("")||supplier.getTelephone()==null||supplier.getTelephone().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
        }
        if(!"".equals(supplier.getEmail().trim())){
            if(!supplier.getEmail().matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "邮箱格式不正确!");
            }
        }
        if(!"".equals(supplier.getQq().trim())){
            if(!supplier.getQq().matches("^[0-9]*$")){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "QQ必须是数字!");
            }
        }
        if(!"".equals(supplier.getTelephone().trim())){
            if(!supplier.getTelephone().matches("^[0-9]*$")){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "联系电话必须是数字!");
            }
        }
        supplier.setName(supplier.getName().replaceAll("\\s*",""));
        Date now=new Date();
        supplier.setCreateTime(now);
        long id;
        try {
            //id=customerService.getIdByName(customer.getName(),appId);
            id=supplierService.getIdByTelephone(supplier.getTelephone());
        }catch (Exception e){
            id=0;
        }
        if(id!=0){
            if(id!=supplier.getId()){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "此提供商已存在");
            }
        }
        supplierService.update(supplier);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    /*添加saas化*/
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        long appId= UserContext.currentUserAppId();

        List<Supplier> list=supplierService.list(appId,null,null,null,null,null,null,null,null);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString){
        Supplier supplier = JsonMapper.buildNonDefaultMapper().fromJson(jsonString, Supplier.class);
        supplierService.delete(supplier);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Path("/listSupplierByNameAndLinkman")
    @POST
    public String listSupplierByNameAndLinkman(@FormParam("name") String name,@FormParam("linkman") String linkman){
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
        List<Supplier> list = supplierService.getByNameAndLinkman(name,linkman,appId);
        List<Supplier> supplierList=new ArrayList<Supplier>();
        for(Supplier supplier:list){
            Supplier subSupplier = new Supplier();
            subSupplier.setName(supplier.getName());
            subSupplier.setDescription(supplier.getDescription());
            subSupplier.setAddress(supplier.getAddress());
            subSupplier.setLinkman(supplier.getLinkman());
            subSupplier.setTelephone(supplier.getTelephone());
            subSupplier.setEmail(supplier.getEmail());
            subSupplier.setQq(supplier.getQq());
            subSupplier.setCreateTime(supplier.getCreateTime());
            supplierList.add(subSupplier);
        }
        if (supplierList.toArray().length==0)  {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "查询不到结果!");
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(supplierList, JsonResultUtils.Code.SUCCESS);


    }
}
