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
    <script type="text/javascript" src="map/js/initMap.js"></script>
    <script type="text/javascript" src="map/js/addMarker.js"></script>
    <script type="text/javascript" src="map/js/addressLinkAge.js"></script>
    <script type="text/javascript" src="js/rightTab.js"></script>
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
                    <div id="riskrankContent"></div>
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
            <div id="leftTab">
                <span class='searchTabClick' id="searchTab">查询</span>
                <span class='lineTab'>画线</span>
                <span class='lineTab'>画圈</span>
            </div>
            <div id="search" class="searchShow">
                  <span id="titleSearch">
                    <span class="searchItem" id="provinceSearch">
                     <select id="province">
                         <option><%=request.getParameter("province")%></option>
                     </select>
                    </span>
                       <span id="citySearch"><select id="city">
                           <option><%=URLDecoder.decode(request.getParameter("city"), "utf-8")%></option>
                       </select>
                       </span>
                        <span id="areaSearch"><select id="area">
                            <option><%=URLDecoder.decode(request.getParameter("area"),"utf-8")%></option>
                        </select>
                         </span>
                        <span><select id="unit">
                            <option>---请选择单位----</option>
                         </select>
                         </span>
                       <span><select id="more">
                           <option>---更多筛选----</option>
                           <option>目前没有数据</option>
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
    var province='<%=request.getParameter("province")%>';
    var city='<%=URLDecoder.decode(request.getParameter("city"),"utf-8")%>';
    var area='<%=URLDecoder.decode(request.getParameter("area"),"utf-8")%>';
    var lat='<%=request.getParameter("lat")%>';
    var lng='<%=request.getParameter("lng")%>';
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
                    $.post($.URL.craneinspectreport.getCraneInspectReportInfoByAddressAndEquipment,{"address_equipmentvariety":address_equipmentvariety,"itemInfoId":itemInfo},getCraneInspectReportInfoByAddressAndEquipmentCallBack,"json");
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
        $.showCompanyRisk(city,area,12);
        $.addressLinkAge("province","city","area","unit",province);
        $("#searchTab").click(function(){
            if($(this).hasClass("searchTab")){
                $(this).removeClass("searchTab").addClass("searchTabClick");
            }else if($(this).hasClass("searchTabClick")){
                $(this).removeClass("searchTabClick").addClass("searchTab");
            }
            if($("#search").hasClass("searchShow")){
                $("#search").removeClass("searchShow").addClass("searchHide");
            }else if($("#search").hasClass("searchHide")){
                $("#search").removeClass("searchHide").addClass("searchShow");
            }
        });
    });
</script>
</body>
</html>
