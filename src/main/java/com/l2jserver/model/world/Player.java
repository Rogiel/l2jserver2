package com.l2jserver.model.world;

import java.util.List;

import com.l2jserver.model.template.SkillTemplate;
import com.l2jserver.model.world.capability.Attackable;
import com.l2jserver.model.world.capability.Attacker;
import com.l2jserver.model.world.capability.Castable;
import com.l2jserver.model.world.capability.Caster;
import com.l2jserver.model.world.capability.Listenable;
import com.l2jserver.model.world.capability.Parent;
import com.l2jserver.model.world.capability.Playable;
import com.l2jserver.model.world.capability.Spawnable;
import com.l2jserver.model.world.player.PlayerEvent;
import com.l2jserver.model.world.player.PlayerListener;
import com.l2jserver.util.Coordinate;

/**
 * {@link Player} is any object that can be controlled by the player. The most
 * common implementation is {@link Character}.
 * 
 * @author Rogiel
 */
public abstract class Player extends AbstractObject implements Playable,
		Spawnable, Attacker, Attackable,
		Listenable<PlayerListener, PlayerEvent>, Caster, Parent {
	@Override
	public void spawn(Coordinate coordinate) {

	}

	@Override
	public void receiveAttack(Attacker attacker) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attack(Attackable target) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addListener(PlayerListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeListener(PlayerListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PlayerListener> getListeners() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean support(Class<? extends PlayerEvent> eventType) {
		return eventType.isAssignableFrom(PlayerEvent.class);
	}
	
	@Override
	public Coordinate getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dispatch(PlayerEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cast(SkillTemplate skill, Castable cast) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSpawned() {
		// TODO Auto-generated method stub
		return false;
	}
}
