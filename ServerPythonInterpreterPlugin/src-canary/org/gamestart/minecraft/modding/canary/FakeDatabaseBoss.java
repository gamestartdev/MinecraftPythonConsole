package org.gamestart.minecraft.modding.canary;

import java.util.LinkedHashMap;
import java.util.Map;

import net.canarymod.Canary;
import net.canarymod.api.inventory.ItemType;

import org.gamestart.minecraft.modding.database.IDatabaseBoss;

public class FakeDatabaseBoss implements IDatabaseBoss {
	private Map<String,Map<ItemType,String>> spellsPerItemPerUser = new LinkedHashMap<String,Map<ItemType,String>>();
public FakeDatabaseBoss(){
	LinkedHashMap<ItemType, String> mappie = new LinkedHashMap<ItemType, String>();
	mappie.put(ItemType.IronAxe, "setblock(myX(),myY(),myZ())");
	spellsPerItemPerUser.put("sapphon", mappie);
}
	public String getUserSpellForItem(ItemType itemType, String techMageUserName) {
		if (spellsPerItemPerUser.containsKey(techMageUserName)){
			if(spellsPerItemPerUser.get(techMageUserName).containsKey(itemType)){
				return spellsPerItemPerUser.get(techMageUserName).get(itemType);
			}
			else{
				Canary.getServer().broadcastMessage("No spells attached to item " + itemType);
			}
		}else{
			Canary.getServer().broadcastMessage("No spells for player " + techMageUserName);
		}
		return "";
	}

}
