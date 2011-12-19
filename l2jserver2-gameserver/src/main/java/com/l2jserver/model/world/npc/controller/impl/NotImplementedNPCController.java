/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.model.world.npc.controller.impl;

import java.util.Arrays;

import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.npc.BaseNPCController;
import com.l2jserver.util.exception.L2Exception;
import com.l2jserver.util.html.markup.HtmlTemplate;
import com.l2jserver.util.html.markup.MarkupTag;

/**
 * This is a pseudo-controller used as an placeholder for controllers that were
 * not yet implemented.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class NotImplementedNPCController extends BaseNPCController {
	@Override
	public void interact(NPC npc, L2Character character,
			final String... args) throws L2Exception {
		// action not handled
		final HtmlTemplate template = new HtmlTemplate() {
			@Override
			protected void build(MarkupTag body) {
				body.text(
						"Sorry ${name}, but the action you have requested is not yet implemented.")
						.p();
				body.text("Arguments: " + Arrays.toString(args));
			}
		}.register("name", character.getName());
		npcService.talk(npc, character, template);
	}
}
