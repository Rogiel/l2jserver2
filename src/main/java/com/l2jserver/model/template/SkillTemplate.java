package com.l2jserver.model.template;

import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.template.capability.Castable;

public abstract class SkillTemplate extends AbstractTemplate implements
		Castable {
	public SkillTemplate(TemplateID id) {
		super(id);
	}
}
