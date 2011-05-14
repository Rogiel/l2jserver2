package com.l2jserver.model.world.player;

import com.l2jserver.model.world.Player;
import com.l2jserver.util.Coordinate;

/**
 * Event dispatched once an player is teleported.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class PlayerTeleportEvent extends PlayerSpawnEvent {
	/**
	 * Creates a new instance
	 * 
	 * @param player
	 *            the teleported player
	 * @param coordinate
	 *            the coordinate
	 */
	public PlayerTeleportEvent(Player player, Coordinate coordinate) {
		super(player, coordinate);
	}
}
