package com.l2jserver.model.id.template;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.template.SkillTemplate;
import com.l2jserver.service.game.template.TemplateService;

public class SkillTemplateID extends TemplateID<SkillTemplate> {
	private final TemplateService templateService;

	@Inject
	public SkillTemplateID(@Assisted int id, TemplateService templateService) {
		super(id);
		this.templateService = templateService;
	}

	@Override
	public SkillTemplate getTemplate() {
		return (SkillTemplate) templateService.getTemplate(this);
	}
}
