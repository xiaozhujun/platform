<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-
transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>起重机械风险管理平台-区域风险</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery-1.10.2.js" type="text/javascript"></script>
    <script src="js/jquery.json-2.4.min.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerLayout.js" type="text/javascript"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script>
    <script type="text/javascript" src="map/js/getParam.js"></script>
    <script src="js/config.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/rightTab.js"></script>
    <link rel="stylesheet" href="map/css/showCompany.css"/>
    <link rel="stylesheet" href="map/css/style.css"/>
    <script type="text/javascript" src="map/js/getParam.js"></script>
    <script type="text/javascript" src="map/js/initMap.js"></script>
    <script type="text/javascript" src="map/js/addMarker.js"></script>
    <script type="text/javascript" src="map/js/addressLinkAge.js"></script>
    <script type="text/javascript">
        var province='<%=request.getParameter("province")%>';
        var city='<%=URLDecoder.decode(request.getParameter("city"),"utf-8")%>';
        var lat='<%=request.getParameter("lat")%>';
        var lng='<%=request.getParameter("lng")%>';

        $(document).ready(function(){
            $("#layout").ligerLayout({leftWidth:200});
            $("#titleContainer").load("title.html");
            $.switchTab("riskRank","riskInfo","rightRank","rightshow");
            $("#panelarrow2").click(function(){
                if($("#rightmain").css("display")!='none'){
                    $("#panelimg2").css("background","url('map/images/sprites.png') no-repeat scroll -8px -20px rgba(0, 0, 0, 0)").parent().css("right","0");
                    $("#rightmain").css("display","none");
                }else{
                    $("#panelimg2").css("background","url('map/images/sprites.png') no-repeat scroll -2px -20px  rgba(0, 0, 0, 0)").parent().css("right","384px");
                    $("#rightmain").css("display","block");
                }
            });
            $.showAreaRisk(province,city);
        });
    </script>


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
            <div id="rightmain">
                <div id="tab">
                    <span id='riskRank'>风险排名</span>
                </div>
                <div id="rightRank">
                    <div class="rankContent" id="rankContent">
                        <div id="rankTitle"></div>
                        <div id="riskrankContent"></div>
                    </div>
                </div>
            </div>
            <div id="leftcontainer">
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
    $(function ()
    {
        $("#layout").ligerLayout({leftWidth:200});
        $("#titleContainer").load("title.html");
    });
</script>

<%--<script type="text/javascript">

    </script>

<script type="text/javascript" src="map/js/initMap.js"></script>
<script type="text/javascript">
    $.initArea(province,city,10,0)
</script>--%>
</body>
</html>
