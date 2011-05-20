/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.service.game.world;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.model.world.capability.Positionable;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.game.scripting.ScriptingService;
import com.l2jserver.service.game.template.TemplateService;
import com.l2jserver.service.game.world.event.WorldEventDispatcher;
import com.l2jserver.service.game.world.event.WorldEventDispatcherImpl;
import com.l2jserver.service.game.world.filter.FilterIterator;
import com.l2jserver.service.game.world.filter.WorldObjectFilter;
import com.l2jserver.service.game.world.filter.impl.IDFilter;
import com.l2jserver.service.game.world.filter.impl.InstanceFilter;
import com.l2jserver.service.game.world.filter.impl.KnownListFilter;
import com.l2jserver.service.logging.LoggingService;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * Default implementation for {@link WorldService}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ LoggingService.class, TemplateService.class, ScriptingService.class,
		DatabaseService.class, WorldIDService.class })
public class WorldServiceImpl extends AbstractService implements WorldService {
	/**
	 * The logger
	 */
	private static final Logger log = LoggerFactory
			.getLogger(WorldServiceImpl.class);

	/**
	 * The set of all objects registered in the world
	 */
	private final Set<WorldObject> objects = CollectionFactory.newSet();
	/**
	 * The world event dispatcher
	 */
	private final WorldEventDispatcherImpl dispatcher;
	/**
	 * The {@link WorldIDService}
	 */
	private final WorldIDService idService;

	@Inject
	public WorldServiceImpl(WorldEventDispatcher dispatcher,
			WorldIDService idService) {
		this.dispatcher = (WorldEventDispatcherImpl) dispatcher;
		this.idService = idService;
	}

	@Override
	protected void doStart() throws ServiceStartException {
		objects.clear();
		idService.load();
	}

	@Override
	public boolean add(WorldObject object) {
		log.debug("Adding object {} to world", object);
		return objects.add(object);
	}

	@Override
	public boolean remove(WorldObject object) {
		log.debug("Removing object {} from world", object);
		// we also need to remove all listeners for this object
		dispatcher.clear(object.getID());
		return objects.remove(object);
	}

	@Override
	public boolean contains(WorldObject object) {
		return objects.contains(object);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends WorldObject> T find(ObjectID<T> id) {
		final IDFilter filter = new IDFilter(id);
		for (final WorldObject object : objects) {
			if (filter.accept(object))
				return (T) object;
		}
		return null;
	}

	@Override
	public void knownlist(Positionable object, KnownListCallback callback) {
		if (object == null)
			return;
		if (callback == null)
			return;
		for (Positionable known : iterable(new KnownListFilter(object))) {
			callback.knownObject(known);
		}
	}

	@Override
	public WorldEventDispatcher getEventDispatcher() {
		return dispatcher;
	}

	@Override
	public <T extends WorldObject> List<T> list(WorldObjectFilter<T> filter) {
		log.debug("Listing objects with filter {}", filter);
		final List<T> list = CollectionFactory.newList();
		for (final T object : this.iterable(filter)) {
			list.add(object);
		}
		return list;
	}

	@Override
	public <T extends WorldObject> List<T> list(Class<T> type) {
		log.debug("Listing of type {}", type);
		return list(new InstanceFilter<T>(type));
	}

	@Override
	public Iterator<WorldObject> iterator() {
		return objects.iterator();
	}

	@Override
	public <T extends WorldObject> Iterator<T> iterator(
			final WorldObjectFilter<T> filter) {
		return new FilterIterator<T>(filter, objects.iterator());
	}

	@Override
	public <T extends WorldObject> Iterable<T> iterable(
			final WorldObjectFilter<T> filter) {
		return new Iterable<T>() {
			@Override
			public Iterator<T> iterator() {
				return new FilterIterator<T>(filter, objects.iterator());
			}
		};
	}

	@Override
	protected void doStop() throws ServiceStopException {
		objects.clear();
	}
}
