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

import java.util.concurrent.atomic.AtomicBoolean;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.l2jserver.GameServerModule;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.id.object.provider.ItemIDProvider;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.item.ItemDropEvent;
import com.l2jserver.model.world.item.ItemEvent;
import com.l2jserver.model.world.item.ItemListener;
import com.l2jserver.model.world.player.event.PlayerEvent;
import com.l2jserver.model.world.player.event.PlayerListener;
import com.l2jserver.model.world.player.event.PlayerSpawnEvent;
import com.l2jserver.service.ServiceManager;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.event.WorldEventDispatcher;
import com.l2jserver.service.game.world.id.WorldIDService;

public class WorldEventDispatcherImplTest {
	private WorldService world;
	private WorldEventDispatcher dispatcher;

	private CharacterIDProvider cidFactory;
	private ItemIDProvider iidFactory;

	@Before
	public void tearUp() throws ServiceStartException {
		Injector injector = Guice.createInjector(Stage.PRODUCTION,
				new GameServerModule());

		injector.getInstance(ServiceManager.class).start(WorldIDService.class);

		cidFactory = injector.getInstance(CharacterIDProvider.class);
		iidFactory = injector.getInstance(ItemIDProvider.class);

		world = injector.getInstance(WorldService.class);
		dispatcher = injector.getInstance(WorldEventDispatcher.class);
		Assert.assertNotNull(world);
		Assert.assertNotNull(dispatcher);
		world.start();
	}

	@Test
	public void testListeners1() throws InterruptedException {
		final L2Character character1 = new L2Character(null);
		character1.setID(cidFactory.createID());
		final L2Character character2 = new L2Character(null);
		character2.setID(cidFactory.createID());
		final Item item1 = new Item(null);
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
		final L2Character character1 = new L2Character(null);
		character1.setID(cidFactory.createID());
		final L2Character character2 = new L2Character(null);
		character2.setID(cidFactory.createID());
		final Item item1 = new Item(null);
		item1.setID(iidFactory.createID());
		final Item item2 = new Item(null);
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
		Thread.sleep(1000); // wait a bit for dispatching to happen

		Assert.assertFalse(bool1.get());
		Assert.assertTrue(bool2.get());
	}
}
