/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.model.id.template;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.template.SkillTemplate;
import com.l2jserver.model.template.TeleportationTemplate;
import com.l2jserver.service.game.template.TemplateService;
import com.l2jserver.util.jaxb.TeleportationTemplateIDAdapter;

/**
 * An {@link TemplateID} instance representing an {@link SkillTemplate} object
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@XmlJavaTypeAdapter(TeleportationTemplateIDAdapter.class)
public class TeleportationTemplateID extends
		TemplateID<TeleportationTemplate, Integer> {
	/**
	 * The template service
	 */
	private final TemplateService templateService;

	@Inject
	public TeleportationTemplateID(@Assisted int id,
			TemplateService templateService) {
		super(id);
		this.templateService = templateService;
	}

	@Override
	public TeleportationTemplate loadTemplate() {
		return templateService.getTemplate(this);
	}
}
