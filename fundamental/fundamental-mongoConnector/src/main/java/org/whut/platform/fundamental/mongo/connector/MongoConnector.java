package org.whut.platform.fundamental.mongo.connector;

import com.mongodb.*;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
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
    private String dbName;
    private String collectionName;

    static {
        String host = FundamentalConfigProvider.get("mongo.host");
        String port = FundamentalConfigProvider.get("mongo.port");
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

    public static DB getDB(String dbName){
        return mongo.getDB(dbName);
    }



    //传感器数据舒服构造函数
    public MongoConnector(String dbName, String collectionName){
        this.dbName = dbName;
        this.collectionName = collectionName;
        mongo = MongoConnector.getMongo();
    }

    //保存文档对象
    public String insertDocument(String documentJSON){
        DB db = mongo.getDB(dbName);
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
        DB db = mongo.getDB(dbName);
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
        DB db = mongo.getDB(dbName);
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
        DB db = mongo.getDB(dbName);
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
    //取根据每一类来找出某一列的值，用来算最大值
    public List<String> getOneColumnByEquipmentVariety(String column,String equipmentVariety){
        DB db = mongo.getDB(dbName);
        DBCollection collection = db.getCollection(collectionName);
        DBCursor dbCursor=collection.find();
        List<String> list=new ArrayList<String>();
        while (dbCursor.hasNext()){
               List<DBObject> d=(ArrayList<DBObject>)dbCursor.next().get("craneinspectreports");
               for(DBObject dd:d){
                   if(dd.get("equipmentVariety").equals(equipmentVariety)){
                       list.add((String)dd.get(column));
                   }
               }
        }
        return list;
    }
}
