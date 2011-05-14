package com.l2jserver.model.id.template;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.service.game.template.TemplateService;

/**
 * An {@link TemplateID} instance representing an {@link ItemTemplate} object
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ItemTemplateID extends TemplateID<ItemTemplate> {
	/**
	 * The template service
	 */
	private final TemplateService templateService;

	@Inject
	protected ItemTemplateID(@Assisted int id, TemplateService templateService) {
		super(id);
		this.templateService = templateService;
	}

	@Override
	public ItemTemplate getTemplate() {
		return templateService.getTemplate(this);
	}
}
