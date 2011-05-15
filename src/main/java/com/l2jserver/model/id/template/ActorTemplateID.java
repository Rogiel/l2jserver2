package com.l2jserver.model.id.template;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.template.ActorTemplate;
import com.l2jserver.service.game.template.TemplateService;

/**
 * An {@link TemplateID} instance representing an {@link ActorTemplate}
 * object
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ActorTemplateID<T extends ActorTemplate<?>> extends TemplateID<T> {
	/**
	 * The template service
	 */
	private final TemplateService templateService;

	@Inject
	protected ActorTemplateID(@Assisted int id,
			TemplateService templateService) {
		super(id);
		this.templateService = templateService;
	}

	@Override
	public T getTemplate() {
		return templateService.getTemplate(this);
	}
}
