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
package com.l2jserver.model.world.npc.controller;

import java.util.Arrays;

import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;

import com.l2jserver.game.net.Lineage2Client;
import com.l2jserver.game.net.packet.server.SM_HTML;
import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.util.exception.L2Exception;
import com.l2jserver.util.html.markup.HtmlTemplate;
import com.l2jserver.util.html.markup.MarkupTag;

/**
 * The {@link BaseNPCController} handful methods for controlling NPCs. This
 * implementation is also used for {@link NPC NPCs} that don't have any special
 * behavior.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class BaseNPCController implements NPCController {
	@Override
	public void action(NPC npc, Lineage2Client conn, L2Character character,
			final String... args) throws L2Exception {
		if (args.length == 2) {
			if (args[0].equals("Chat")) {
				if (talk(npc, conn, character,
						Arrays.copyOfRange(args, 1, args.length)))
					return;
			}
		} else if (args.length == 0 || args.length == 1) {
			// default action is talk
			if (talk(npc, conn, character, new String[0]))
				return;
		}
		// action not handled message
		final HtmlTemplate template = new HtmlTemplate() {
			@Override
			protected void build(MarkupTag body) {
				body.text(
						"Sorry ${name}, but the action you have requested is not yet implemented.")
						.p();
				body.text("Arguments: " + Arrays.toString(args));
			}
		}.register("name", character.getName());
		conn.write(new SM_HTML(npc, template));
		conn.sendActionFailed();
	}

	/**
	 * Talks with this NPC
	 * 
	 * @param npc
	 *            the {@link NPC} instance
	 * @param conn
	 *            the connection to {@link L2Character}
	 * @param character
	 *            the interacting character
	 * @param args
	 *            the action arguments
	 * @return true if chat message was sent
	 * @throws L2Exception
	 *             if the talk action could not be performed
	 */
	protected boolean talk(NPC npc, Lineage2Client conn, L2Character character,
			String... args) throws L2Exception {
		String id = null;
		if (args.length >= 1) {
			id = args[0];
		}
		final String html = getHTML(npc, id);
		if (html == null)
			return false;
		conn.write(new SM_HTML(npc, html));
		conn.sendActionFailed();
		return true;
	}

	/**
	 * Returns the NPC HTML message
	 * 
	 * @param npc
	 *            the npc
	 * @param id
	 *            the html message id
	 * @return the html code
	 */
	protected String getHTML(NPC npc, String id) {
		// id correction - on l2j default chat is also "0".
		if ("0".equals(id)) // avoid NullPointerException
			id = null;

		final NPCTemplate template = npc.getTemplate();
		String html = template.getHTML(id);
		if (html == null)
			return null;
		// TODO use an decent template engine
		return html.replaceAll("%objectId%", npc.getID().getID().toString());
	}

	/**
	 * This method will try to remove empty lines and all unnecessary space from
	 * the HTML code.
	 * 
	 * @param html
	 *            the html code
	 * @return the trimmed html code
	 */
	protected String trimHTML(String html) {
		try {
			return new Parser(html).elements().nextNode().toHtml();
		} catch (ParserException e) {
			return html;
		}
	}
}
