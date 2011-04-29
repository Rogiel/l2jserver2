package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;

/**
 * Defines an {@link AbstractObject} that can be equipped with {@link Equipable}
 * instances.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Equiper extends ObjectCapability {
	void equip(Equipable equipable);

	void setEquipment(Object slot, Equipable equipment);

	void getEquipment(Object slot);
}
