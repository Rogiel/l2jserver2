package com.l2jserver.model.id.template;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.template.SkillTemplate;
import com.l2jserver.service.game.template.TemplateService;

/**
 * An {@link TemplateID} instance representing an {@link SkillTemplate}
 * object
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SkillTemplateID extends TemplateID<SkillTemplate> {
	/**
	 * The template service
	 */
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
