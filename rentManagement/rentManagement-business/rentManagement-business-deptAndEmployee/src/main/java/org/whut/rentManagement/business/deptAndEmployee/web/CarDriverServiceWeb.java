package org.whut.rentManagement.business.deptAndEmployee.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.deptAndEmployee.entity.CarDriver;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Component
@Path("/car_Driver")
public class CarDriverServiceWeb {
    private static PlatformLogger logger = PlatformLogger.getLogger(CarDriverServiceWeb.class);
    @Autowired
    private org.whut.rentManagement.business.deptAndEmployee.service.CarDriverService carDriverService;
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name")String name,@FormParam("carNumber")String carNumber,@FormParam("carType")String carType){
        if ((name==null||name.trim().equals(""))){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "名字不能为空");
        }
        if ((carNumber==null||carNumber.trim().equals(""))){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "车牌号不能为空");
        }
        long appId= UserContext.currentUserAppId();
        long id;
        try{
            id =carDriverService.getIdByCarNumber(carNumber, appId);
        }catch (Exception e){
            id=0;
        }
        if (id==0){
            CarDriver car_driver=new CarDriver();
            car_driver.setName(name);
            car_driver.setCarType(carType);
            car_driver.setCarNumber(carNumber);
            car_driver.setAppId(appId);
            Date now=new Date();
            car_driver.setCreateTime(now);
            carDriverService.add(car_driver);
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "该司机已存在");

     }
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString)
    {
        CarDriver car_driver = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,CarDriver.class);
        if ((car_driver.getName()==null||car_driver.getName().trim().equals(""))){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "名字不能为空");
        }else{
            if ((car_driver.getCarNumber()==null||car_driver.getCarNumber().trim().equals(""))){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "车牌号不能为空");
            }
        }
        long appId = UserContext.currentUserAppId();
        long id;
        try{
            id=carDriverService.getIdByCarNumber(car_driver.getCarNumber(), appId);
        }catch (Exception e) {
            id = 0;
        }
        if(id==0||id==car_driver.getId()) {
            carDriverService.update(car_driver);
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "修改的部门已存在！");
    }
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString)
    {
        CarDriver car_driver= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,CarDriver.class);
        carDriverService.delete(car_driver);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        long appId= UserContext.currentUserAppId();
        List<CarDriver> list = carDriverService.list(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
}
