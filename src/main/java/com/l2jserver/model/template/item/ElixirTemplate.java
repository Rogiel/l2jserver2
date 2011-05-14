package com.l2jserver.model.template.item;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.world.Item;

/**
 * Template for consumable elixir {@link Item}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class ElixirTemplate extends ConsumableTemplate {
	public ElixirTemplate(ItemTemplateID id, String icon, ItemMaterial material) {
		super(id, icon, material);
	}
}
