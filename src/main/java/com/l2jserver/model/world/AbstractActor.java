package com.l2jserver.model.world;

import java.util.List;

import com.l2jserver.model.template.SkillTemplate;
import com.l2jserver.model.template.capability.Attackable;
import com.l2jserver.model.world.actor.ActorEvent;
import com.l2jserver.model.world.actor.ActorListener;
import com.l2jserver.model.world.capability.Actor;
import com.l2jserver.model.world.capability.Attacker;
import com.l2jserver.model.world.capability.Castable;
import com.l2jserver.model.world.capability.Equipable;
import com.l2jserver.model.world.capability.Equiper;
import com.l2jserver.util.Coordinate;
import com.l2jserver.util.factory.CollectionFactory;

public abstract class AbstractActor extends AbstractObject implements Actor {
	private final List<ActorListener> listeners = CollectionFactory
			.newList(ActorListener.class);

	protected int level;
	protected int hp;
	protected Coordinate position;

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

	@Override
	public Coordinate getPosition() {
		return position;
	}

	@Override
	public void setPosition(Coordinate coord) {
		this.position = coord;
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
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

	@Override
	public void addListener(ActorListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(ActorListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void dispatch(ActorEvent e) {
		for (final ActorListener listener : listeners) {
			listener.dispatch(e);
		}
	}
}
