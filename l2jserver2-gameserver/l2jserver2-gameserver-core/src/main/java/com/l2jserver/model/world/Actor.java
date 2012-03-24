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

import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.id.object.ActorID;
import com.l2jserver.model.id.template.ActorTemplateID;
import com.l2jserver.model.template.ActorTemplate;
import com.l2jserver.model.template.actor.ActorSex;
import com.l2jserver.model.world.actor.ActorSkillContainer;
import com.l2jserver.model.world.actor.stat.ActorStats;

/**
 * Abstract {@link Actor} class.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class Actor extends PositionableObject {
	/**
	 * The {@link Actor} {@link TemplateID}
	 */
	protected ActorTemplateID<?> templateID;

	/**
	 * The actor sex
	 */
	protected ActorSex sex;

	/**
	 * The actor level
	 */
	protected int level;
	/**
	 * The actor HP
	 */
	protected double HP;
	/**
	 * The actor MP
	 */
	protected double MP;

	/**
	 * The actor experience points
	 */
	protected long experience;
	/**
	 * The actor sp points
	 */
	protected int sp;

	/**
	 * State of the actor. Will be null if it is idle
	 */
	private transient ActorState state;

	/**
	 * The valid states for an actor
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum ActorState {
		/**
		 * This state indicates the actor is being teleported
		 */
		TELEPORTING,
		/**
		 * This state indicates the actor is casting a skill
		 */
		CASTING,
		/**
		 * This state indicates the actor is attacking
		 */
		ATTACKING,
		/**
		 * This state indicates the actor is moving
		 */
		MOVING,
		/**
		 * This state indicates the actor is dead
		 */
		DEAD;
	}

//	/**
//	 * The currently effects active on the actor
//	 */
//	protected final ActorEffectContainer effects = new ActorEffectContainer(this);
	/**
	 * The skills learned by this actor
	 */
	protected final ActorSkillContainer skills = new ActorSkillContainer(this);

	/**
	 * @param templateID
	 *            the actor template id
	 */
	protected Actor(ActorTemplateID<?> templateID) {
		this.templateID = templateID;
	}

	/**
	 * @return the actor stats
	 */
	public abstract ActorStats<?> getStats();

	/**
	 * @return the sex
	 */
	public ActorSex getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(ActorSex sex) {
		desireUpdate();
		this.sex = sex;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		desireUpdate();
		this.level = level;
	}

	/**
	 * @return the hP
	 */
	public double getHP() {
		return HP;
	}

	/**
	 * @param hP
	 *            the hP to set
	 */
	public void setHP(double hP) {
		desireUpdate();
		HP = hP;
	}

	/**
	 * @return the mP
	 */
	public double getMP() {
		return MP;
	}

	/**
	 * @param mP
	 *            the mP to set
	 */
	public void setMP(double mP) {
		desireUpdate();
		MP = mP;
	}

	/**
	 * @return the experience
	 */
	public long getExperience() {
		return experience;
	}

	/**
	 * @param experience
	 *            the experience to set
	 */
	public void setExperience(long experience) {
		desireUpdate();
		this.experience = experience;
	}

	/**
	 * @return the sp
	 */
	public int getSP() {
		return sp;
	}

	/**
	 * @param sp
	 *            the sp to set
	 */
	public void setSP(int sp) {
		desireUpdate();
		this.sp = sp;
	}

	/**
	 * @return the state
	 */
	public ActorState getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(ActorState state) {
		this.state = state;
	}

	/**
	 * @return true if character is doing nothing
	 */
	public boolean isIdle() {
		return state == null;
	}

	/**
	 * @return true if character is being teleported
	 */
	public boolean isTeleporting() {
		return state == ActorState.TELEPORTING;
	}

	/**
	 * @return true if character is moving
	 */
	public boolean isMoving() {
		return state == ActorState.MOVING;
	}

	/**
	 * @return true if character is dead
	 */
	public boolean isDead() {
		return state == ActorState.DEAD;
	}

	/**
	 * @return true if character is casting
	 */
	public boolean isCasting() {
		return state == ActorState.CASTING;
	}

	/**
	 * @return true if character is attacking
	 */
	public boolean isAttacking() {
		return state == ActorState.ATTACKING;
	}

//	/**
//	 * @return the active effects on this actor
//	 */
//	public ActorEffectContainer getEffects() {
//		return effects;
//	}

	/**
	 * @return the actor skills
	 */
	public ActorSkillContainer getSkills() {
		return skills;
	}

	/**
	 * @return the templateID
	 */
	public ActorTemplateID<?> getTemplateID() {
		return templateID;
	}

	/**
	 * @return the template
	 */
	public ActorTemplate getTemplate() {
		return templateID.getTemplate();
	}

	@Override
	public ActorID<?> getID() {
		return (ActorID<?>) super.getID();
	}
}
