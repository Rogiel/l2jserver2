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
package com.l2jserver.service.world;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.WorldServiceImpl;
import com.l2jserver.service.game.world.event.WorldEventDispatcher;
import com.l2jserver.service.game.world.event.WorldEventDispatcherImpl;
import com.l2jserver.service.game.world.filter.impl.InstanceFilter;

public class WorldServiceImplTest {
	private WorldService world;

	@Before
	public void tearUp() throws ServiceStartException {
		Injector injector = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				bind(WorldService.class).to(WorldServiceImpl.class).in(
						Scopes.SINGLETON);
				bind(WorldEventDispatcher.class).to(
						WorldEventDispatcherImpl.class).in(Scopes.SINGLETON);
			}
		});

		world = injector.getInstance(WorldService.class);
		Assert.assertNotNull(world);
		world.start();
	}

	@Test
	public void testAdd() {
		final L2Character character = new L2Character(null, null);
		world.add(character);
	}

	@Test
	public void testRemove() {
		final L2Character character = new L2Character(null, null);
		world.add(character);
		world.remove(character);
	}

	@Test
	public void testContains() {
		final L2Character character = new L2Character(null, null);
		world.add(character);
		Assert.assertTrue(world.contains(character));
	}

	@Test
	public void testIterator() {
		final L2Character character1 = new L2Character(null, null);
		final L2Character character2 = new L2Character(null, null);
		final Item item1 = new Item(null);
		world.add(character1);
		world.add(character2);
		world.add(item1);

		for (final WorldObject o : world) {
			Assert.assertNotNull(o);
		}
		final Iterable<Item> it = world.iterable(new InstanceFilter<Item>(
				Item.class));
		for (final WorldObject o : it) {
			Assert.assertNotNull(o);
		}
	}
}
