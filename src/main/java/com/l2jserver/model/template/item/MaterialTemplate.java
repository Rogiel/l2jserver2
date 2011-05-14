package com.l2jserver.model.template.item;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.model.world.Item;

/**
 * Template for material {@link Item}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class MaterialTemplate extends ItemTemplate {
	public MaterialTemplate(ItemTemplateID id) {
		super(id);
	}
}
