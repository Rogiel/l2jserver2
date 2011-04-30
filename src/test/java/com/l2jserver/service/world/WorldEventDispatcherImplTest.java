package com.l2jserver.service.world;

import java.util.concurrent.atomic.AtomicBoolean;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.l2jserver.db.dao.DAOModuleMySQL5;
import com.l2jserver.model.id.factory.CharacterIDFactory;
import com.l2jserver.model.id.factory.IDFactoryModule;
import com.l2jserver.model.id.factory.ItemIDFactory;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
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

	private CharacterIDFactory cidFactory;
	private ItemIDFactory iidFactory;

	@Before
	public void tearUp() throws ServiceStartException {
		Injector injector = Guice.createInjector(new BasicServiceModule(),
				new DAOModuleMySQL5(), new IDFactoryModule(),
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

		cidFactory = injector.getInstance(CharacterIDFactory.class);
		iidFactory = injector.getInstance(ItemIDFactory.class);

		world = injector.getInstance(WorldService.class);
		dispatcher = injector.getInstance(WorldEventDispatcher.class);
		Assert.assertNotNull(world);
		Assert.assertNotNull(dispatcher);
		world.start();
	}

	@Test
	public void testListeners1() throws InterruptedException {
		final L2Character character1 = new L2Character();
		character1.setID(cidFactory.createID());
		final L2Character character2 = new L2Character();
		character2.setID(cidFactory.createID());
		final Item item1 = new Item();
		item1.setID(iidFactory.createID());
		world.add(character1);
		world.add(character2);
		world.add(item1);

		final AtomicBoolean bool = new AtomicBoolean();
		Assert.assertFalse(bool.get());

		dispatcher.addListener(character1, new PlayerListener() {
			@Override
			protected boolean dispatch(PlayerEvent e) {
				bool.set(true);
				return false;
			}
		});
		dispatcher.addListener(character1, new PlayerListener() {
			@Override
			protected boolean dispatch(PlayerEvent e) {
				bool.set(true);
				return false;
			}
		});

		dispatcher.dispatch(new PlayerSpawnEvent(character1, null));
		Thread.sleep(100); // wait a bit for dispatching to happen
		Assert.assertTrue(bool.get());

		bool.set(false);

		dispatcher.dispatch(new PlayerSpawnEvent(character1, null));
		Thread.sleep(100); // wait a bit for dispatching to happen
		Assert.assertFalse(bool.get());
	}

	@Test
	public void testListeners2() throws InterruptedException {
		final L2Character character1 = new L2Character();
		character1.setID(cidFactory.createID());
		final L2Character character2 = new L2Character();
		character2.setID(cidFactory.createID());
		final Item item1 = new Item();
		item1.setID(iidFactory.createID());
		final Item item2 = new Item();
		item2.setID(iidFactory.createID());
		world.add(character1);
		world.add(character2);
		world.add(item1);
		world.add(item2);

		final AtomicBoolean bool1 = new AtomicBoolean();
		final AtomicBoolean bool2 = new AtomicBoolean();

		Assert.assertFalse(bool1.get());
		Assert.assertFalse(bool2.get());

		dispatcher.addListener(character1, new PlayerListener() {
			@Override
			public boolean dispatch(PlayerEvent e) {
				bool1.set(true);
				return true;
			}
		});
		dispatcher.addListener(item1, new ItemListener() {
			@Override
			public boolean dispatch(ItemEvent e) {
				bool2.set(true);
				return true;
			}
		});

		dispatcher.dispatch(new ItemDropEvent(character1, item1));
		Thread.sleep(100); // wait a bit for dispatching to happen

		Assert.assertTrue(bool1.get());
		Assert.assertTrue(bool2.get());
	}
}
