package com.l2jserver.service.game.world;

import java.util.Iterator;

import com.l2jserver.model.world.WorldObject;
import com.l2jserver.model.world.filter.WorldObjectFilter;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;

public class WorldServiceImpl implements WorldService {
	@Override
	public void start() throws ServiceStartException {
		// TODO Auto-generated method stub

	}

	@Override
	public void register(WorldObject object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unregister(WorldObject object) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<WorldObject> iterator() {
		// return objects.iterator();
		return null;
	}

	public <T extends WorldObject> Iterator<T> iterator(WorldObjectFilter<T> filter) {
		// return objects.iterator();
		return null;
	}

	@Override
	public void stop() throws ServiceStopException {
		// TODO Auto-generated method stub

	}
}
