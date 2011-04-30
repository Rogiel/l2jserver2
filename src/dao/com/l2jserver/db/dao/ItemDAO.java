package com.l2jserver.db.dao;

import java.util.List;

import com.l2jserver.model.id.ID;
import com.l2jserver.model.id.ItemID;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.cache.Cacheable;
import com.l2jserver.service.cache.IgnoreCaching;
import com.l2jserver.service.database.DataAccessObject;

/**
 * The {@link ItemDAO} is can load and save {@link Character character
 * instances} .
 * 
 * @author Rogiel
 */
public interface ItemDAO extends DataAccessObject<Item>, Cacheable {
	/**
	 * Load the instance represented by <tt>id</tt> from the database
	 * 
	 * @param id
	 *            the id
	 */
	Item load(ItemID id);

	/**
	 * Load the inventory for an {@link L2Character character}.
	 * 
	 * @param character
	 *            the character
	 * @return amount of items loaded
	 */
	int loadInventory(L2Character character);

	/**
	 * Loads an List of all {@link ID}s in the database
	 * 
	 * @return the list containing all ids
	 */
	@IgnoreCaching
	List<ItemID> listIDs();

	/**
	 * Save the instance to the database. If a new database entry was created
	 * returns true.
	 * 
	 * @param id
	 *            the id
	 * @return true if created a new entry in database (i.e. insert), false if
	 *         not created (i.e. update)
	 */
	@IgnoreCaching
	boolean save(Item character);
}
