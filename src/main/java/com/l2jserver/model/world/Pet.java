package com.l2jserver.model.world;

import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.world.capability.Summonable;
import com.l2jserver.util.Coordinate;

public class Pet extends Player implements Summonable {
	private CharacterID ownerID;

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

	/**
	 * @return the ownerID
	 */
	public CharacterID getOwnerID() {
		return ownerID;
	}

	/**
	 * @return the owner
	 */
	public L2Character getOwner() {
		return ownerID.getObject();
	}

	/**
	 * @param ownerID
	 *            the ownerID to set
	 */
	public void setOwnerID(CharacterID ownerID) {
		this.ownerID = ownerID;
	}
}
