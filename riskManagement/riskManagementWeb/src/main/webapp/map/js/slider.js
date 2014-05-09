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
              if(provincevalue!="0"&&cityvalue=="0"&&areavalue=="0"){
                  // 只查省的风险值
                  $.post($.URL.craneinspectreport.getCityRiskRankFormRiskRange,{"value":value,"province":provincevalue}, $.showCityRankByValueRange,"json");
              }else if(provincevalue!="0"&&cityvalue!="0"&&areavalue=="0"){
                  // 只查省的风险值
                  $.post($.URL.craneinspectreport.getAreaRiskRankFormRiskRange,{"value":value,"province":provincevalue,"city":cityvalue}, $.showAreaRankByValueRange,"json");
              }
              else if(provincevalue!="0"&&cityvalue!="0"&&areavalue!="0"){
                  //查省市区
                  $.post($.URL.craneinspectreport.showRiskRankByValueRange, {"value":value,"city":cityvalue,"area":areavalue}, $.showRiskRankByValueRange,"json");
              }else if(provincevalue=="0"&&cityvalue=="0"&&areavalue=="0"){
                  $.post($.URL.craneinspectreport.getProvinceRiskRankFormRiskRange, {"value":value}, $.showProvinceRiskRankFormRiskRange,"json");
              }
          }
      });
  },
    showProvinceRiskRankFormRiskRange:function showProvinceRiskRankFormRiskRange(data){
        if(data.code==200){
            $("#tab").show("");
            $("#rankTitle").html("");
            $("#riskrankContent").html("");
            if(data.data[0]==undefined){
                $("#riskrankContent").append("对不起,数据不存在!");
                $.initMap("中国",5);
            }else{
                $("#rankTitle").html("");
                $("#riskrankContent").html("");
                var rankTitle="<div id='riskttitle'><span class='rtitlerank'>风险排名</span><span class='rtitleItem'>城市</span><span class='rtitleriskItem'>设备数</span></div>";
                $("#rankTitle").append(rankTitle);
                for(var i=0;i<data.data.length;i++){
                    var rankContent;
                    if(i%2==0){
                        rankContent="<div class='riskcontentEven' id='riskcontent"+data.data[i].id+"'>"+"<span class='rrank'>"+(i+1)+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].province+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].craneNumber+"</span></span></div>"
                    }else{
                        rankContent="<div class='riskcontent' id='riskcontent"+data.data[i].id+"'>"+"<span class='rrank'>"+(i+1)+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].province+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].craneNumber+"</span></span></div>"
                    }
                    $("#riskrankContent").append(rankContent);
                    $.provinceClick("#riskcontent"+data.data[i].id,1);
                    $.riskContentClick("riskcontent"+data.data[i].id);
                    /*$.initCityWithDataRule(data.str,8,1);*/
                    $.initMap("中国",5);
                    $.drawProvinceBoundaryWithRule(data.data[i],1);
                }
            }
        }
    },
    showCityRankByValueRange:function showCityRankByValueRange(data){
    if(data.code==200){
        $("#tab").show("");
        $("#rankTitle").html("");
        $("#riskrankContent").html("");
        if(data.data[0]==undefined){
            $("#riskrankContent").append("对不起,数据不存在!");
            $.initMap(data.str,8);
        }else{
            $("#rankTitle").html("");
            $("#riskrankContent").html("");
            var rankTitle="<div id='riskttitle'><span class='rtitlerank'>风险排名</span><span class='rtitleItem'>城市</span><span class='rtitleriskItem'>设备数</span></div>";
            $("#rankTitle").append(rankTitle);
            for(var i=0;i<data.data.length;i++){
                var rankContent;
                if(i%2==0){
                    rankContent="<div class='riskcontentEven' id='riskcontent"+data.data[i].id+"'>"+"<span class='rrank'>"+(i+1)+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].city+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].craneNumber+"</span></span></div>"
                }else{
                    rankContent="<div class='riskcontent' id='riskcontent"+data.data[i].id+"'>"+"<span class='rrank'>"+(i+1)+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].city+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].craneNumber+"</span></span></div>"
                }
                $("#riskrankContent").append(rankContent);
                $.cityClick(data.str,"#riskcontent"+data.data[i].id,1);
                $.riskContentClick("riskcontent"+data.data[i].id);
                /*$.initCityWithDataRule(data.str,8,1);*/
                $.initMap(data.str,8);
                $.drawCityBoundaryWithRule(data.str,data.data[i],1);
            }
        }
    }
},
    showAreaRankByValueRange:function showAreaRankByValueRange(data){
        if(data.code==200){
            $("#tab").show("");
            $("#rankTitle").html("");
            $("#riskrankContent").html("");
            if(data.data[0]==undefined){
                $("#riskrankContent").append("对不起,数据不存在!");
                var arr=data.str.split(",");
                $.initMap(arr[1],10);
            }else{
                $("#rankTitle").html("");
                $("#riskrankContent").html("");
                var rankTitle="<div id='riskttitle'><span class='rtitlerank'>风险排名</span><span class='rtitleItem'>城市</span><span class='rtitleriskItem'>设备数</span></div>";
                $("#rankTitle").append(rankTitle);
                for(var i=0;i<data.data.length;i++){
                    var rankContent;
                    if(i%2==0){
                        rankContent="<div class='riskcontentEven' id='riskcontent"+data.data[i].id+"'>"+"<span class='rrank'>"+(i+1)+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].area+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].craneNumber+"</span></span></div>"
                    }else{
                        rankContent="<div class='riskcontent' id='riskcontent"+data.data[i].id+"'>"+"<span class='rrank'>"+(i+1)+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].area+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].craneNumber+"</span></span></div>"
                    }
                    $("#riskrankContent").append(rankContent);
                    $.areaClick(data.str,"#riskcontent"+data.data[i].id,1);
                    $.riskContentClick("riskcontent"+data.data[i].id);
                    var arr=data.str.split(",");
                    $.initMap(arr[1],10);
                    $.drawAreaBoundaryWithRule(arr[0],arr[1],data.data[i],1);
                }
            }
        }
    },
    showRiskRankByValueRange:function showRiskRankByValueRange(data){
    if(data.code==200){
        $("#rankTitle").html("");
        $("#riskrankContent").html("");
        var rankTitle="<div id='riskttitle'><span class='rtitlerank'>风险排名</span><span class='rtitleItem'>企业</span><span class='rtitleriskItem'>设备数</span></div>";
        $("#rankTitle").append(rankTitle);
        if(data.data[0]==undefined){
            $("#riskrankContent").append("对不起,数据不存在!");
            $.clearAllMarker();
        }else{
            $.loadUnitRiskRankValue(data);
            var arr= $.getMarkerArray(data);
            $.addMarker(arr);
        }
    }
}
});