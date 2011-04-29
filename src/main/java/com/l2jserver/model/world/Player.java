package com.l2jserver.model.world;

import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.model.world.capability.Actor;
import com.l2jserver.model.world.capability.Parent;
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
		Teleportable, Parent {
	protected Lineage2Connection connection;

	@Override
	public void teleport(Coordinate coordinate) {
		final PlayerTeleportEvent event = new PlayerTeleportEvent(this, coordinate);
		this.setPosition(coordinate);
		event.dispatch();
	}

	/**
	 * @return the connection
	 */
	public Lineage2Connection getConnection() {
		return connection;
	}

	/**
	 * @param connection
	 *            the connection to set
	 */
	public void setConnection(Lineage2Connection connection) {
		this.connection = connection;
	}
}
