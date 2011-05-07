package com.l2jserver.model.id.template;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.template.CharacterTemplate;
import com.l2jserver.service.game.template.TemplateService;

public class CharacterTemplateID extends TemplateID<CharacterTemplate> {
	private final TemplateService templateService;

	@Inject
	protected CharacterTemplateID(@Assisted int id,
			TemplateService templateService) {
		super(id);
		this.templateService = templateService;
	}

	@Override
	public CharacterTemplate getTemplate() {
		return (CharacterTemplate) templateService.getTemplate(this);
	}
}
