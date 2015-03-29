package org.gamestart.minecraft.modding.database.factories;

import org.gamestart.minecraft.modding.canary.FakeDatabaseBoss;
import org.gamestart.minecraft.modding.database.IDatabaseBoss;
import org.gamestart.minecraft.modding.database.MongoDBAccessor;
import org.gamestart.minecraft.modding.database.MongoDBBoss;

public class DBBossFactory {
	public static IDatabaseBoss manufactureMongoDBBoss(MongoDBAccessor accessor){
		return new MongoDBBoss(accessor);
	}
	
	public static IDatabaseBoss manufactureFakeMongoDBBossForTesting(){
		return new FakeDatabaseBoss();
	}
}
