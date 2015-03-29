package org.gamestart.minecraft.modding.database;

import java.util.LinkedHashMap;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;

import org.bson.Document;

public class MongoDBBoss implements IDatabaseBoss{
	protected static final String WAND_COLLECTION_NAME = "spells";
	protected static final String PYTHON_CODE_FIELD_NAME = "pythonCode";
	protected static final String USER_NAME_FIELD_NAME = "userName";
	protected static final String ITEM_NAME_FIELD_NAME = "itemName";
	protected MongoDBAccessor accessor;
	
	public MongoDBBoss(MongoDBAccessor accessorToUse){
		this.accessor = accessorToUse;
	}
	
	public String getUserSpellForItem(ItemType itemType, String techMageUserName){
		LinkedHashMap<String, Object> documentMatchCriteria = new LinkedHashMap<String, Object>();
		documentMatchCriteria.put(USER_NAME_FIELD_NAME, techMageUserName);
		documentMatchCriteria.put(ITEM_NAME_FIELD_NAME, itemType.getMachineName());
		Document wandDocument = accessor.getDocumentFromCollection(WAND_COLLECTION_NAME, documentMatchCriteria);
		return wandDocument == null || wandDocument.get(PYTHON_CODE_FIELD_NAME) == null ? "" : wandDocument.get(PYTHON_CODE_FIELD_NAME).toString();
	}
	
	public String getUserSpellForItem(Item item, Player player){
		return this.getUserSpellForItem(item.getType(), player.getName());
	}
}