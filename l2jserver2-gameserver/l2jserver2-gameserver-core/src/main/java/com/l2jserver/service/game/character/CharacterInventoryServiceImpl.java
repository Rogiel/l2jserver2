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
package com.l2jserver.service.game.character;

import com.google.inject.Inject;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.item.ItemCreatedEvent;
import com.l2jserver.model.world.item.ItemRemovedEvent;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.event.WorldEventDispatcherService;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterInventoryServiceImpl extends AbstractService implements
		CharacterInventoryService {
	/**
	 * The {@link WorldService} event dispatcher
	 */
	private final WorldEventDispatcherService eventDispatcherService;

	/**
	 * @param eventDispatcherService
	 *            the event dispatcher
	 */
	@Inject
	public CharacterInventoryServiceImpl(
			WorldEventDispatcherService eventDispatcherService) {
		this.eventDispatcherService = eventDispatcherService;
	}

	@Override
	public void add(L2Character character, Item item) {
		if (character.getInventory().has(item)) {
			// throw an exception
		}
		character.getInventory().add(item);
		eventDispatcherService.dispatch(new ItemCreatedEvent(character, item));
	}

	@Override
	public void remove(L2Character character, Item item) {
		if (!character.getInventory().has(item)) {
			// throw an exception
		}
		character.getInventory().add(item);
		eventDispatcherService.dispatch(new ItemRemovedEvent(character, item));
	}

	@Override
	public void reorder(L2Character character, Item item, int order) {
		// TODO implement item reordering
	}
}
