package com.l2jserver.model.template.item;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.model.world.Item;

/**
 * Template for seed {@link Item}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class SeedTemplate extends ItemTemplate {
	public SeedTemplate(ItemTemplateID id) {
		super(id);
	}
}
