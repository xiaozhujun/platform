/**
 * Created with IntelliJ IDEA.
 * User: YangRichard
 * Date: 15-2-16
 * Time: 下午11:37
 * To change this template use File | Settings | File Templates.
 */
$.extend({
    //绘制路径
    drawPolyline:function drawOnePolyline(pointArr){
                var p = [];
                for(var i=0;i<pointArr.lng.length;i++) {
                    var point = new BMap.Point(parseFloat(pointArr.lng[i]),parseFloat(pointArr.lat[i]));
                    p.push(point);
                }
                var polyline = new BMap.Polyline(p,{
                    strokeColor:"blue", strokeWeight:6, strokeOpacity:0.5
                });
                map.addOverlay(polyline);
    },

    //增加端点
    addStartPoint:function addStartPoint(pointArr,name,number) {
        var markData = {};
        markData.data = [];
        var curMark = {};
        curMark.lng = pointArr.lng[0];
        curMark.lat = pointArr.lat[0];
        curMark.name = name;
        curMark.number = number;
        markData.data.push(curMark);
        $.addDeviceMark(markData.data,0);
    }
});
