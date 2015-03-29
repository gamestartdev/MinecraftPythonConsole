package org.gamestart.minecraft.modding.database;

import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;

public class MongoDBAccessor {
	private MongoClient mongoClient;
	private String databaseName;

	public MongoDBAccessor(ServerAddress address, List<MongoCredential> credentialList, String databaseName){
		this.databaseName = databaseName;
		mongoClient = new MongoClient(address,credentialList);
	}
	
	public Document getDocumentFromCollection(String collectionName, Map<String,Object> documentMatchCriteria){
		BasicDBObject filterObject = new BasicDBObject(documentMatchCriteria);
		Document documentFound = getCollectionInDatabase(databaseName, collectionName).find(filterObject).first();
		if(documentFound == null){
			System.out.println("Returning error document");
			return new ErrorDocument();
		}
		else return documentFound;
	}

	private MongoCollection<Document> getCollectionInDatabase(
			String databaseName, String collectionName) {
		return mongoClient.getDatabase(databaseName).getCollection(collectionName);
	}
	
	public void putDocumentIntoCollectionIfNotExtant(Document documentToAdd, String collectionName){
		MongoCollection<Document> collectionIntoWhichWeWillInsert = getCollectionInDatabase(databaseName, collectionName);
		collectionIntoWhichWeWillInsert.insertOne(documentToAdd);
	}
	
	public void cleanup(){
		mongoClient.close();
	}
}
