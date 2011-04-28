package com.l2jserver.model.template;

import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.template.capability.Consumable;

public abstract class ConsumableTemplate extends ItemTemplate implements
		Consumable {
	public ConsumableTemplate(TemplateID id) {
		super(id);
	}
}
