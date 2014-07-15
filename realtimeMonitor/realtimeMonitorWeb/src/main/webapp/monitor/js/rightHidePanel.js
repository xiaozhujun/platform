/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-5-3
 * Time: 下午9:01
 * To change this template use File | Settings | File Templates.
 */
 $.extend({
     rightHidePanel:function rightHidePanel(panelArrowId,panelImgId,rightMainId){
         var panelArrow="#"+panelArrowId;
         var rightMain="#"+rightMainId;
         var panelImg="#"+panelImgId;
         $(panelArrow).click(function(){
             if($(rightMain).css("display")!='none'){
                 $(panelImg).css("background","url('images/sprites.png') no-repeat scroll -8px -20px rgba(0, 0, 0, 0)").parent().css("right","0");
                 $(rightMain).css("display","none");
             }else{
                 $(panelImg).css("background","url('images/sprites.png') no-repeat scroll -2px -20px  rgba(0, 0, 0, 0)").parent().css("right","384px");
                 $(rightMain).css("display","block");
             }

         });
}

 });
