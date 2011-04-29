package com.l2jserver.model.world.actor;

import com.l2jserver.model.world.capability.Actor;
import com.l2jserver.model.world.event.WorldEvent;

public interface ActorEvent extends WorldEvent {
	Actor getActor();
}
