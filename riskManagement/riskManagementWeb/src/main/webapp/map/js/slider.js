/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-5-7
 * Time: 下午4:51
 * To change this template use File | Settings | File Templates.
 */
$.extend({
  mySlider:function mySlider(provinceId,cityId,areaId,equipVarietyId,useTimeId){
      jQuery("#Slider4").slider({ from: 1, to: 9, scale: [1, '|', 3, '|', '5', '|', 7, '|', 9], limits: false, step: 1, dimension: '', skin: "plastic",
          callback: function( value ){
              console.dir( this );
              var province="#"+provinceId;
              var city="#"+cityId;
              var area="#"+areaId;
              var equipVariety="#"+equipVarietyId;
              var useTime="#"+useTimeId;
              var provincevalue=$(province).val();
              var cityvalue=$(city).val();
              var areavalue=$(area).val();
              var equipVarietyvalue=$(equipVariety).val();
              var useTimevalue=$(useTime).val();
              if(provincevalue=="0"&&cityvalue=="0"&&areavalue=="0"){
                  //全国的+风险区间
                  $.post($.URL.dataRuleAddress.getProvinceInfoWithDataRuleByCondition,{"equipmentVariety":equipVarietyvalue,"useTime":useTimevalue,"value":value}, getProvinceInfoWithDataRuleByConditionCallback,"json");
                  //$.post($.URL.craneinspectreport.getProvinceRiskRankFormRiskRange, {"value":value}, $.showProvinceRiskRankFormRiskRange,"json");
              }else if(provincevalue!="0"&&cityvalue=="0"&&areavalue=="0"){
                  // 省+风险区间
                  $.post($.URL.craneinspectreport.getCityInfoByCondition,{"province":provincevalue,"equipmentVariety":equipVarietyvalue,"useTime":useTimevalue,"value":value}, getCityInfoByConditionCallback,"json");
              }else if(provincevalue!="0"&&cityvalue!="0"&&areavalue=="0"){
                  // 省市+风险区间
                  $.post($.URL.craneinspectreport.getAreaInfoByCondition,{"province":provincevalue,"city":cityvalue,"equipmentVariety":equipVarietyvalue,"useTime":useTimevalue,"value":value},getAreaInfoByConditionCallback,"json");
              }else if(provincevalue!="0"&&cityvalue!="0"&&areavalue!="0"){
                  //查省市区+风险区间
                  $.post($.URL.craneinspectreport.getCraneInfoByCondition,{"province":provincevalue,"city":cityvalue,"area":areavalue,"equipmentVariety":equipVarietyvalue,"useTime":useTimevalue,"value":value},getCraneInfoByConditionCallback,"json");
              }
              function getProvinceInfoWithDataRuleByConditionCallback(data){
                  $.initMap("中国",5);
                  $.getProvinceWithRule(data);
                  $.showProvinceRank(data);
              }
              function getCraneInfoByConditionCallback(data){
                  $.showRiskRank(data);
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
          }
      });
  }
});
