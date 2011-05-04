package com.l2jserver.model.template;

import com.l2jserver.model.id.template.SkillTemplateID;
import com.l2jserver.model.template.capability.Castable;

public abstract class SkillTemplate extends AbstractTemplate implements
		Castable {
	public SkillTemplate(SkillTemplateID id) {
		super(id);
	}

	@Override
	public SkillTemplateID getID() {
		return (SkillTemplateID) super.getID();
	}
}
