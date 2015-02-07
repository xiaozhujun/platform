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
     },
     rightHideList:function rightHideList(data){
         console.log(data);
         $("#rightshow").html("");
         for(i=0;i<data.length;i++){
             var hideField={"id":data[i].id,"name":data[i].name,"number":data[i].number,"lng":data[i].lng,"lat":data[i].lat,"address":data[i].address,"userName":data[i].userName,"deviceName":data[i].deviceName};
             var jsonH= $.toJSON(hideField);

             var deviceItem="<div class='deviceAll'><div class='deviceItem' id='deviceItem"+data[i].id+"'><span class='devicecontent'><span class='deviceApp'>设备名称:"+data[i].name
                 +"</span><span class='deviceAddress'><span class='deviceFont'>设备编号:</span>"+data[i].number+"</span><span class='deviceAddress'><span class='deviceFont'>客户名称:</span>"
                 +data[i].userName+"</span><span class='deviceFont'>使用地点:</span>"+data[i].address
                 +"</span><span class='hideField' id='hideField"+data[i].id+"'>"+jsonH+"</span></span></span></div></div>";
             $("#rightshow").append(deviceItem);
             var deviceItemId="#deviceItem"+data[i].id;
             var deviceInfoId="#deviceInfo"+data[i].id;
             $(deviceItemId).mouseover(function(){
                 var id=this.id.substring(10,this.id.length);
                 var hideField="#hideField"+id;
                 var data= JSON2.parse($(hideField).text());
                 var deviceId=data.id;
                 var title="设备"+data.name+"的简要信息";

                 var name = data.name;
                 var number = data.number;
                 var content="<div style='float: left;width: 300px;'><div style='width: 200px;'>设备编号:"+data.number+
                     "</div style='width: 200px;'>设备名称:"+data.name+"</div>"+"</div></div>";
                 var point=data.lng+"|"+data.lat;
                 var isOpen=0;
                 var icon={};
                 icon.w=23;
                 icon.h=25;
                 icon.t=21;
                 icon.x=9;
                 icon.lb=12;
                 icon.l=46;
                 $.addOneMarkerWithId(deviceId,title,content,point,isOpen,icon,0);
             });
             $(deviceItemId).click(function(){
                 $.rightHideClick(this.id,this.id.substring(11,deviceItemId.length));
             });
         }
     },
     rightHideClick:function rightHideClick(deviceItemId,deviceInfoId) {
         var deviceInfo="#deviceInfo"+deviceInfoId;

         $(".deviceItemHover").removeClass("deviceItemHover");

         if($("#"+deviceItemId).hasClass("deviceItem")){
             $("#"+deviceItemId).addClass("deviceItemHover");
         }else{
             $("#"+deviceItemId).removeClass("deviceItemHover");
         }
         if($(deviceInfo).hasClass("deviceInfoHide")){
             $(deviceInfo).removeClass("deviceInfoHide").addClass("deviceInfoClick");
         }else{
             $(deviceInfo).removeClass("deviceInfoClick").addClass("deviceInfoHide");
         }
     }
 });
