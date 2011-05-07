package com.l2jserver.model.id.object;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.world.capability.Actor;

public abstract class ActorID<T extends Actor> extends ObjectID<T> {
	@Inject
	public ActorID(@Assisted int id) {
		super(id);
	}
}
