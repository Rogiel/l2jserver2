package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;

/**
 * Defines an {@link AbstractObject} that can be damaged (HP)
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Damagable extends ObjectCapability {
	void receiveDamage(int damage);

	int getHP();

	void setHP(int hp);
}
