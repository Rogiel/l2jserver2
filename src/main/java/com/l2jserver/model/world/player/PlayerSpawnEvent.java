package com.l2jserver.model.world.player;

import com.l2jserver.model.world.Player;
import com.l2jserver.model.world.capability.Actor;
import com.l2jserver.model.world.capability.Spawnable;
import com.l2jserver.model.world.event.SpawnEvent;
import com.l2jserver.util.Coordinate;

public class PlayerSpawnEvent implements PlayerEvent, SpawnEvent {
	private final Player player;
	private final Coordinate coordinate;

	public PlayerSpawnEvent(Player player, Coordinate coordinate) {
		this.player = player;
		this.coordinate = coordinate;
	}
	
	@Override
	public void dispatch() {
		if (player != null)
			player.dispatch(this);
	}

	@Override
	public Spawnable getObject() {
		return player;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public Coordinate getCoordinate() {
		return coordinate;
	}

	@Override
	public Actor getActor() {
		return player;
	}
}
