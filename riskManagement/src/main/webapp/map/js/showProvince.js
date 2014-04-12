if (typeof console == "undefined"){
    window.console = {log: function(){}};
}
/*if(window.map){
    delete map;
}*/
$.initMap("中国",5);
function getBoundary(data){
    $.drawBoundary("cityRisk.jsp",null,null,null,data);
}
/*map.clearOverlays();*/
$.clearAllMarker;
var provinceArray = [
    "广西-#FF0000", "广东-#FF3300", "湖南-#FF0000", "贵州-#FF3300", "云南-#FF3300",
    "福建-#FF0066", "江西-#FF3300", "浙江-#33FF00", "安徽-#FF0000", "湖北-#FF0066",
    "河南-#33FF00", "江苏-#FF0000", "四川-#FF0000", "海南省-#FF3300", "山东-#FF0066", "辽宁-#FF3300",
    "新疆-#FF0000", "西藏-#33FF00", "陕西-#FF0000", "河北-#33FF00", "黑龙江-#FF3300", "宁夏-#FF3300",
    "内蒙古自治区-#33FF00", "青海-#FF3300", "甘肃-#33FF00", "山西-#FF0066", "吉林省-#33FF00",
    "北京-#FBC5DC", "天津-#33FF00", "上海-#FF0000", "重庆市-#33FF00", "香港-#C8C1E3"
];
for(var i=0;i<provinceArray.length;i++){
    getBoundary(provinceArray[i]);
} 