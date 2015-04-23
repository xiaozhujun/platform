

var changeStr = '';   //初始化一个
var city='<%=URLDecoder.decode(request.getParameter("city"),"utf-8")%>';
var area='<%=URLDecoder.decode(request.getParameter("area"),"utf-8")%>';
function FillUrls() {


    //获取用户输入的关键字
    var strdomin = $("#more").val()

    //如果请求为空的话就不进行请求
    if (strdomin == null || strdomin == "") {
        $("#auto").empty();
        $("#auto").hide();
        return;
    }
    //跟上次请求的关键字相同就返回
    //    if (changeStr == strdomin)
    //        return;  

    changeStr = strdomin;
    window.status = "请求中";
    var _url;
    if($$("catid").value==5){
         _url='rs/craneinspectreport/getCraneInfoByFuzzyUnitAddress';
    }
    else if($$("catid").value==8){
        _url='rs/craneinspectreport/getCraneInfoByFuzzyUsePoint';
    }
    else if($$("catid").value==9){
        _url='rs/craneinspectreport/getCraneInfoByFuzzySafeManager';
    }
    else if($$("catid").value==10){
        _url='rs/craneinspectreport/getCraneInfoByFuzzyEquipmentVariety';
    }
    else if($$("catid").value==12){
        _url='rs/craneinspectreport/getCraneInfoByFuzzyManufactureUnit';
    }
    else {
        _url='rs/craneinspectreport/getCraneInfoByFuzzyQuery';
    }
    //封装请求百度服务器的参数（只有关键字是动态的，其他几个参数都是固定的）
    var qsData = {'city':city,'area':area,'require':'%'+$("#more").val()+'%'};
    //var _qsData={'city':'武汉市','area':'新洲区','require':'%'+$("#more").val()+'%'};
    //发jsonp（跨域请求js）
    $.ajax({
        async: false,
        url:_url ,
        type: "POST",
        dataType: 'json',
        callback: ShowDiv,
        data: qsData,
        timeout: 5000,
        success: function (data) {
            if(data.code==200){
                var Info = data.data;   //拿到关键字提示
                var wordText = $("#more").val();
                var autoNode = $("#auto");   //缓存对象（弹出框）
                if (Info.length == 0) {
                    autoNode.hide();
                    return false;
                }
                autoNode.empty();  //清空上次
                for (var i = 0; i < Info.length; i++) {
                        var wordNode = Info[i];   //弹出框里的每一条内容


                    var newDivNode = $("<div>").attr("id", i);    //设置每个节点的id值
                    if(i==Info.length-1){
                        newDivNode.attr("style", "font:14px/25px arial;height:25px;padding:0 9px;cursor: pointer;background-color:white;width:230px;height:45px;border-color:#0095E2;border-style:solid;border-width:0 1px 1px 1px");
                    }else{
                        newDivNode.attr("style", "font:14px/25px arial;height:25px;padding:0 9px;cursor: pointer;background-color:white;width:230px;height:45px;border-color:#0095E2;border-style:solid;border-width:0 1px 0px 1px");
                    }
                    newDivNode.html(wordNode).appendTo(autoNode);  //追加到弹出框
                    //鼠标移入高亮，移开不高亮
                    newDivNode.mouseover(function () {
                        if (highlightindex != -1) {        //原来高亮的节点要取消高亮（是-1就不需要了）
                            autoNode.children("div").eq(highlightindex).css("background-color", "white");
                        }
                        //记录新的高亮节点索引
                        highlightindex = $(this).attr("id");
                        $(this).css("background-color", "#72C2FF");
                    });
                    newDivNode.mouseout(function () {
                        $(this).css("background-color", "white");
                    });

                    //鼠标点击文字上屏
                    newDivNode.click(function () {
                        //取出高亮节点的文本内容
                        var comText = autoNode.hide().children("div").eq(highlightindex).text();
                        highlightindex = -1;
                        //文本框中的内容变成高亮节点的内容
                        $("#more").val(comText);
                    })
                    if (Info.length > 0) {    //如果返回值有内容就显示出来
                        autoNode.show();
                    } else {               //服务器端无内容返回 那么隐藏弹出框
                        autoNode.hide();
                        //弹出框隐藏的同时，高亮节点索引值也变成-1
                        highlightindex = -1;
                    }
                }
                window.status = "请求结束";
            }
        },
        error: function (xhr) {
        }
    });
}

function ShowDiv(data) {
}

var timeoutId;   //延迟请求服务器
var highlightindex = -1;   //高亮
$(function () {
    $("#more").keyup(function (event) {
        var myEvent = event || window.event;
        var keyCode = myEvent.keyCode;    //键值 不同的值代表不同的键  13是回车等
        //console.log(keyCode);

        //只有按键盘的字母键、退格、删除、空格、ESC等键才进行响应：8退格backspace 46删除delete 空格32
        if (keyCode >= 65 && keyCode <= 90 || keyCode >= 48 && keyCode <= 57 || keyCode >= 96 && keyCode <= 111 || keyCode >= 186 && keyCode <= 222 || keyCode == 8 || keyCode == 46 || keyCode == 32 || keyCode == 13) {
            //考虑到请求是百度的服务器，故不需要考虑性能问题，没必要等几秒再请求，直接实时请求。如果延时请求，解开下面代码即可
            //                    clearTimeout(timeoutId);
            //                    timeoutId = setTimeout(function () {
            //                        timeoutId = FillUrls();
            //                    }, 500)
            FillUrls();
            if (highlightindex != -1) {
                highlightindex = -1;
                //autoNodes.eq(highlightindex).css("background-color", "white");
            }
        }

        else if (keyCode == 38 || keyCode == 40) {
            if (keyCode == 38) {       //向上
                var autoNodes = $("#auto").children("div")
                if (highlightindex != -1) {
                    //如果原来存在高亮节点，则将背景色改称白色
                    autoNodes.eq(highlightindex).css("background-color", "white");
                    highlightindex--;
                } else {
                    highlightindex = autoNodes.length - 1;
                }
                if (highlightindex == -1) {
                    //如果修改索引值以后index变成-1，则将索引值指向最后一个元素
                    highlightindex = autoNodes.length - 1;
                }
                //让现在高亮的内容变成红色
                autoNodes.eq(highlightindex).css("background-color", "#72C2FF");

                //取出当前选中的项 赋值到输入框内
                var comText = autoNodes.eq(highlightindex).text();
                $("#more").val(comText);
            }
            if (keyCode == 40) {    //向下
                var autoNodes = $("#auto").children("div")
                if (highlightindex != -1) {
                    //如果原来存在高亮节点，则将背景色改称白色
                    autoNodes.eq(highlightindex).css("background-color", "white");
                }
                highlightindex++;
                if (highlightindex == autoNodes.length) {
                    //如果修改索引值以后index变成-1，则将索引值指向最后一个元素
                    highlightindex = 0;
                }
                //让现在高亮的内容变成红色
                autoNodes.eq(highlightindex).css("background-color", "#72C2FF");
                var comText = autoNodes.eq(highlightindex).text();
                $("#more").val(comText);
            }
        } else if (keyCode == 13) {
            //回车
            //下拉框有高亮内容
            if (highlightindex != -1) {
                //取出高亮节点的文本内容
                var comText = $("#auto").hide().children("div").eq(highlightindex).text();
                highlightindex = -1;
                //文本框中的内容变成高亮节点的内容
                $("#more").val(comText);
            } else {
                //下拉框没有高亮内容
                $("#auto").hide();

                //已经提交，让文本框失去焦点（再点提交或者按回车就不会触发keyup事件了）
                $("#more").get(0).blur();
            }
            $("#queryBtn").click();
        } else if (keyCode == 27) {    //按下Esc 隐藏弹出层
            if ($("#auto").is(":visible")) {
                $("#auto").hide();
            }
        }
    });
    //点击页面隐藏自动补全提示框
    document.onclick = function (e) {
        var e = e ? e : window.event;
        var tar = e.srcElement || e.target;
        if (tar.id != "more") {
            if ($("#auto").is(":visible")) {
                $("#auto").css("display", "none")
            }
        }
    }
});