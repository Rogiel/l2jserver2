package com.l2jserver.model.template.capability;

import com.l2jserver.model.template.Template;
import com.l2jserver.model.world.capability.Attacker;

/**
 * Defines an {@link Template template} {@link TemplateCapability capability}
 * that can be selled.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Dropable extends TemplateCapability {
	void sell(Attacker source,
			com.l2jserver.model.world.capability.Attackable target);
}
