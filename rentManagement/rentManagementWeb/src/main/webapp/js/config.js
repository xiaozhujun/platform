(function($){
    $.URL = {
        "power":{
            "add":"rs/power/add",
            "update":"rs/power/update",
            "delete":"rs/power/delete",
            "list":"rs/power/list"
        },
        "user":{
            "add":"rs/user/add",
            "update":"rs/user/update",
            "delete":"rs/user/delete",
            "list":"rs/user/list",
            "getId":"rs/user/getIdByName" ,
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
        "contract":{
            "add":"rs/contract/add",
            "update":"rs/contract/update",
            "delete":"rs/contract/delete",
            "list":"rs/contract/list",
            "getContractById":"rs/contract/getContractById"
        },
        "customer":{
            "add":"rs/customer/add",
            "update":"rs/customer/update",
            "delete":"rs/customer/delete",
            "list":"rs/customer/list",
            "listCustomerByNameAndLinkman":"rs/customer/listCustomerByNameAndLinkman"
        },
        "supplier":{
            "add":"rs/supplier/add",
            "update":"rs/supplier/update",
            "delete":"rs/supplier/delete",
            "list":"rs/supplier/list",
            "listSupplierByNameAndLinkman":"rs/supplier/listSupplierByNameAndLinkman"
        },
        "installation":{
            "list":"rs/installation/list",
            "add":"rs/installation/add",
            "update":"rs/installation/update",
            "delete":"rs/installation/delete"
        },
        "employee":{
            "list":"rs/employee/list",
            "add":"rs/employee/add",
            "update":"rs/employee/update",
            "delete":"rs/employee/delete",
            "getDepartmentById":"rs/employee/getDepartmentById"
        },
        "department":{
            "add":"rs/department/add",
            "list":"rs/department/list",
            "update":"rs/department/update",
            "delete":"rs/department/delete"
        },
        "car_Driver":{
            "add":"rs/car_Driver/add",
            "list":"rs/car_Driver/list",
            "update":"rs/car_Driver/update",
            "delete":"rs/car_Driver/delete"
        },
        "device":{
            "add":"rs/device/add",
            "update":"rs/device/update",
            "delete":"rs/device/delete",
            "list":"rs/device/list",
            "getIdByNumber":"rs/device/getIdByNumber"
        },
        "contract":{
            "add":"rs/contract/add",
            "update":"rs/contract/update",
            "delete":"rs/contract/delete",
            "list":"rs/contract/list",
            "getContractById":"rs/contract/getContractById"
        },
        "deviceType":{
            "list":"rs/deviceType/list",
            "add":"rs/deviceType/add",
            "update":"rs/deviceType/update",
            "delete":"rs/deviceType/delete",
            "getIdByName":"rs/deviceType/getIdByName"
        },
        "stock_in_sheet":{
            "add":"rs/stock_in_sheet/add" ,
            "getList":"rs/stock_in_sheet/getList",
            "update":"rs/stock_in_sheet/update",
            "delete":"rs/stock_in_sheet/delete"
        },
        "stock_out_sheet":{
            "add" :"rs/stock_out_sheet/add" ,
            "list":"rs/stock_out_sheet/list" ,
            "delete": "rs/stock_out_sheet/delete" ,
            "update":"rs/stock_out_sheet/update"
        },
        "selfInspect":{
            "add":"rs/selfInspect/add" ,
            "update":"rs/selfInspect/update",
            "delete":"rs/selfInspect/delete" ,
            "list":"rs/selfInspect/list"
        },
        "remove":{
            "add":"rs/remove/add"  ,
            "update":"rs/remove/update",
            "delete":"rs/remove/delete" ,
            "list":"rs/remove/list"
        },
        "prebury":{
            "add":"rs/prebury/add"  ,
            "update":"rs/prebury/update",
            "delete":"rs/prebury/delete" ,
            "list":"rs/prebury/list"
        },
        "bad_Debt_Sheet":{
            "add":"rs/bad_Debt_Sheet/add",
            "update":"rs/bad_Debt_Sheet/update",
            "delete":"rs/bad_Debt_Sheet/delete",
            "list":"rs/bad_Debt_Sheet/list"
        },
        "skill":{
            "add":"rs/skill/add",
            "update":"rs/skill/update",
            "delete":"rs/skill/delete",
            "list":"rs/skill/list",
            "getSkillNameById":"rs/skill/getSkillNameById"
        },
        "storehouse":{
            "add" :"rs/storeHouse/add" ,
            "list":"rs/storeHouse/list" ,
            "delete": "rs/storeHouse/delete" ,
            "update":"rs/storeHouse/update",
            "getIdByNameAndAppId":"rs/storeHouse/getIdByNameAndAppId"
        },
        "dashboard":{
            "mainDeviceList":"rs/device/getMainDeviceList",
            "installationList":"rs/installation/getInstallList",
            "removeList":"rs/remove/getRemoveList"
        }
    }
})(jQuery);