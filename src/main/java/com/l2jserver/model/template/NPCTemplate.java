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
package com.l2jserver.model.template;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.server.NPCHtmlMessagePacket;
import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.id.template.NPCTemplateID;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.template.capability.Interactable;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.Actor.Race;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.game.character.CannotSetTargetServiceException;
import com.l2jserver.service.game.character.CharacterService;
import com.l2jserver.service.network.NetworkService;
import com.l2jserver.util.calculator.Calculator;
import com.l2jserver.util.exception.L2Exception;
import com.l2jserver.util.html.markup.HtmlTemplate;

/**
 * Template for {@link NPC}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class NPCTemplate extends ActorTemplate<NPC> implements
		Interactable<NPC> {
	/**
	 * The {@link NetworkService}
	 */
	@Inject
	protected NetworkService networkService;
	/**
	 * The {@link CharacterService}
	 */
	@Inject
	protected CharacterService charService;
	/**
	 * The {@link ItemTemplateID} provider
	 */
	@Inject
	protected ItemTemplateIDProvider itemTemplateIdProvider;

	/**
	 * The NPC name
	 */
	protected String name = null;
	/**
	 * The NPC title
	 */
	protected String title = null;
	/**
	 * The attackable state of the NPC
	 */
	protected boolean attackable = false;

	/**
	 * The collision radius
	 */
	protected double collisionRadius = 0;
	/**
	 * The collision height
	 */
	protected double collisionHeight = 0;

	protected NPCTemplate(NPCTemplateID id) {
		super(id, null);
	}

	@Override
	public void action(NPC npc, L2Character character, String... args)
			throws L2Exception {
		Preconditions.checkNotNull(npc, "npc");
		Preconditions.checkNotNull(character, "character");
		Preconditions.checkNotNull(args, "args");

		final Lineage2Connection conn = networkService.discover(character
				.getID());
		if (conn == null)
			return;

		// target this npc
		try {
			charService.target(character, npc);
		} catch (CannotSetTargetServiceException e) {
			conn.sendActionFailed();
			return;
		}

		if (args.length == 0 || args[0].equals("Chat"))
			talk(npc, character, conn, args);
	}

	/**
	 * Talks with this NPC
	 * 
	 * @param npc
	 *            the npc
	 * @param character
	 *            the character
	 * @param conn
	 *            the lineage 2 connection
	 * @param args
	 *            the action arguments
	 * @throws L2Exception
	 */
	protected void talk(NPC npc, L2Character character,
			Lineage2Connection conn, String... args) throws L2Exception {
		if (args.length == 0 || (args.length >= 1 && args[0].equals("Chat"))) {
			String name = "";
			if (args.length == 2)
				name = args[1];
			final HtmlTemplate template = getChat(name);
			if (template != null) {
				template.register("npcid", npc.getID().getID());
				conn.write(new NPCHtmlMessagePacket(npc, template));
			}
		}
		conn.sendActionFailed();
	}

	protected HtmlTemplate getChat(String name) throws L2Exception {
		return null;
	}

	/**
	 * Receives an attack from an {@link Actor}
	 * 
	 * @param npc
	 *            the {@link NPC} being attacked
	 * @param calculator
	 *            the calculator
	 * @param attacker
	 *            the attacker actor
	 */
	public void receiveAttack(NPC npc, Calculator calculator, Actor attacker) {
		// TODO add attributes to calculator!
	}

	@Override
	public NPC createInstance() {
		return new NPC(this.getID());
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the attackable
	 */
	public boolean isAttackable() {
		return attackable;
	}

	/**
	 * @return the collision radius
	 */
	public double getCollisionRadius() {
		return collisionRadius;
	}

	/**
	 * @return the collision height
	 */
	public double getCollisionHeight() {
		return collisionHeight;
	}

	/**
	 * @return the race
	 */
	public Race getRace() {
		return race;
	}

	@Override
	public NPCTemplateID getID() {
		return (NPCTemplateID) super.getID();
	}
}
