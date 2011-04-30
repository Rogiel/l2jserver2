package com.l2jserver.model.world;

import com.l2jserver.model.world.capability.Actor;
import com.l2jserver.model.world.capability.Playable;
import com.l2jserver.model.world.capability.Teleportable;
import com.l2jserver.model.world.player.PlayerTeleportEvent;
import com.l2jserver.util.Coordinate;

/**
 * {@link Player} is any object that can be controlled by the player. The most
 * common implementation is {@link L2Character}.
 * 
 * @author Rogiel
 */
public abstract class Player extends AbstractActor implements Playable, Actor,
		Teleportable {
	@Override
	public void teleport(Coordinate coordinate) {
		final PlayerTeleportEvent event = new PlayerTeleportEvent(this,
				coordinate);
		this.setPosition(coordinate);
		// event.dispatch();
	}
}
