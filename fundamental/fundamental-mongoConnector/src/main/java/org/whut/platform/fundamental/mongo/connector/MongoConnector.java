package org.whut.platform.fundamental.mongo.connector;

import com.mongodb.*;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.exception.BusinessException;
import org.whut.platform.fundamental.logger.PlatformLogger;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 13-11-1
 * Time: 下午5:54
 * To change this template use File | Settings | File Templates.
 */
public class MongoConnector {

    private static PlatformLogger logger = PlatformLogger.getLogger(MongoConnector.class);

    private static Mongo mongo;
    private static String user;
    private static String password;
    private String dbName;
    private String collectionName;

    static {
        String host = FundamentalConfigProvider.get("mongo.host");
        String port = FundamentalConfigProvider.get("mongo.port");
        user = FundamentalConfigProvider.get("mongo.user");
        password = FundamentalConfigProvider.get("mongo.password");
        try {
            mongo = new Mongo(host,Integer.parseInt(port));
            logger.info("mongo is initialized,host is "+host+" ,port is "+port);
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static Mongo getMongo(){
        return mongo;
    }

    //传感器数据舒服构造函数
    public MongoConnector(String dbName, String collectionName){
        this.dbName = dbName;
        this.collectionName = collectionName;
        mongo = MongoConnector.getMongo();
    }

    //保存文档对象
    public String insertDocument(String documentJSON){
        DB db = getDB(dbName);
        DBCollection collection = db.getCollection(collectionName);
        DBObject document = (DBObject) JSON.parse(documentJSON);
        WriteResult result = collection.insert(document);
        Object objectId = document.get("_id");
        if(objectId!=null){
            return ((ObjectId)objectId).toStringMongod();
        }
        return null;
    }

    public String insertDocumentObject(DBObject documentObject){
        DB db = getDB(dbName);
        DBCollection collection = db.getCollection(collectionName);
        WriteResult result = collection.insert(documentObject);
        Object objectId = documentObject.get("_id");
        if(objectId!=null){
            return ((ObjectId)objectId).toStringMongod();
        }
        return null;
    }

    //获取文档对象
    public DBObject getDocument(String objectID){
        if(objectID==null){
            throw new IllegalArgumentException("object id is null");
        }
        DB db = getDB(dbName);
        DBCollection collection = db.getCollection(collectionName);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(objectID));
        DBObject document = collection.findOne(query);
        return document;
    }

    //获取文档内的数据数组
    public ArrayList getDocumentData(String objectID){
        DBObject dbObject = getDocument(objectID);
        ArrayList data = (ArrayList)dbObject.get("data");
        return data;
    }
    //根据reportNumber来提取某一条记录
    public DBObject  getDBObjectByReportNumber(String reportNuumber){
        DB db = getDB(dbName);
        DBCollection collection = db.getCollection(collectionName);
        DBCursor dbCursor=collection.find();
        while (dbCursor.hasNext()){
            List<DBObject> d=(ArrayList<DBObject>)dbCursor.next().get("craneinspectreports");
            for(DBObject dd:d){
                if(dd.get("reportnumber").equals(reportNuumber)){
                    return dd;
                };
            }
        }
        return null;
    }

    //根据reportNumber来提取某一条记录
    public List<DBObject>  getInspectItemRecordByMongoId(String mongoId){
        DBObject document = getDocument(mongoId);
        List<DBObject> list= (List<DBObject>)document.get("inspectitemrecords");
        return list;
    }

    //取根据每一类来找出某一列的值，用来算最大值
    public List<String> getOneColumnByEquipmentVariety(String column,List<String> equipmentVariety){
        DB db = getDB(dbName);
        DBCollection collection = db.getCollection(collectionName);
        DBCursor dbCursor=collection.find();
        List<String> list=new ArrayList<String>();
        while (dbCursor.hasNext()){
            List<DBObject> d=(ArrayList<DBObject>)dbCursor.next().get("craneinspectreports");
            for(String equipment:equipmentVariety){
                for(DBObject dd:d){
                    if(dd.get("equipmentvariety").equals(equipment)){
                        list.add((String)dd.get(column));
                    }
                }
            }
        }
        return list;
    }
    public DBObject getMaxValueByCraneType(String craneTypeId){
        DB db = getDB(dbName);
        DBCollection collection = db.getCollection(collectionName);
        DBCursor dbCursor=collection.find();
        while (dbCursor.hasNext()){
            List<DBObject> d=(ArrayList<DBObject>)dbCursor.next().get("maxValue");
            for(DBObject dd:d){
                if(dd.get("typeId").equals(craneTypeId)){
                    return dd;
                };
            }
        }
        return null;
    }
    public List<DBObject> getDbArrayListFromMongo1(){
        //从mongo中拿出所有的记录
        DB db = getDB(dbName);
        DBCollection collection = db.getCollection(collectionName);
        DBCursor dbCursor=collection.find();
        List<List<DBObject>> dd=new ArrayList<List<DBObject>>();
        List<DBObject> d=new ArrayList<DBObject>();
        while (dbCursor.hasNext()){
            d=(ArrayList<DBObject>)dbCursor.next().get("craneinspectreports");
            dd.add(d);
        }
        return d;
    }
    public List<List<DBObject>> getDbArrayListFromMongo2(String sTime,String eTime,String sensorNum){
        //从mongo中拿出所有的记录
        DB db = getDB(dbName);
        DBCollection collection = db.getCollection(collectionName);

        BasicDBObject query =new BasicDBObject();
        query.put("time", new BasicDBObject("$gt", sTime).append("$lt", eTime));
        query.put("sensorNum", sensorNum);
        DBCursor dbCursor = collection.find(query);

        List<List<DBObject>> dd=new ArrayList<List<DBObject>>();
        List<DBObject> d=new ArrayList<DBObject>();
        while (dbCursor.hasNext()){
            d=(ArrayList<DBObject>)dbCursor.next().get("data");
            dd.add(d);
        }
        return dd;
    }

    public List<List<DBObject>> getDbArrayLastListFromMongo(){
        //从mongo中拿出所有的记录
        DB db = getDB(dbName);
        DBCollection collection = db.getCollection(collectionName);
        BasicDBObject query =new BasicDBObject();
        DBCursor dbCursor = collection.find();
        List<List<DBObject>> dd=new ArrayList<List<DBObject>>();
        List<DBObject> d=new ArrayList<DBObject>();
        while (dbCursor.hasNext()){
            d=(ArrayList<DBObject>)dbCursor.next().get("data");
        }
        dd.add(d);
        return dd;
    }
    public List<List<DBObject>> getDbArrayLastListFromMongo(String num){
        //从mongo中拿出所有的记录
        DB db = getDB(dbName);
        DBCollection collection = db.getCollection(collectionName);
        BasicDBObject query =new BasicDBObject();
        query.put("sensorNum", num);
        DBCursor dbCursor = collection.find(query);
        List<List<DBObject>> dd=new ArrayList<List<DBObject>>();
        List<DBObject> d=new ArrayList<DBObject>();
        while (dbCursor.hasNext()){
            d=(ArrayList<DBObject>)dbCursor.next().get("data");
        }
        dd.add(d);
        return dd;
    }
    public List<List<DBObject>> getDbArrayLastListFromMongo2(){
        //从mongo中拿出所有的记录
        DB db = getDB(dbName);
        DBCollection collection = db.getCollection(collectionName);
        BasicDBObject query =new BasicDBObject();
        DBCursor dbCursor = collection.find();
        List dd=new ArrayList();
        Object list=null;
        while (dbCursor.hasNext()){
            list=dbCursor.next().get("time");
        }
        dd.add(list);
        return dd;
    }
    public List<List<DBObject>> getDbArrayLastListFromMongo2(String num){
        //从mongo中拿出所有的记录
        DB db = getDB(dbName);
        DBCollection collection = db.getCollection(collectionName);
        BasicDBObject query =new BasicDBObject();
        query.put("sensorNum", num);
        DBCursor dbCursor = collection.find(query);
        List dd=new ArrayList();
        Object list=null;
        while (dbCursor.hasNext()){
            list=dbCursor.next().get("time");
        }
        dd.add(list);
        return dd;
    }
    public List<List<DBObject>> getDbArrayListFromMongo3(String sTime,String eTime,String sensorNum){
        //从mongo中拿出所有的记录
        DB db = getDB(dbName);
        DBCollection collection = db.getCollection(collectionName);
//      DBCursor dbCursor=collection.find();

        BasicDBObject query =new BasicDBObject();

        query.put("time", new BasicDBObject("$gt", sTime).append("$lt", eTime));
        query.put("sensorNum", sensorNum);
        DBCursor dbCursor = collection.find(query);
        // DBCursor dbCursor = collection.find({"time": { "$gte":sTime ,"$lt":eTime} ,"sensorNum":sensorNum});
        List dd=new ArrayList();
        Object list;
        while (dbCursor.hasNext()){
            list=dbCursor.next().get("time");
            dd.add(list);
        }
        return dd;
    }
    public List<List<DBObject>> getDbArrayListFromMongo(){
        //从mongo中拿出所有的记录
        DB db = getDB(dbName);
        DBCollection collection = db.getCollection(collectionName);
        DBCursor dbCursor=collection.find();
        List<List<DBObject>> dd=new ArrayList<List<DBObject>>();
        List<DBObject> d=new ArrayList<DBObject>();
        while (dbCursor.hasNext()){
            d=(ArrayList<DBObject>)dbCursor.next().get("craneinspectreports");
            dd.add(d);
        }
        return dd;
    }
    public void dropCollection(){
        DB db = getDB(dbName);
        DBCollection collection = db.getCollection(collectionName);
        collection.drop();
    }

    //根据查询条件返回文档数组
    public List<DBObject> getDocumentList(DBObject query){
        if(query==null){
            throw new IllegalArgumentException("object id is null");
        }
        DB db = getDB(dbName);
        DBCollection collection = db.getCollection(collectionName);
        return collection.find(query).toArray();
    }

    public static DB getDB(String dbName){
        if(mongo!=null){
            DB db = mongo.getDB(dbName);
            if(user!=null&&password!=null){
                if(db.authenticate(user,password.toCharArray())){
                    return db;
                }else{
                    throw new BusinessException(new Exception("mongo's user and password auth fail!"));
                }
            }
            return db;
        }
        return null;
    }
}
