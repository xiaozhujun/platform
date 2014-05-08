/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-5-7
 * Time: 下午4:51
 * To change this template use File | Settings | File Templates.
 */
$.extend({
  mySlider:function mySlider(){
      jQuery("#Slider4").slider({ from: 1, to: 9, scale: [1, '|', 3, '|', '5', '|', 7, '|', 9], limits: false, step: 1, dimension: '', skin: "plastic",
          callback: function( value ){
              console.dir( this );
              var provincevalue=$("#province").val();
              var cityvalue=$("#city").val();
              var areavalue=$("#area").val();
              if(provincevalue!="0"&&cityvalue=="0"&&areavalue=="0"){
                  // 只查省的风险值
                  $.post($.URL.craneinspectreport.getCityRiskRankFormRiskRange,{"value":value,"province":provincevalue},showCityRankByValueRange,"json");
              }else if(provincevalue!="0"&&cityvalue!="0"&&areavalue=="0"){
                  // 只查省的风险值
                  $.post($.URL.craneinspectreport.getAreaRiskRankFormRiskRange,{"value":value,"province":provincevalue,"city":cityvalue},showAreaRankByValueRange,"json");
              }
              else if(provincevalue!="0"&&cityvalue!="0"&&areavalue!="0"){
                  //查省市区
                  $.post($.URL.craneinspectreport.showRiskRankByValueRange, {"value":value,"city":cityvalue,"area":areavalue},showRiskRankByValueRange,"json");
              }else if(provincevalue=="0"&&cityvalue=="0"&&areavalue=="0"){
                  $.post($.URL.craneinspectreport.showRiskRankByValueRange, {"value":value,"city":city,"area":area},showRiskRankByValueRange,"json");
              }
          }
      });
  }
});
