package com.l2jserver.model.template.capability;

import com.l2jserver.model.template.Template;
import com.l2jserver.model.world.capability.Caster;

/**
 * Defines an {@link Template template} {@link TemplateCapability capability}
 * that an {@link Caster} can cast on an
 * {@link com.l2jserver.model.world.capability.Castable}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public interface Castable extends TemplateCapability {
	void cast(Caster caster,
			com.l2jserver.model.world.capability.Castable... targets);
}
