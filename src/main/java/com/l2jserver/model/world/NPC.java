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
package com.l2jserver.model.world;

import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.id.object.NPCID;
import com.l2jserver.model.id.template.NPCTemplateID;
import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.model.world.npc.NPCStats;
import com.l2jserver.service.game.ai.AIScript;

/**
 * NPC stand for "Not Playable Character" and is an character that no player has
 * control over it. In most cases they are controlled by an {@link AIScript} .
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class NPC extends Actor {
	/**
	 * This NPC stats
	 */
	private final NPCStats stats = new NPCStats(this);

	/**
	 * The npc state
	 */
	private NPCState state;

	public enum NPCState {
		MOVING, ATTACKING;
	}

	/**
	 * Creates a new instance
	 * 
	 * @param templateID
	 *            the {@link NPC} {@link TemplateID}
	 */
	public NPC(NPCTemplateID templateID) {
		super(templateID);
	}

	@Override
	public NPCStats getStats() {
		return stats;
	}

	/**
	 * @return the state
	 */
	public NPCState getState() {
		return state;
	}

	/**
	 * @return true if NPC is idle
	 */
	public boolean isIdle() {
		return state == null;
	}

	/**
	 * @return true if NPC is idle
	 */
	public boolean isMoving() {
		return state == NPCState.MOVING;
	}

	/**
	 * @return true if NPC is idle
	 */
	public boolean isAttacking() {
		return state == NPCState.ATTACKING;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(NPCState state) {
		this.state = state;
	}
	
	// TEMPLATE WRAPPERS
	@Override
	public ActorSex getSex() {
		return this.getTemplate().getSex();
	}

	@Override
	public void setSex(ActorSex sex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getLevel() {
		return this.getTemplate().getLevel();
	}

	@Override
	public void setLevel(int level) {
		throw new UnsupportedOperationException();
	}

	public double getMaxHP() {
		return this.getTemplate().getMaximumHP();
	}

	public void setMaxHP(double maxHP) {
		throw new UnsupportedOperationException();
	}

	public double getMaxMP() {
		return this.getTemplate().getMaximumMP();
	}

	public void setMaxMP(double maxMP) {
		throw new UnsupportedOperationException();
	}

	@Override
	public long getExperience() {
		return this.getTemplate().getExperience();
	}

	@Override
	public void setExperience(long experience) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getSP() {
		return this.getTemplate().getSP();
	}

	@Override
	public void setSP(int sp) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @return the NPC template ID
	 */
	@Override
	public NPCTemplateID getTemplateID() {
		return (NPCTemplateID) templateID;
	}

	/**
	 * @return the NPC template
	 */
	@Override
	public NPCTemplate getTemplate() {
		return (NPCTemplate) templateID.getTemplate();
	}

	@Override
	public NPCID getID() {
		return (NPCID) super.getID();
	}
}
