package org.gamestart.minecraft.modding.canary;

import net.canarymod.api.inventory.Item;
import net.canarymod.hook.HookHandler;
import net.canarymod.hook.player.PlayerArmSwingHook;
import net.canarymod.logger.Logman;
import net.canarymod.plugin.PluginListener;

import org.gamestart.minecraft.modding.database.IDatabaseBoss;
import org.python.util.PythonInterpreter;

import com.macuyiko.canaryconsole.CanaryParser;
import com.macuyiko.minecraftpyserver.ConsolePlugin;

public class ItemSwingSpellListener implements PluginListener {
	
	private IDatabaseBoss theBoss;
	private Logman logman;

	public ItemSwingSpellListener(IDatabaseBoss theBoss, Logman logman){
		this.theBoss = theBoss;
		this.logman = logman;
	}
	
    @HookHandler
    public void onSwingItem(PlayerArmSwingHook hook) {
        Item itemHeld = hook.getPlayer().getItemHeld();
        String playerName = hook.getPlayer().getDisplayName();
        String userSpellForItemFromMongoDB = theBoss.getUserSpellForItem(itemHeld.getType(), playerName);
        if(itemHeld != null && itemHeld.getType() != null){
        	if(userSpellForItemFromMongoDB != null && userSpellForItemFromMongoDB != ""){
        		executePythonFromPlayer(userSpellForItemFromMongoDB, playerName);
        	}       
        }
    }

	private void executePythonFromPlayer(String python, String playerName) {
		try{
			PythonInterpreter interpreter = new PythonInterpreter(
				null, ConsolePlugin.getPythonSystemState());
		logman.info("Executing python on behalf of " + playerName + ": " + python);
		CanaryParser.parse(interpreter, "from mcapi import *\n"+"player = getplayer('"+playerName+"')\n"+python,true);
		}catch(Exception e){
			logman.catching(e);
		}
	}
}
