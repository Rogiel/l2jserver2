package com.l2jserver.model.template;

import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.template.capability.Attackable;

public abstract class WeaponTemplate extends ItemTemplate implements Attackable {
	public WeaponTemplate(TemplateID id) {
		super(id);
	}
}
