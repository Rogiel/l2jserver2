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

import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.client.CharacterActionPacket.CharacterAction;
import com.l2jserver.game.net.packet.server.NPCHtmlMessagePacket;
import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.id.template.NPCTemplateID;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.world.AbstractActor.Race;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.capability.Actor;
import com.l2jserver.service.game.CharacterService;
import com.l2jserver.service.network.NetworkService;
import com.l2jserver.util.calculator.Calculator;
import com.l2jserver.util.html.markup.Markup;
import com.l2jserver.util.html.markup.Markup.Builder;
import com.l2jserver.util.html.markup.MarkupTag;

/**
 * Template for {@link NPC}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class NPCTemplate extends ActorTemplate<NPC> {
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
	 * The movement speed multiplier
	 */
	protected double movementSpeedMultiplier = 1.0;
	/**
	 * The attack speed multiplier
	 */
	protected double attackSpeedMultiplier = 1.0;

	/**
	 * The collision radius
	 */
	protected double collisionRadius = 0;
	/**
	 * The collision height
	 */
	protected double collisionHeight = 0;

	protected int maxHp;

	protected NPCTemplate(NPCTemplateID id) {
		super(id, null);
	}

	/**
	 * Performs an interaction with this NPC. This is normally invoked from
	 * <tt>npc</tt> instance.
	 * 
	 * @param character
	 *            the interacting character
	 * @param action
	 *            the action performed
	 */
	public void action(NPC npc, L2Character character, CharacterAction action) {
		final Lineage2Connection conn = networkService.discover(character
				.getID());
		if (conn == null)
			return;

		// target this npc
		charService.target(character, npc);
		
		// generate not implemented message
		final Markup markup = new Markup(name + " - Notice", new Builder() {
			@Override
			public void build(MarkupTag body) {
				body.text("This NPC is not yet implemented!");
				body.addLink("Click me!", "test");
			}
		});
		conn.write(new NPCHtmlMessagePacket(npc, markup.build()));
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
	 * @return the movementSpeedMultiplier
	 */
	public double getMovementSpeedMultiplier() {
		return movementSpeedMultiplier;
	}

	/**
	 * @return the attackSpeedMultiplier
	 */
	public double getAttackSpeedMultiplier() {
		return attackSpeedMultiplier;
	}

	/**
	 * @return the collisionRadius
	 */
	public double getCollisionRadius() {
		return collisionRadius;
	}

	/**
	 * @return the collisionHeight
	 */
	public double getCollisionHeight() {
		return collisionHeight;
	}

	/**
	 * @return the maxHp
	 */
	public int getMaxHP() {
		return maxHp;
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
