(function($){
    $.URL = {
        "power":{
            "add":"rs/power/add",
            "update":"rs/power/update",
            "delete":"rs/power/delete",
            "list":"rs/power/list"
        }  ,
        "address":{
            "list" :"rs/address/list"  ,
            "getCityByProvince"  :"rs/address/getCityByProvince" ,
            "getAreaByCity":"rs/address/getAreaByCity",
            "findIdByArea"  :"rs/address/findByArea/{province},{city},{area}"
        },
        "report":
        {
            "showCraneReport" :"rs/report/showCraneReport",
            "exportCraneReport":"rs/report/exportCraneReport"
        }  ,
        "craneinspectreport":
        {
            "getUnitaddressByArea" :"rs/craneinspectreport/getUnitaddressByArea"
        }

    }

})(jQuery);