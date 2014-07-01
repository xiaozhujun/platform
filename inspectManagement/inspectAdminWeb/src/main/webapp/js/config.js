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
            "getId":"rs/user/getIdByName" ,
            "currentUserId": "rs/user/currentUserId"
        },
        "appauthority":{
             "add":"rs/authority/add",
             "update":"rs/authority/update",
             "delete":"rs/authority/delete",
            "list":"rs/authority/list",
            "show":"rs/authority/show"
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
            "addParentMenu" :"rs/menu/addParentMenu" ,
            "addSonMenu" :"rs/menu/addSonMenu" ,
            /*"list":"rs/menu/list",*/
            "update":"rs/menu/update",
            "fsupdate":"rs/menu/fsupdate",
            "delete":"rs/menu/delete",
            "list":"rs/menu/list",
            "getParentInfoById": "rs/menu/getParentInfoById"  ,
            "getMenuByUserId": "rs/menu/getMenuByUserId"

        },
        "authoritymenu" :{
            "add" :"rs/authoritymenu/add" ,
            "list":"rs/authoritymenu/list",
            "update":"rs/authoritymenu/update",
            "delete":"rs/authoritymenu/delete"
        }
    }
})(jQuery);