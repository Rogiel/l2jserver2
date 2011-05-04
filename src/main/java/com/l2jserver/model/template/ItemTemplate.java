package com.l2jserver.model.template;

import com.l2jserver.model.id.template.ItemTemplateID;

public abstract class ItemTemplate extends AbstractTemplate {
	public ItemTemplate(ItemTemplateID id) {
		super(id);
	}

	@Override
	public ItemTemplateID getID() {
		return (ItemTemplateID) super.getID();
	}
}
