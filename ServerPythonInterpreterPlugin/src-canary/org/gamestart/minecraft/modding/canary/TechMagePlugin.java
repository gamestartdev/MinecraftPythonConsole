package org.gamestart.minecraft.modding.canary;

import org.gamestart.minecraft.modding.database.IDatabaseBoss;
import org.gamestart.minecraft.modding.database.MongoDBAccessorFactory;
import org.gamestart.minecraft.modding.database.factories.DBBossFactory;

import net.canarymod.Canary;
import net.canarymod.plugin.Plugin;

public class TechMagePlugin extends Plugin { 

    @Override
    public boolean enable() {
        IDatabaseBoss theBoss = DBBossFactory.manufactureMongoDBBoss(MongoDBAccessorFactory.manufacture("localhost", 22715, "techmageserver", "kick2thegroin", "techmageservertest"));
//    	IDatabaseBoss totallyFakeBossForTesting = new FakeDatabaseBoss();
        Canary.hooks().registerListener(new ItemSwingSpellListener(theBoss), this);
        getLogman().info("Enabling "+getName() + " Version " + getVersion());
        getLogman().info("Authored by "+getAuthor());
        return true;
    }

    @Override 
    public void disable() {   
    	getLogman().info("Disabling "+ getName() + " Version " + getVersion()); 
    } 

}