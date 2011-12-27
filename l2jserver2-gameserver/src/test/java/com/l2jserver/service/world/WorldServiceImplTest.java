/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.service.world;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.l2jserver.GameServerModule;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.service.ServiceManager;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.WorldServiceImpl;
import com.l2jserver.service.game.world.filter.impl.InstanceFilter;

/**
 * Tests for {@link WorldServiceImpl}
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class WorldServiceImplTest {
	/**
	 * The world service
	 */
	private WorldService world;
	/**
	 * The character id provider
	 */
	private CharacterIDProvider provider;

	/**
	 * Preparation for tests
	 * @throws ServiceStartException
	 */
	@Before
	public void tearUp() throws ServiceStartException {
		Injector injector = Guice.createInjector(new GameServerModule());

		world = injector.getInstance(ServiceManager.class).start(
				WorldService.class);
		provider = injector.getInstance(CharacterIDProvider.class);
	}

	/**
	 * Test adding object to world
	 */
	@Test
	public void testAdd() {
		final L2Character character = new L2Character(null);
		character.setID(provider.createID());
		Assert.assertTrue(world.add(character));
	}

	/**
	 * Test adding <code>null</code> object to world
	 */
	@Test(expected = NullPointerException.class)
	public void testAddNullId() {
		final L2Character character = new L2Character(null);
		world.add(character);
	}

	/**
	 * Test removing object from world
	 */
	@Test
	public void testRemove() {
		final L2Character character = new L2Character(null);
		character.setID(provider.createID());
		Assert.assertTrue(world.add(character));
		Assert.assertTrue(world.remove(character));
	}

	/**
	 * Test removing 2 objects from world
	 */
	@Test
	public void testRemoveOther() {
		final L2Character character1 = new L2Character(null);
		character1.setID(provider.createID());

		final L2Character character2 = new L2Character(null);
		character2.setID(provider.createID());

		Assert.assertTrue(world.add(character1));
		Assert.assertFalse(world.remove(character2));
	}

	/**
	 * Test removing <code>null</code> id
	 */
	@Test(expected = NullPointerException.class)
	public void testRemoveNullId() {
		final L2Character character = new L2Character(null);
		world.add(character);
		world.remove(character);
	}

	/**
	 * Test if the object exists
	 */
	@Test
	public void testContains() {
		final L2Character character = new L2Character(null);
		character.setID(provider.createID());
		Assert.assertTrue(world.add(character));
		Assert.assertTrue(world.contains(character));
	}

	/**
	 * Test if the an <code>null</code> object exists
	 */
	@Test(expected = NullPointerException.class)
	public void testContainsNullId() {
		final L2Character character = new L2Character(null);
		world.add(character);
		Assert.assertTrue(world.contains(character));
	}

	/**
	 * Test the world iterator
	 */
	@Test
	public void testIterator() {
		final L2Character character1 = new L2Character(null);
		character1.setID(provider.createID());
		final L2Character character2 = new L2Character(null);
		character2.setID(provider.createID());
		final Item item1 = new Item(null);
		item1.setID(provider.createID());
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
