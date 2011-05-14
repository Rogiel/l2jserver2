package com.l2jserver.model.template.item;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.template.capability.Stackable;
import com.l2jserver.model.world.Item;

/**
 * Template for Potion {@link Item}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class PotionTemplate extends ConsumableTemplate implements
		Stackable<Item> {
	public PotionTemplate(ItemTemplateID id, String icon) {
		super(id, icon, ItemMaterial.LIQUID);
	}
}
