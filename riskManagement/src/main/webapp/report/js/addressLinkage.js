/**
 * Created with IntelliJ IDEA.
 * User: Sunhui
 * Date: 14-4-23
 * Time: 下午23:51
 * To change this template use File | Settings | File Templates.
 */

    /*
     发送post请求，获得省数据
     */
$.post($.URL.address.getProvinceList,null,listCallback,"json");
/*
 所有联动效果基于第一次请求，不嵌套的话第二次会得不到值
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

        $("#province").ligerComboBox({
            data: provinceList,
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
                        $("#city").ligerComboBox({
                            data:cityList,
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
                                        $("#area").ligerComboBox({ isShowCheckBox:true,isMultiSelect:false,
                                            data:areaList, valueFieldID: 'areaSelect',
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
                                                        $("#unitaddress").ligerComboBox({ isShowCheckBox:true,isMultiSelect:false,
                                                            data:unitList, valueFieldID: 'unitaddressSelect' })
                                                    }
                                                }
                                            }
                                        })
                                    }
                                }
                            }
                        })
                    }
                }
            }
        })
    }
} ;
