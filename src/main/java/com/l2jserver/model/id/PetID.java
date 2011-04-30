package com.l2jserver.model.id;

import com.google.inject.Inject;
import com.l2jserver.db.dao.PetDAO;
import com.l2jserver.model.world.Pet;

public final class PetID extends ObjectID<Pet> {
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
