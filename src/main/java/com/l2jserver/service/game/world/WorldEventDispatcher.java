package com.l2jserver.service.game.world;

import com.l2jserver.model.world.AbstractObject;
import com.l2jserver.model.world.event.WorldEvent;

public interface WorldEventDispatcher {
	void dispatch(AbstractObject object, WorldEvent event);
}
