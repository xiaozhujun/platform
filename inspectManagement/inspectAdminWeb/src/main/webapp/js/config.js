(function($){
    $.URL = {
        "apppower":{
            "add":"rs/power/add",
            "update":"rs/power/update",
            "delete":"rs/power/delete",
            "list":"rs/power/list"
        },
        "appuser":{
            "add":"rs/user/add",
            "update":"rs/user/update",
            "delete":"rs/user/delete",
            "list":"rs/user/list",
            "getId":"rs/user/getIdByName"
        },
        "appauthority":{
             "add":"rs/authority/add",
             "update":"rs/authority/update",
             "delete":"rs/authority/delete",
            "list":"rs/authority/list"
        },
        "appuserappauthority":{
              "add":"rs/userappauthority/add"
        }  ,
        "app" :{
            "add" :"rs/app/add" ,
            "list":"rs/app/list",
            "update":"rs/app/update",
            "delete":"rs/app/delete"
        },
        "menu" :{
            "add" :"rs/menu/add" ,
            /*"list":"rs/menu/list",*/
            "update":"rs/menu/update",
            "delete":"rs/menu/delete"
        }
    }
})(jQuery);