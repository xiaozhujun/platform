(function($){
    $.URL = {
        "power":{
            "add":"rs/power/add",
            "update":"rs/power/update",
            "delete":"rs/power/delete",
            "list":"rs/power/list"
        },
        "authority":{
            "add":"rs/authority/add"
        },
        "user":{
            "add":"rs/user/add",
            "update":"rs/user/update",
            "delete":"rs/user/delete",
            "list":"rs/user/list",
            "getId":"rs/user/getIdByName",
            "currentUserId": "rs/user/currentUserId",
            "currentUserInfo":"rs/user/currentUser"
        },
        "authority":{
             "add":"rs/authority/add",
             "update":"rs/authority/update",
             "delete":"rs/authority/delete",
             "list":"rs/authority/list"
        },
        "userAuthority":{
              "add":"rs/userAuthority/add"
        },
        "area":{
            "add":"rs/area/add",
            "list":"rs/area/list"
        },
        "group":{
            "add":"rs/group/add",
            "list":"rs/group/getList",
            "update":"rs/group/update",
            "delete":"rs/group/delete"
        },
        "collector":{
            "add":"rs/collector/add",
            "list":"rs/collector/list",
            "update":"rs/collector/update",
            "delete":"rs/collector/delete"
        }
    }
})(jQuery);