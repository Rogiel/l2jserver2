package com.l2jserver.model.world.actor;

import com.l2jserver.model.world.capability.Actor;

/**
 * Class controlling active effects on an {@link Actor}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ActorEffects {
	/**
	 * The actor being affected by the effects
	 */
	private final Actor actor;

	/**
	 * Creates a new instance
	 * 
	 * @param actor
	 *            the actor
	 */
	public ActorEffects(Actor actor) {
		this.actor = actor;
	}

	/**
	 * @return the actor
	 */
	public Actor getActor() {
		return actor;
	}
}
