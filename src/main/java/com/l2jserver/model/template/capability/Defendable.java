package com.l2jserver.model.template.capability;

import com.l2jserver.model.template.Template;
import com.l2jserver.model.world.capability.Attacker;

/**
 * Defines an {@link Template template} {@link TemplateCapability capability}
 * that an {@link com.l2jserver.model.world.capability.Attackable} can use to
 * defend from an {@link Attacker}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Defendable extends TemplateCapability {
	void defend(Attacker source,
			com.l2jserver.model.world.capability.Attackable target);

	int getPhysicalDefense();

	int getMagicalDefense();
}
