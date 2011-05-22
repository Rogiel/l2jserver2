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
package com.l2jserver.model.template.npc;

import com.l2jserver.model.id.template.NPCTemplateID;
import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.util.exception.L2Exception;
import com.l2jserver.util.html.markup.HtmlTemplate;
import com.l2jserver.util.html.markup.MarkupTag;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class MonsterNPCTemplate extends NPCTemplate {
	/**
	 * Creates a new instance of this template
	 * 
	 * @param id
	 *            the template id
	 * @param race
	 *            the npc race
	 */
	protected MonsterNPCTemplate(NPCTemplateID id) {
		super(id);
	}

	@Override
	protected HtmlTemplate getChat(String name) throws L2Exception {
		return new HtmlTemplate() {
			@Override
			protected void build(MarkupTag body) {
				body.text("Sorry, but you can't interact with me yet.");
			}
		};
	}
}
