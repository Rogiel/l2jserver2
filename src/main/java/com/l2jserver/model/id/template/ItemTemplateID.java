package com.l2jserver.model.id.template;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.service.game.template.TemplateService;

public class ItemTemplateID extends TemplateID<ItemTemplate> {
	private final TemplateService templateService;

	@Inject
	protected ItemTemplateID(@Assisted int id, TemplateService templateService) {
		super(id);
		this.templateService = templateService;
	}

	@Override
	public ItemTemplate getTemplate() {
		return (ItemTemplate) templateService.getTemplate(this);
	}
}
