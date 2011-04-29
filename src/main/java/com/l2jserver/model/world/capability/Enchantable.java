package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;

/**
 * Defines an {@link AbstractObject} that can be enchanted.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Enchantable extends ObjectCapability {
	/**
	 * Get the current enchant level in the object
	 * 
	 * @return the enchant level
	 */
	int getEnchantLevel();

	/**
	 * Set the new enchant level in the object
	 * 
	 * @param level
	 *            the enchant level
	 */
	void setEnchantLevel(int level);
}
