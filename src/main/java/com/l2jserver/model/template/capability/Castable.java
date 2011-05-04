package com.l2jserver.model.template.capability;

import com.l2jserver.model.world.capability.Caster;

public interface Castable extends TemplateCapability {
	void cast(Caster caster,
			com.l2jserver.model.world.capability.Castable... targets);
}
