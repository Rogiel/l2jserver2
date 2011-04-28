package com.l2jserver.model.world;

import com.l2jserver.model.world.capability.Attackable;
import com.l2jserver.model.world.capability.Attacker;
import com.l2jserver.model.world.capability.Child;
import com.l2jserver.model.world.capability.Playable;
import com.l2jserver.model.world.capability.Spawnable;
import com.l2jserver.util.Coordinate;

public class Item extends AbstractObject implements Playable, Spawnable,
		Attacker, Attackable, Child<Player> {
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
	public boolean isSpawned() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Player getParent() {
		return null;
	}

	@Override
	public Coordinate getPosition() {
		// TODO Auto-generated method stub
		return null;
	}
}
