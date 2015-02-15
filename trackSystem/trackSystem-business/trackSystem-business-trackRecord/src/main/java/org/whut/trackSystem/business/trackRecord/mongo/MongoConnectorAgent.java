package org.whut.trackSystem.business.trackRecord.mongo;

import com.mongodb.*;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.mongo.connector.MongoConnector;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-2-14
 * Time: 下午2:59
 * To change this template use File | Settings | File Templates.
 */
public class MongoConnectorAgent {
    private static MongoConnector mongoConnector;

    public MongoConnectorAgent(String dbName,String collectorName) {
        mongoConnector = new MongoConnector(dbName,collectorName);
    }

    public String insertDocument(String documentJSON) {
        return mongoConnector.insertDocument(documentJSON);
    }

    public String insertDocumentObject(DBObject documentObject) {
        return mongoConnector.insertDocumentObject(documentObject);
    }

    public DBObject getDocument(String objectID) {
        return mongoConnector.getDocument(objectID);
    }

    public List<DBObject> getDocumentList(DBObject query) {
        return mongoConnector.getDocumentList(query);
    }

    public Mongo getMongo() {
        return mongoConnector.getMongo();
    }

    public DBCursor getCursor(DBObject query) {
        Mongo mongo = getMongo();
        DB db = mongo.getDB(FundamentalConfigProvider.get("device.mongo.deviceDB"));
        DBCollection collection = db.getCollection(FundamentalConfigProvider.get("device.mongo.deviceCollection"));
        DBCursor cursor = collection.find(query);
        return cursor;
    }
}
