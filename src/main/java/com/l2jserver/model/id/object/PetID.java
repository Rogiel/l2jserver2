package com.l2jserver.model.id.object;

import com.google.inject.Inject;
import com.l2jserver.db.dao.PetDAO;
import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.world.Pet;

/**
 * An {@link ObjectID} instance representing an {@link Pet} object
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public final class PetID extends ActorID<Pet> {
	/**
	 * Data Access Object (DAO) for pets
	 */
	private final PetDAO petDao;

	@Inject
	protected PetID(int id, PetDAO petDao) {
		super(id);
		this.petDao = petDao;
	}

	@Override
	public Pet getObject() {
		return petDao.load(this);
	}
}
