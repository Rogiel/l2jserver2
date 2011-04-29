package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;

/**
 * Defines an {@link AbstractObject} that can be damaged (HP)
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Mana extends ObjectCapability {
	void consumeMana(int amount);

	int getMP();

	void setMP(int mana);
}
