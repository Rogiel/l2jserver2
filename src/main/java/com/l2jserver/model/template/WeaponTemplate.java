package com.l2jserver.model.template;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.template.capability.Attackable;
import com.l2jserver.model.world.Item;

/**
 * Template for Weapon {@link Item}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class WeaponTemplate extends ItemTemplate implements Attackable {
	public WeaponTemplate(ItemTemplateID id) {
		super(id);
	}
}
