package com.l2jserver.model.world.actor;

import com.l2jserver.model.world.capability.Actor;
import com.l2jserver.model.world.event.WorldEvent;

/**
 * Base event for {@link Actor} instances
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface ActorEvent extends WorldEvent {
	Actor getActor();
}
