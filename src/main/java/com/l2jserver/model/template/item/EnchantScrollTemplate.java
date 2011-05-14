package com.l2jserver.model.template.item;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.world.Item;

/**
 * Template for consumable enchant scroll {@link Item}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class EnchantScrollTemplate extends ScrollTemplate {
	public EnchantScrollTemplate(ItemTemplateID id, String icon) {
		super(id, icon);
	}
}
