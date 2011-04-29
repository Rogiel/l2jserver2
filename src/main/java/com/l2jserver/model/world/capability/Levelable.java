package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;

/**
 * Defines an {@link AbstractObject} that has levels.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Levelable extends ObjectCapability {
	/**
	 * Get this object`s level
	 * 
	 * @return the level
	 */
	int getLevel();

	/**
	 * Set this object's level
	 * 
	 * @param level
	 */
	void setLevel(int level);
}
