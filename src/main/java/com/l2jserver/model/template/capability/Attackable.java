package com.l2jserver.model.template.capability;

import com.l2jserver.model.template.Template;
import com.l2jserver.model.world.capability.Attacker;

/**
 * Defines an {@link Template template} {@link TemplateCapability capability}
 * that an {@link Attacker} can use to attack an
 * {@link com.l2jserver.model.world.capability.Attackable}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public interface Attackable extends TemplateCapability {
	void attack(Attacker source,
			com.l2jserver.model.world.capability.Attackable target);

	int getPhysicalDamage();

	int getMagicalDamage();
}
