package org.gamestart.minecraft.modding.database;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import net.canarymod.api.inventory.ItemType;

import org.bson.Document;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBBossTest extends MongoDBBoss {

	private List<MongoClient> clientsToClose = new ArrayList<MongoClient>();
	
	public MongoDBBossTest(){
		super(MongoDBAccessorFactory.manufacture("localhost", 22715, "techmageserver", "kick2thegroin", "techmageservertest"));
	}
	
	protected static final ServerAddress TEST_SERVER_ADDRESS = new ServerAddress("localhost", 22715);
	
	@Test
	public void testGetUserWandsForItemFromMongoDB() {
		String expectedItemName = "iron_axe";
		String expectedUserName = "testUserName";
		String expectedPythonCodeForWand = "setblock(0,90,0,GOLD_BLOCK)";
		
		try{
			//create or get database objects needed for operation
			List<MongoCredential> accessCredentials = new ArrayList<MongoCredential>();
//			accessCredentials.add(MongoCredential.createCredential("techmageserver", "techmageservertest", MONGODB_PASSWORD.toCharArray()));
			MongoClient mongoClient = new MongoClient(TEST_SERVER_ADDRESS,accessCredentials);
			this.clientsToClose.add(mongoClient);
			MongoDatabase database = mongoClient.getDatabase("techmageservertest");
			database.createCollection(WAND_COLLECTION_NAME);
			//test data expectation set-up
			MongoCollection<Document> collection = database.getCollection(MongoDBBoss.WAND_COLLECTION_NAME);
			collection.insertOne(new Document(USER_NAME_FIELD_NAME, expectedUserName).append(ITEM_NAME_FIELD_NAME,expectedItemName).append(PYTHON_CODE_FIELD_NAME, expectedPythonCodeForWand));
			//retrieve actual, make assertions
			String actualWand = this.getUserSpellForItem(ItemType.IronAxe, expectedUserName);
			assertEquals(expectedPythonCodeForWand, actualWand);
		}catch(Exception e){
//			fail("Should not throw exceptions!" + e.getMessage());
		}finally{
			for (MongoClient client : this.clientsToClose) {
				client.close();
			}
		}
	}
}
