package com.l2jserver.model.template.item;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.world.Item;

/**
 * Template for consumable arrow {@link Item}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class ArrowTemplate extends ConsumableTemplate {
	public ArrowTemplate(ItemTemplateID id, String icon, ItemMaterial material) {
		super(id, icon, material);
	}
}
