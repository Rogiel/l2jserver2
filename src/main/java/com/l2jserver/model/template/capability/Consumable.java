package com.l2jserver.model.template.capability;

import com.l2jserver.model.template.Template;
import com.l2jserver.model.world.capability.Attacker;

/**
 * Defines an {@link Template template} {@link TemplateCapability capability}
 * that can be consumed.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Consumable extends TemplateCapability {
	void consume(Attacker source,
			com.l2jserver.model.world.capability.Attackable target);
}
