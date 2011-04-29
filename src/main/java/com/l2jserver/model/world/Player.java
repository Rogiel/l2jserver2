package com.l2jserver.model.world;

import java.util.List;

import com.l2jserver.model.template.SkillTemplate;
import com.l2jserver.model.world.capability.Attackable;
import com.l2jserver.model.world.capability.Attacker;
import com.l2jserver.model.world.capability.Castable;
import com.l2jserver.model.world.capability.Caster;
import com.l2jserver.model.world.capability.Levelable;
import com.l2jserver.model.world.capability.Listenable;
import com.l2jserver.model.world.capability.Parent;
import com.l2jserver.model.world.capability.Playable;
import com.l2jserver.model.world.capability.Spawnable;
import com.l2jserver.model.world.player.PlayerEvent;
import com.l2jserver.model.world.player.PlayerListener;
import com.l2jserver.util.Coordinate;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * {@link Player} is any object that can be controlled by the player. The most
 * common implementation is {@link Character}.
 * 
 * @author Rogiel
 */
public abstract class Player extends AbstractObject implements Playable,
		Spawnable, Attacker, Attackable,
		Listenable<PlayerListener, PlayerEvent>, Caster, Parent, Levelable {
	private final List<PlayerListener> listeners = CollectionFactory
			.newList(PlayerListener.class);

	@Override
	public void spawn(Coordinate coordinate) {

	}

	@Override
	public void attack(Attackable target,
			com.l2jserver.model.template.capability.Attackable weapon) {

	}

	@Override
	public void receiveAttack(Attacker attacker,
			com.l2jserver.model.template.capability.Attackable weapon) {

	}

	@Override
	public void addListener(PlayerListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(PlayerListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void dispatch(PlayerEvent e) {
		for (final PlayerListener listener : listeners) {
			listener.dispatch(e);
		}
	}

	@Override
	public Coordinate getPosition() {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLevel(int level) {
		// TODO Auto-generated method stub
	}
}
