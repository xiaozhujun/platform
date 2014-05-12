/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-5-11
 * Time: 下午6:20
 * To change this template use File | Settings | File Templates.
 */
$.extend({
    conditionSearch:function conditionSearch(pId,cId,aId,eId,usId,slideId){
        var pro=$(pId).find('option:selected').val();
        var city=$(cId).find('option:selected').val();
        var area=$(aId).find('option:selected').val();
        var equipVariety=$(eId).find('option:selected').val();
        var useTime=$(usId).find('option:selected').val();
        var slide=$(slideId).val();
        if(pro=="0"&&city=="0"&&area=="0"){
            //1.在全国范围内设备类型筛选
            $.initMap("中国",5);
            $.post($.URL.dataRuleAddress.getProvinceListWithDataRole,null,getProvinceListCallback,"json");
            $.post($.URL.dataRuleAddress.getProvinceInfoWithDataRuleByCondition,{"equipmentVariety":equipVariety,"useTime":useTime,"value":slide},getProvinceInfoWithDataRuleByConditionCallback,"json");
        }else if(pro!="0"&&city=="0"&&area=="0"){
            //4.在省+设备类型
            $.initMap(pro,8);
            $.post($.URL.address.getCityByProvince,{"province":pro}, getCityByProvinceCallback,"json");
            $.post($.URL.craneinspectreport.getCityInfoByCondition,{"province":pro,"equipmentVariety":equipVariety,"useTime":useTime,"value":slide},getCityInfoByConditionCallback,"json");

        }else if(pro!="0"&&city!="0"&&area=="0"){
            //7.省市+设备类型
            $.initMap(city,10);
            $.post($.URL.address.getAreaByProvinceAndCity,{"province":pro,"city":city}, getAreaByProvinceAndCityCallback,"json");
            $.post($.URL.craneinspectreport.getAreaInfoByCondition,{"province":pro,"city":city,"equipmentVariety":equipVariety,"useTime":useTime,"value":slide},getAreaInfoByConditionCallback,"json");
        }
        else if(pro!="0"&&city!="0"&&area!="0"){
            //10.省市区+设备类型
            $.initMap(area,12);
            $.post($.URL.craneinspectreport.getCraneInfoByCondition,{"province":pro,"city":city,"area":area,"equipmentVariety":equipVariety,"useTime":useTime,"value":slide},getCraneInfoByConditionCallback,"json");
        }
        function getCraneInfoByConditionCallback(data){
            $.showUnitRiskRankCallback(data);
            $.addMarkerArray(data);
        }
        function getCityInfoByConditionCallback(data){
            $.initMap(data.str,8)
            $.getCityWithRule(data.str,data,1);
            $.showCityRank(data);
        }
        function getAreaInfoByConditionCallback(data){
            var str=data.str.split(",");
            $.initMap(str[1],10);
            $.getAreaWithRule(str[0],str[1],data,1);
            $.showAreaRank(data);
        }
        function getProvinceInfoWithDataRuleByConditionCallback(data){
            $.initMap("中国",5);
            $.getProvinceWithRule(data,1);
            $.showProvinceRank(data);
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
        function getProvinceListCallback(data){
            if(data.code==200){
                $("#province").html("");
                var pSearch="<option value='0'>---请选择---</option>";
                for(i=0;i<data.data.length;i++){
                    pSearch+="<option value='"+data.data[i].province+"'>"+data.data[i].province+"</option>";
                }
                $("#province").html(pSearch);
                //$(provinceSelectedValue).attr("selected",true);
            }
        }
    }

});
