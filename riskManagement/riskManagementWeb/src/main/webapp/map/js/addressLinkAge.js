/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-4-13
 * Time: 下午3:51
 * To change this template use File | Settings | File Templates.
 */
$.extend({
   addressLinkAge:function addressLinkAge(provinceId,cityId,areaId,unitId,equipVarietyId,useTimeId,provinceValue,cityValue,areaValue){
       //$.post($.URL.address.getProvinceList,null,getProvinceListCallback,"json");
       $.post($.URL.dataRuleAddress.getProvinceAndColorWithDataRole,null,getProvinceListCallback,"json");
       //$.post($.URL.dataRuleAddress.getCityAndColorWithDataRole,{"province":provinceValue}, initCityByProvinceCallback,"json");
      // $.post($.URL.dataRuleAddress.getAreaAndColorWithDataRole,{"province":provinceValue,"city":cityValue}, initAreaByProvinceAndCityCallback,"json");
       var pId="#"+provinceId;
       var cId="#"+cityId;
       var aId="#"+areaId;
       var uId="#"+unitId;
       var equipVarietyId="#"+equipVarietyId;
       var useTimeId="#"+useTimeId;
       var cityOption="#"+cityId+"option:not(:first)";
       var areaOption="#"+areaId+"option:not(:first)";
       var unitOption="#"+unitId+"option:not(:first)";
       //var provinceSelectedValue=pId+" option[value='"+provinceValue+"']";
   /*    var citySelectedValue=cId+" option[value='"+cityValue+"']";
       var areaSelectedValue=aId+" option[value='"+areaValue+"']";*/
       $(pId).change(function(){
           $("#alert").html("");
        /*   $(cityOption).remove();
           $(areaOption).remove();
           $(unitOption).remove();*/
           $(cId).html("<option value='0'>---请选择---</option>");
           $(aId).html("<option value='0'>---请选择---</option>");
           var pro=$(pId).find('option:selected').val();
           if(pro=="0"){
               $.showProvinceRisk(1);
           }else{
               $.showCityRisk(pro,1);
               $.post($.URL.dataRuleAddress.getCityAndColorWithDataRole,{"province":pro}, getCityByProvinceCallback,"json");
           }
           //$.post($.URL.address.getCityByProvince,{"province":pro},getCityByProvinceCallback,"json");
//           $.post($.URL.craneinspectreport.getCityAvgRiskValueByProvince,{"province":pro}, $.showCityRank,"json");
       });
       $(cId).change(function(){
           $("#alert").html("");
          /* $(areaOption).remove();
           $(unitOption).remove();*/
           $(aId).html("<option value='0'>---请选择---</option>");
           var pro=$(pId).find('option:selected').val();
           var city=$(this).find('option:selected').val();
           if(pro!="0"&&city=="0"){
               $("#alert").html("请选择城市");
           }else{
               $.showAreaRisk(pro,city,1);
//           $.initArea(pro,city,10,1);
               // $.post($.URL.address.getAreaByProvinceAndCity,{"province":pro,"city":city},getAreaByProvinceAndCityCallback,"json");
               $.post($.URL.dataRuleAddress.getAreaAndColorWithDataRole,{"province":pro,"city":city}, getAreaByProvinceAndCityCallback,"json");
           }
       });
       $(aId).change(function(){
           $("#alert").html("");
           $(unitOption).remove();
           var pro=$(pId).find('option:selected').val();
           var city=$(cId).find('option:selected').val();
           var area=$(this).find('option:selected').val();
            if(pro!="0"&&city!="0"&&area=="0"){
                $("#alert").html("请选择地区");
            }else{
                $.showCompanyRisk(city,area,12);
                $.post($.URL.craneinspectreport.getUnitaddressByArea,{"province":pro,"city":city,"area":area}, getUnitaddressByAreaCallback,"json");
                $.post($.URL.craneinspectreport.getEquipmentVarietyList,null,getEquipmentVarietyCallback,"json");
                $.post($.URL.craneinspectreport.getUseTimeList,null,getUseTimeListCallback,"json");
            }
           //$.dragAbleNavigate(area);
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
       $(equipVarietyId).change(function(){
           var pro=$(pId).find('option:selected').text();
           var city=$(cId).find('option:selected').text();
           var area=$(aId).find('option:selected').text();
           var equipVariety=$(equipVarietyId).find('option:selected').text();
           $.post($.URL.craneinspectreport.getCraneInfoByEquipmentVariety,{"province":pro,"city":city,"area":area,"equipVariety":equipVariety},getCraneInfoByEquipmentVarietyCallback,"json");
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
           function getCityByProvinceCallback(data){
               if(data.code==200){
                   $(cId).html("");
                   var citySearch="<option value='0'>---请选择---</option>";
                   for(i=0;i<data.data.length;i++){
                       citySearch+="<option value='"+data.data[i].city+"'>"+data.data[i].city+"</option>";
                   }
                   $(cId).html(citySearch);
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
           function getAreaByProvinceAndCityCallback(data){
               if(data.code==200){
                   $(aId).html("");
                   var areaSearch="<option value='0'>---请选择---</option>";
                   for(i=0;i<data.data.length;i++){
                       areaSearch+="<option value='"+data.data[i].area+"'>"+data.data[i].area+"</option>";
                   }
                   $(aId).html(areaSearch);
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
               $(equipVarietyId).html("");
               var equipVarietySearch="<option value='0'>---请选择---</option>";
               if(data.data[0]==undefined){
                   equipVarietySearch+="<option>对不起,数据不存在!</option>"
               }else{
                   for(i=0;i<data.data.length;i++){
                       equipVarietySearch+="<option value='"+data.data[i].equipmentVariety+"'>"+data.data[i].equipmentVariety+"</option>";
                   }
               }
               $(equipVarietyId).html(equipVarietySearch);
           }
       }
       function getUseTimeListCallback(data){
           if(data.code==200){
               $(useTimeId).html("");
               var useTimeSearch="<option value='0'>---请选择---</option>";
               if(data.data[0]==undefined){
                   useTimeSearch+="<option>对不起,数据不存在!</option>"
               }else{
                   for(i=0;i<data.data.length;i++){
                       useTimeSearch+="<option value='"+data.data[i]+"'>"+data.data[i]+"</option>";
                   }
               }
               $(useTimeId).html(useTimeSearch);
           }
       }
       function getCraneInfoByEquipmentVarietyCallback(data){
           $.showRiskRank(data);
           $.addMarkerArray(data);
       }
   }
});