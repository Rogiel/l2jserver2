package com.l2jserver.model.world.player;

import com.l2jserver.model.world.Player;
import com.l2jserver.model.world.capability.Spawnable;
import com.l2jserver.model.world.event.SpawnEvent;

public class PlayerSpawnEvent implements PlayerEvent, SpawnEvent {
	private final Player player;

	public PlayerSpawnEvent(Player player) {
		this.player = player;
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
	public void dispatch() {
		if(player != null)
			player.dispatch(this);
	}
}
