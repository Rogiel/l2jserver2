package com.l2jserver.model.id.factory;

import com.l2jserver.model.id.ID;

/**
 * The ID factory is used to create instances of IDs. It will automatically make
 * sure the ID is free before allocating it.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface IDFactory<T extends ID> {
	/**
	 * Creates the ID object for an <b>EXISTING</b> ID.
	 * 
	 * @param id
	 * @return
	 */
	T createID(int id); 
}
