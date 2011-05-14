package com.l2jserver.model.template.item;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.world.Item;

/**
 * Template for consumable Dye {@link Item}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class DyeTemplate extends ConsumableTemplate {
	public DyeTemplate(ItemTemplateID id, String icon, ItemMaterial material) {
		super(id, icon, material);
	}
}
