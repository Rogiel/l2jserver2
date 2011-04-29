package com.l2jserver.model.world;

import java.util.concurrent.atomic.AtomicBoolean;

import junit.framework.Assert;

import org.junit.Test;

import com.l2jserver.model.world.filter.impl.InstanceFilter;
import com.l2jserver.model.world.item.ItemDropEvent;
import com.l2jserver.model.world.item.ItemEvent;
import com.l2jserver.model.world.item.ItemListener;
import com.l2jserver.model.world.player.PlayerEvent;
import com.l2jserver.model.world.player.PlayerListener;
import com.l2jserver.model.world.player.PlayerSpawnEvent;

public class WorldTest {
	@Test
	public void testAdd() {
		final World world = new World();
		final Character character = new Character();
		world.add(character);
	}

	@Test
	public void testRemove() {
		final World world = new World();
		final Character character = new Character();
		world.add(character);
		world.remove(character);
	}

	@Test
	public void testContains() {
		final World world = new World();
		final Character character = new Character();
		world.add(character);
		Assert.assertTrue(world.contains(character));
	}

	@Test
	public void testIterator() {
		final World world = new World();
		final Character character1 = new Character();
		final Character character2 = new Character();
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

	@Test
	public void testListeners1() {
		final World world = new World();
		final Character character1 = new Character();
		final Character character2 = new Character();
		final Item item1 = new Item();
		world.add(character1);
		world.add(character2);
		world.add(item1);

		final AtomicBoolean bool = new AtomicBoolean();
		Assert.assertFalse(bool.get());
		character1.addListener(new PlayerListener() {
			@Override
			public void dispatch(PlayerEvent e) {
				bool.set(true);
				e.getPlayer().removeListener(this);
			}
		});
		character1.addListener(new PlayerListener() {
			@Override
			public void dispatch(PlayerEvent e) {
				// bool.set(true);
			}
		});
		world.getDispatcher().dispatch(new PlayerSpawnEvent(character1));
		Assert.assertTrue(bool.get());

		bool.set(false);

		world.getDispatcher().dispatch(new PlayerSpawnEvent(character1));
		Assert.assertFalse(bool.get());
	}

	@Test
	public void testListeners2() {
		final World world = new World();
		final Character character1 = new Character();
		final Character character2 = new Character();
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

		world.getDispatcher().dispatch(new ItemDropEvent(character1, item1));
		Assert.assertTrue(bool1.get());
		Assert.assertTrue(bool2.get());
	}
}
