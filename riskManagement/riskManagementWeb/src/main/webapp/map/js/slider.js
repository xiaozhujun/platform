/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-5-7
 * Time: 下午4:51
 * To change this template use File | Settings | File Templates.
 */
$.extend({
  mySlider:function mySlider(provinceId,cityId,areaId,equipVarietyId,useTimeId){
      jQuery("#Slider4").slider({ from: 0, to: 10, scale: [0, '|', 2, '|', '4', '|', 6, '|', 8 ,'|',10], limits: false, step: 1, dimension: '', skin: "plastic",
          callback: function(value){
              var pId="#"+provinceId;
              var cId="#"+cityId;
              var aId="#"+areaId;
              var eId="#"+equipVarietyId;
              var usId="#"+useTimeId;
              var slideId="#Slider4";
              $.conditionSearch(pId,cId,aId,eId,usId,slideId);
          }
      });
  }
});
