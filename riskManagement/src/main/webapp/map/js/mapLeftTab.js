/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-4-16
 * Time: 上午9:32
 * To change this template use File | Settings | File Templates.
 */
$.extend({
    mapLeftTab:function mapLeftTab(searchTabId,searchId,drawCircleId,drawLineId){
        var searchTab="#"+searchTabId;
        var search="#"+searchId;
        var drawCircle="#"+drawCircleId;
        var drawLine="#"+drawLineId;
        $(searchTab).click(function(){
            if($(this).hasClass("searchTab")){
                $(this).removeClass("searchTab").addClass("searchTabClick");
            }else if($(this).hasClass("searchTabClick")){
                $(this).removeClass("searchTabClick").addClass("searchTab");
            }
            if($(search).hasClass("searchShow")){
                $(search).removeClass("searchShow").addClass("searchHide");
            }else if($(search).hasClass("searchHide")){
                $(search).removeClass("searchHide").addClass("searchShow");
            }
        });
        $(drawCircle).click(function(){
            if($(this).text()=="画圈"){
                $(this).text("取消");
            }else if($(this).text()=="取消"){
                $(this).text("画圈");
            }
            var flag=false;
            if($(this).hasClass("lineTabClick")){
                $(this).removeClass("lineTabClick").addClass("lineTab");
                flag=false;
                $.drawCircle(flag);
            }else if($(this).hasClass("lineTab")){
                $(this).removeClass("lineTab").addClass("lineTabClick");
                flag=true;
                $.drawCircle(flag);
            }

        });
        $(drawLine).click(function(){
            if($(this).text()=="画线"){
                $(this).text("取消");
            }else if($(this).text()=="取消"){
                $(this).text("画线");
            }
            var flag=false;
            if($(this).hasClass("lineTabClick")){
                $(this).removeClass("lineTabClick").addClass("lineTab");
                flag=false;
                $.drawLine(flag);
            }else if($(this).hasClass("lineTab")){
                $(this).removeClass("lineTab").addClass("lineTabClick");
                flag=true;
                $.drawLine(flag);
            }
        });
    }
});