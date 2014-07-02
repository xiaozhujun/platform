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
        "inspectTable":{
            "add":"rs/inspectTable/add",
            "getList":"rs/inspectTable/getList",
            "delete":"rs/inspectTable/delete",
            "update":"rs/inspectTable/update"
        },
        "inspectItem":{
            "add":"rs/inspectItem/add",
            "list":"rs/inspectItem/list",
            "update":"rs/inspectItem/update" ,
            "delete":"rs/inspectItem/delete",
            "getInspectAreaIdByNames":"rs/inspectItem/getInspectAreaIdByNames",
            "getInspectAreaNameByDeviceTypeNameAndAppId":"rs/inspectItem/getInspectAreaNameByDeviceTypeNameAndAppId"
        },
        "department":{
            "add":"rs/department/add",
            "list":"rs/department/list",
            "update":"rs/department/update",
            "delete":"rs/department/delete",
            "canUseList":"rs/department/canUseList"
        },
        "device":{
            "add":"rs/device/add" ,
            "list":"rs/device/list",
            "update":"rs/device/update",
            "delete":"rs/device/delete",
            "getId":"rs/device/getId",
            "getListByCondition":"rs/device/getListByCondition" ,
            "query":"rs/device/query" ,
            " getInfoByCondition" :"rs/device/getInfoByCondition"
        },
        "deviceType":{
            "add":"rs/deviceType/add",
            "update":"rs/deviceType/update",
            "delete":"rs/deviceType/delete" ,
            "list":"rs/deviceType/list",
            "getName":"rs/deviceType/getName"
        }  ,
        "inspectArea":{
            "add":"rs/inspectArea/add",
            "update":"rs/inspectArea/update",
            "delete":"rs/inspectArea/delete",
            "list":"rs/inspectArea/list"

        },
        "inspectTag":{
            "add":"rs/inspectTag/add",
            "update":"rs/inspectTag/update",
            "delete":"rs/inspectTag/delete",
            "list":"rs/inspectTag/list",
            "getId":"rs/inspectTag/getId"
        },
        "employee":{
            "add":"rs/employee/add",
            "update":"rs/employee/update",
            "delete":"rs/employee/delete",
            "list":"rs/employee/list",
            "listEmployeeRoleByNameAndRole":"rs/employee/listEmployeeRoleByNameAndRole",
            "listEmployeeByNameDepartmentAndRole":"rs/employee/listEmployeeByNameDepartmentAndRole"
        },
        "employeeRole":{
            "add":"rs/employeeRole/add",
            "update":"rs/employeeRole/update",
            "delete":"rs/employeeRole/delete",
            "canUseList":"rs/employeeRole/canUseList",
            "list":"rs/employeeRole/list"
        },
        "inspectChoice":{
            "add":"rs/inspectChoice/add",
            "getList":"rs/inspectChoice/getList",
            "update":"rs/inspectChoice/update" ,
            "delete":"rs/inspectChoice/delete",
            "getChoiceValues":"rs/inspectChoice/getChoiceValues"
        }     ,
        "menu" :{
            "addParentMenu" :"rs/menu/addParentMenu" ,
            "addSonMenu" :"rs/menu/addSonMenu" ,
            /*"list":"rs/menu/list",*/
            "update":"rs/menu/update",
            "delete":"rs/menu/delete",
            "list":"rs/menu/list",
            "getParentInfoById": "rs/menu/getParentInfoById"  ,
            "getMenuByUserId": "rs/menu/getMenuByUserId"
        },
        "inspectReport":{
            "reportSearch":"rs/inspectReport/reportSearch",
            "getInspectTableRecordList":"rs/inspectReport/getInspectTableRecordList",
            "getInspectInfo":"rs/inspectReport/getInspectInfo",
            "getInspectPeoplePie":"rs/inspectReport/getInspectPeoplePie",
            "getInspectDevicePie":"rs/inspectReport/getInspectDevicePie",
            "getInspectTableRecordGroupByEmployer":"rs/inspectReport/getInspectTableRecordGroupByEmployer",
            "getInspectTableRecordGroupByDevice":"rs/inspectReport/getInspectTableRecordGroupByDevice"
        },
        "taskPlan":{
            "add":"rs/taskPlan/add",
            "list":"rs/taskPlan/list",
            "update":"rs/taskPlan/update",
            "delete":"rs/taskPlan/delete",
            "query":"rs/taskPlan/query"
        }
    }
})(jQuery);