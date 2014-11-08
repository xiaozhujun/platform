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
            "list":"rs/area/list",
            "update":"rs/area/update",
            "getAreaNameListByAppId":"rs/area/getAreaNameListByAppId" ,
            "getAreaByGroupId":"rs/area/getAreaByGroupId"
        },
        "group":{
            "add":"rs/group/add",
            "list":"rs/group/getList",
            "update":"rs/group/update",
            "delete":"rs/group/delete",
            "getIdByGroupName":"rs/group/getIdByGroupName"
        },
        "collector":{
            "add":"rs/collector/add",
            "list":"rs/collector/list",
            "update":"rs/collector/update",
            "delete":"rs/collector/delete",
            "getAreaNames":"rs/collector/getAreaNames",
            "getCollectorByAreaId":"rs/collector/getCollectorByAreaId",
            "getCollectorByGroupId":"rs/collector/getCollectorByGroupId",
            "getCollectorNameByAppId":"rs/collector/getCollectorNameByAppId" ,
            "getListByAppId": "rs/collector/rs/collector/getListByAppId"
        },
        "sensor":{
            "add":"rs/sensor/add" ,
            "list":"rs/sensor/list",
            "update":"rs/sensor/update",
            "delete":"rs/sensor/delete",
            "homePage":"rs/sensor/homePageList",
            "updateWarnCount":"rs/sensor/updateWarnCount",
            "getMongoDataList":"rs/sensor/getMongoDataList",
            "getMongoDataListInJson":"rs/sensor/getMongoDataListInJson",
            "getMongoDataLastList":"rs/sensor/getMongoDataLastList",
            "getMongoDataLastTimeByNumber":"rs/sensor/getMongoDataLastTimeByNumber",
            "getMongoDataLastListByNumber":"rs/sensor/getMongoDataLastListByNumber",
            "getSensorsByAreaId":"rs/sensor/getSensorsByAreaId",
            "getListByGroupAreaAndMonitor":"rs/sensor/getListByGroupAreaAndMonitor",
            "getNumberBySensorId":"rs/sensor/getNumberBySensorId",
            "getSensorIdAndNumbersByName":"rs/sensor/getSensorIdAndNumbersByName",
            "getCollectorNameBySensorNumber":"rs/sensor/getCollectorNameBySensorNumber",
            "getDataTypeByIdAndAppId":"rs/sensor/getDataTypeByIdAndAppId",
            "getDataTypeBySNumAndAppId":"rs/sensor/getDataTypeBySNumAndAppId"
        },
        "sensors":{
            "getSensorDataArray":"rs/sensors/sensor/data/",
            "getWarnCondition":"rs/sensors/sensor/warnCondition/" ,
            "getCollectorStatus":"rs/sensors/sensor/collectorStatus/"
        },
        "warnCondition":{
            "add":"rs/warnCondition/add",
            "list":"rs/warnCondition/list"
        } ,
        "report":{
            "query":"rs/report/query/"
        } ,
        "websocket":{
            "register":"ws://localhost:8080/realtimeMonitor/websocket/hello"
        }
    }
})(jQuery);