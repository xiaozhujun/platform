package org.whut.platform.business.riskcolor.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.riskcolor.entity.RiskColor;
import org.whut.platform.business.riskcolor.service.RiskColorService;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-4-14
 * Time: 上午9:30
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/riskColor")
public class RiskColorServiceWeb {
     @Autowired
     RiskColorService riskColorService;
     @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
     @Path("/list")
     @GET
     public String getRiskValues(){
         List<RiskColor> list = riskColorService.list();
         return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
     }

     @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
     @Path("/setColor")
     @POST
     public String setColor(@FormParam("riskValue") float riskValue,@FormParam("riskColor") String riskColor){
         if(riskColor==null ||  riskColor.trim().equals("")){
             return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "风险颜色值不能为空!");
         }
         RiskColor rc = new RiskColor();
         rc.setRiskValue(riskValue);
         rc.setRiskColor(riskColor);
         int success = riskColorService.setColor(rc);
         if(success>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
         }
         else {
             return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
         }

     }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        RiskColor riskColor = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,RiskColor.class);
        int updateId= riskColorService.update(riskColor);
        if (updateId>0)
        {
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);

    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString){
        RiskColor riskColor = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,RiskColor.class);

        int successId= riskColorService.delete(riskColor);
          if (successId>0)
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
           else
              return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
    }
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/getRiskValueList")
    @POST
    public String getRiskValueList()
    {
        List<RiskColor> list=riskColorService.getRiskValueList();
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
}
