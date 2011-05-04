package com.l2jserver.db.dao;

import java.util.List;

import com.l2jserver.model.id.ID;
import com.l2jserver.model.id.object.ClanID;
import com.l2jserver.model.world.Clan;
import com.l2jserver.service.cache.Cacheable;
import com.l2jserver.service.cache.IgnoreCaching;
import com.l2jserver.service.database.DataAccessObject;

/**
 * The {@link ClanDAO} is can load and save {@link Clan clan instances}.
 * 
 * @author Rogiel
 */
public interface ClanDAO extends DataAccessObject<Clan>, Cacheable {
	/**
	 * Load the instance represented by <tt>id</tt> from the database
	 * 
	 * @param id
	 *            the id
	 */
	Clan load(ClanID id);
	
	/**
	 * Loads an List of all {@link ID}s in the database
	 * 
	 * @return the list containing all ids
	 */
	@IgnoreCaching
	List<ClanID> listIDs();

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
	boolean save(Clan character);
}
