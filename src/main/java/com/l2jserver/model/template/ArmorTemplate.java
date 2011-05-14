package com.l2jserver.model.template;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.template.capability.Defendable;
import com.l2jserver.model.template.capability.IncomingDamageIntercept;
import com.l2jserver.model.world.Item;

/**
 * Template for Armor {@link Item}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class ArmorTemplate extends ItemTemplate implements Defendable,
		IncomingDamageIntercept {
	public ArmorTemplate(ItemTemplateID id) {
		super(id);
	}
}
