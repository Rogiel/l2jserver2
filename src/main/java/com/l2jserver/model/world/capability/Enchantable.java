package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;

/**
 * Defines an {@link AbstractObject} that can be enchanted.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Enchantable extends ObjectCapability {
	public int getEnchantLevel();

	public int setEnchantLevel();
}
