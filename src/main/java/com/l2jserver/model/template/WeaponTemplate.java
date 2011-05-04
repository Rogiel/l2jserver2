package com.l2jserver.model.template;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.template.capability.Attackable;

public abstract class WeaponTemplate extends ItemTemplate implements Attackable {
	public WeaponTemplate(ItemTemplateID id) {
		super(id);
	}
}
