package com.l2jserver.model.template.item;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.model.world.Item;

/**
 * Template for harvest {@link Item}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class HarvestTemplate extends ItemTemplate {
	public HarvestTemplate(ItemTemplateID id) {
		super(id);
	}
}
