package com.l2jserver.model.world.player;

import com.l2jserver.model.world.Player;
import com.l2jserver.util.Coordinate;

public class PlayerTeleportEvent extends PlayerSpawnEvent {
	public PlayerTeleportEvent(Player player, Coordinate coordinate) {
		super(player, coordinate);
	}
}
