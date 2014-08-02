package org.whut.monitor.business.report.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryOperators;
import com.mongodb.util.JSON;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.mongo.connector.MongoConnector;

import java.text.SimpleDateFormat;
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

    //根据json条件字符串查询文档列表
    public List<DBObject> queryDocuments(String params){

        DBObject query = (DBObject) JSON.parse(params);
        return mongoConnector.getDocumentList(query);
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
    public List<DBObject> queryDocuments(long startTime,long endTime,String sensorNum){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ArrayList<BasicDBObject> query = new ArrayList<BasicDBObject>();
        if(startTime!=0L){
            query.add(new BasicDBObject(FundamentalConfigProvider.get("monitor.mongo.field.sensor.time"), new BasicDBObject("$gte", startTime)));
        }
        if(endTime!=0L){
            query.add(new BasicDBObject(FundamentalConfigProvider.get("monitor.mongo.field.sensor.time"), new BasicDBObject("$lte", endTime)));
        }
        if(sensorNum!=null){
            query.add(new BasicDBObject(FundamentalConfigProvider.get("monitor.mongo.field.sensor.id"), sensorNum));
        }
        BasicDBObject condition = new BasicDBObject(QueryOperators.AND,query);
        return mongoConnector.getDocumentList(condition);
    }

    //获取指定传感器在时间段内的数据
    public List<DBObject> queryDocuments(String startTime,String endTime,String sensorNum){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ArrayList<BasicDBObject> query = new ArrayList<BasicDBObject>();
        if(startTime!=null){
            query.add(new BasicDBObject(FundamentalConfigProvider.get("monitor.mongo.field.sensor.time"), new BasicDBObject("$gte", startTime)));
        }
        if(endTime!=null){
            query.add(new BasicDBObject(FundamentalConfigProvider.get("monitor.mongo.field.sensor.time"), new BasicDBObject("$lte", endTime)));
        }
        if(sensorNum!=null){
            query.add(new BasicDBObject(FundamentalConfigProvider.get("monitor.mongo.field.sensor.id"), sensorNum));
        }
        BasicDBObject condition = new BasicDBObject(QueryOperators.AND,query);
        return mongoConnector.getDocumentList(condition);
    }
}
