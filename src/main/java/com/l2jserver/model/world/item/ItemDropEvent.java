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
package com.l2jserver.model.world.item;

import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.Player;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.model.world.player.event.PlayerEvent;

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
	public ObjectID<?>[] getDispatchableObjects() {
		return new ObjectID<?>[] { item.getID() };
	}
}
