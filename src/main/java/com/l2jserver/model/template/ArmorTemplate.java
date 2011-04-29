package com.l2jserver.model.template;

import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.template.capability.Defendable;
import com.l2jserver.model.template.capability.IncomingDamageIntercept;

public abstract class ArmorTemplate extends ItemTemplate implements Defendable,
		IncomingDamageIntercept {
	public ArmorTemplate(TemplateID id) {
		super(id);
	}
}
