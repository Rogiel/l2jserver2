package com.l2jserver.service.world;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.model.world.filter.impl.InstanceFilter;
import com.l2jserver.service.BasicServiceModule;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.game.world.WorldEventDispatcher;
import com.l2jserver.service.game.world.WorldEventDispatcherImpl;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.WorldServiceImpl;

public class WorldServiceImplTest {
	private WorldService world;

	@Before
	public void tearUp() throws ServiceStartException {
		Injector injector = Guice.createInjector(new BasicServiceModule(),
				new AbstractModule() {
					@Override
					protected void configure() {
						bind(WorldService.class).to(WorldServiceImpl.class).in(
								Scopes.SINGLETON);
						bind(WorldEventDispatcher.class).to(
								WorldEventDispatcherImpl.class).in(
								Scopes.SINGLETON);
					}
				});

		world = injector.getInstance(WorldService.class);
		Assert.assertNotNull(world);
		world.start();
	}

	@Test
	public void testAdd() {
		final L2Character character = new L2Character();
		world.add(character);
	}

	@Test
	public void testRemove() {
		final L2Character character = new L2Character();
		world.add(character);
		world.remove(character);
	}

	@Test
	public void testContains() {
		final L2Character character = new L2Character();
		world.add(character);
		Assert.assertTrue(world.contains(character));
	}

	@Test
	public void testIterator() {
		final L2Character character1 = new L2Character();
		final L2Character character2 = new L2Character();
		final Item item1 = new Item();
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
