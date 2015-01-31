/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-4-12
 * Time: 下午8:30
 * To change this template use File | Settings | File Templates.
 */
$.extend({
    //创建地图函数：
    createMap:function createMap(address,size){
        var map = new BMap.Map("container");//在百度地图容器中创建一个地图
        map.centerAndZoom(address,size);//设定地图的中心点和坐标并将地图显示在地图容器中
        window.map = map;//将map变量存储在全局
    },
    clearAllMarker:function clearAllMarker(){
        window.map.clearOverlays();
    },
//地图事件设置函数：
    setMapEvent:function setMapEvent(){
        map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
        map.enableScrollWheelZoom();//启用地图滚轮放大缩小
        map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
        map.enableKeyboard();//启用键盘上下左右键移动地图
    },
//地图控件添加函数：
    addMapControl:function addMapControl(){
        //向地图中添加缩放控件
        var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
        map.addControl(ctrl_nav);
        //向地图中添加缩略图控件
        var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:1});
        map.addControl(ctrl_ove);
        //向地图中添加比例尺控件
        var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
        map.addControl(ctrl_sca);
    },
    initMap:function initMap(address,size){
        $.createMap(address,size);//创建地图
        $.setMapEvent();//设置地图事件
        $.addMapControl();//向地图添加控件
    },
    initProvinceWithDataRule:function initProvinceWithDataRule(country,size,flag){
        $.initMap(country,size);
        $.clearAllMarker;
        /*var provinceArray=$.getProvinceData();
        $.drawProvinceBoundaryWithDataRule(province,provinceArray,flag);*/
        $.post($.URL.dataRuleAddress.getProvinceAndColorWithDataRole,null,getProvinceCallBack,"json");
        function getProvinceCallBack(data){
            $.getProvinceWithRule(data,flag);
            if(flag==0){
            }else if(flag==1){
            $.showProvinceRank(data);
            }
        }
},
    initCityWithDataRule:function initCityWithDataRule(province,size,flag){
        $.initMap(province,size);
        $.clearAllMarker;
        /*根据省以及session查出这个人的city*/
        //var jsonString={};
        $.conditionSearch("#province","#city","#area","#equipVariety","#useTime","#Slider4");
       /* $.post($.URL.dataRuleAddress.getCityAndColorWithDataRole,{"province":province},getCityCallBack,"json");
        function getCityCallBack(data){
            $.getCityWithRule(province,data,flag);
            if(flag==0){
            $.showCityRankShow(data);
            }else if(flag==1){
            $.showCityRank(data);
            }
        }*/
    },
    initAreaWithDataRule:function initAreaWithDataRule(province,city,size,flag){
        $.initMap(city,size);
        $.clearAllMarker;
        /*根据省和市以及session查出这个人的area*/
        $.conditionSearch("#province","#city","#area","#equipVariety","#useTime","#Slider4");
       /* $.post($.URL.dataRuleAddress.getAreaAndColorWithDataRole,{"province":province ,"city":city},getAreaCallBack,"json");
        function getAreaCallBack(data){
            $.getAreaWithRule(province,city,data,flag);
            if(flag==0){
                $.showAreaRankShow(data);
            }else if(flag==1){
                $.showAreaRank(data);
            }
        }*/
    },
    showProvinceRisk:function showProvinceRisk(flag)
    {
        $.initProvinceWithDataRule("中国",5,flag);
    },
    /*
     显示城市风险
     @Param province 省
     @Param flag标记 用来控制地图上画出的颜色区域的是否可点击
     */
    showCityRisk:function showCityRisk(province,flag)
    {
        $.initCityWithDataRule(province,8,flag);
    },
    /*
     显示地区风险
     @Param province 省
     @Param city 城市
     @Param flag标记 用来控制地图上画出的颜色区域的是否可点击
     */
    showAreaRisk:function showAreaRisk(province,city,flag)
    {
        $.initAreaWithDataRule(province,city,10,flag);
    },
    showCompanyRisk:function showCompanyRisk(city,area,size){
    $.initAndAddMarker(city,area);
    $.initMap(area,size);

},
    /*
    画圆功能
    利用DrawingManager类来实现
     */
    drawCircle:function drawCircle(flag){
    var myDrawingManagerObject = new BMapLib.DrawingManager(map, {isOpen: flag,
        drawingType: BMAP_DRAWING_CIRCLE, enableDrawingTool: true,
        enableCalculate: false});
    myDrawingManagerObject.setDrawingMode(BMAP_DRAWING_CIRCLE);
    myDrawingManagerObject.addEventListener("overlaycomplete", function(e) {
        var radius=e.overlay.getRadius();
        var centerlng= e.overlay.getCenter().lng;
        var centerlat= e.overlay.getCenter().lat;
        var circledata={};
        circledata.radius=radius;
        circledata.centerlng=centerlng;
        circledata.centerlat=centerlat;
        $.post($.URL.craneinspectreport.getCraneInspectReportInfoFromCircle,circledata,getCraneInspectReportInfoFromCircleCallback,"json");
        function getCraneInspectReportInfoFromCircleCallback(data){
            if(data.code=200){
                $("#rankTitle").html("");
                $("#riskrankContent").html("");
                if(data.data[0]==undefined){
                    $("#riskrankContent").append("对不起,数据不存在!");
                }else{
                    $("#rankTitle").html("");
                    $("#riskrankContent").html("");
                    var rankTitle="<div id='riskttitle'><span class='rtitlerank'>风险排名</span><span class='rtitleItem'>企业</span><span class='rtitleriskItem'>风险值</span></div>";
                    $("#rankTitle").append(rankTitle);
                    for(i=0;i<data.data.length;i++){
                    /*var j=1;
                    for(var i=0;i<data.data.length;i++){
                        if(i>0){
                            preValue=data.data[i-1].riskValue;
                            if(data.data[i].riskValue==preValue)
                                j=j;
                            else
                            {
                                j++;
                            }
                        }*/
                        var rankContent;
                        if(i%2==0){
                            rankContent="<div class='riskcontentEven' id='riskcontent"+data.data[i].id+"'>" +"<span class='rrank'>"+(i+1)+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].unitAddress+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].riskValue+"</span></span></div>"
                        }else{
                            rankContent="<div class='riskcontent' id='riskcontent"+data.data[i].id+"'>" +"<span class='rrank'>"+(i+1)+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].unitAddress+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].riskValue+"</span></span></div>"
                        }
                       rankContent="<div class='riskcontent' id='riskcontent"+data.data[i].id+"'>" +"<span class='rrank'>"+(i+1)+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].unitAddress+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].riskValue+"</span></span></div>"
                        $("#riskrankContent").append(rankContent);
                        $.rightTabMouseEvent("riskcontent"+data.data[i].id);
                        $.rightTabMouseClickEvent("riskcontent"+data.data[i].id);
                        $.riskContentClick("riskcontent"+data.data[i].id);
                    }
                }
            }
        }

    });
},
    drawLine:function drawLine(flag){
        var myDrawingManagerObject = new BMapLib.DrawingManager(map,{isOpen: flag,
            drawingType: BMAP_DRAWING_POLYLINE, enableDrawingTool: true,
            enableCalculate: false});
        myDrawingManagerObject.setDrawingMode(BMAP_DRAWING_POLYLINE);
        myDrawingManagerObject.addEventListener("overlaycomplete",function(e){

        });
    },
    /*
    地图导航功能
     */
    dragAbleNavigate:function dragAbleNavigate(address){
        var transit = new BMap.DrivingRoute(map, {
            renderOptions: {
                map: map,
                panel: "rank",
                enableDragging : true //起终点可进行拖拽
            }
        });
        transit.search("汉口站",address);
    },
    /*
    根据数据权限给省画出轮廓
     */
    drawProvinceBoundaryWithRule:function drawProvinceBoundaryWithRule(data,flag){
       if(data!=null){
           var bdary=new BMap.Boundary();
           bdary.get(data.province,function(rs){
               console.log(rs);
               var maxNum=-1,maxPly;
               var color=data.color;
               var count=rs.boundaries.length;
               for(var i=0;i<count;i++){
                   var ply=new BMap.Polygon(rs.boundaries[i],{strokeWeight:1,strokeOpacity:0.5,fillColor:color,fillOpacity:0.3,strokeColor:"#000000"});
                   map.addOverlay(ply);
                   if(flag==0){       //flag为0时有点击事件，flag为1时没有点击事件
                   }else if(flag==1){
                       ply.addEventListener("click",function(e){
                           name=data.province;
                           var latlng=e.point;
                           var info=new BMap.InfoWindow(name+" "+latlng.lat+","+latlng.lng,{width:220});
                           map.openInfoWindow(info,latlng);
                           //高亮闪烁显示鼠标点击的省
                           delay=0;
                           for (flashTimes=0;flashTimes<3;flashTimes++){
                               delay+=400;
                               setTimeout(function(){
                                   ply.setFillColor("#FFFF00");
                               },delay);

                               delay+=400;
                               setTimeout(function(){
                                   ply.setFillColor(color);
                               },delay);
                           }
                           $("#province option[value='"+data.province+"']").attr("selected",true);
                           $.post($.URL.dataRuleAddress.getCityAndColorWithDataRole,{"province":data.province}, $.getCityByProvinceCallback,"json");
                           $.showCityRisk(data.province,1);                    });
                   }
               }
               if(maxPly){
                   map.setViewport(maxPly.getPoints());
               }
           });
       }
    },
    /*
     根据数据权限给城市画出轮廓
     */
    drawCityBoundaryWithRule:function drawCityBoundaryWithRule(province,data,flag){
        if(data!=null){
        var bdary=new BMap.Boundary();
        bdary.get(data.city,function(rs){
            console.log(rs);
            var maxNum=-1,maxPly;
            var color=data.color;
            var count=rs.boundaries.length;
            for(var i=0;i<count;i++){
                var ply=new BMap.Polygon(rs.boundaries[i],{strokeWeight:1,strokeOpacity:0.5,fillColor:color,fillOpacity:0.3,strokeColor:"#000000"});
                map.addOverlay(ply);
                if(flag==0){       //flag为0时有点击事件，flag为1时没有点击事件
                }else if(flag==1){
                    ply.addEventListener("click",function(e){
                        name=data.city;
                        var latlng=e.point;
                        var info=new BMap.InfoWindow(name+" "+latlng.lat+","+latlng.lng,{width:220});
                        map.openInfoWindow(info,latlng);
                        //高亮闪烁显示鼠标点击的省
                        delay=0;
                        for (flashTimes=0;flashTimes<3;flashTimes++){
                            delay+=400;
                            setTimeout(function(){
                                ply.setFillColor("#FFFF00");
                            },delay);

                            delay+=400;
                            setTimeout(function(){
                                ply.setFillColor(color);
                            },delay);
                        }
                        $("#city option[value='"+data.city+"']").attr("selected",true);
                        $.post($.URL.dataRuleAddress.getAreaAndColorWithDataRole,{"province":province,"city":data.city}, $.getAreaByProvinceAndCityCallback,"json");
                        $.showAreaRisk(province,data.city,1);                    });
                }
            }
            if(maxPly){
                map.setViewport(maxPly.getPoints());
            }
        });
        }
    },
    /*
     根据数据权限给地区画出轮廓
     */
    drawAreaBoundaryWithRule:function drawAreaBoundaryWithRule(province,city,data,flag){
        if(data!=null){
        var bdary=new BMap.Boundary();
        bdary.get(data.area,function(rs){
            console.log(rs);
            var maxNum=-1,maxPly;
            var color=data.color;
            var count=rs.boundaries.length;
            for(var i=0;i<count;i++){
                var ply=new BMap.Polygon(rs.boundaries[i],{strokeWeight:1,strokeOpacity:0.5,fillColor:color,fillOpacity:0.3,strokeColor:"#000000"});
                map.addOverlay(ply);
                if(flag==0){       //flag为0时有点击事件，flag为1时没有点击事件
                }else if(flag==1){
                    ply.addEventListener("click",function(e){
                        name=data.area;
                        var latlng=e.point;
                        var info=new BMap.InfoWindow(name+" "+latlng.lat+","+latlng.lng,{width:220});
                        map.openInfoWindow(info,latlng);
                        //高亮闪烁显示鼠标点击的省
                        delay=0;
                        for (flashTimes=0;flashTimes<3;flashTimes++){
                            delay+=400;
                            setTimeout(function(){
                                ply.setFillColor("#FFFF00");
                            },delay);

                            delay+=400;
                            setTimeout(function(){
                                ply.setFillColor(color);
                            },delay);
                        }
                        $("#area option[value='"+data.area+"']").attr("selected",true);
                        $.showCompanyRisk(city,data.area,12);
                    });
                }
            }
            if(maxPly){
                map.setViewport(maxPly.getPoints());
            }
        });
        }
    },
    getProvinceWithRule:function getProvinceWithRule(data,flag){
        if(data.code==200){
            for(i=0;i<data.data.length;i++){
                $.drawProvinceBoundaryWithRule(data.data[i],flag);
            }
        }
    },
    getCityWithRule:function getCityWithRule(province,data,flag){
        if(data.code==200){
            for(i=0;i<data.data.length;i++){
                $.drawCityBoundaryWithRule(province,data.data[i],flag);
            }
        }
    },
    getAreaWithRule:function getAreaWithRule(province,city,data,flag){
         if(data.code==200){
             for(i=0;i<data.data.length;i++){
                 $.drawAreaBoundaryWithRule(province,city,data.data[i],flag);
             }
         }
    },
    riskContentClick:function  riskContentClick(id){
        $("#"+id).click(function(){
            if($("#"+id).hasClass("riskcontent")){
                $("#"+id).removeClass("riskcontent").addClass("riskcontentClick");
            }else if($("#"+id).hasClass("riskcontentEven")){
                $("#"+id).removeClass("riskcontentEven").addClass("riskcontentEvenClick");
            }
        });
    },
    addMarkerArray:function addMarkerArray(data){
        var riskRankArray=new Array();
        for(i=0;i<data.data.length;i++){
            $.rightTabMouseEvent("riskcontent"+data.data[i].id);
            $.rightTabMouseClickEvent("riskcontent"+data.data[i].id);
            var item={};
            item.title=data.data[i].unitAddress;
            item.content=data.data[i].equipmentVariety+",风险值:"+data.data[i].riskValue;
            item.point=data.data[i].lng+"|"+data.data[i].lat;
            item.isOpen=0;
            /*item.icon={w:23,h:25,l:115,t:21,x:9,lb:12};*/
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
            }
            riskRankArray.push(item);
        }
        $.clearAllMarker();
        $.addMarker(riskRankArray);//向地图中添加marker
    },
    switchToRiskRankTab:function switchToRiskRankTab(){
        $.rTabClick("#riskRank","#riskInfo","#rightRank","#rightshow");
    }
});