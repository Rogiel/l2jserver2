package com.l2jserver.model.world.actor;

import com.l2jserver.model.world.capability.Actor;

public class ActorEffects {
	private final Actor actor;

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
