/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-4-13
 * Time: 下午8:04
 * To change this template use File | Settings | File Templates.
 */
$.extend({
    switchTab: function switchTab(rankTab,InfoTab,rightRank,rightShow){
        var rTab="#"+rankTab;
        var iTab="#"+InfoTab;
        var rRank="#"+rightRank;
        var rShow="#"+rightShow;
        $(rTab).css("background-color","#3399FF");
        $(rTab).css("color","#ffffff");
        $(iTab).click(function(){
            $(rTab).css("background-color","#F7F7F7");
            $(iTab).css("background-color","#3399FF");
            $(iTab).css("color","#ffffff");
            $(rTab).css("color","#999999");
            //$("#rightshow").css("display","block");
            $(rRank).animate({left:"400px"},rightCallback);
            $(rShow).animate({left:""});
            function  rightCallback(){
                $(rShow).css("display","block");}
        });
        $(rTab).click(function(){
            $(rTab).css("background-color","#3399FF");
            $(rTab).css("color","#ffffff");
            $(iTab).css("color","#999999");
            $(iTab).css("background-color","#F7F7F7");
            $(rRank).css("display","block");
            $(rRank).animate({left:""});
            $(rShow).animate({left:"400px"});
        });
    }
});