package com.l2jserver.model.template.capability;

import com.l2jserver.model.world.capability.Attacker;

public interface Attackable extends TemplateCapability {
	void attack(Attacker source,
			com.l2jserver.model.world.capability.Attackable target);
	
	public int getDamage();
}
