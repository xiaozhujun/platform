(function($){
    $.URL = {
<<<<<<< HEAD
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

=======
       "power":{
           "add":"rs/power/add",
           "update":"rs/power/update",
           "delete":"rs/power/delete",
           "list":"rs/power/list"
<<<<<<< HEAD
       },
        "user":{
            "add":"rs/user/add"
        },
        "craneInspectReport":{
            "list":"rs/craneinspectreport/list",
            "upload" :"rs/craneinspectreport/upload",
            "listRepeat":"rs/craneinspectreport/listRepeat" ,
            "addRepeat":"rs/craneinspectreport/addRepeat"
        },
        "authority":{
            "add":"rs/authority/add"
        }
=======
       }  ,
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
>>>>>>> 226e87f0bebc1bd549b15d95998cb4d5b59bbe17
>>>>>>> 23dd51744e660700d6196a2d52cb2394d49b9f1c
    }
})(jQuery);