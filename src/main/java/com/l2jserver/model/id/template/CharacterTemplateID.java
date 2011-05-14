package com.l2jserver.model.id.template;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.template.CharacterTemplate;
import com.l2jserver.service.game.template.TemplateService;

/**
 * An {@link TemplateID} instance representing an {@link CharacterTemplate}
 * object
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterTemplateID extends TemplateID<CharacterTemplate> {
	/**
	 * The template service
	 */
	private final TemplateService templateService;

	@Inject
	protected CharacterTemplateID(@Assisted int id,
			TemplateService templateService) {
		super(id);
		this.templateService = templateService;
	}

	@Override
	public CharacterTemplate getTemplate() {
		return templateService.getTemplate(this);
	}
}
