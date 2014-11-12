package org.whut.rentManagement.business.deptAndEmployee.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.deptAndEmployee.entity.CarDriver;
import org.whut.rentManagement.business.deptAndEmployee.entity.SubCarDriver;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
        SubCarDriver subCarDriver =  JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubCarDriver.class);
        long appId = UserContext.currentUserAppId();
        if ((subCarDriver.getName()==null||subCarDriver.getName().equals(""))){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "名字不能为空");
        }
        if ((subCarDriver.getCarNumber()==null||subCarDriver.getCarNumber().equals(""))){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "车牌号不能为空");
        }
//        if ((subCarDriver.getCreateTime()==null)){
//            CarDriver carDriverNoDate = new CarDriver();
//            carDriverNoDate.setId(subCarDriver.getId());
//            carDriverNoDate.setName(subCarDriver.getName());
//            carDriverNoDate.setCarNumber(subCarDriver.getCarNumber());
//            carDriverNoDate.setCarType(subCarDriver.getCarType());
//            carDriverNoDate.setAppId(appId);
//            long dpsNoDate;
//            try{
//                dpsNoDate=carDriverService.getIdByCarNumber(subCarDriver.getCarNumber(), appId);
//            }catch (Exception e) {
//                dpsNoDate = 0;
//            }
//            if(dpsNoDate!=0) {
//                if(dpsNoDate!=subCarDriver.getId()){
//                    return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "修改的车牌已存在！");
//                }
//            }
//            carDriverService.update(carDriverNoDate);
//            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
//        }SUCCESS
        CarDriver carDriver = new CarDriver();
//        Date date= null;
        carDriver.setId(subCarDriver.getId());
        carDriver.setName(subCarDriver.getName());
        carDriver.setCarNumber(subCarDriver.getCarNumber());
        carDriver.setCarType(subCarDriver.getCarType());
        carDriver.setAppId(appId);
//        SimpleDateFormat DFT = new SimpleDateFormat("yyyy-MM-dd");
//        try{
//            date=DFT.parse(subCarDriver.getCreateTime());
//        }catch (Exception e){
//            JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"日期格式错误");
//        }
//        //调节时区，解决更新时间会显示前一天的问题
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
//        int day = c.get(Calendar.DATE);
//        c.set(Calendar.DATE, day + 1);
//        Date dateAfter = c.getTime();
//        carDriver.setCreateTime(dateAfter);
        long dps;
        try{
            dps=carDriverService.getIdByCarNumber(subCarDriver.getCarNumber(), appId);
        }catch (Exception e) {
            dps = 0;
        }
        if(dps!=0) {
            if(dps!=subCarDriver.getId()){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "修改的司机已存在！");
            }
        }
        carDriverService.update(carDriver);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
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
    @Path("/listCar_DriverByNameAndCar_Number")
    @POST
    public String listCar_DriverByNameAndCar_Number(@FormParam("name") String name,@FormParam("carNumber") String carNumber){
        if((name==null||name.equals(""))&&(carNumber==null||carNumber.equals(""))){
            name="";
            carNumber="";
        }
        else if((name!=null||!name.equals(""))&&(carNumber==null||carNumber.equals(""))){
            carNumber="";
        }
        else if((name==null||name.equals(""))&&(carNumber!=null||!carNumber.equals(""))){
            name="";
        }
        long appId = UserContext.currentUserAppId();
        name="%"+name+"%";
        carNumber="%"+carNumber+"%";
        List<CarDriver> list = carDriverService.getByNameAndCar_Number(name,carNumber,appId);
        List<CarDriver> carDriverList=new ArrayList<CarDriver>();
        for(CarDriver carDriver:list){
            CarDriver subCarDriver = new CarDriver();
            subCarDriver.setName(carDriver.getName());
            subCarDriver.setCarNumber(carDriver.getCarNumber());
            subCarDriver.setCarType(carDriver.getCarType());
            subCarDriver.setAppId(appId);
            subCarDriver.setCreateTime(carDriver.getCreateTime());
            carDriverList.add(subCarDriver);
        }
        if (carDriverList.toArray().length==0)  {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "查询不到结果!");
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(carDriverList, JsonResultUtils.Code.SUCCESS);


    }
}
