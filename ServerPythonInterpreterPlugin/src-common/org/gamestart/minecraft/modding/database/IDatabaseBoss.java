package org.gamestart.minecraft.modding.database;

import net.canarymod.api.inventory.ItemType;

public interface IDatabaseBoss {
	public String getUserSpellForItem(ItemType itemType, String techMageUserName);
}
