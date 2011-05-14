package com.l2jserver.model.template;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.world.Item;

/**
 * Template for Potion {@link Item}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class PotionTemplate extends ConsumableTemplate {
	public PotionTemplate(ItemTemplateID id) {
		super(id);
	}
}
