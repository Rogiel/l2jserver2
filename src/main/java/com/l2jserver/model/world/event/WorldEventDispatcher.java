package com.l2jserver.model.world.event;

import com.l2jserver.model.world.World;

public class WorldEventDispatcher {
	private final World world;

	public WorldEventDispatcher(World world) {
		this.world = world;
	}

	public void dispatch(WorldEvent event) {
		//TODO implement threaded model
		event.dispatch();
	}
}
