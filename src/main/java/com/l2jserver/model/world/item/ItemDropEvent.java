package com.l2jserver.model.world.item;

import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.Player;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.model.world.capability.Actor;
import com.l2jserver.model.world.capability.Listenable;
import com.l2jserver.model.world.player.PlayerEvent;

/**
 * Event dispatched once an {@link Item} has been dropped on the ground.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ItemDropEvent implements ItemEvent, PlayerEvent {
	/**
	 * The dropping player
	 */
	private final Player player;
	/**
	 * The item dropped
	 */
	private final Item item;

	/**
	 * Creates a new instance of this event
	 * 
	 * @param player
	 *            the dropping player
	 * @param item
	 *            the dropped item
	 */
	public ItemDropEvent(Player player, Item item) {
		this.player = player;
		this.item = item;
	}

	@Override
	public WorldObject getObject() {
		return item;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public Item getItem() {
		return item;
	}

	@Override
	public Actor getActor() {
		return player;
	}

	@Override
	public Listenable<?, ?>[] getDispatchableObjects() {
		return new Listenable<?, ?>[] { player, item };
	}
}
