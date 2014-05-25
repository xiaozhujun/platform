package org.whut.monitor.report.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.whut.platform.fundamental.mongo.connector.MongoConnector;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-5-15
 * Time: 下午9:15
 * To change this template use File | Settings | File Templates.
 */
public class ReportService {


    private String sensorDB;
    private String sensorCollection;
    private MongoConnector mongoConnector;
    private ResourceBundle resourceBundle;

    //构造函数
    public ReportService(){
        resourceBundle = ResourceBundle.getBundle("mongo");
        if (resourceBundle == null) {
            throw new IllegalArgumentException("[mongo.properties] is not found!");
        }
        sensorDB = resourceBundle.getString("mongo.sensorDB");
        sensorCollection = resourceBundle.getString("mongo.sensorCollection");

        mongoConnector = new MongoConnector(sensorDB,sensorCollection);
    }

    //获得传感器当前最新的
    public DBObject getDocument(String documentId){
        return mongoConnector.getDocument(documentId);
    }

    //获得传感器当前数据数组
    public ArrayList getDocumentDataById(String sensorId){
        DBObject dbObject = getDocument(sensorId);
        if(dbObject==null){
            return null;
        }
        return (ArrayList)dbObject.get(resourceBundle.getString("mongo.field.sensor.data"));
    }

    //根据条件查询文档列表
    public List<DBObject> queryDocuments(HashMap<String,String> params){

        BasicDBObject query = new BasicDBObject();
        String key = null;
        if(params!=null){
           Iterator<String> it = params.keySet().iterator();
           while (it.hasNext()){
               key = it.next();
               query.put(key,params.get(key));
           }
        }
        return mongoConnector.getDocumentList(query);
    }

    //获取指定传感器在时间段内的数据
    public List<DBObject> queryDocuments(Date startTime,Date endTime,String sensorNum){
        BasicDBObject query = new BasicDBObject();
        if(startTime!=null){
            query.append("time",new BasicDBObject("$gte",startTime));
        }
        if(endTime!=null){
            query.append("time",new BasicDBObject("$lte",endTime));
        }
        if(sensorNum!=null){
            query.append("sensorNum",new BasicDBObject("$ne",sensorNum));
        }
        return mongoConnector.getDocumentList(query);
    }
}
