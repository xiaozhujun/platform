/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-5-8
 * Time: 下午4:31
 * To change this template use File | Settings | File Templates.
 */
$.extend({
  mySearch:function mySearch(btnId,inputId,autoId,cityId,areaId,catId){
      var btn="#"+btnId;
      var input="#"+inputId;
      var auto="#"+autoId;
      var city="#"+cityId;
      var area="#"+areaId;
      document.onkeydown=function(e){
          var theEvent=window.event||e;
          var code=theEvent.keyCode||theEvent.which;
          if(code==13){
              $(btn).click();
              document.getElementById(inputId).focus();
              document.getElementById(inputId).blur();
          }
      }
      $(auto).click(function(){
          $(btn).click();
      })
      $(btn).click(function(){
          var data = {};
          data.city = $(city).val();
          data.area = $(area).val();
          data.require = '%'+$(input).val()+'%';
          if($(input))
              if($$(catId).value==5){
                  $.post($.URL.craneinspectreport.fuzzyQueryByUnitAddress,data,showRiskRankByFuzzyQuery,"json");
              }else if($$(catId).value==8){
                  $.post($.URL.craneinspectreport.fuzzyQueryByUserPoint,data,showRiskRankByFuzzyQuery,"json");
              }else if($$(catId).value==9){
                  $.post($.URL.craneinspectreport.fuzzyQueryBySafeManager,data,showRiskRankByFuzzyQuery,"json");
              }else if($$(catId).value==10){
                  $.post($.URL.craneinspectreport.fuzzyQueryByEquipmentVariety,data,showRiskRankByFuzzyQuery,"json");
              }else if($$(catId).value==12){
                  $.post($.URL.craneinspectreport.fuzzyQueryByManufactureUnit,data,showRiskRankByFuzzyQuery,"json");
              }else{
                  $.post($.URL.craneinspectreport.fuzzyQuery,data,showRiskRankByFuzzyQuery,"json");
              }
      });
      function showRiskRankByFuzzyQuery(data){
          if(data.code==200){
              $("#rankTitle").html("");
              $("#riskrankContent").html("");
              var rankTitle="<div id='riskttitle'><span class='rtitlerank'>风险排名</span><span class='rtitleItem'>企业</span><span class='rtitleriskItem'>风险值</span></div>";
              $("#rankTitle").append(rankTitle);
              if(data.data[0]==undefined){
                  $("#riskrankContent").append("对不起,"+$("#area").val()+"没有您要查找的数据!");
                  $.clearAllMarker();
              }else{
                  $("#rankTitle").html("");
                  $("#riskrankContent").html("");
                  var rankTitle="<div id='riskttitle'><span class='rtitlerank'>风险排名</span><span class='rtitleItem'>企业</span><span class='rtitleriskItem'>风险值</span></div>";
                  $("#rankTitle").append(rankTitle);
                  var j=1;
                  for(var i=0;i<data.data.length;i++){
                      if(i>0){
                          preValue=data.data[i-1].riskValue;
                          if(data.data[i].riskValue==preValue)
                              j=j;
                          else
                          {
                              j++;
                          }
                      }
                      var rankContent;
                      if(i%2==0){
                          rankContent="<div class='riskcontentEven' id='riskcontent"+data.data[i].id+"'>" +"<span class='rrank'>"+j+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].unitAddress+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].riskValue+"</span></span></div>"
                      }else{
                          rankContent="<div class='riskcontent' id='riskcontent"+data.data[i].id+"'>" +"<span class='rrank'>"+j+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].unitAddress+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].riskValue+"</span></span></div>"
                      }
                      $("#riskrankContent").append(rankContent);
                  }
                  $.addMarkerArray(data);
              }
          }
          $.switchToRiskRankTab();
      }
  }
});
