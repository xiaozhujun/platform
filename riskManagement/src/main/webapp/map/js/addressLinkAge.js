/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-4-13
 * Time: 下午3:51
 * To change this template use File | Settings | File Templates.
 */
$.extend({
   addressLinkAge:function addressLinkAge(provinceId,cityId,areaId,unitId,provinceValue){
       //$.post($.URL.address.getProvinceList,null,getProvinceListCallback,"json");
       $.post($.URL.dataRuleAddress.getProvinceAndColorWithDataRole,null,getProvinceListCallback,"json");
       var pId="#"+provinceId;
       var cId="#"+cityId;
       var aId="#"+areaId;
       var uId="#"+unitId;
       var cityOption="#"+cityId+"option:not(:first)";
       var areaOption="#"+areaId+"option:not(:first)";
       var unitOption="#"+unitId+"option:not(:first)";
       var provinceSelectedValue=pId+" option[value='"+provinceValue+"']";

       $(pId).change(function(){
           $(cityOption).remove();
           $(areaOption).remove();
           $(unitOption).remove();
           var pro=$(pId).find('option:selected').text();
           $.showCityRisk(pro);
           $.post($.URL.dataRuleAddress.getCityAndColorWithDataRole,{"province":pro},getCityByProvinceCallback,"json");
           //$.post($.URL.address.getCityByProvince,{"province":pro},getCityByProvinceCallback,"json");
//           $.post($.URL.craneinspectreport.getCityAvgRiskValueByProvince,{"province":pro}, $.showCityRank,"json");
       });
       $(cId).change(function(){
           $(areaOption).remove();
           $(unitOption).remove();
           var pro=$(pId).find('option:selected').text();
           var city=$(this).find('option:selected').text();
           $.showAreaRisk(pro,city);
//           $.initArea(pro,city,10,1);
          // $.post($.URL.address.getAreaByProvinceAndCity,{"province":pro,"city":city},getAreaByProvinceAndCityCallback,"json");
           $.post($.URL.dataRuleAddress.getAreaAndColorWithDataRole,{"province":pro,"city":city},getAreaByProvinceAndCityCallback,"json");
       });
       $(aId).change(function(){
           $(unitOption).remove();
           var pro=$(pId).find('option:selected').text();
           var city=$(cId).find('option:selected').text();
           var area=$(this).find('option:selected').text();
           $.showCompanyRisk(city,area,12);
           $.post($.URL.craneinspectreport.getUnitaddressByArea,{"province":pro,"city":city,"area":area},getUnitaddressByAreaCallback,"json");
           $.dragAbleNavigate(area);
       });
       $(uId).change(function(){
           var pro=$(pId).find('option:selected').text();
           var city=$(cId).find('option:selected').text();
           var area=$(aId).find('option:selected').text();
           var unit=$(this).find('option:selected').text();
           $.getCompanyInfoByUnitAddress(unit);
           $.showUnitRiskRank(unit);
           $.showRiskInfo(unit);
       });
       //联动回调
       function getProvinceListCallback(data){
           if(data.code==200){
               $(pId).html("");
               var pSearch="<option>---请选择---</option>";
               for(i=0;i<data.data.length;i++){
                   pSearch+="<option value='"+data.data[i].province+"'>"+data.data[i].province+"</option>";
               }
               $(pId).html(pSearch);
               $(provinceSelectedValue).attr("selected",true);
           }
       }
       function getCityByProvinceCallback(data){
           if(data.code==200){
               $(cId).html("");
               var citySearch="<option>---请选择---</option>";
               for(i=0;i<data.data.length;i++){
                   citySearch+="<option value='"+data.data[i].city+"'>"+data.data[i].city+"</option>";
               }
               $(cId).html(citySearch);
           }
       }
       function getAreaByProvinceAndCityCallback(data){
           if(data.code==200){
               $(aId).html("");
               var areaSearch="<option>---请选择---</option>";
               for(i=0;i<data.data.length;i++){
                   areaSearch+="<option value='"+data.data[i].area+"'>"+data.data[i].area+"</option>";
               }
               $(aId).html(areaSearch);
           }
       }
       function getUnitaddressByAreaCallback(data){
           if(data.code==200){
               $(uId).html("");
               var unitSearch="<option>---请选择---</option>";
               if(data.data[0]==undefined){
                   unitSearch+="<option>对不起,数据不存在!</option>"
               }else{
                   for(i=0;i<data.data.length;i++){
                       unitSearch+="<option value='"+data.data[i].unitAddress+"'>"+data.data[i].unitAddress+"</option>";
                   }
               }
               $(uId).html(unitSearch);
           }
       }
   }
});