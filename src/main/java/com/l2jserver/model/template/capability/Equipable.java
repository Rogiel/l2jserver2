package com.l2jserver.model.template.capability;

import com.l2jserver.model.world.capability.Equiper;

public interface Equipable extends TemplateCapability {
	void equip(Equiper equiper);
}
