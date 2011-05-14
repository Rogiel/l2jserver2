package com.l2jserver.model.world.player;

import com.l2jserver.model.world.Player;
import com.l2jserver.model.world.actor.ActorEvent;

/**
 * Base event for {@link Player} objects
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface PlayerEvent extends ActorEvent {
	/**
	 * @return the player
	 */
	Player getPlayer();
}
