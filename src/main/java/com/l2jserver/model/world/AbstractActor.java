package com.l2jserver.model.world;

import com.l2jserver.model.template.SkillTemplate;
import com.l2jserver.model.template.capability.Attackable;
import com.l2jserver.model.world.actor.ActorEffects;
import com.l2jserver.model.world.capability.Actor;
import com.l2jserver.model.world.capability.Attacker;
import com.l2jserver.model.world.capability.Castable;
import com.l2jserver.model.world.capability.Equipable;
import com.l2jserver.model.world.capability.Equiper;
import com.l2jserver.util.dimensional.Coordinate;
import com.l2jserver.util.dimensional.Point;

/**
 * Abstract {@link Actor} class.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class AbstractActor extends AbstractObject implements Actor {
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

		public final int option;

		Race(int option) {
			this.option = option;
		}

		public static Race fromOption(int option) {
			for (final Race race : values()) {
				if (race.option == option)
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

	@Override
	public void receiveDamage(int damage) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getHP() {
		return hp;
	}

	@Override
	public void setHP(int hp) {
		this.hp = hp;
	}

	@Override
	public void receiveAttack(Attacker attacker, Attackable weapon) {
		// TODO
	}

	@Override
	public void attack(com.l2jserver.model.world.capability.Attackable target,
			Attackable weapon) {
		// TODO
	}

	@Override
	public void cast() {
		// TODO
	}

	@Override
	public void cast(SkillTemplate skill, Castable cast) {
		// TODO
	}

	@Override
	public void spawn(Coordinate coordinate) {
		// TODO
	}

	@Override
	public boolean isSpawned() {
		return false;
	}

	/**
	 * @return the coordinate point
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * @param point
	 *            the coordinate point to set
	 */
	public void setPoint(Point point) {
		this.point = point;
	}

	@Override
	public Coordinate getPosition() {
		return point.getCoordinate();
	}

	@Override
	public void setPosition(Coordinate coord) {
		this.point = new Point(coord, (point != null ? point.getAngle() : 0));
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

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the active effects on this actor
	 */
	@Override
	public ActorEffects getEffects() {
		return effects;
	}

	@Override
	public void die(WorldObject killer) {
		// TODO
	}

	@Override
	public void equip(Equipable equipable) {
		// TODO
	}

	@Override
	public boolean isEquiped(Equipable equipment) {
		return false;
	}

	@Override
	public boolean isEquiped(
			com.l2jserver.model.template.capability.Equipable equipable) {
		return false;
	}

	@Override
	public void setEquipment(Object slot, Equipable equipment) {
		// TODO
	}

	@Override
	public void getEquipment(Object slot) {
		// TODO
	}

	@Override
	public void equip(Equiper equiper) {
		// TODO
	}
}
