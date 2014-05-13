/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-4-13
 * Time: 下午3:51
 * To change this template use File | Settings | File Templates.
 */
$.extend({
   addressLinkAge:function addressLinkAge(provinceId,cityId,areaId,unitId,equipVarietyId,useTimeId,Slider4Id,provinceValue,cityValue,areaValue){
       //$.post($.URL.address.getProvinceList,null,getProvinceListCallback,"json");
       $.post($.URL.dataRuleAddress.getProvinceListWithDataRole,null,getProvinceListCallback,"json");
       //$.post($.URL.dataRuleAddress.getCityAndColorWithDataRole,{"province":provinceValue}, initCityByProvinceCallback,"json");
      // $.post($.URL.dataRuleAddress.getAreaAndColorWithDataRole,{"province":provinceValue,"city":cityValue}, initAreaByProvinceAndCityCallback,"json");
       $.post($.URL.craneinspectreport.getEquipmentVarietyList,null,getEquipmentVarietyCallback,"json");
       //$.post($.URL.craneinspectreport.getUseTimeList,null,getUseTimeListCallback,"json");
       var pId="#"+provinceId;
       var cId="#"+cityId;
       var aId="#"+areaId;
       var uId="#"+unitId;
       var eId="#"+equipVarietyId;
       var usId="#"+useTimeId;
       var slideId="#"+Slider4Id;
       var cityOption="#"+cityId+"option:not(:first)";
       var areaOption="#"+areaId+"option:not(:first)";
       var unitOption="#"+unitId+"option:not(:first)";
       var equipVarietyFirst=eId+" option[value='0']";
       var useTimeFirst=usId+" option[value='0']";
       $(pId).change(function(){
           $("#alert").html("");
           $(cId).html("<option value='0'>---请选择---</option>");
           $(aId).html("<option value='0'>---请选择---</option>");
           $.conditionSearch(pId,cId,aId,eId,usId,slideId);
         });
       $(cId).change(function(){
           $("#alert").html("");
           $(aId).html("<option value='0'>---请选择---</option>");
           $.conditionSearch(pId,cId,aId,eId,usId,slideId)
       });
       $(aId).change(function(){
           $("#alert").html("");
           $(unitOption).remove();
           $.conditionSearch(pId,cId,aId,eId,usId,slideId)
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
       $(eId).change(function(){
           $.conditionSearch(pId,cId,aId,eId,usId,slideId)
       });
       $(usId).change(function(){
           $.conditionSearch(pId,cId,aId,eId,usId,slideId)
       });
       //联动回调
       function getProvinceListCallback(data){
           if(data.code==200){
               $(pId).html("");
               var pSearch="<option value='0'>---请选择---</option>";
               for(i=0;i<data.data.length;i++){
                   pSearch+="<option value='"+data.data[i].province+"'>"+data.data[i].province+"</option>";
               }
               $(pId).html(pSearch);
               //$(provinceSelectedValue).attr("selected",true);
           }
       }
       function initCityByProvinceCallback(data){
           if(data.code==200){
               $(cId).html("");
               var citySearch="<option value='0'>---请选择---</option>";
               for(i=0;i<data.data.length;i++){
                   citySearch+="<option value='"+data.data[i].city+"'>"+data.data[i].city+"</option>";
               }
               $(cId).html(citySearch);
               //$(citySelectedValue).attr("selected",true);
           }
       }
         function initAreaByProvinceAndCityCallback(data){
             if(data.code==200){
                 $(aId).html("");
                 var areaSearch="<option value='0'>---请选择---</option>";
                 for(i=0;i<data.data.length;i++){
                     areaSearch+="<option value='"+data.data[i].area+"'>"+data.data[i].area+"</option>";
                 }
                 $(aId).html(areaSearch);
                 //$(areaSelectedValue).attr("selected",true);
             }
         }
       function getUnitaddressByAreaCallback(data){
           if(data.code==200){
               $(uId).html("");
               var unitSearch="<option value='0'>---请选择---</option>";
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
       function getEquipmentVarietyCallback(data){
           if(data.code==200){
               $(eId).html("");
               var equipVarietySearch="<option value='0'>---请选择---</option>";
               if(data.data[0]==undefined){
                   equipVarietySearch+="<option>对不起,数据不存在!</option>"
               }else{
                   for(i=0;i<data.data.length;i++){
                       equipVarietySearch+="<option value='"+data.data[i].equipmentVariety+"'>"+data.data[i].equipmentVariety+"</option>";
                   }
               }
               $(eId).html(equipVarietySearch);
           }
       }
    /*   function getUseTimeListCallback(data){
           if(data.code==200){
               $(usId).html("");
               var useTimeSearch="<option value='0'>---请选择---</option>";
               if(data.data[0]==undefined){
                   useTimeSearch+="<option>对不起,数据不存在!</option>"
               }else{
                   for(i=0;i<data.data.length;i++){
                       useTimeSearch+="<option value='"+data.data[i]+"'>"+data.data[i]+"</option>";
                   }
               }
               $(usId).html(useTimeSearch);
           }
       }*/

   }
});