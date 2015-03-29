package org.gamestart.minecraft.modding.canary;

import net.canarymod.api.inventory.Item;
import net.canarymod.hook.HookHandler;
import net.canarymod.hook.player.PlayerArmSwingHook;
import net.canarymod.plugin.PluginListener;

import org.gamestart.minecraft.modding.database.IDatabaseBoss;

public class ItemSwingSpellListener implements PluginListener {
	
	private IDatabaseBoss theBoss;

	public ItemSwingSpellListener(IDatabaseBoss theBoss){
		this.theBoss = theBoss;
	}
	
    @HookHandler
    public void onSwingItem(PlayerArmSwingHook hook) {
        Item itemHeld = hook.getPlayer().getItemHeld();
        if(itemHeld == null || itemHeld.getType() == null){
        }
        else{
        	String userSpellForItemFromMongoDB = theBoss.getUserSpellForItem(itemHeld.getType(), hook.getPlayer().getDisplayName());
        	if(userSpellForItemFromMongoDB != null && userSpellForItemFromMongoDB != ""){
        		//DO THE PYTHON HERE, HOWEVER SEPPE DOES THE PYTHON!
        	}       
        }
    }
}
