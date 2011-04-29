package com.l2jserver.model.world;

import com.l2jserver.model.world.capability.Child;
import com.l2jserver.model.world.capability.Summonable;
import com.l2jserver.util.Coordinate;

public class Pet extends Player implements Child<L2Character>, Summonable {
	@Override
	public L2Character getParent() {
		return null;
	}

	@Override
	public void teleport(Coordinate coordinate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void summon(Coordinate coordinate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSummoned() {
		// TODO Auto-generated method stub
		return false;
	}
}
