/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-4-12
 * Time: 上午10:47
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
   drawBoundary:function getBoundary(url,province,city,area,data){
    var bdary=new BMap.Boundary();
    bdary.get(data.split("-")[0],function(rs){
        console.log(rs);
        var maxNum=-1,maxPly;
        var color=data.split("-")[1];
        var count=rs.boundaries.length;
        for(var i=0;i<count;i++){
            var ply=new BMap.Polygon(rs.boundaries[i],{strokeWeight:1,strokeOpacity:0.5,fillColor:color,strokeColor:"#000000"});
            map.addOverlay(ply);
            ply.addEventListener("click",function(e){
                name=data.split("-")[0];
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
                if(province==null&&city==null&&area==null){
                location =url+"?province="+encodeURI(name)+"&lat="+encodeURI(latlng.lat)+"&lng="+encodeURI(latlng.lng);
                }else if(province!=null&&city==null&&area==null){
                location=url+"?province="+province+"&city="+encodeURI(name)+"&lat="+encodeURI(latlng.lat)+"&lng="+encodeURI(latlng.lng);
                }else if(province!=null&&city!=null&&area==null){
                location=url+"?province="+province+"&city="+encodeURI(city)+"&area="+encodeURI(name)+"&lat="+encodeURI(latlng.lat)+"&lng="+encodeURI(latlng.lng);
                }else if(url==null&&province!=null&&city==null&&area==null){

                }
                });
        }
        if(maxPly){
            map.setViewport(maxPly.getPoints());
        }
    });
}
});