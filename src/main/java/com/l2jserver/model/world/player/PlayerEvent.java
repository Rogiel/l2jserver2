package com.l2jserver.model.world.player;

import com.l2jserver.model.world.Player;
import com.l2jserver.model.world.actor.ActorEvent;

public interface PlayerEvent extends ActorEvent {
	Player getPlayer();
}
