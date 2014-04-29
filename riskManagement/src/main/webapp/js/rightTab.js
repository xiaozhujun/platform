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
        $(iTab).click(function(){
            $.iTabClick(rTab,iTab,rRank,rShow);
        });
        $(rTab).click(function(){
            $.rTabClick(rTab,iTab,rRank,rShow);
        });
    },
    iTabClick:function iTabClick(rTab,iTab,rRank,rShow){
        $(rTab).removeClass("riskRankClick").addClass("riskRank");
        $(iTab).removeClass("riskInfo").addClass("riskInfoClick");
        $(rRank).animate({left:"400px"},rightCallback);
        $(rShow).animate({left:""});
        function  rightCallback(){
            $(rShow).css("display","block");}
    },
    rTabClick:function rTabClick(rTab,iTab,rRank,rShow){
        $(rTab).removeClass("riskRank").addClass("riskRankClick");
        $(iTab).removeClass("riskInfoClick").addClass("riskInfo");
        $(rRank).css("display","block");
        $(rRank).animate({left:""});
        $(rShow).animate({left:"400px"});
    }
});