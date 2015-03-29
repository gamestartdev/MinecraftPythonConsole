package com.macuyiko.canaryconsole;

import java.io.IOException;

import org.gamestart.minecraft.modding.canary.ItemSwingSpellListener;
import org.gamestart.minecraft.modding.database.IDatabaseBoss;
import org.gamestart.minecraft.modding.database.MongoDBAccessor;
import org.gamestart.minecraft.modding.database.MongoDBAccessorFactory;
import org.gamestart.minecraft.modding.database.factories.DBBossFactory;

import com.macuyiko.minecraftpyserver.ConsolePlugin;
import com.macuyiko.minecraftpyserver.SetupUtils;

import net.canarymod.Canary;
import net.canarymod.plugin.Plugin;

public class MainPlugin extends Plugin {
	
	public static final MongoDBAccessor TEST_DB_ACCESSOR = MongoDBAccessorFactory.manufacture("localhost", 22715, "techmageserver", "kick2thegroin", "techmageservertest");


	public MainPlugin() {
		super();
	}
	
    @Override
    public boolean enable() {
    	getLogman().info("Loading Python Console");
    	try {
    		int tcpsocketserverport = getConfig().getInt("pythonconsole.serverconsole.telnetport", 44444);
    		int websocketserverport = getConfig().getInt("pythonconsole.serverconsole.websocketport", 44445);
    		String serverpass = getConfig().getString("pythonconsole.serverconsole.password", "swordfish");
    		getConfig().save();
    		SetupUtils.setup();
    		ConsolePlugin.start(this, tcpsocketserverport, websocketserverport, serverpass);
    		
        	logEnablingDatabaseConnection();
            IDatabaseBoss theBoss = DBBossFactory.manufactureMongoDBBoss(TEST_DB_ACCESSOR);
//        	IDatabaseBoss totallyFakeBossForTesting = new FakeDatabaseBoss();
            Canary.hooks().registerListener(new ItemSwingSpellListener(theBoss, getLogman()), this);
		} catch (IOException e) {
			getLogman().error(e.getMessage());
			return false;
		}
    	return true;
    }
    
	private void logEnablingDatabaseConnection() {
		getLogman().info("Enabling database connection for TechMage - version " + getVersion());
	}


	@Override
	public void disable() {}
    
}


