/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-4-12
 * Time: 下午4:12
 * To change this template use File | Settings | File Templates.
 */
$.extend({
    //根据一个json数组来在地图上添加marker
    addMarker:function addMarker(markerArr){
        for(var i=0;i<markerArr.length;i++){
            var json = markerArr[i];
            $.addOneMarker(json.title,json.content,json.point,json.isOpen,json.icon,i);
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
    //根据单位来添加覆盖物以及查询相应的信息
    getCompanyInfoByUnitAddress:function getCompanyInfoByUnitAddress(unitAddress){
        $.clearAllMarker();
        $.post($.URL.craneinspectreport.getOneUnitAddressInfo,{"unitAddress":unitAddress},getOneUnitAddressInfoCallback,"json");
        function getOneUnitAddressInfoCallback(data){
            var unitAddressMarker=new Array();
            if(data.code==200){
                unitAddressMarker= $.getOneMarkerValue(unitAddressMarker,data.data[0]);
                $.addMarker(unitAddressMarker);
            }
        }
    },
//根据省市区来添加覆盖物以及查询相应的企业信息
    initAndAddMarker:function initAndAddMarker(city,area){
        $.conditionSearch("#province","#city","#area","#equipVariety","#useTime","#Slider4");
        /*$.post($.URL.craneinspectreport.getAreaInfo,{"city":city,"area":area},areaInfoCallback,"json");
        $.post($.URL.craneinspectreport.getAreaInfo,{"city":city,"area":area}, $.showUnitRiskRankCallback,"json");
        function areaInfoCallback(data){
            if(data.code==200){
                $("#rightcontent").html("");
                if(data.data[0]==undefined){
                    var error="<div class='errorInfo'>对不起,数据不存在!</div>";
                    $("#rightcontent").append(error);
                }else{
                    var chaoyangMarker= $.getMarkerArray(data);
                    //创建和初始化地图函数：
                    $.addMarker(chaoyangMarker);//向地图中添加marker
                    $.post($.URL.craneinspectreport.getAreaInfoByUnitAddress,{"unitAddress":chaoyangMarker[0].title}, $.getAreaInfoByUnitAddressCallback,"json");

                }
            }
        }*/

    },
    //鼠标点上去之后添加相应的标记
    mouseEvent:function mouseEvent(title,content,point,isOpen,icon,i){
        $.addOneMarker(title,content,point,isOpen,icon,i)
    },
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
    },
    rightTabMouseClickEvent:function rightTabMouseClickEvent(id){
        var _id="#"+id;
        $(_id).click(function(){
            $.iTabClick("#riskRank","#riskInfo","#rightRank","#rightshow");
            var unitAddress=$("#"+this.id).children(".rcontentItem").children(".unitFont").text();
            $.post($.URL.craneinspectreport.getAreaInfoByUnitAddress,{"unitAddress":unitAddress}, $.getAreaInfoByUnitAddressCallback,"json");
        });
        // $.post($.URL.craneinspectreport.getCraneInspectReportInfoById,{"id":cid},getCraneInspectReportInfoByIdCallback,"json");
    },
    addOneMarker:function addOneMarker(title,content,point,isOpen,icon,i){
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
                $.iTabClick("#riskRank","#riskInfo","#rightRank","#rightshow");
                $.post($.URL.craneinspectreport.getAreaInfoByUnitAddress,{"unitAddress":title}, $.getAreaInfoByUnitAddressCallback,"json");
            });
            _marker.addEventListener("mouseover",function(){
                this.openInfoWindow(_iw);
                $.post($.URL.craneinspectreport.getOneUnitAddressInfo,{"unitAddress":title},mouseoverCallback,"json");
                $.post($.URL.craneinspectreport.getAreaInfoByUnitAddress,{"unitAddress":title}, $.getAreaInfoByUnitAddressCallback,"json");
            });
            _marker.addEventListener("mouseout",function(){
                $.post($.URL.craneinspectreport.getOneUnitAddressInfo,{"unitAddress":title},mouseoutCallback,"json");
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
    },
    showUnitRiskRank:function showUnitRiskRank(unitAddress){
        $.post($.URL.craneinspectreport.getOneUnitAddressInfo,{"unitAddress":unitAddress}, $.showUnitRiskRankCallback,"json");
    },
    showRiskInfo:function showRiskInfo(unit){
        $.post($.URL.craneinspectreport.getAreaInfoByUnitAddress,{"unitAddress":unit}, $.getAreaInfoByUnitAddressCallback,"json");
    },
    rightShowInfoClick:function rightShowInfoClick(infoFontNum){
        $("#"+infoFontNum).click(function(){
            if($("#"+this.id).hasClass("righttop")){
                $("#"+this.id).removeClass("righttop").addClass("righttopClick");
            }else if($("#"+this.id).hasClass("righttopClick")){
                $("#"+this.id).removeClass("righttopClick").addClass("righttop");
            }
            var _itemInfo="#itemInfo"+this.id.substring(8,this.id.length);
            if($(_itemInfo).hasClass("itemInfo")){
                $(_itemInfo).removeClass("itemInfo").addClass("itemInfoHide");
            }else if($(_itemInfo).hasClass("itemInfoHide")){
                $(_itemInfo).removeClass("itemInfoHide").addClass("itemInfo");
            }
        });
    },
    getAreaInfoByUnitAddressCallback:function getAreaInfoByUnitAddressCallback(data){
        if(data.code==200){
            $("#righttitle").html("");
            $("#rightcontent").html("");
            $("#righttitle").append(data.str);
            for(var i=0;i<data.data.length;i++){
                var dataString="<div class='rightitem' ><div class='righttopClick' id='infofont"+data.data[i].id+"'><span class='pic'><img src='image/qizhongji.jpg'></span><span class='info'><span class='itemfont'>"+data.data[i].equipmentVariety+"</span><span class='_itemfont'>"+data.data[i].craneNumber+"台</span><span class='hideField' id='hideField"+data.data[i].id+"'>"+data.data[i].unitAddress+","+data.data[i].equipmentVariety+"</span></div><div class='itemInfo' id='itemInfo"+data.data[i].id+"'></div><div class='cl'></div></div>"
                $("#rightcontent").append(dataString);
                var infoFontNum="infofont"+data.data[i].id;
                var hideField="#hideField"+data.data[i].id;
                var address_equipmentvariety=$(hideField).text();
                var itemInfo="#itemInfo"+data.data[i].id;
                $(itemInfo).html("");
                $.post($.URL.craneinspectreport.getCraneInspectReportInfoByAddressAndEquipment,{"address_equipmentvariety":address_equipmentvariety,"itemInfoId":itemInfo}, $.getCraneInspectReportInfoByAddressAndEquipmentCallBack,"json");
                $.rightShowInfoClick(infoFontNum);

            }
        }
    },
    getCraneInspectReportInfoByAddressAndEquipmentCallBack:function getCraneInspectReportInfoByAddressAndEquipmentCallBack(data){
        if(data.code==200){
            var itemInfoId=data.str;
            for(i=0;i<data.data.length;i++){
                var itemInfo="<div class='_info'><span><span class='_infoTitle'><span class='_titleFont'>起重机风险管理平台</span></span><span class='infoImg'><img src='image/qizhongji.jpg'></span><span class='infoMsg'><span class='msgItem'><span class='msgItemFont'>"+data.data[i].equipmentVariety+"</span></span><span class='msgItem'><span class='msgItemFont'>风险值:"+data.data[i].riskValue+"</span></span><span class='msgItem'><span class='msgItemFont'>制造许可编号:"+data.data[i].manufactureLicenseNumber+"</span></span><span class='msgItem'><span class='msgItemFont'>组织机构代码:"+data.data[i].unitNumber+"</span></span><span class='msgItem'><span class='msgItemFont'>使用地点:"+data.data[i].userPoint+"</span></span><span class='msgItem'><span class='msgItemFont'>安全管理人员:"+data.data[i].safeManager+"</span></span><span class='msgItem'><span class='msgItemFont'>联系电话:"+data.data[i].contactPhone+"</span></span>" +
                    "<span class='msgItem'><span class='msgItemFont'>制造单位:"+data.data[i].manufactureUnit+"</span></span><span class='msgItem'><span class='msgItemFont'>使用单位地址:"+data.data[i].unitAddress+"</span></span></span></div>";
                $(itemInfoId).append(itemInfo);
            }
        }
    },
    showUnitRiskRankCallback:function showUnitRiskRankCallback(data){
        if(data.code==200){
            $("#tab").show("");
            $("#rankTitle").html("");
            $("#riskrankContent").html("");
            if(data.data[0]==undefined){
                $("#riskrankContent").append("对不起,数据不存在!");
            }else{
                $.loadUnitRiskRankValue(data);
            }
        }
    },
    showAreaRank:function showAreaRank(data){
        if(data.code==200){
            $("#tab").show("");
            $("#rankTitle").html("");
            $("#riskrankContent").html("");
            if(data.data[0]==undefined){
                $("#riskrankContent").append("对不起,数据不存在!");
            }else{
                $.loadAreaRiskRankValue(data);
            }
        }
    },
    showCityRank:function showCityRank(data){
        if(data.code==200){
            $("#tab").show("");
            $("#rankTitle").html("");
            $("#riskrankContent").html("");
            if(data.data[0]==undefined){
                $("#riskrankContent").append("对不起,数据不存在!");
            }else{
                $.loadCityRiskRankValue(data);
            }
        }
    },
    showProvinceRank:function showProvinceRank(data){
        if(data.code==200){
            $("#tab").show("");
            $("#rankTitle").html("");
            $("#riskrankContent").html("");
            if(data.data[0]==undefined){
                $("#riskrankContent").append("对不起,数据不存在!");
            }else{
                $.loadProvinceRiskRankValue(data);
            }
        }
    },
    provinceClick:function provinceClick(id,flag){
        var province=$(id).children(".rcontentItem").children(".unitFont").text();
        $(id).click(function(){
            if(flag==0){
            }else if(flag==1){
                $("#province option[value='"+province+"']").attr("selected",true);
                $.post($.URL.dataRuleAddress.getCityAndColorWithDataRole,{"province":province}, $.getCityByProvinceCallback,"json");
                $.showCityRisk(province,1);
            }
        });

    },
    cityClick:function cityClick(province,id,flag){
        var city=$(id).children(".rcontentItem").children(".unitFont").text();
        $(id).click(function(){
            if(flag==0){
            }else if(flag==1){
                $("#city option[value='"+city+"']").attr("selected",true);
                $.post($.URL.dataRuleAddress.getAreaAndColorWithDataRole,{"province":province,"city":city}, $.getAreaByProvinceAndCityCallback,"json");
                $.showAreaRisk(province,city,1);
            }
        });

    },
    areaClick:function areaClick(province_city,id,flag){
        var str=province_city.split(",");
        var area=$(id).children(".rcontentItem").children(".unitFont").text();
        $(id).click(function(){
            if(flag==0){
                location="companyRisk.jsp?province="+str[0]+"&city="+encodeURI(str[1])+"&area="+encodeURI(area);
            }else if(flag==1){
                $("#area option[value='"+area+"']").attr("selected",true);
                $.showCompanyRisk(str[1],area,12);
            }
        });
    },
    getMarkerArray:function getMarkerArray(data){
        var chaoyangMarker=new Array();
        for(i=0;i<data.data.length;i++){
            $.getOneMarkerValue(chaoyangMarker,data.data[i]);
        }
        return chaoyangMarker;
    },
    getOneMarkerValue:function getOneMarkerValue(array,arrayValue){
        var item={};
        item.title=arrayValue.unitAddress;
        item.content=arrayValue.equipmentVariety+",风险值:"+arrayValue.riskValue;
        item.point=arrayValue.lng+"|"+arrayValue.lat;
        item.isOpen=0;
        /*item.icon={w:23,h:25,l:115,t:21,x:9,lb:12};*/
        item.icon={};
        item.icon.w=23;
        item.icon.h=25;
        item.icon.t=21;
        item.icon.x=9;
        item.icon.lb=12;
        if(arrayValue.riskValue==1){
            item.icon.l=23;
        }
        if(arrayValue.riskValue==2){
            item.icon.l=0;
        }
        if(arrayValue.riskValue==3){
            item.icon.l=69;
        }
        if(arrayValue.riskValue==4){
            item.icon.l=115;
        }
        if(arrayValue.riskValue==5){
            item.icon.l=46;
        }
        if(arrayValue.riskValue==6){
            item.icon.l=46;
        }
        array.push(item);
    },
    loadCityRiskRankValue:function loadCityRiskRankValue(data){
        $("#rankTitle").html("");
        $("#riskrankContent").html("");
        var rankTitle="<div id='riskttitle'><span class='rtitlerank'>风险排名</span><span class='rtitleItem'>城市</span><span class='rtitleriskItem'>设备数</span></div>";
        $("#rankTitle").append(rankTitle);
        for(var i=0;i<data.data.length;i++){
            var rankContent;
            if(i%2==0){
                if(data.data[i].craneNumber=="null"){
                    data.data[i].craneNumber="0";
                }else{

                }
                rankContent="<div class='riskcontentEven' id='riskcontent"+data.data[i].id+"'>"+"<span class='rrank'>"+(i+1)+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].city+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].craneNumber+"</span></span></div>"
            }else{
                if(data.data[i].craneNumber=="null"){
                    data.data[i].craneNumber="0";
                }else{

                }
                rankContent="<div class='riskcontent' id='riskcontent"+data.data[i].id+"'>"+"<span class='rrank'>"+(i+1)+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].city+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].craneNumber+"</span></span></div>"
            }
            $("#riskrankContent").append(rankContent);
            $.cityClick(data.str,"#riskcontent"+data.data[i].id,1);
            $.riskContentClick("riskcontent"+data.data[i].id);
        }
        $.switchToRiskRankTab();
    },
    loadProvinceRiskRankValue:function loadProvinceRiskRankValue(data){
        $.switchToRiskRankTab();
        $("#rankTitle").html("");
        $("#riskrankContent").html("");
        var rankTitle="<div id='riskttitle'><span class='rtitlerank'>风险排名</span><span class='rtitleItem'>省名</span><span class='rtitleriskItem'>设备数</span></div>";
        $("#rankTitle").append(rankTitle);
        for(var i=0;i<data.data.length;i++){
            if(data.data[i]!=null){
                var rankContent;
                if(i%2==0){
                    rankContent="<div class='riskcontentEven' id='riskcontent"+data.data[i].id+"'>"+"<span class='rrank'>"+(i+1)+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].province+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].craneNumber+"</span></span></div>"
                }else{
                    rankContent="<div class='riskcontent' id='riskcontent"+data.data[i].id+"'>"+"<span class='rrank'>"+(i+1)+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].province+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].craneNumber+"</span></span></div>"
                }
                $("#riskrankContent").append(rankContent);
                $.provinceClick("#riskcontent"+data.data[i].id,1);
                $.riskContentClick("riskcontent"+data.data[i].id);
            }
        }
        $.switchToRiskRankTab();
    },
    loadAreaRiskRankValue:function loadAreaRiskRankValue(data){
        $("#rankTitle").html("");
        $("#riskrankContent").html("");
        var rankTitle="<div id='riskttitle'><span class='rtitlerank'>风险排名</span><span class='rtitleItem'>区域</span><span class='rtitleriskItem'>设备数</span></div>";
        $("#rankTitle").append(rankTitle);
        for(var i=0;i<data.data.length;i++){
            var rankContent;
            if(i%2==0){
                if(data.data[i].craneNumber=="null"){
                    data.data[i].craneNumber="0";
                }else{

                }
                rankContent="<div class='riskcontentEven' id='riskcontent"+data.data[i].id+"'>"+"<span class='rrank'>"+(i+1)+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].area+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].craneNumber+"</span></span></div>"
            }else{
                if(data.data[i].craneNumber=="null"){
                    data.data[i].craneNumber="0";
                }else{

                }
                rankContent="<div class='riskcontent' id='riskcontent"+data.data[i].id+"'>"+"<span class='rrank'>"+(i+1)+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].area+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].craneNumber+"</span></span></div>"
            }
            $("#riskrankContent").append(rankContent);
            $.areaClick(data.str,"#riskcontent"+data.data[i].id,1);
            $.riskContentClick("riskcontent"+data.data[i].id);
        }
        $.switchToRiskRankTab();
    },
    loadUnitRiskRankValue:function loadUnitRiskRankValue(data){
        $("#rankTitle").html("");
        $("#riskrankContent").html("");
        var rankTitle="<div id='riskttitle'><span class='rtitlerank'>风险排名</span><span class='rtitleItem'>企业</span><span class='rtitleriskItem'>设备数</span></div>";
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
                rankContent="<div class='riskcontentEven' id='riskcontent"+data.data[i].id+"'>" +"<span class='rrank'>"+j+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].unitAddress+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].craneNumber+"</span></span></div>"
            }else{
                rankContent="<div class='riskcontent' id='riskcontent"+data.data[i].id+"'>" +"<span class='rrank'>"+j+"</span>" +"<span class='rcontentItem'><span class='unitFont'>"+data.data[i].unitAddress+"</span></span>" +"<span class='riskItem'><span class='riskFont'>"+data.data[i].craneNumber+"</span></span></div>"
            }
            $("#riskrankContent").append(rankContent);
        }
        for(i=0;i<data.data.length;i++){
            $.rightTabMouseEvent("riskcontent"+data.data[i].id);
            $.rightTabMouseClickEvent("riskcontent"+data.data[i].id);
        }
        $.switchToRiskRankTab();
    },
    getCityByProvinceCallback:function getCityByProvinceCallback(data){
    if(data.code==200){
        $("#city").html("");
        var citySearch="<option value='0'>---请选择---</option>";
        for(i=0;i<data.data.length;i++){
            citySearch+="<option value='"+data.data[i].city+"'>"+data.data[i].city+"</option>";
        }
        $("#city").html(citySearch);
    }
},
    getAreaByProvinceAndCityCallback:function getAreaByProvinceAndCityCallback(data){
    if(data.code==200){
        $("#area").html("");
        var areaSearch="<option value='0'>---请选择---</option>";
        for(i=0;i<data.data.length;i++){
            areaSearch+="<option value='"+data.data[i].area+"'>"+data.data[i].area+"</option>";
        }
        $("#area").html(areaSearch);
    }
}
});