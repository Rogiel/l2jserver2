package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;

/**
 * Defines an {@link AbstractObject} that can be equipped into an
 * {@link Equiper}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Equipable extends ObjectCapability {
	void equip(Equiper equiper);
}
