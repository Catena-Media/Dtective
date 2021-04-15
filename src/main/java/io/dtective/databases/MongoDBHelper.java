package io.dtective.databases;

import io.dtective.configuration.ParameterMap;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.dtective.quality.framework.http.HttpStepsCore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MongoDBHelper {

    private static final Logger LOGGER = LogManager.getLogger(HttpStepsCore.class);
    private final MongoClient mongoClient;

    /**
     * Constructor to create MongoDB Connection which will be
     * either local or remote based on host param.
     */
    public MongoDBHelper() {
        String host = ParameterMap.getParamMongoDBHost();
        int port = ParameterMap.getParamMongoDBPort();
        if ((!host.contains("localhost")) && (!host.contains("127.0.0.1"))) {
            String user = ParameterMap.getParamMongoDBUser();
            String pass = ParameterMap.getParamMongoDBPass();
            MongoClientURI uri = new MongoClientURI("mongodb://"+ user +":"+ pass +"@"+ host +":"+ port +"/admin?ssl=true");
            mongoClient = new MongoClient(uri);
        } else {
            mongoClient = new MongoClient(host, port);
        }
    }

    /**
     * Method update a single mongo record.
     *
     * @param dbName database name.
     * @param collectionName collection name.
     * @param filterFieldName collection field name which will be used as search criteria.
     * @param filterValue value for the field filterFieldName which will be user as search criteria.
     * @param fieldName Name of the field which will have its value updated.
     * @param newValue Value of the field fieldName. This will be new value if the update is successful.
     */
    public void updateMongoDBRecord(String dbName, String collectionName, String filterFieldName,
                                    String filterValue, String fieldName, String newValue) {

        MongoDatabase db = mongoClient.getDatabase(dbName);
        MongoCollection<Document> collection = db.getCollection(collectionName);

        collection.updateOne(eq(filterFieldName, filterValue), set(fieldName, newValue));
    }

    /**
     * Method to import a single mongo DB record into the data base.
     * The file extension can be either .txt or .json.
     * In order to the endpoints properly return the values inserted the ids needs to be.
     * with the format "ObjectId("000000000000000000000002")".
     *
     * @param dbName database name.
     * @param collectionName collection name.
     * @param pathName path file name. It must be the absolute path.
     */
    public void importMongoObjectFileIntoCollection(String dbName, String collectionName, String pathName) {
        try {
            MongoDatabase db = mongoClient.getDatabase(dbName);

            //Read file content which will be inserted
            Scanner scanner = new Scanner(new File(pathName));
            String text = scanner.useDelimiter("\\A").next();

            //Delete Record which will be inserted
            deleteRecordByObjId(dbName, collectionName, returnObjectID(pathName));

            //Perform insert
            db.getCollection(collectionName).insertOne(Document.parse(text));

            scanner.close();

            LOGGER.info("Successfully imported into database: " + dbName + " collection: " + collectionName);

        } catch (Exception e) {
            LOGGER.info("Failed to import into database: " + dbName + " collection: " + collectionName);
            e.printStackTrace();
        }
    }

    /**
     * Method to delete a single MongoDB record
     *
     * @param dbName database name
     * @param collectionName collection name
     * @param objectId It should pass just the object value.
     */
    public void deleteRecordByObjId(String dbName, String collectionName, String objectId) {
        try {
            MongoDatabase db = mongoClient.getDatabase(dbName);
            db.getCollection(collectionName).deleteOne(new Document("_id", new ObjectId(objectId)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to check if a single Mongo DB record is present on the database
     *
     * @param dbName database name
     * @param collectionName collection name
     * @param map It should pass a map which is be used as search criteria.
     * @return 1 in case the search criteria found the record and 0 in case it couldn't find the anything.
     */
    public long searchRecord(String dbName, String collectionName, Map<String, String> map) {
        DB db = mongoClient.getDB(dbName);
        DBCollection coll = db.getCollection(collectionName);
        BasicDBObject query = new BasicDBObject();
        for (int i = 0; i < map.size(); i++) {
            query.append(map.keySet().toArray()[i].toString(), map.values().toArray()[i].toString());
        }
        DBObject document = coll.findOne(query);
        return document != null ? 1 : 0;
    }

    /**
     * Method to delete all records from a collection.
     * @param dbName database name.
     * @param collectionName collection name.
     */
    public void deleteAllDocumentsFromACollection(String dbName, String collectionName) {
        try {
            MongoDatabase db = mongoClient.getDatabase(dbName);
            db.getCollection(collectionName).deleteMany(new Document());
            LOGGER.info("Successfully deleted all the documents from database: " + dbName + " collection: " + collectionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to count the amount of records from a collection,
     * @param dbName database name.
     * @param collectionName collection name.
     */
    public long countAllDocumentsFromACollection(String dbName, String collectionName) {
        long count = 0;
        try {
            MongoDatabase db = mongoClient.getDatabase(dbName);
            count = db.getCollection(collectionName).countDocuments();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * Method to return the _id of a MongoDB file. Used as an auxiliary method.
     * @param filePath path to the file.
     */
    public String returnObjectID(String filePath) {
        String id = "";
        final int startIndex = 10;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                int begin = line.indexOf("\"", startIndex);
                int end = line.lastIndexOf("\"");
                if (line.contains("\"_id\"")) {
                    id = line.substring(begin + 1, end);
                }
            }
            return id;
        } catch (IOException e) {
            e.printStackTrace();
            return id;
        }
    }

    /**
     * Method to perform a search by key returning a string value.
     * @param dbName database name.
     * @param collectionName collection name.
     * @param key name of the field to perform the search.
     * @param value value of the field to perform the search.
     */
    public String getRecordFind(String dbName, String collectionName, String key, Object value) {
        DB db = mongoClient.getDB(dbName);
        DBCollection coll = db.getCollection(collectionName);

        BasicDBObject findObject = new BasicDBObject().append(key, value);
        BasicDBObject sortObject = new BasicDBObject().append("created_at", -1);

        DBCursor cur = coll.find(findObject).sort(sortObject).limit(1);
        DBObject obj = cur.one();

        if (obj != null) {
            org.bson.Document doc = org.bson.Document.parse(String.valueOf(obj));
            JsonWriterSettings relaxed = JsonWriterSettings.builder().outputMode(JsonMode.RELAXED).build();
            return String.valueOf(doc.toJson(relaxed));
        } else {
            return "";
        }
    }

    /**
     * Method to delete a single MongoDB record.
     * @param dbName database name.
     * @param collectionName collection name.
     * @param jsonPathField json path to the field which will be deleted.
     * @param value value of the field which will be deleted.
     */
    public void deleteRecord(String dbName, String collectionName, String jsonPathField, String value) {
        try {
            MongoDatabase db = mongoClient.getDatabase(dbName);
            if (jsonPathField.equals("_id")) {
                db.getCollection(collectionName).deleteOne(new Document(jsonPathField, new ObjectId(value)));
            } else {
                db.getCollection(collectionName).deleteOne(new Document(jsonPathField, value));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method return an MongoDB objectID.
     */
    public String generateObjectID() {
        return new ObjectId().toString();
    }

     /**
     * Method return an MongoDB objectID.
     * @param dbName database name.
     * @param collectionName collection name.
     * @param map map to be used as filter criteria.
     */
    public long searchRecord(String dbName, String collectionName, HashMap<String, Object> map) {
        DB db = mongoClient.getDB(dbName);
        DBCollection coll = db.getCollection(collectionName);
        BasicDBObject query = new BasicDBObject();

        for (int i = 0; i < map.size(); ++i) {
            query.append(map.keySet().toArray()[i].toString(), map.values().toArray()[i]);
        }

        DBObject document = coll.findOne(query);
        return document != null ? 1L : 0L;
    }
}
