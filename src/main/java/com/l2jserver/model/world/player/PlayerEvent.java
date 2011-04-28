package com.l2jserver.model.world.player;

import com.l2jserver.model.world.Player;
import com.l2jserver.model.world.event.WorldEvent;

public interface PlayerEvent extends WorldEvent {
	Player getPlayer();
}
