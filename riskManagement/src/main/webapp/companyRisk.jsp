<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-
transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>起重机械风险管理平台-企业风险</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery-1.10.2.js" type="text/javascript"></script>
    <script src="js/jquery.json-2.4.min.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerLayout.js" type="text/javascript"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script>
    <script type="text/javascript" src="map/js/getParam.js"></script>
    <script src="js/config.js" type="text/javascript"></script>
    <link rel="stylesheet" href="map/css/showCompany.css"/>
    <link rel="stylesheet" href="map/css/style.css"/>
    <script type="text/javascript" src="map/js/getParam.js"></script>
    <style type="text/css">
        body{ padding:10px; margin:0;font-family: 'Hiragino Sans GB','Microsoft YaHei',sans-serif;}
        #layout{  width:100%; margin:40px;  height:400px;
            margin:0; padding:0;}
        h4{ margin:20px;}
    </style>
</head>
<body style="padding:10px">
<div id="layout">
    <div id="mainContainer" position="center" title="">
        <div id="main">
            <div class="relative"></div>
            <div id="panelarrow2" title="隐藏列表" style="" class="">
                <div id="panelimg2" class="arrow_bg_img"></div>
                <!--<img class="collapse-left3" src="img/panelarrow2.png" id="panelimg2">-->
            </div>
            <div id="rightmain">
            <div id="tab">
                <span id='riskRank'>风险排名</span>
                <span id="riskInfo">风险详情</span>
            </div>
            <div id="rightRank">
                <div class="rankContent" id="rankContent">
                    <div id="rankTitle"></div>
                    <div id="rankContent"></div>
                </div>
            </div>
            <div id="rightshow">
                <div><div class='righttitle' id="righttitle"></div>
                </div>
                <div id="rightcontent" class="rightcontent">
                </div>
            </div>
            </div>
            <div id="leftcontainer">
            <div id="search">
                  <span id="titleSearch">
                    <span class="searchItem">
                        <select id="pro">
                            <option>北京</option>
                            <option>上海</option>
                            <option>湖北</option>
                        </select>
                    </span>
                       <span><select id="city">
                           <option>北京市</option>
                           <option>上海市</option>
                           <option>武汉市</option>
                       </select>
                       </span>
                        <span><select id="area">
                            <option>朝阳区</option>
                            <option>浦东区</option>
                            <option>新洲区</option>
                        </select>
                         </span>
                        <span><select id="unit">
                          <option>中国特种设备检测研究院</option>
                          <option>上海特检所</option>
                          <option>湖北省武汉市新洲区阳逻双柳武船</option>
                         </select>
                         </span>
                    </span>
            </div>
            <div id="container"></div>
            </div>
        </div>
    </div>
    <!--<div position="right"></div>-->
    <div id="titleContainer" position="top"></div>
    <!--<div position="bottom"></div>-->
</div>
<div style="display:none;">
</div>
<script type="text/javascript">
    var city='<%=URLDecoder.decode(request.getParameter("city"),"utf-8")%>';
    var pname='<%=URLDecoder.decode(request.getParameter("pname"),"utf-8")%>';
    var lat='<%=request.getParameter("lat")%>';
    var lng='<%=request.getParameter("lng")%>';
    function initMap(lng,lat){
        createMap(lng,lat);//创建地图
        setMapEvent();//设置地图事件
        addMapControl();//向地图添加控件
    }
    //创建地图函数：
    function createMap(lng,lat){
        var map = new BMap.Map("container");//在百度地图容器中创建一个地图
        map.centerAndZoom(pname,12);//设定地图的中心点和坐标并将地图显示在地图容器中
        window.map = map;//将map变量存储在全局
    }
    //地图事件设置函数：
    function setMapEvent(){
        map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
        map.enableScrollWheelZoom();//启用地图滚轮放大缩小
        map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
        map.enableKeyboard();//启用键盘上下左右键移动地图
    }
    //地图控件添加函数：
    function addMapControl(){
        //向地图中添加缩放控件
        var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
        map.addControl(ctrl_nav);
        //向地图中添加缩略图控件
        var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:1});
        map.addControl(ctrl_ove);
        //向地图中添加比例尺控件
        var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
        map.addControl(ctrl_sca);
    }
    //创建marker
    function addMarker(markerArr){
        for(var i=0;i<markerArr.length;i++){
            var json = markerArr[i];
            var p0 = json.point.split("|")[0];
            var p1 = json.point.split("|")[1];
            var point = new BMap.Point(p0,p1);
            var iconImg = createIcon(json.icon);
            var marker = new BMap.Marker(point,{icon:iconImg});
            var iw = createInfoWindow(i,markerArr);
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
                var _iw = createInfoWindow(i,markerArr);
                var _marker = marker;
                if(index==0){
                    _marker.openInfoWindow(_iw);
                }
                _marker.addEventListener("click",function(){
                    this.openInfoWindow(_iw);
                    var name=markerArr[index];
                    $.post("rs/craneinspectreport/getAreaInfoByUnitAddress",{"name":name.title},mapCallback,"json");
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
                                $.post("rs/craneinspectreport/getCraneInspectReportInfoByAddressAndEquipment",{"address_equipmentvariety":address_equipmentvariety,"itemInfoId":itemInfo},getCraneInspectReportInfoByAddressAndEquipmentCallBack,"json");
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
    }
    //创建InfoWindow
    function createInfoWindow(i,markerArr){
        var json = markerArr[i];
        var iw = new BMap.InfoWindow("<b class='iw_poi_title' title='" + json.title + "'>" + json.title + "</b><div class='iw_poi_content'>"+json.content+"</div>");
        return iw;
    }
    //创建一个Icon
    function createIcon(json){
        var icon = new BMap.Icon("http://app.baidu.com/map/images/us_mk_icon.png", new BMap.Size(json.w,json.h),{imageOffset: new BMap.Size(-json.l,-json.t),infoWindowOffset:new BMap.Size(json.lb+5,1),offset:new BMap.Size(json.x,json.h)})
        return icon;
    }
    function searchByStationName(address) {
        //map.clearOverlays();//清空原来的标注
        var keyword = address;
        localSearch.setSearchCompleteCallback(function (searchResult) {
            var poi = searchResult.getPoi(0);
            //map.centerAndZoom(poi.point, 13);
            var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地方对应的经纬度
            map.addOverlay(marker);
            var content = address+ "<br/><br/>经度：" + poi.point.lng + "<br/>纬度：" + poi.point.lat;
            var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
            marker.addEventListener("click", function () { this.openInfoWindow(infoWindow); });
            // marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
        });
        localSearch.search(keyword);
    }
    function pnameCallback(data){
        if(data.code==200){
            //$("#rightshow").css("display","block");
            $("#righttitle").html("");
            $("#rightcontent").html("");
            $("#righttitle").append(data.str);
            for(var i=0;i<data.data.length;i++){
                var dataString="<div class='rightitem'><div class='righttop'><span class='pic'><img src='image/qizhongji.jpg'></span><span class='info'><span class='itemfont'>"+data.data[i].equipmentVariety+"</span><span class='itemfont'>风险值:"+data.data[i].riskValue+"</span><span class='hideField' id='hideField"+data.data[i].id+"'>"+data.data[i].unitAddress+","+data.data[i].equipmentVariety+"</span><span class='infofont' id='infofont"+data.data[i].id+"'>详情</span></div><div class='itemInfo' id='itemInfo"+data.data[i].id+"'></div><div class='cl'></div></div>"
                $("#rightcontent").append(dataString);
                var infoFontNum="infofont"+data.data[i].id;
                $("#"+infoFontNum).click(function(){
                    var hideField="#hideField"+(this.id).substring(8,(this.id).length);
                    var address_equipmentvariety=$(hideField).text();
                    var itemInfo="#itemInfo"+(this.id).substring(8,(this.id).length);
                    $(itemInfo).html("");
                    $.post("rs/craneinspectreport/getCraneInspectReportInfoByAddressAndEquipment",{"address_equipmentvariety":address_equipmentvariety,"itemInfoId":itemInfo},getCraneInspectReportInfoByAddressAndEquipmentCallBack,"json");
                    $(itemInfo).toggle();
                });
            }
        }
    }
    function getCraneInspectReportInfoByAddressAndEquipmentCallBack(data){
            if(data.code==200){
                var itemInfoId=data.str;
                for(i=0;i<data.data.length;i++){
                    var itemInfo="<div class='_info'><span><span class='_infoTitle'><span class='_titleFont'>起重机风险管理平台</span></span><span class='infoImg'><img src='image/qizhongji.jpg'></span><span class='infoMsg'><span class='msgItem'><span class='msgItemFont'>"+data.data[i].equipmentVariety+"</span></span><span class='msgItem'><span class='msgItemFont'>制造许可编号:"+data.data[i].manufactureLicenseNumber+"</span></span><span class='msgItem'><span class='msgItemFont'>组织机构代码:"+data.data[i].unitNumber+"</span></span><span class='msgItem'><span class='msgItemFont'>使用地点:"+data.data[i].userPoint+"</span></span><span class='msgItem'><span class='msgItemFont'>安全管理人员:"+data.data[i].safeManager+"</span></span><span class='msgItem'><span class='msgItemFont'>联系电话:"+data.data[i].contactPhone+"</span></span>" +
                            "<span class='msgItem'><span class='msgItemFont'>制造单位:"+data.data[i].manufactureUnit+"</span></span><span class='msgItem'><span class='msgItemFont'>使用单位地址:"+data.data[i].unitAddress+"</span></span></span><span class='detailMsg'>查看完整内容</span></div>";
                         $(itemInfoId).append(itemInfo);
                }
            }
    }
    $(document).ready(function(){

        $("#layout").ligerLayout({leftWidth:200});
        $("#titleContainer").load("title.html");
        $("#riskInfo").click(function(){
            $("#riskRank").css("background-color","#F7F7F7");
            $("#riskInfo").css("background-color","#999999");
            $("#riskInfo").css("color","#ffffff");
            $("#riskRank").css("color","#999999");
           $("#rightRank").css("display","none");
           $("#rightshow").css("display","block");
        });
        $("#riskRank").click(function(){
            $("#riskRank").css("background-color","#999999");
            $("#riskRank").css("color","#ffffff");
            $("#riskInfo").css("color","#999999");
            $("#riskInfo").css("background-color","#F7F7F7");
            $("#rightRank").css("display","block");
            $("#rightshow").css("display","none");
        });
        $("#panelarrow2").click(function(){
            if($("#rightmain").css("display")!='none'){
                $("#panelimg2").css("background","url('map/images/sprites.png') no-repeat scroll -8px -20px rgba(0, 0, 0, 0)").parent().css("right","0");
                $("#rightmain").css("display","none");
            }else{
                $("#panelimg2").css("background","url('map/images/sprites.png') no-repeat scroll -2px -20px  rgba(0, 0, 0, 0)").parent().css("right","384px");
                $("#rightmain").css("display","block");
            }
        });
       $.post("rs/craneinspectreport/getAreaInfo",{"city":city,"pname":pname},areaInfoCallback,"json");
       /*$("#rankContent").load("rs/report/showRiskRank/",{"city":city,"pname":pname},showRiskRank,"json");*/
        $.post("rs/craneinspectreport/showRiskRank",{"city":city,"pname":pname},showRiskRank,"json");
       var chaoyangMarker=new Array();
        function areaInfoCallback(data){
             if(data.code==200){
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
                 addMarker(chaoyangMarker);//向地图中添加marker
                 $.post("rs/craneinspectreport/getAreaInfoByUnitAddress",{"name":chaoyangMarker[0].title},pnameCallback,"json");

                 }
             }
        }
       function showRiskRank(data1){
            if(data1.code==200){
                $("#rankTitle").html("");
                $("rankContent").html("");
                var rankTitle="<table id='risktable'><tr><th>企业</th><th>风险值</th></tr>"
                $("#rankTitle").append(rankTitle);
                for(i=0;i<data1.data.length;i++){
                    var rankContent="<tr><td><span class='unitFont'>"+data1.data[i].unitAddress+"</span></td><td><span class='riskFont'>"+data1.data[i].riskValue+"</span></td></tr>"
                    $("#rankTitle").append(rankContent);
                }
            }
        }
            initMap(lng,lat);
    });
</script>
</body>
</html>
