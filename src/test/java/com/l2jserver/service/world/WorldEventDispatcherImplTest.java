package com.l2jserver.service.world;

import java.util.concurrent.atomic.AtomicBoolean;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.item.ItemDropEvent;
import com.l2jserver.model.world.item.ItemEvent;
import com.l2jserver.model.world.item.ItemListener;
import com.l2jserver.model.world.player.PlayerEvent;
import com.l2jserver.model.world.player.PlayerListener;
import com.l2jserver.model.world.player.PlayerSpawnEvent;
import com.l2jserver.service.BasicServiceModule;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.game.world.WorldEventDispatcher;
import com.l2jserver.service.game.world.WorldEventDispatcherImpl;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.WorldServiceImpl;

public class WorldEventDispatcherImplTest {
	private WorldService world;
	private WorldEventDispatcher dispatcher;

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
		dispatcher = injector.getInstance(WorldEventDispatcher.class);
		Assert.assertNotNull(world);
		Assert.assertNotNull(dispatcher);
		world.start();
	}

	@Test
	public void testListeners1() {
		final L2Character character1 = new L2Character();
		final L2Character character2 = new L2Character();
		final Item item1 = new Item();
		world.add(character1);
		world.add(character2);
		world.add(item1);

		final AtomicBoolean bool = new AtomicBoolean();
		Assert.assertFalse(bool.get());
		character1.addListener(new PlayerListener() {
			@Override
			protected void dispatch(PlayerEvent e) {
				bool.set(true);
				e.getPlayer().removeListener(this);
			}
		});
		character1.addListener(new PlayerListener() {
			@Override
			protected void dispatch(PlayerEvent e) {
				// bool.set(true);
			}
		});
		dispatcher.dispatch(new PlayerSpawnEvent(character1, null));
		Assert.assertTrue(bool.get());

		bool.set(false);

		dispatcher.dispatch(new PlayerSpawnEvent(character1, null));
		Assert.assertFalse(bool.get());
	}

	@Test
	public void testListeners2() {
		final L2Character character1 = new L2Character();
		final L2Character character2 = new L2Character();
		final Item item1 = new Item();
		final Item item2 = new Item();
		world.add(character1);
		world.add(character2);
		world.add(item1);
		world.add(item2);

		final AtomicBoolean bool1 = new AtomicBoolean();
		final AtomicBoolean bool2 = new AtomicBoolean();

		Assert.assertFalse(bool1.get());
		Assert.assertFalse(bool2.get());

		character1.addListener(new PlayerListener() {
			@Override
			public void dispatch(PlayerEvent e) {
				bool1.set(true);
			}
		});
		item1.addListener(new ItemListener() {
			@Override
			public void dispatch(ItemEvent e) {
				bool2.set(true);
			}
		});

		dispatcher.dispatch(new ItemDropEvent(character1, item1));
		Assert.assertTrue(bool1.get());
		Assert.assertTrue(bool2.get());
	}
}
