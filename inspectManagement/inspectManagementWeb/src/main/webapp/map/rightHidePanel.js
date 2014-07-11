/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-5-3
 * Time: 下午9:01
 * To change this template use File | Settings | File Templates.
 */
 $.extend({
     rightHidePanel:function rightHidePanel(panelarrow2Id,panelimg2Id,rightmainId){
         var panelarrow2="#"+panelarrow2Id;
         var rightmain="#"+rightmainId;
         var panelimg2="#"+panelimg2Id;
         $(panelarrow2).click(function(){
             if($(rightmain).css("display")!='none'){
                 $(panelimg2).css("background","url('images/sprites.png') no-repeat scroll -8px -20px rgba(0, 0, 0, 0)").parent().css("right","0");
                 $(rightmain).css("display","none");
             }else{
                 $(panelimg2).css("background","url('images/sprites.png') no-repeat scroll -2px -20px  rgba(0, 0, 0, 0)").parent().css("right","384px");
                 $(rightmain).css("display","block");
             }
         });
}});
