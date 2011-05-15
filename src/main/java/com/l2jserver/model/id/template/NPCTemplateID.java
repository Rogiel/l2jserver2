package com.l2jserver.model.id.template;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.service.game.template.TemplateService;

/**
 * An {@link TemplateID} instance representing an {@link NPCTemplate}
 * object
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class NPCTemplateID extends ActorTemplateID<NPCTemplate> {
	@Inject
	protected NPCTemplateID(@Assisted int id,
			TemplateService templateService) {
		super(id, templateService);
	}
}
