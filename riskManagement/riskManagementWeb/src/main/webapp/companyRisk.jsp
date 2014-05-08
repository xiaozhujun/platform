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
    <script type="text/javascript" src="map/js/rightHidePanel.js"></script>
    <%--<script src="search/js/jquery-1.8.2.min.js"></script>--%>
    <script src="search/js/common.js"></script>
    <script src="search/js/autoComplete.js" type="text/javascript"></script>
    <link href="search/css/style.css" rel="stylesheet" type="text/css" />
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
                <span id='riskRank' class="riskRankClick">风险排名</span>
                <span id="riskInfo" class="riskInfo">风险详情</span>
            </div>
                <div id="input_search">
              <%-- <span class="more"><input name="more" type="text" id="more" ltype="text" style="width:280px"/>
                         </span>
                      <span class="inputSearchBtn" id="queryBtn">查询
                      </span>--%>
                  <%--<div id="search_box">
                      <input type="text" id="more" value="Search" class="swap_value" />
                      <input type="image" src="search/btn_search_box.gif" width="27" height="24" id="queryBtn" alt="Search" title="Search" />
                  </div>--%>
                  <div class="search_box">
                      <span class="left l_bg"></span>
                      <span class="right r_bg"></span>
                      <div class="search">
                              <div id="pt1" class="select">
                                  <a id="s0">全站搜索</a>
                                  <div style="display:none;" id="pt2" class="part">
                                      <p>
                                          <a id="s1">单位地址</a>
                                          <a id="s2">使用地点</a>
                                          <a id="s3">管理人员</a>
                                          <a id="s4">设备品种</a>
                                          <a id="s5">制造单位</a>
                                          <a id="s6">全站搜索</a>
                     <%--                 <a id="s7">常用软件</a>
                                          <a id="s8">图标素材</a>
                                          <a id="s9">PNG图标</a>
                                          <a id="s10">GIF图标</a>
                                          <a id="s11">网页模板</a>
                                          <a id="s12">QQ表情</a>--%>
                                      </p>
                                  </div>
                              </div>
                              <input id="catid" name="catid" type="hidden" value="7">
                              <input id="more"  class="enter"  type="text" name="wd" autocomplete="off" onFocus="if(this.value=='请输入关键字…'){this.value='';}else{this.select();}this.style.color='black';"  value="请输入关键字…">
                              <input class="sb" name="Input" type="submit" id="queryBtn"  value="">
                          <div  style="width: 350px">
                              <div id="auto" class="auto" > </div>
                          </div>
                      </div>
                  </div>
                </div>
            <div id="rightRank">
                <div class="rankContent" id="rankContent">
                    <div id="rankTitle"></div>
                    <div id="riskrankContent"></div>
                </div>
            </div>
            <div id="rightshow">
               <%-- <div><div class='righttitle' id="righttitle"></div>
                </div>--%>
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
                    </script>
                </span>
            </div>
            <div id="search" class="searchShow">
                  <span id="titleSearch">
                    <span class="searchItem" id="provinceSearch">
                     <select id="province" onmousedown="this.sindex=this.selectedIndex; this.selectedIndex=-1;" onmousemove="if(this.sindex) this.selectedIndex=this.sindex;" onchange="this.sindex=this.selectedIndex;">
                         <option><%=request.getParameter("province")%></option>
                     </select>
                    </span>
                       <span id="citySearch"><select id="city" onmousedown="this.sindex=this.selectedIndex; this.selectedIndex=-1;" onmousemove="if(this.sindex) this.selectedIndex=this.sindex;" onchange="this.sindex=this.selectedIndex;">
                           <option><%=URLDecoder.decode(request.getParameter("city"), "utf-8")%></option>
                       </select>
                       </span>
                        <span id="areaSearch"><select id="area" onmousedown="this.sindex=this.selectedIndex; this.selectedIndex=-1;" onmousemove="if(this.sindex) this.selectedIndex=this.sindex;" onchange="this.sindex=this.selectedIndex;">
                            <option><%=URLDecoder.decode(request.getParameter("area"),"utf-8")%></option>
                        </select>
                         </span>
                        <%--<span><select id="unit">
                            <option>---请选择单位----</option>
                         </select>
                         </span>--%>
                       <span id="alert"></span>
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
    }
        function showProvinceRankByValueRange(data){
                  if(data.code==200){

                  }
        }
        function showCityRankByValueRange(data){
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
                    var rankTitle="<div id='riskttitle'><span class='rtitlerank'>风险排名</span><span class='rtitleItem'>城市</span><span class='rtitleriskItem'>风险值</span></div>";
                    $("#rankTitle").append(rankTitle);
                    for(var i=0;i<data.data.length;i++){
                        var rankContent;
                        if(i%2==0){
                            rankContent="<div class='riskcontentEven' id='riskcontent"+data.data[i].id+"'>"+"<span class='rrank'>"+(i+1)+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].city+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].riskvalue+"</span></span></div>"
                        }else{
                            rankContent="<div class='riskcontent' id='riskcontent"+data.data[i].id+"'>"+"<span class='rrank'>"+(i+1)+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].city+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].riskvalue+"</span></span></div>"
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
        }
        function showAreaRankByValueRange(data){
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
                    var rankTitle="<div id='riskttitle'><span class='rtitlerank'>风险排名</span><span class='rtitleItem'>城市</span><span class='rtitleriskItem'>风险值</span></div>";
                    $("#rankTitle").append(rankTitle);
                    for(var i=0;i<data.data.length;i++){
                        var rankContent;
                        if(i%2==0){
                            rankContent="<div class='riskcontentEven' id='riskcontent"+data.data[i].id+"'>"+"<span class='rrank'>"+(i+1)+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].area+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].riskvalue+"</span></span></div>"
                        }else{
                            rankContent="<div class='riskcontent' id='riskcontent"+data.data[i].id+"'>"+"<span class='rrank'>"+(i+1)+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].area+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].riskvalue+"</span></span></div>"
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
        }
    $(document).ready(function(){

        $("#layout").ligerLayout({leftWidth:200});
        $("#titleContainer").load("title.html");
        $.switchTab("riskRank","riskInfo","rightRank","rightshow");
        $.rightHidePanel("panelarrow2","panelimg2","rightmain");
        $.showCompanyRisk(city,area,12);
        //省市区联动效果
        $.addressLinkAge("province","city","area","unit",province,city,area);
        //地图左边的tab页
        $.mapLeftTab("searchTab","search","drawCircle","drawLine");
        //地图导航
       // $.dragAbleNavigate(area);
    });
      document.onkeydown=function(e){
          var theEvent=window.event||e;
          var code=theEvent.keyCode||theEvent.which;
          if(code==13){
              $("#queryBtn").click();
              document.getElementById("more").focus();
              document.getElementById("more").blur();
          }
      }
        $("#auto").click(function(){
            $("#queryBtn").click();
        })
        $("#queryBtn").click(function(){
            var data = {};
            data.city = $("#city").val();
            data.area = $("#area").val();
            data.require = '%'+$("#more").val()+'%';
            if($("#more"))
            if($$("catid").value==5){
                $.post($.URL.craneinspectreport.fuzzyQueryByUnitAddress,data,showRiskRankByFuzzyQuery,"json");
            }else if($$("catid").value==8){
                $.post($.URL.craneinspectreport.fuzzyQueryByUserPoint,data,showRiskRankByFuzzyQuery,"json");
            }else if($$("catid").value==9){
                $.post($.URL.craneinspectreport.fuzzyQueryBySafeManager,data,showRiskRankByFuzzyQuery,"json");
            }else if($$("catid").value==10){
                $.post($.URL.craneinspectreport.fuzzyQueryByEquipmentVariety,data,showRiskRankByFuzzyQuery,"json");
            }else if($$("catid").value==12){
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
                    $("#riskrankContent").append("对不起,你现在所在区域为"+$("#area").val());
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
            /*
            右侧鼠标的mouseover以及mouseout事件
             */
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
                                };
                                $.mouseEvent(title,content,point,isOpen,icon,i);
                            }
                        }
                    }
                });
            }
        }
</script>
</body>
</html>
