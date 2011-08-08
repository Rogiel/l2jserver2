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
package com.l2jserver.service.game.template;

import com.l2jserver.model.game.Skill;
import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.id.template.provider.TemplateIDProvider;
import com.l2jserver.model.template.Template;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.Service;

/**
 * Service that loads {@link L2Character}, {@link NPC}, {@link Item} and
 * {@link Skill} {@link Template templates}. The service on startup, loads from
 * files or from the database the data and parses them into
 * <tt>com.l2jserver.model.template</tt> classes. Once they are loaded,
 * templates can be retrieved using any {@link TemplateID} object created from a
 * {@link TemplateIDProvider}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface TemplateService extends Service {
	/**
	 * Get the template assigned with <tt>id</tt>
	 * 
	 * @param <T>
	 *            the template type
	 * @param id
	 *            the template id
	 * @return the template
	 */
	<T extends Template<?>> T getTemplate(TemplateID<T, ?> id);
}
