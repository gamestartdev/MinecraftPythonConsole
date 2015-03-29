package org.gamestart.minecraft.modding.database;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MongoDBAccessorFactory {
	
	public static MongoDBAccessor manufacture(String hostName, int portNumber, String databaseUserName, String databasePassword, String databaseName){
		List<MongoCredential> accessCredentials = new ArrayList<MongoCredential>();
//		accessCredentials.add(MongoCredential.createCredential(databaseUserName, databaseName, databasePassword.toCharArray()));
		return new MongoDBAccessor(new ServerAddress(hostName, portNumber), accessCredentials, databaseName);
	}
}
