/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-4-12
 * Time: 下午4:12
 * To change this template use File | Settings | File Templates.
 */
$.extend({
    addMarkerWithId:function addMarker(markerArr){
        for(var i=0;i<markerArr.length;i++){
            var json = markerArr[i];
            $.addOneMarkerWithId(json.userId,json.title,json.content,json.point,json.isOpen,json.icon,i);
        }
    },
    //创建InfoWindow
    createInfoWindow:function createInfoWindow(i,markerArr){
        var json = markerArr[i];
        var iw = new BMap.InfoWindow("<b class='iw_poi_title' title='" + json.title + "'>" + json.title + "</b><div class='iw_poi_content'>"+json.content+"</div>");
        return iw;
    },
    //创建一个InfoWindow
    createOneInfoWindow:function createOneInfoWindow(title,content){
        var iw = new BMap.InfoWindow("<b class='iw_poi_title' title='" + title + "'>" + title + "</b><div class='iw_poi_content'>"+content+"</div>");
        return iw;
    },
    //创建一个Icon
    createIcon:function createIcon(json){
        var icon = new BMap.Icon("http://app.baidu.com/map/images/us_mk_icon.png", new BMap.Size(json.w,json.h),{imageOffset: new BMap.Size(-json.l,-json.t),infoWindowOffset:new BMap.Size(json.lb+5,1),offset:new BMap.Size(json.x,json.h)})
        return icon;
    },
    addOneMarkerWithId:function addOneMarker(id,title,content,point,isOpen,icon,i){
        var p0 = point.split("|")[0];
        var p1 = point.split("|")[1];
        var point = new BMap.Point(p0,p1);
        var iconImg = $.createIcon(icon);
        var marker = new BMap.Marker(point,{icon:iconImg});
        var iw = $.createOneInfoWindow(title,content);
        var label = new BMap.Label("",{"offset":new BMap.Size(icon.lb-icon.x+10,-20)});
        marker.setLabel(label);
        map.addOverlay(marker);
        label.setStyle({
            borderColor:"#808080",
            color:"#333",
            cursor:"pointer"
        });
        (function(){
            var index = i;
            var _iw = $.createOneInfoWindow(title,content);
            var _marker = marker;
            if(index==0){
                _marker.openInfoWindow(_iw);
            }
            _marker.addEventListener("click",function(){
                this.openInfoWindow(_iw);
                $.myMouseover(id);
            });
            _marker.addEventListener("mouseover",function(){
                this.openInfoWindow(_iw);
                $.myMouseover(id);
            });
            _marker.addEventListener("mouseout",function(){
                $.myMouseout(id);
                // $.post($.URL.cranedevicereport.getOneUnitAddressInfo,{"unitAddress":title},mouseoutCallback,"json");
            });
            function mouseoverCallback(data){
                if(data.code==200){
                    var riskcontentId="#riskcontent"+data.data[0].id;
                    $(riskcontentId).removeClass("riskcontent").addClass("mouseEvent");

                }
            };
            function mouseoutCallback(data){
                if(data.code==200){
                    var riskcontentId="#riskcontent"+data.data[0].id;
                    $(riskcontentId).removeClass("mouseEvent").addClass("riskcontent");

                }
            };
            _iw.addEventListener("open",function(){
                _marker.getLabel().hide();
            })
            _iw.addEventListener("close",function(){
                _marker.getLabel().show();
            })
            label.addEventListener("click",function(){
                _marker.openInfoWindow(_iw);

            })
            if(!!isOpen){
                label.hide();
                _marker.openInfoWindow(_iw);
            }
        })()
    }
    ,myMouseover:function myMouseover(id){
        var deviceInfo="#deviceInfo"+id;
        var riskcontentId="#deviceItem"+id;
        $(".deviceItemHover").removeClass("deviceItem");
        $(riskcontentId).addClass("deviceItemHover");
        $(deviceInfo).removeClass("deviceInfoHide").addClass("deviceInfoClick");

        $(".showMainDeviceLink").unbind("click");
        $(".showMainDeviceLink").click(function(){
            mainDeviceId=$(this).attr("mainDeviceId");
            $.ligerDialog.open({
                height:600,
                width: 800,
                title : '设备详情',
                url: 'device/mainDeviceDetail.html',
                showMax: false,
                showToggle: true,
                showMin: false,
                isResize: true,
                slide: false,
                data: {
                    name: $("#txtValue").val()
                },
                //自定义参数
                myDataName: $("#txtValue").val()
            });
        });
    },
    myMouseout:function myMouseover(id){
        var deviceInfo="#deviceInfo"+id;
        var riskcontentId="#deviceItem"+id;
        $(".deviceItemHover").removeClass("deviceItemHover");
        $(riskcontentId).addClass("deviceItem");
        $(deviceInfo).removeClass("deviceInfoHide").addClass("deviceInfoClick");
    }
});