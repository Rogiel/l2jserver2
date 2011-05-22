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
import com.l2jserver.model.world.actor.ActorEffects;
import com.l2jserver.model.world.actor.ActorSkillContainer;
import com.l2jserver.util.dimensional.Coordinate;
import com.l2jserver.util.dimensional.Point;

/**
 * Abstract {@link Actor} class.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class Actor extends PositionableObject {
	/**
	 * The actor race
	 */
	protected Race race;

	/**
	 * Represents the actor race.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum Race {
		HUMAN(0x00), ELF(0x01), DARK_ELF(0x02), ORC(0x03), DWARF(0x04), KAMAEL(
				0x05);

		/**
		 * The numeric ID representing this race
		 */
		public final int id;

		Race(int id) {
			this.id = id;
		}

		/**
		 * Finds the race based on the <tt>id</tt>
		 * 
		 * @param id
		 *            the id
		 * @return the race constant
		 */
		public static Race fromOption(int id) {
			for (final Race race : values()) {
				if (race.id == id)
					return race;
			}
			return null;
		}
	}

	/**
	 * The actor sex
	 */
	protected Sex sex;

	/**
	 * Represent the sex of an actor.
	 * <p>
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum Sex {
		MALE(0x00), FEMALE(0x01);

		public final int option;

		Sex(int option) {
			this.option = option;
		}

		public static Sex fromOption(int option) {
			for (Sex sex : values()) {
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

	public int getHP() {
		return hp;
	}

	public void setHP(int hp) {
		this.hp = hp;
	}

	/**
	 * @return the race
	 */
	public Race getRace() {
		return race;
	}

	/**
	 * @param race
	 *            the race to set
	 */
	public void setRace(Race race) {
		this.race = race;
	}

	/**
	 * @return the sex
	 */
	public Sex getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(Sex sex) {
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

	@Override
	public ActorID<?> getID() {
		return (ActorID<?>) super.getID();
	}
}
