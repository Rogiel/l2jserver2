package com.l2jserver.model.template;

import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.template.capability.Attackable;
import com.l2jserver.model.template.capability.Enchantable;

public abstract class WeaponTemplate extends ItemTemplate implements
		Attackable, Enchantable {
	public WeaponTemplate(TemplateID id) {
		super(id);
	}
}
