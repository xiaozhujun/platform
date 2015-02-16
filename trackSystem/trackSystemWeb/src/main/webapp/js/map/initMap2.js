/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-2-7
 * Time: 下午1:47
 * To change this template use File | Settings | File Templates.
 */
$.extend({
    //创建地图函数：
    createMap:function createMap(div,address,size){
        var map = new BMap.Map(div);//在百度地图容器中创建一个地图
        map.centerAndZoom(address,size);//设定地图的中心点和坐标并将地图显示在地图容器中
        window.map = map;//将map变量存储在全局
    },
    createMapByLat:function createMapByLat(div,lng,lat,size){
        var map = new BMap.Map(div);//在百度地图容器中创建一个地图
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
    initMap:function initMap(div,address,size){
        $.createMap(div,address,size);//创建地图
        $.setMapEvent();//设置地图事件
        $.addMapControl();//向地图添加控件
    },
    initMapByLat:function initMapByLat(div,lng,lat,size){
        $.createMapByLat(div,lng,lat,size);
        $.setMapEvent();//设置地图事件
        $.addMapControl();//向地图添加控件
    },
    addDeviceMark:function add(data,doClear){
        var riskRankArray=new Array();
        var count = 0;
        for(var i=0;i<data.length;i++){
            count++;
            var item={};
            item.deviceId=data[i].id;
            item.title="设备"+data[i].name+"的简要信息";

            item.content="<div style='float: left;width: 300px;'><div style='width: 200px;'>设备编号:"+data[i].number+
                "</div style='width: 200px;'>设备名称:"+data[i].name+"</div>"+"</div></div>";
            item.point=data[i].lng+"|"+data[i].lat;
            item.isOpen=0;
            item.icon={};
            item.icon.w=23;
            item.icon.h=25;
            item.icon.t=21;
            item.icon.x=9;
            item.icon.lb=12;
            item.icon.l=46;
            riskRankArray.push(item);;
        }
        if(doClear == 1) {
            $.clearAllMarker();
        }
        $.addMarkerWithId(riskRankArray);
    }
});
