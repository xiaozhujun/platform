/**
 * Created with IntelliJ IDEA.
 * User: Sunhui
 * Date: 14-4-23
 * Time: 下午23:51
 * To change this template use File | Settings | File Templates.
 */
$("#unitaddress").ligerComboBox({ isShowCheckBox:true,isMultiSelect:false, width:400,
    data:null, valueFieldID: 'unitaddressSelect' })
$("#area").ligerComboBox({ isShowCheckBox:true,isMultiSelect:false,
    data:null, valueFieldID: 'areaSelect',
    /*
     选择区域后的联动效果
     */
    onSelected:function(m,n)
    {

        var testdata={};
        if($("#area").val()!="请选择"&&$("#area").val()!="")
        {
            testdata.province=$("#province").val();
            testdata.city=$("#city").val();
            testdata.area=$("#area").val();
            $.post($.URL.craneinspectreport.getUnitaddressByArea,testdata,getunitCallback,"json");
        }

        function getunitCallback(unitdata){
            if(unitdata.code==200){
                var unitList=[];
                unitList .push({id:0,text:"请选择"}) ;
                for(var i=0;i<unitdata.data.length;i++)
                {
                    var j={};
                    j["text"]=unitdata.data[i].unitAddress;
                    j["id"]=i+1;
                    unitList.push(j);
                }
                $("#unitaddress").ligerComboBox({data:unitList})  ;
            }
        }
    }
})
$("#city").ligerComboBox({
    data:null,
    isShowCheckBox:true,isMultiSelect:false,
    /*
     选择市后的联动效果
     */
    onSelected:function(m,n)
    {
        if($("#city").val()!="请选择"&&$("#city").val()!="")
        {
            $.post($.URL.address.getAreaByProvinceAndCity,{"province":$("#province").val(),"city":$("#city").val()},getarealistCallback,"json");
        }

        function getarealistCallback(areadata){
            if(areadata.code==200){

                var areaList=[];
                areaList .push({id:0,text:"请选择"}) ;
                for(var i=0;i<areadata.data.length;i++)
                {
                    var j={};
                    j["text"]=areadata.data[i].area;
                    j["id"]=i+1;
                    areaList.push(j);
                }
                $("#area").ligerComboBox({data:areaList})  ;
            }}
    }
})

$("#province").ligerComboBox({
    data: null,
    isShowCheckBox:true,isMultiSelect:false,
    valueFieldID: 'provinceSelect' ,
    /*
     选择省后的联动效果
     */
    onSelected:function(a,b)
    {
        if($("#province").val()!="请选择"&&$("#province").val()!="")
        {
            $.post($.URL.address.getCityByProvince,{"province":b},getcitylistCallback,"json");
        }
        function getcitylistCallback(citydata){
            if(citydata.code==200){
                var cityList=[];
                cityList .push({id:0,text:"请选择"});
                for(var i=0;i<citydata.data.length;i++)
                {
                    var j={};
                    j["text"]=citydata.data[i].city;
                    j["id"]=i+1;
                    cityList.push(j);
                }
                $("#city").ligerComboBox({data:cityList})  ;
            }
        }
    }
})

    /*
     发送post请求，获得省数据
     */
$.post($.URL.address.getProvinceList,null,listCallback,"json");
     /*
     *  清空自带样式
     **/
$("#province").removeClass("l-text-field");
$("#city").removeClass("l-text-field");
$("#area").removeClass("l-text-field");
$("#unitaddress").removeClass("l-text-field");
$("#equipmentvariety").removeClass("l-text-field");
/*
 获得省数据
 */
function listCallback(data){
    if(data.code==200){
        /*
         取出省地址数据
         */
        var provinceList=[];
        provinceList.push({id:0,text:"请选择"}) ;
        for(var i=0;i<data.data.length;i++)
        {
            var j={};
            j["text"]=data.data[i].province;
            j["id"]=i+1;
            provinceList.push(j);
        }
        $("#province").ligerComboBox({data:provinceList})  ;
    }
}
/*
 定义起重机类型数据源，
 */
$.post($.URL.craneinspectreport.getEquipmentVarietyList,null,getEquipmentVarietyListCallback,"json");
function getEquipmentVarietyListCallback(equdata)
{
    if(equdata.code==200)
    {

        var equVarietyList=[];
        equVarietyList.push({id:0,text:"请选择"}) ;
        for(i=0;i<equdata.data.length;i++)
        {
            var  j={};
            j["text"]=equdata.data[i].equipmentVariety;
            j["id"]=i+1;
            equVarietyList.push(j);
        }
       $("#equipmentvariety").ligerComboBox({data:equVarietyList})
    }
}
$("#equipmentvariety").ligerComboBox({ isShowCheckBox:true,isMultiSelect:false,
    data: null  ,width:255
    , valueFieldID: 'equipmentvarietySelect' })
$("#province").removeClass("l-text-field");
$("#city").removeClass("l-text-field");
$("#area").removeClass("l-text-field");
$("#unitaddress").removeClass("l-text-field");
$("#equipmentvariety").removeClass("l-text-field");

