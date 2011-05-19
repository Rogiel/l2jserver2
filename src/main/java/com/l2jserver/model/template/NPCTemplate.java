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
import com.l2jserver.model.id.template.NPCTemplateID;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.world.AbstractActor.Race;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.game.CharacterService;
import com.l2jserver.service.network.NetworkService;

/**
 * Template for {@link NPC}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class NPCTemplate extends ActorTemplate<NPC> {
	@Inject
	protected NetworkService networkService;
	@Inject
	protected CharacterService charService;
	@Inject
	protected ItemTemplateIDProvider itemTemplateIdProvider;

	protected String name = null;
	protected String title = null;
	protected boolean attackable = false;

	protected double movementSpeedMultiplier = 1.0;
	protected double attackSpeedMultiplier = 1.0;

	protected double collisionRadius = 0;
	protected double collisionHeigth = 0;

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
		System.out.println(action);
		charService.target(character, npc);
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
	 * @return the collisionHeigth
	 */
	public double getCollisionHeigth() {
		return collisionHeigth;
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
