(function($){
    var baseUrl="http://localhost:8080/realtimeMonitor/";
    $.URL = {
        "power":{
            "add":baseUrl+"rs/power/add",
            "update":baseUrl+"rs/power/update",
            "delete":baseUrl+"rs/power/delete",
            "list":baseUrl+"rs/power/list"
        },
        "user":{
            "add":baseUrl+"rs/user/add",
            "update":baseUrl+"rs/user/update",
            "delete":baseUrl+"rs/user/delete",
            "list":baseUrl+"rs/user/list",
            "getId":baseUrl+"rs/user/getIdByName",
            "currentUserId": "rs/user/currentUserId",
            "currentUserInfo":baseUrl+"rs/user/currentUser" ,
            "keepAlive":baseUrl+"rs/user/keepAlive"
        },
        "authority":{
             "add":baseUrl+"rs/authority/add",
             "update":baseUrl+"rs/authority/update",
             "delete":baseUrl+"rs/authority/delete",
             "list":baseUrl+"rs/authority/list"
        },
        "userAuthority":{
              "add":baseUrl+"rs/userAuthority/add"
        },
        "area":{
            "add":baseUrl+"rs/area/add",
            "list":baseUrl+"rs/area/list",
            "update":baseUrl+"rs/area/update",
            "getAreaListByGroupName":baseUrl+"rs/area/getAreaListByGroupName",
            "getAreaNameListByAppId":baseUrl+"rs/area/getAreaNameListByAppId" ,
            "getAreaByGroupId":baseUrl+"rs/area/getAreaByGroupId"
        },
        "group":{
            "add":baseUrl+"rs/group/add",
            "list":baseUrl+"rs/group/getList",
            "update":baseUrl+"rs/group/update",
            "delete":baseUrl+"rs/group/delete",
            "getIdByGroupName":baseUrl+"rs/group/getIdByGroupName"
        },
        "collector":{
            "add":baseUrl+"rs/collector/add",
            "list":baseUrl+"rs/collector/list",
            "update":baseUrl+"rs/collector/update",
            "delete":baseUrl+"rs/collector/delete",
            "getAreaNames":baseUrl+"rs/collector/getAreaNames",
            "getCollectorByAreaId":baseUrl+"rs/collector/getCollectorByAreaId",
            "getCollectorByGroupId":baseUrl+"rs/collector/getCollectorByGroupId",
            "getCollectorNameByAppId":baseUrl+"rs/collector/getCollectorNameByAppId" ,
            "getListByAppId": "rs/collector/rs/collector/getListByAppId"
        },
        "sensor":{
            "add":baseUrl+"rs/sensor/add" ,
            "list":baseUrl+"rs/sensor/list",
            "update":baseUrl+"rs/sensor/update",
            "delete":baseUrl+"rs/sensor/delete",
            "homePage":baseUrl+"rs/sensor/homePageList",
            "updateWarnCount":baseUrl+"rs/sensor/updateWarnCount",
            "getMongoDataList":baseUrl+"rs/sensor/getMongoDataList",
            "getMongoDataListInJson":baseUrl+"rs/sensor/getMongoDataListInJson",
            "getMongoDataLastList":baseUrl+"rs/sensor/getMongoDataLastList",
            "getMongoDataLastTimeByNumber":baseUrl+"rs/sensor/getMongoDataLastTimeByNumber",
            "getMongoDataLastListByNumber":baseUrl+"rs/sensor/getMongoDataLastListByNumber",
            "getSensorsByAreaId":baseUrl+"rs/sensor/getSensorsByAreaId",
            "getSensorsByAreaName":baseUrl+"rs/sensor/getSensorsByAreaName",
            "getListByGroupName":baseUrl+"rs/sensor/getListByGroupName",
            "getListByGroupAreaAndMonitor":baseUrl+"rs/sensor/getListByGroupAreaAndMonitor",
            "getNumberBySensorId":baseUrl+"rs/sensor/getNumberBySensorId",
            "getSensorIdAndNumbersByName":baseUrl+"rs/sensor/getSensorIdAndNumbersByName",
            "getCollectorNameBySensorNumber":baseUrl+"rs/sensor/getCollectorNameBySensorNumber",
            "getDataTypeByIdAndAppId":baseUrl+"rs/sensor/getDataTypeByIdAndAppId"
        },
        "sensors":{
            "getSensorDataArray":baseUrl+"rs/sensors/sensor/data/",
            "getWarnCondition":baseUrl+"rs/sensors/sensor/warnCondition/" ,
            "getCollectorStatus":baseUrl+"rs/sensors/sensor/collectorStatus/"
        },
        "warnCondition":{
            "add":baseUrl+"rs/warnCondition/add",
            "list":baseUrl+"rs/warnCondition/list"
        } ,
        "report":{
            "query":baseUrl+"rs/report/query/"
        } ,
        "websocket":{
            "register":"ws://localhost:8080/realtimeMonitor/websocket/hello"
        }
    }
})(jQuery);