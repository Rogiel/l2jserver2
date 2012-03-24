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
package com.l2jserver.service.game.item;

import com.l2jserver.model.dao.ItemDAO;
import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.Service;
import com.l2jserver.service.game.character.CharacterAction;
import com.l2jserver.service.game.spawn.AlreadySpawnedServiceException;
import com.l2jserver.service.game.spawn.NotSpawnedServiceException;
import com.l2jserver.service.game.spawn.SpawnPointNotFoundServiceException;
import com.l2jserver.service.game.spawn.SpawnService;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.util.geometry.Point3D;

/**
 * This service handles item management. Drop and pick up, create and destroy.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface ItemService extends Service {
	/**
	 * Creates a new item with the given <code>template</code>.
	 * <p>
	 * Instances created by this method are not stored in the database yet. But
	 * they can be stored using {@link ItemDAO#insert(Item)}
	 * 
	 * @param template
	 *            the item template
	 * @return a new item instance
	 */
	Item create(ItemTemplate template);

	/**
	 * Creates a new item with the given <code>template</code>.
	 * <p>
	 * Instances created by this method are not stored in the database yet. But
	 * they can be stored using {@link ItemDAO#insert(Item)}
	 * 
	 * @param template
	 *            the item template
	 * @param count
	 *            the amount of items to be created
	 * @return a new item instance
	 * @throws NonStackableItemsServiceException
	 *             this item cannot be stacked
	 */
	Item create(ItemTemplate template, long count)
			throws NonStackableItemsServiceException;

	/**
	 * Executes an action for an Item.
	 * 
	 * @param item
	 *            the item
	 * @param character
	 *            the character issuing the action
	 * @param action
	 *            the action type
	 * @return <code>item</code> or another instance if the item was stacked.
	 * @throws ItemNotOnGroundServiceException
	 *             if the item is not on ground at the moment
	 * @throws NotSpawnedServiceException
	 *             if the item is not registered with {@link SpawnService}
	 */
	Item action(Item item, L2Character character, CharacterAction action)
			throws ItemNotOnGroundServiceException, NotSpawnedServiceException;

	/**
	 * Splits this item in two pieces
	 * 
	 * @param item
	 *            the item to be splitted
	 * @param count
	 *            the amount of items that will be decreased from the argument
	 *            {@link Item} and added to the result one.
	 * @return the splitted item
	 * @throws NotEnoughItemsServiceException
	 *             if <code>count</code> is bigger than {@link Item#getCount()}.
	 * @throws NonStackableItemsServiceException
	 *             if the items are not stackable and thus indivisible
	 */
	Item split(Item item, long count) throws NotEnoughItemsServiceException,
			NonStackableItemsServiceException;

	/**
	 * Stacks several items into a single one. Items must be provided by the
	 * same template!
	 * 
	 * @param items
	 *            the items to be stacked
	 * @return the stacked item (it may be a totally new item or it might reuse
	 *         another one)
	 * @throws NonStackableItemsServiceException
	 *             if the item templates says they are not stackable
	 */
	Item stack(Item... items) throws NonStackableItemsServiceException;

	/**
	 * Destroys the given item
	 * 
	 * @param item
	 *            the item to be destroyed
	 * @param count
	 *            the amount of items to be destroyed
	 * @return <code>true</code> if the entire item was destroyed.
	 *         <code>false</code> only if it was partially destroyed.
	 * @throws NotEnoughItemsServiceException
	 *             if <code>count</code> is bigger than {@link Item#getCount()}.
	 * @throws NonStackableItemsServiceException
	 *             if the item could not be destroyed because it was not
	 *             possible to split it
	 */
	boolean destroy(Item item, long count)
			throws NotEnoughItemsServiceException,
			NonStackableItemsServiceException;

	/**
	 * Destroys several items
	 * 
	 * @param items
	 *            the items to be destroyed
	 */
	void destroy(Item... items);

	/**
	 * Picks up an dropped item and places it into another players inventory
	 * 
	 * @param item
	 *            the item
	 * @param character
	 *            the character
	 * @return <code>item</code> or another instance if the item was stacked.
	 * @throws ItemNotOnGroundServiceException
	 *             if the item is not on ground at the moment
	 * @throws NotSpawnedServiceException
	 *             if the item is not registered with {@link SpawnService}
	 */
	Item pickUp(Item item, L2Character character)
			throws ItemNotOnGroundServiceException, NotSpawnedServiceException;

	/**
	 * Drops an item on the ground. If <code>actor</code> is not
	 * <code>null</code> he is flagged as the dropper actor.
	 * 
	 * @param item
	 *            the item
	 * @param count
	 *            the number of items to be dropped. Will cause an split.
	 * @param point
	 *            the drop point. Can be <code>null</code> if
	 *            {@link Item#getPoint()} is set.
	 * @param actor
	 *            the dropping actor. Can be <code>null</code>.
	 * @return <code>item</code> or another instance if the item was splitted.
	 * @throws ItemAlreadyOnGroundServiceException
	 *             if the item is already dropped
	 * @throws AlreadySpawnedServiceException
	 *             if the item is already spawned in the {@link WorldService}
	 * @throws SpawnPointNotFoundServiceException
	 *             if <code>point</code> was <code>null</code> and
	 *             {@link Item#getPoint()} was not set.
	 * @throws NotEnoughItemsServiceException
	 *             if <code>count</code> is bigger than {@link Item#getCount()}.
	 * @throws NonStackableItemsServiceException
	 *             if the item could not be splitted
	 */
	Item drop(Item item, long count, Point3D point, Actor actor)
			throws ItemAlreadyOnGroundServiceException,
			AlreadySpawnedServiceException, SpawnPointNotFoundServiceException,
			NotEnoughItemsServiceException, NonStackableItemsServiceException;

	/**
	 * Drops an item on the ground. If <code>actor</code> is not
	 * <code>null</code> he is flagged as the dropper actor.
	 * 
	 * @param item
	 *            the item
	 * @param point
	 *            the drop point. Can be <code>null</code> if
	 *            {@link Item#getPoint()} is set.
	 * @param actor
	 *            the dropping actor. Can be <code>null</code>.
	 * @throws ItemAlreadyOnGroundServiceException
	 *             if the item is already dropped
	 * @throws AlreadySpawnedServiceException
	 *             if the item is already spawned in the {@link WorldService}
	 * @throws SpawnPointNotFoundServiceException
	 *             if <code>point</code> was <code>null</code> and
	 *             {@link Item#getPoint()} was not set.
	 * @throws NotEnoughItemsServiceException
	 *             if <code>count</code> is bigger than {@link Item#getCount()}.
	 * @throws NonStackableItemsServiceException
	 *             if the item could not be splitted
	 */
	void drop(Item item, Point3D point, Actor actor)
			throws ItemAlreadyOnGroundServiceException,
			AlreadySpawnedServiceException, SpawnPointNotFoundServiceException,
			NotEnoughItemsServiceException, NonStackableItemsServiceException;
}
