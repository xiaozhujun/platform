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
        body{ padding:10px; margin:0;}
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

            <div id="rightshow">
                <div id='righttitle'>
                </div>
                <div id="rightcontent">
                </div>
            </div>
            <div id="container"></div>

        </div>
    </div>
    <!--<div position="right"></div>-->
    <div id="titleContainer" position="top"></div>
    <!--<div position="bottom"></div>-->
</div>

<div style="display:none;">

</div>

<script type="text/javascript">
    var pname='<%=URLDecoder.decode(request.getParameter("pname"),"utf-8")%>';
    var lat='<%=request.getParameter("lat")%>';
    var lng='<%=request.getParameter("lng")%>';

    function initMap(lng,lat,Marker){
        createMap(lng,lat);//创建地图
        setMapEvent();//设置地图事件
        addMapControl();//向地图添加控件
        addMarker(Marker);//向地图中添加marker
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
                    $.post("rs/device/showInfo",{"name":name.title},mapCallback,"json");

                });
                function  mapCallback(data){
                    if(data.code==200){
                        $("#rightshow").css("display","block");
                        $("#panelimg2").css("background","url('map/images/sprites.png') no-repeat scroll -2px -20px  rgba(0, 0, 0, 0)").parent().css("right","384px");
                        $("#righttitle").html("");
                        $("#rightcontent").html("");
                        $("#righttitle").append(data.str);
                        for(var i=0;i<data.data.length;i++){
                            var dataString="<div class='rightitem'><span class='pic'><img src='image/qizhongji.jpg'></span><span class='info'><span class='itemfont'>"+data.data[i].name+"</span><span class='itemfont'>"+data.data[i].description+"</span></span><span class='riskvalue'>风险值:"+data.data[i].riskvalue+"</span> ";
                            $("#rightcontent").append(dataString);
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
    //标注点数组
    var chaoyangMarker = [{title:"北京市特种设备检测中心",content:"电梯2台,起重机1台,风险值:1",point:"116.422|40.002",isOpen:0,icon:{w:23,h:25,l:115,t:21,x:9,lb:12}}
        ,{title:"中国特种设备检测研究院",content:"电梯1台,起重机1台,风险值:2",point:"116.412|39.983",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
        ,{title:"北京百昌特种设备贸易有限公司",content:"电梯1台,起重机3台,风险值:3",point:"116.457|39.918",isOpen:0,icon:{w:23,h:25,l:92,t:21,x:9,lb:12}}
        ,{title:"燃气集团有限责任公司特种设备检验所",content:"起重机2台,风险值:5",point:"116.404|39.9716",isOpen:0,icon:{w:23,h:25,l:0,t:21,x:9,lb:12}}
    ];


    function pnameCallback(data){
        if(data.code==200){
            $("#rightshow").css("display","block");
            $("#righttitle").html("");
            $("#rightcontent").html("");
            $("#righttitle").append(data.str);
            for(var i=0;i<data.data.length;i++){
                var dataString="<div class='rightitem'><span class='pic'><img src='image/qizhongji.jpg'></span><span class='info'><span class='itemfont'>"+data.data[i].name+"</span><span class='itemfont'>"+data.data[i].description+"</span></span><span class='riskvalue'>风险值:"+data.data[i].riskvalue+"</span> ";
                $("#rightcontent").append(dataString);
            }
        }
    }

    $(document).ready(function(){

        $("#layout").ligerLayout({leftWidth:200});
        $("#titleContainer").load("title.html");

        $("#panelarrow2").click(function(){
            if($("#rightshow").css("display")!='none'){
                $("#panelimg2").css("background","url('map/images/sprites.png') no-repeat scroll -8px -20px rgba(0, 0, 0, 0)").parent().css("right","0");
                $("#rightshow").css("display","none");
            }else{
                $("#panelimg2").css("background","url('map/images/sprites.png') no-repeat scroll -2px -20px  rgba(0, 0, 0, 0)").parent().css("right","384px");
                $("#rightshow").css("display","block");
            }
        });

        //创建和初始化地图函数：
        initMap(lng,lat,chaoyangMarker);//创建和初始化地图
        $.post("rs/device/showInfo",{"name":chaoyangMarker[0].title},pnameCallback,"json");
    });
</script>
</body>
</html>
