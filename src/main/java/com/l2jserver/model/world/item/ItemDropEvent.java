package com.l2jserver.model.world.item;

import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.Player;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.model.world.capability.Actor;
import com.l2jserver.model.world.player.PlayerEvent;

public class ItemDropEvent implements ItemEvent, PlayerEvent {
	private final Player player;
	private final Item item;

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
	public void dispatch() {
		item.dispatch(this);
		if (player != null)
			player.dispatch(this);
	}

	@Override
	public Actor getActor() {
		return player;
	}
}
