package com.l2jserver.model.template.item;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.model.template.capability.Consumable;
import com.l2jserver.model.world.Item;

/**
 * Template for consumable {@link Item}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class ConsumableTemplate extends ItemTemplate implements
		Consumable {
	public ConsumableTemplate(ItemTemplateID id, String icon,
			ItemMaterial material) {
		super(id, icon, material);
	}
}
