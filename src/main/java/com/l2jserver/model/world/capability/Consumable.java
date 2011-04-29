package com.l2jserver.model.world.capability;

import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.model.world.AbstractObject;

/**
 * Defines an {@link AbstractObject} that can be consumed.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Consumable extends ObjectCapability {
	void consume(ItemTemplate item, Castable target);
}
