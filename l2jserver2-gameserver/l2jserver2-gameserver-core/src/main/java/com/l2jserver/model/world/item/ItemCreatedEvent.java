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
package com.l2jserver.model.world.item;

import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.Player;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.model.world.character.event.CharacterEvent;

/**
 * Event dispatched once an {@link Item} has been created directly into the
 * player's inventory
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ItemCreatedEvent implements ItemEvent, CharacterEvent {
	/**
	 * The character character owning the item
	 */
	private final L2Character character;
	/**
	 * The item created
	 */
	private final Item item;

	/**
	 * Creates a new instance of this event
	 * 
	 * @param character
	 *            the character who owns the item (if any)
	 * @param item
	 *            the created item
	 */
	public ItemCreatedEvent(L2Character character, Item item) {
		this.character = character;
		this.item = item;
	}

	@Override
	public WorldObject getObject() {
		return item;
	}

	@Override
	public L2Character getCharacter() {
		return character;
	}

	@Override
	public Player getPlayer() {
		return character;
	}

	@Override
	public Item getItem() {
		return item;
	}

	@Override
	public Actor getActor() {
		return character;
	}

	@Override
	public ObjectID<?>[] getDispatchableObjects() {
		return new ObjectID<?>[] { item.getID() };
	}
}
