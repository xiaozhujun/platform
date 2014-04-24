<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-
transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>起重机械风险管理平台-企业风险</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery-1.10.2.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="js/jquery.json-2.4.min.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerLayout.js" type="text/javascript"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
    <script type="text/javascript" src="map/js/getParam.js"></script>
    <script src="js/config.js" type="text/javascript"></script>
    <link rel="stylesheet" href="map/css/showCompany.css"/>
    <link rel="stylesheet" href="map/css/style.css"/>
    <script type="text/javascript" src="map/js/getParam.js"></script>
    <script type="text/javascript" src="map/js/initMap.js"></script>
    <script type="text/javascript" src="map/js/addMarker.js"></script>
    <script type="text/javascript" src="map/js/addressLinkAge.js"></script>
    <script type="text/javascript" src="js/rightTab.js"></script>
    <%--引入jslider--%>
    <link rel="stylesheet" href="jslider/css/jslider.css" type="text/css">
    <link rel="stylesheet" href="jslider/css/jslider.plastic.css" type="text/css">
    <script type="text/javascript" src="jslider/js/jshashtable-2.1_src.js"></script>
    <script type="text/javascript" src="jslider/js/jquery.numberformatter-1.2.3.js"></script>
    <script type="text/javascript" src="jslider/js/tmpl.js"></script>
    <script type="text/javascript" src="jslider/js/jquery.dependClass-0.1.js"></script>
    <script type="text/javascript" src="jslider/js/draggable-0.1.js"></script>
    <script type="text/javascript" src="jslider/js/jquery.slider.js"></script>
    <script type="text/javascript" src="map/js/mapLeftTab.js"></script>
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
                <span class='lineTab' id="drawLine">画线</span>
                <span class='lineTab' id="drawCircle">画圈</span>
                <span class="slider">
                    <div class="layout-slider">
                        <input id="Slider4" type="slider" name="area" value="2;10" />
                    </div>
                    <script type="text/javascript" charset="utf-8">
                        jQuery("#Slider4").slider({ from: 1, to: 9, scale: [1, '|', 3, '|', '5', '|', 7, '|', 9], limits: false, step: 1, dimension: '', skin: "plastic",
                                                       callback: function( value ){
                                                           console.dir( this );
                                                           $.post($.URL.craneinspectreport.showRiskRankByValueRange, {"value":value,"city":city,"area":area},showRiskRankByValueRange,"json");
                                                       }
                                                     });
                    </script>
                </span>
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
                       <span><input name="more" type="text" id="more" ltype="text" style="width:300px"/>
                         </span>
                      <span><input type="button" value="查询" id="queryBtn" style="width:80px" class="l-button"/>
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
        function showRiskRankByValueRange(data){
        if(data.code==200){
            $("#rankTitle").html("");
            $("#riskrankContent").html("");
            var rankTitle="<div id='riskttitle'><span class='rtitlerank'>风险排名</span><span class='rtitleItem'>企业</span><span class='rtitleriskItem'>风险值</span></div>";
             $("#rankTitle").append(rankTitle);
            if(data.data[0]==undefined){
                $("#riskrankContent").append("对不起,数据不存在!");
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
                    var rankContent="<div class='riskcontent' id='riskcontent"+data.data[i].id+"'>" +"<span class='rrank'>"+j+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].unitAddress+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].riskValue+"</span></span></div>"
                    $("#riskrankContent").append(rankContent);
                }
                for(i=0;i<data.data.length;i++){
                    $.rightTabMouseEvent("riskcontent"+data.data[i].id);
                }
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
        $.mapLeftTab("searchTab","search","drawCircle","drawLine");
        $.dragAbleNavigate(area);
    });

        $("#queryBtn").click(function(){
            var data = {};
            data.city = city;
            data.area = area;
            data.require = '%'+$("#more").val()+'%';
            $.post($.URL.craneinspectreport.fuzzyQuery,data,showRiskRankByFuzzyQuery,"json");
        });

        function showRiskRankByFuzzyQuery(data){
            if(data.code==200){
                $("#rankTitle").html("");
                $("#riskrankContent").html("");
                var rankTitle="<div id='riskttitle'><span class='rtitlerank'>风险排名</span><span class='rtitleItem'>企业</span><span class='rtitleriskItem'>风险值</span></div>";
                $("#rankTitle").append(rankTitle);
                if(data.data[0]==undefined){
                    $("#riskrankContent").append("对不起,数据不存在!");
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
                        var rankContent="<div class='riskcontent' id='riskcontent"+data.data[i].id+"'>" +"<span class='rrank'>"+j+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].unitAddress+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].riskValue+"</span></span></div>"
                        $("#riskrankContent").append(rankContent);
                    }
                    var MarkerArray=new Array();
                    for(i=0;i<data.data.length;i++){
                        var item={};
                        item.title=data.data[i].unitAddress;
                        item.content=data.data[i].equipmentVariety+",风险值:"+data.data[i].riskValue;
                        item.point=data.data[i].lng+"|"+data.data[i].lat;
                        item.isOpen=0;
                        item.icon={};
                        item.icon.w=23;
                        item.icon.h=25;
                        item.icon.t=21;
                        item.icon.x=9;
                        item.icon.lb=12;
                        if(data.data[i].riskValue==1){
                            item.icon.l=23;
                        }
                        if(data.data[i].riskValue==2){
                            item.icon.l=0;
                        }
                        if(data.data[i].riskValue==3){
                            item.icon.l=69;
                        }
                        if(data.data[i].riskValue==4){
                            item.icon.l=115;
                        }
                        if(data.data[i].riskValue==5){
                            item.icon.l=46;
                        }
                        if(data.data[i].riskValue==6){
                            item.icon.l=46;
                        };
                        MarkerArray.push(item);
                    }
                    $.clearAllMarker();
                    $.addMarker(MarkerArray);
                }
            }
            rightTabMouseEvent:function rightTabMouseEvent(id){
                var _id="#"+id;
                $(_id).mouseover(function(){
                    var cid=this.id.substring(11,this.id.length);
                    $.post($.URL.craneinspectreport.getCraneInspectReportInfoById,{"id":cid},getCraneInspectReportInfoByIdCallback,"json");
                    var dataArray=new Array();
                    function getCraneInspectReportInfoByIdCallback(data){
                        if(data.code==200){
                            for(i=0;i<1;i++){
                                var title=data.data[0].unitAddress;
                                var content=data.data[0].equipmentVariety+",风险值:"+data.data[0].riskValue;
                                var point=data.data[0].lng+"|"+data.data[0].lat;
                                isOpen=0;
                                var icon={};
                                icon.w=23;
                                icon.h=25;
                                icon.t=21;
                                icon.x=9;
                                icon.lb=12;
                                if(data.data[0].riskValue==1){
                                    icon.l=23;
                                }
                                if(data.data[0].riskValue==2){
                                    icon.l=0;
                                }
                                if(data.data[0].riskValue==3){
                                    icon.l=69;
                                }
                                if(data.data[0].riskValue==4){
                                    icon.l=115;
                                }
                                if(data.data[0].riskValue==5){
                                    icon.l=46;
                                }
                                if(data.data[0].riskValue==6){
                                    icon.l=46;
                                }
                                ;     $.mouseEvent(title,content,point,isOpen,icon,i);
                            }
                        }
                    }
                });
            }
        }
</script>
</body>
</html>
