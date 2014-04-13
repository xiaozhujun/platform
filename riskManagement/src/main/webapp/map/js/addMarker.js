/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-4-12
 * Time: 下午4:12
 * To change this template use File | Settings | File Templates.
 */
$.extend({
     addMarker:function addMarker(markerArr){
         for(var i=0;i<markerArr.length;i++){
             var json = markerArr[i];
             var p0 = json.point.split("|")[0];
             var p1 = json.point.split("|")[1];
             var point = new BMap.Point(p0,p1);
             var iconImg = $.createIcon(json.icon);
             var marker = new BMap.Marker(point,{icon:iconImg});
             var iw = $.createInfoWindow(i,markerArr);
             var label = new BMap.Label("",{"offset":new BMap.Size(json.icon.lb-json.icon.x+10,-20)});
             marker.setLabel(label);
             map.addOverlay(marker);
             label.setStyle({
                 borderColor:"#808080",
                 color:"#333",
                 cursor:"pointer"
             });
             (function(){
                 var index = i;
                 var _iw = $.createInfoWindow(i,markerArr);
                 var _marker = marker;
                 if(index==0){
                     _marker.openInfoWindow(_iw);
                 }
                 _marker.addEventListener("click",function(){
                     this.openInfoWindow(_iw);
                     var name=markerArr[index];
                     $.post($.URL.craneinspectreport.getAreaInfoByUnitAddress,{"name":name.title},mapCallback,"json");
                 });
                 function  mapCallback(data){
                     if(data.code==200){
                         //$("#rightshow").css("display","block");
                         $("#panelimg2").css("background","url('map/images/sprites.png') no-repeat scroll -2px -20px  rgba(0, 0, 0, 0)").parent().css("right","384px");
                         $("#righttitle").html("");
                         $("#rightcontent").html("");
                         $("#righttitle").append(data.str);
                         for(var i=0;i<data.data.length;i++){
                             var dataString="<div class='rightitem'><div class='righttop'><span class='pic'><img src='image/qizhongji.jpg'></span><span class='info'><span class='itemfont'>"+data.data[i].equipmentVariety+"</span><span class='itemfont'>风险值:"+data.data[i].riskValue+"</span><span class='hideField' id='hideField"+data.data[i].id+"'>"+data.data[i].unitAddress+","+data.data[i].equipmentVariety+"</span><span class='infofont' id='infofont"+data.data[i].id+"'>详情</span></div><div class='itemInfo' id='itemInfo"+data.data[i].id+"'></div></div>"
                             $("#rightcontent").append(dataString);
                             var infoFontNum="infofont"+data.data[i].id;
                             $("#"+infoFontNum).click(function(){
                                 var hideField="hideField"+(this.id).substring(8,(this.id).length);
                                 var address_equipmentvariety=$("#"+hideField).text();
                                 var itemInfo="#itemInfo"+(this.id).substring(8,(this.id).length);
                                 $(itemInfo).html("");
                                 $.post($.URL.craneinspectreport.getCraneInspectReportInfoByAddressAndEquipment,{"address_equipmentvariety":address_equipmentvariety,"itemInfoId":itemInfo},getCraneInspectReportInfoByAddressAndEquipmentCallBack,"json");
                                 $(itemInfo).toggle();
                             });
                         }
                     }
                 }
                 _iw.addEventListener("open",function(){
                     _marker.getLabel().hide();
                 })
                 _iw.addEventListener("close",function(){
                     _marker.getLabel().show();
                 })
                 label.addEventListener("click",function(){
                     _marker.openInfoWindow(_iw);

                 })
                 if(!!json.isOpen){
                     label.hide();
                     _marker.openInfoWindow(_iw);
                 }
             })()
         }
     },
    //创建InfoWindow
    createInfoWindow:function createInfoWindow(i,markerArr){
    var json = markerArr[i];
    var iw = new BMap.InfoWindow("<b class='iw_poi_title' title='" + json.title + "'>" + json.title + "</b><div class='iw_poi_content'>"+json.content+"</div>");
    return iw;
},
    //创建一个Icon
    createIcon:function createIcon(json){
    var icon = new BMap.Icon("http://app.baidu.com/map/images/us_mk_icon.png", new BMap.Size(json.w,json.h),{imageOffset: new BMap.Size(-json.l,-json.t),infoWindowOffset:new BMap.Size(json.lb+5,1),offset:new BMap.Size(json.x,json.h)})
    return icon;
   },  //根据单位来添加覆盖物以及查询相应的信息
    getCompanyInfoByUnitAddress:function getCompanyInfoByUnitAddress(unitAddress){
    $.clearAllMarker();
    $.post($.URL.craneinspectreport.getOneUnitAddressInfo,{"unitAddress":unitAddress},getOneUnitAddressInfoCallback,"json");
    var unitAddressMarker=new Array();
    function getOneUnitAddressInfoCallback(data){
        if(data.code==200){
            var item={};
            item.title=data.data[0].unitAddress;
            item.content=data.data[0].equipmentVariety+",风险值:"+data.data[0].riskValue;
            item.point=data.data[0].lng+"|"+data.data[0].lat;
            item.isOpen=0;
            /*item.icon={w:23,h:25,l:115,t:21,x:9,lb:12};*/
            item.icon={};
            item.icon.w=23;
            item.icon.h=25;
            item.icon.t=21;
            item.icon.x=9;
            item.icon.lb=12;
            if(data.data[0].riskValue==1){
                item.icon.l=23;
            }
            if(data.data[0].riskValue==2){
                item.icon.l=0;
            }
            if(data.data[0].riskValue==3){
                item.icon.l=69;
            }
            if(data.data[0].riskValue==4){
                item.icon.l=115;
            }
            if(data.data[0].riskValue==5){
                item.icon.l=46;
            }
            if(data.data[0].riskValue==6){
                item.icon.l=46;
            }
            unitAddressMarker.push(item);
            $.addMarker(unitAddressMarker);
        }
    }
},
//根据省市区来添加覆盖物以及查询相应的企业信息
    initAndAddMarker:function initAndAddMarker(city,area){
    $.post($.URL.craneinspectreport.getAreaInfo,{"city":city,"pname":area},areaInfoCallback,"json");
    $.post($.URL.craneinspectreport.showRiskRank,{"city":city,"pname":area},showRiskRank,"json");
    var chaoyangMarker=new Array();
    function areaInfoCallback(data){
        if(data.code==200){
            $("#rightcontent").html("");
            if(data.data[0][0]==undefined){
                var error="<div class='errorInfo'>对不起,数据不存在!</div>";
                $("#rightcontent").append(error);
            }else{
                for(i=0;i<data.data[0].length;i++){
                    var item={};
                    item.title=data.data[0][i].unitAddress;
                    item.content=data.data[0][i].equipmentVariety+",风险值:"+data.data[1][i].riskValue;
                    item.point=data.data[0][i].lng+"|"+data.data[0][i].lat;
                    item.isOpen=0;
                    /*item.icon={w:23,h:25,l:115,t:21,x:9,lb:12};*/
                    item.icon={};
                    item.icon.w=23;
                    item.icon.h=25;
                    item.icon.t=21;
                    item.icon.x=9;
                    item.icon.lb=12;
                    if(data.data[1][i].riskValue==1){
                        item.icon.l=23;
                    }
                    if(data.data[1][i].riskValue==2){
                        item.icon.l=0;
                    }
                    if(data.data[1][i].riskValue==3){
                        item.icon.l=69;
                    }
                    if(data.data[1][i].riskValue==4){
                        item.icon.l=115;
                    }
                    if(data.data[1][i].riskValue==5){
                        item.icon.l=46;
                    }
                    if(data.data[1][i].riskValue==6){
                        item.icon.l=46;
                    }
                    chaoyangMarker.push(item);
                }
                //创建和初始化地图函数：
                $.addMarker(chaoyangMarker);//向地图中添加marker
                $.post($.URL.craneinspectreport.getAreaInfoByUnitAddress,{"name":chaoyangMarker[0].title},pnameCallback,"json");
            }
        }
    }
    /*function showRiskRank(data){
        if(data.code==200){
            $("#rankTitle").html("");
            $("#riskrankContent").html("");
            var rankTitle="<div id='riskttitle'><span class='rtitlerank'>风险排名</span><span class='rtitleItem'>企业</span><span class='rtitleriskItem'>风险值</span></div>";
            $("#rankTitle").append(rankTitle);
            if(data.data[0]==undefined){
                $("#riskrankContent").append("对不起,数据不存在!");
            }else{
                for(i=0;i<data.data.length;i++){
                    var rankContent="<div class='riskcontent'><span class='rrank'>"+(i+1)+"</span><span class='rcontentItem'><span class='unitFont'>"+data.data[i].unitAddress+"</span></span><span class='riskItem'><span class='riskFont'>"+data.data[i].riskValue+"</span></span></div>"
                    $("#riskrankContent").append(rankContent);
                }
            }
        }
    } */
        function showRiskRank(data){
            if(data.code==200){
                $("#rankTitle").html("");
                $("#riskrankContent").html("");
                /*var rankTitle="<div id='riskttitle'><span class='rtitlerank'>风险排名</span><span class='rtitleItem'>企业</span><span class='rtitleriskItem'>风险值</span></div>";
                 $("#rankTitle").append(rankTitle); */
                if(data.data[0]==undefined){
                    $("#riskrankContent").append("对不起,数据不存在!");
                }else{
                    var temp = data.data[0].riskValue;
                    var j=1;
                    var title1 ="<div class='riskcontenthead'><span class='firmtitle'>企业</span><span class='riskvaluetitle'>风险值</span></div>";
                    var rankContent="";
                    var rankContent2="";

                    for(var i=0;i<data.data.length;i++){
                        var divName="div"+i;
                        var content ="<div class='riskcontent'>"+
                            "<span class='unitaddress'>"+data.data[i].unitAddress+"</span>" +
                            "<span class='riskvalue2'>"+data.data[i].riskValue+"</span>" +
                            "</div>";

                        //读第一条数据
                        if(i==0){

                            rankContent="<div class='backcol' id="+divName+">"+"<div  class='riskcontenthead'>" +
                                "<span class='riskranktitle'>风险排名</span>" +
                                "<span class='riskmarktitle'></span>" +
                                "<span class='riskrankvalue'>"+(i+1)+"</span>" +
                                "</div>"+title1+content;

                        }
                        //读下一块数据
                        else if(i>0&&data.data[i].riskValue!=temp){
                            j+=1;
                            temp=data.data[i].riskValue;


                            rankContent="</div>"+"<div class ='backcol' id="+divName+"><div class='riskcontenthead'>" +
                                "<span class='riskranktitle'>风险排名</span>" +
                                "<span class='riskmarktitle'></span>" +
                                "<span class='riskrankvalue'>"+j+"</span>" +
                                "</div>"
                                +title1+content;
                        }
                        // 读本块第二条数据
                        else{

                            rankContent=content;
                        }
                        while(i==data.data.length-1){
                            rankContent="</div>";
                            break;
                        }

                        rankContent2+=rankContent;
                    }
                    $("#riskrankContent").append(rankContent2);
                }
            }
        }
}
});