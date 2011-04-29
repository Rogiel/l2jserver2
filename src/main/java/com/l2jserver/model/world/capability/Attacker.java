package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;

/**
 * Defines an {@link AbstractObject} that can attack an {@link Attackable}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Attacker extends ObjectCapability {
	void attack(Attackable target,
			com.l2jserver.model.template.capability.Attackable weapon);
}
