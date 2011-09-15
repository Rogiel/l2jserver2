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
package com.l2jserver.model.id.object.provider;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.allocator.IDAllocator;
import com.l2jserver.model.id.provider.IDProvider;
import com.l2jserver.service.game.world.WorldIDService;

/**
 * {@link IDProvider} for {@link CharacterID}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterIDProvider implements ObjectIDProvider<CharacterID> {
	/**
	 * The ID allocator
	 */
	private final IDAllocator allocator;
	/**
	 * The {@link WorldIDService} instance. Used to locate existing IDs.
	 */
	private final WorldIDService idService;
	/**
	 * The Guice Factory
	 */
	private final CharacterIDGuiceFactory factory;

	/**
	 * @param allocator
	 *            the id allocator
	 * @param idService
	 *            the world id service
	 * @param factory
	 *            the character id factory
	 */
	@Inject
	public CharacterIDProvider(IDAllocator allocator, WorldIDService idService,
			CharacterIDGuiceFactory factory) {
		this.allocator = allocator;
		this.idService = idService;
		this.factory = factory;
	}

	@Override
	public CharacterID createID() {
		final CharacterID id = factory.create(allocator.allocate());
		idService.add(id);
		return id;
	}

	@Override
	public CharacterID resolveID(Integer id) {
		CharacterID idObject = idService.resolve(id);
		if (idObject == null) {
			idObject = factory.create(id);
			idService.add(idObject);
		}
		return idObject;
	}

	@Override
	public void destroy(CharacterID id) {
		idService.remove(id);
		allocator.release(id.getID());
	}

	/**
	 * This is an Google Guice factory. Assistect Inject extension will
	 * automatically implement it and create the injected instances.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public interface CharacterIDGuiceFactory {
		/**
		 * Creates a new ID instance
		 * 
		 * @param id
		 *            the numeric ID
		 * @return the new ID created by injection
		 */
		CharacterID create(@Assisted int id);
	}
}
