package com.l2jserver.model.template;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.template.capability.Consumable;

public abstract class ConsumableTemplate extends ItemTemplate implements
		Consumable {
	public ConsumableTemplate(ItemTemplateID id) {
		super(id);
	}
}
