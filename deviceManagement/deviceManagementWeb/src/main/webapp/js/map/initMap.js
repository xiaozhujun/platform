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
    createMapByLat:function createMapByLat(lng,lat,size){
        var map = new BMap.Map("container");//在百度地图容器中创建一个地图
        var point = new BMap.Point(lat,lng);
        map.centerAndZoom(point,size);
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
    initMapByLat:function initMapByLat(lng,lat,size){
        $.createMapByLat(lng,lat,size);
        $.setMapEvent();//设置地图事件
        $.addMapControl();//向地图添加控件
    },
    showCity:function showCity(data,flag){
        /*$.initCityWithDataRule(data.province,8,flag);*/
        $.initCity(data,8,flag);
    },
    drawProvinceBoundaryWithRule:function drawProvinceBoundaryWithRule(data,flag){
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
                       /* $.post($.URL.dataRuleAddress.getCityAndColorWithDataRole,{"province":data.province}, $.getCityByProvinceCallback,"json");*/
                       /* $.showCityRisk(data.province,1);*/
                        $.showCity(data,1);
                    });
                }
            }
            if(maxPly){
                map.setViewport(maxPly.getPoints());
            }
        });
    },
    /*
     根据数据权限给城市画出轮廓
     */
    drawCityBoundaryWithRule:function drawCityBoundaryWithRule(province,data,flag){
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
                        /*$.post($.URL.dataRuleAddress.getAreaAndColorWithDataRole,{"province":province,"city":data.city}, $.getAreaByProvinceAndCityCallback,"json");*/
                        /*$.showAreaRisk(province,data.city,1);*/
                        $.showArea(data,1);
                    });
                }
            }
            if(maxPly){
                map.setViewport(maxPly.getPoints());
            }
        });
    },
    /*
     根据数据权限给地区画出轮廓
     */
    drawAreaBoundaryWithRule:function drawAreaBoundaryWithRule(province,city,data,flag){
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
    },
    addMyMark:function add(data){
        var riskRankArray=new Array();
        for(i=0;i<data.data.length;i++){
            var item={};
            item.userId=data.data[i].id;
            item.title="设备名称:"+data.data[i].name;

            item.content="<div style='float: left;width: 300px;'><div style='width: 200px;'>生产日期:"+data.data[i].produceTime+"<br>施工项目:"+data.data[i].project+"<br><a deviceId="+data.data[i].id+" class='showMainDeviceLink'>查看详情</a>"+"</div></div>";
            item.point=data.data[i].lng+"|"+data.data[i].lat;
            item.isOpen=0;
            item.icon={};
            item.icon.w=23;
            item.icon.h=25;
            item.icon.t=21;
            item.icon.x=9;
            item.icon.lb=12;
            item.icon.l=46;
            riskRankArray.push(item);
        }
        $.clearAllMarker();
        $.addMarkerWithId(riskRankArray);

}
});