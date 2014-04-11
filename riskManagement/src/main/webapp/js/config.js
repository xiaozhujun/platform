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
            "findIdByArea"  :"rs/address/findByArea/{province},{city},{area}",
            "getProvinceList":"rs/address/getProvinceList",
            "getAreaByProvinceAndCity":"rs/address/getAreaByProvinceAndCity"
        },
        "report":
        {
            "showCraneReport" :"rs/report/showCraneReport",
            "exportCraneReport":"rs/report/exportCraneReport"
        }  ,
        "craneinspectreport":
        {
            "getUnitaddressByArea" :"rs/craneinspectreport/getUnitaddressByArea",
            "getAreaInfoByUnitAddress":"rs/craneinspectreport/getAreaInfoByUnitAddress",
            "getCraneInspectReportInfoByAddressAndEquipment":"rs/craneinspectreport/getCraneInspectReportInfoByAddressAndEquipment",
            "getAreaInfo":"rs/craneinspectreport/getAreaInfo",
            "showRiskRank":"rs/craneinspectreport/showRiskRank"
        },
        "craneInspectReport":{
            "list":"rs/craneinspectreport/list",
            "upload" :"rs/craneinspectreport/upload",
            "listRepeat":"rs/craneinspectreport/listRepeat" ,
            "addRepeat":"rs/craneinspectreport/addRepeat"
        },
        "authority":{
            "add":"rs/authority/add"
        },
        "user":{
            "add":"rs/user/add",
            "update":"rs/user/update",
            "delete":"rs/user/delete",
            "list":"rs/user/list",
            "getId":"rs/user/getIdByName"
        },
        "authority":{
             "add":"rs/authority/add",
             "update":"rs/authority/update",
             "delete":"rs/authority/delete",
            "list":"rs/authority/list"
        },
        "userAuthority":{
              "add":"rs/userAuthority/add"
        }
    }
})(jQuery);