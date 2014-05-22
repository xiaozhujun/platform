(function($){
    $.URL = {
        "apppower":{
            "add":"rs/apppower/add",
            "update":"rs/apppower/update",
            "delete":"rs/apppower/delete",
            "list":"rs/apppower/list"
        },
       /* "appauthority":{
            "add":"rs/appauthority/add"
        },*/
        "appuser":{
            "add":"rs/appuser/add",
            "update":"rs/appuser/update",
            "delete":"rs/appuser/delete",
            "list":"rs/appuser/list",
            "getId":"rs/appuser/getIdByName"
        },
        "appauthority":{
             "add":"rs/appauthority/add",
             "update":"rs/appauthority/update",
             "delete":"rs/appauthority/delete",
            "list":"rs/appauthority/list"
        },
        "appuserappauthority":{
              "add":"rs/appuserappauthority/add"
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