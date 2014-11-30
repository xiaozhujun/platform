package org.whut.rentManagement.business.contract.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.contract.entity.Contract;
import org.whut.rentManagement.business.contract.service.ContractService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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

    private static final PlatformLogger logger = PlatformLogger.getLogger(ContractServiceWeb.class);

    @Autowired
    private ContractService contractService;

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("jsonString") String jsonString){
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }
        Contract contract= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Contract.class);
        if(contract.getName()==null||contract.getName().equals("")
                ||contract.getNumber()==null||contract.getNumber().equals("")||contract.getProjectLocation()==null||contract.getProjectLocation().equals("")
                ||contract.getSignTime()==null||contract.getStartTime()==null
                ||contract.getEndTime()==null||contract.getChargeMan()==null||contract.getChargeMan().equals("")
                ){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }
        contract.setAppId(UserContext.currentUserAppId());
        contractService.add(contract);
        return JsonResultUtils.getObjectResultByStringAsDefault(contract.getId(), JsonResultUtils.Code.SUCCESS);

    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }
        Contract contract= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Contract.class);
        if(contract.getName()==null||contract.getName().equals("")
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
    public  String delete(@FormParam("id") long id){
        long appId=UserContext.currentUserAppId();
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
        List<Map<String,Object>> list=contractService.getListByAppId(appId);
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

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/getContractList")
    @POST
    public String getContractList(@FormParam("user")String user,@FormParam("device")String device,@FormParam("sTime")String sTime,@FormParam("eTime")String eTime){
        Map<String,Object> condition = new HashMap<String, Object>();
        if(user!=null&&!user.equals("")){
            condition.put("user",user);
        }
        if(device!=null&&!device.equals("")){
            condition.put("device",device);
        }
        if(sTime!=null&&!sTime.equals("")){
            condition.put("startTime",sTime+" 00:00:00");
        }
        if(eTime!=null&&!eTime.equals("")){
            condition.put("endTime",eTime+" 59:59:59");
        }
        condition.put("appId", UserContext.currentUserAppId());
        List<Map<String,String>> list=contractService.getContractList(condition);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/upload")
    @POST
    public String upload(@Context HttpServletRequest request
    ) {
        if (request == null) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        try {
            MultipartFile file = multipartRequest.getFile("filename");
            String filename = file.getOriginalFilename();
            String [] s = filename.split("\\.");

            long contractId = Long.parseLong(multipartRequest.getParameter("contractId"));
            long appId = UserContext.currentUserAppId();

            String contractAttachmentRootPath =  FundamentalConfigProvider.get("rentManagement.attachment.root.path") ;
            String contractAttachmentRelativePath =  FundamentalConfigProvider.get("rentManagement.attachment.relative.path") ;
            String attachmentDir = contractAttachmentRootPath + contractAttachmentRelativePath+"/"+appId+"/contract";
            String attachment = contractAttachmentRelativePath+"/"+appId+"/attachment/" + contractId +"_"+ filename;
            String attachmentPath = attachmentDir + "/" + contractId +"_"+ filename;

            //判断文件夹是否存在，不存在则创建
            File dir = new File(attachmentDir);
            if(!dir.exists()){
                dir.mkdirs();
            }
            File attachmentFile = new File(attachmentPath);
            if(attachmentFile.exists()){
                attachmentFile.delete();
            }
            attachmentFile.createNewFile();


            InputStream inputStream = file.getInputStream();
            byte[] bs = new byte[1024 * 2];
            int len;
            OutputStream outputStream = new FileOutputStream(attachmentPath);
            while ((len = inputStream.read(bs)) != -1) {
                outputStream.write(bs,0,len);
            }
            outputStream.close();
            inputStream.close();
            Contract contract = new Contract();
            contract.setId(contractId);
            contract.setAppId(UserContext.currentUserAppId());
            contract.setAttachment(attachment);
            contractService.update(contract);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
}
