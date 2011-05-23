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

import com.l2jserver.model.id.object.ActorID;
import com.l2jserver.model.id.template.ActorTemplateID;
import com.l2jserver.model.template.ActorTemplate;
import com.l2jserver.model.world.actor.ActorEffects;
import com.l2jserver.model.world.actor.ActorSkillContainer;
import com.l2jserver.util.dimensional.Point;

/**
 * Abstract {@link Actor} class.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class Actor extends PositionableObject {
	protected ActorTemplateID<?> templateID;

	/**
	 * The actor race
	 */
	protected ActorRace race;

	/**
	 * Represents the actor race.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum ActorRace {
		HUMAN(0x00), ELF(0x01), DARK_ELF(0x02), ORC(0x03), DWARF(0x04), KAMAEL(
				0x05);

		/**
		 * The numeric ID representing this race
		 */
		public final int id;

		ActorRace(int id) {
			this.id = id;
		}

		/**
		 * Finds the race based on the <tt>id</tt>
		 * 
		 * @param id
		 *            the id
		 * @return the race constant
		 */
		public static ActorRace fromOption(int id) {
			for (final ActorRace race : values()) {
				if (race.id == id)
					return race;
			}
			return null;
		}
	}

	/**
	 * The actor sex
	 */
	protected ActorSex sex;

	/**
	 * Represent the sex of an actor.
	 * <p>
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum ActorSex {
		MALE(0x00), FEMALE(0x01);

		public final int option;

		ActorSex(int option) {
			this.option = option;
		}

		public static ActorSex fromOption(int option) {
			for (ActorSex sex : values()) {
				if (sex.option == option)
					return sex;
			}
			return null;
		}
	}

	/**
	 * The actor level
	 */
	protected int level;
	/**
	 * The actor HP
	 */
	protected int hp;
	/**
	 * The actor coordinate point
	 */
	protected Point point;
	/**
	 * The currently effects active on the actor
	 */
	protected final ActorEffects effects = new ActorEffects(this);
	/**
	 * The skills learned by this actor
	 */
	protected final ActorSkillContainer skills = new ActorSkillContainer(this);

	public Actor(ActorTemplateID<?> templateID) {
		this.templateID = templateID;
	}

	public int getHP() {
		return hp;
	}

	public void setHP(int hp) {
		this.hp = hp;
	}

	/**
	 * @return the race
	 */
	public ActorRace getRace() {
		return race;
	}

	/**
	 * @param race
	 *            the race to set
	 */
	public void setRace(ActorRace race) {
		this.race = race;
	}

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
		this.sex = sex;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the active effects on this actor
	 */
	public ActorEffects getEffects() {
		return effects;
	}

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
	public ActorTemplate<?> getTemplate() {
		return templateID.getTemplate();
	}

	@Override
	public ActorID<?> getID() {
		return (ActorID<?>) super.getID();
	}
}
