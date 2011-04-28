package com.l2jserver.model.template.capability;

import com.l2jserver.model.world.capability.Attacker;

public interface Consumable extends TemplateCapability {
	void consume(Attacker source,
			com.l2jserver.model.world.capability.Attackable target);
}
