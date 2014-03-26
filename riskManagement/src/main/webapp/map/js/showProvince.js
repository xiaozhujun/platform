if (typeof console == "undefined"){
    window.console = {log: function(){}};
}

if(window.map){
    delete map;
}
var map = new BMap.Map("container");
map.centerAndZoom("中国", 5);
var stdMapCtrl = new BMap.NavigationControl({type: BMAP_NAVIGATION_CONTROL_SMALL});
map.addControl(stdMapCtrl);
map.enableScrollWheelZoom();
map.enableContinuousZoom();
function getBoundary(data){
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
                location = "cityRisk.jsp?pname="+encodeURI(name)+"&lat="+encodeURI(latlng.lat)+"&lng="+encodeURI(latlng.lng);
            });

        }
        if(maxPly){
            map.setViewport(maxPly.getPoints());
        }
    });
}
map.clearOverlays();
var datas = [
    "广西-#FF0000", "广东-#FF3300", "湖南-#FF0000", "贵州-#FF3300", "云南-#FF3300",
    "福建-#FF0066", "江西-#FF3300", "浙江-#33FF00", "安徽-#FF0000", "湖北-#FF0066",
    "河南-#33FF00", "江苏-#FF0000", "四川-#FF0000", "海南省-#FF3300", "山东-#FF0066", "辽宁-#FF3300",
    "新疆-#FF0000", "西藏-#33FF00", "陕西-#FF0000", "河北-#33FF00", "黑龙江-#FF3300", "宁夏-#FF3300",
    "内蒙古自治区-#33FF00", "青海-#FF3300", "甘肃-#33FF00", "山西-#FF0066", "吉林省-#33FF00",
    "北京-#FBC5DC", "天津-#33FF00", "上海-#FF0000", "重庆市-#33FF00", "香港-#C8C1E3"
];
for(var i=0;i<datas.length;i++){
    getBoundary(datas[i]);
} 