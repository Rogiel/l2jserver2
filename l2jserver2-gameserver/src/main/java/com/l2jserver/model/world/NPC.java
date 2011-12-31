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
package com.l2jserver.model.world;

import com.l2jserver.model.id.object.NPCID;
import com.l2jserver.model.id.template.NPCTemplateID;
import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.model.template.Template;
import com.l2jserver.model.template.actor.ActorSex;
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
	 * The {@link NPC} respawn interval
	 */
	private long respawnInterval;

	/**
	 * This NPC stats
	 */
	private final NPCStats stats = new NPCStats(this);

	/**
	 * Creates a new instance
	 * 
	 * @param template
	 *            the {@link NPC} {@link Template}
	 */
	public NPC(NPCTemplate template) {
		super(template.getID());
	}

	/**
	 * @return the respawnInterval
	 */
	public long getRespawnInterval() {
		return respawnInterval;
	}

	/**
	 * @param respawnInterval
	 *            the respawnInterval to set
	 */
	public void setRespawnInterval(long respawnInterval) {
		desireUpdate();
		this.respawnInterval = respawnInterval;
	}

	@Override
	public NPCStats getStats() {
		return stats;
	}

	// TEMPLATE WRAPPERS
	@Override
	public ActorSex getSex() {
		return this.getTemplate().getInfo().getSex();
	}

	@Override
	public void setSex(ActorSex sex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getLevel() {
		return this.getTemplate().getInfo().getLevel();
	}

	@Override
	public void setLevel(int level) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @return the maximum HP
	 */
	public double getMaxHP() {
		return this.getTemplate().getInfo().getStats().getHp().getMax();
	}

	/**
	 * @param maxHP
	 *            the maximum HP
	 * @throws UnsupportedOperationException
	 *             always
	 */
	public void setMaxHP(double maxHP) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @return the maximum MP
	 */
	public double getMaxMP() {
		return this.getTemplate().getInfo().getStats().getMp().getMax();
	}

	/**
	 * @param maxMP
	 *            the maximum MP
	 * @throws UnsupportedOperationException
	 *             always
	 */
	public void setMaxMP(double maxMP) {
		throw new UnsupportedOperationException();
	}

	@Override
	public long getExperience() {
		return this.getTemplate().getInfo().getExperience();
	}

	@Override
	public void setExperience(long experience) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getSP() {
		return this.getTemplate().getInfo().getSp();
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
