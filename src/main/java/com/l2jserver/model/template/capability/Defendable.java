package com.l2jserver.model.template.capability;

import com.l2jserver.model.world.capability.Attacker;

public interface Defendable extends TemplateCapability {
	void defend(Attacker source,
			com.l2jserver.model.world.capability.Attackable target);
	
	public int getPhysicalDefense();
	public int getMagicalDefense();
}
