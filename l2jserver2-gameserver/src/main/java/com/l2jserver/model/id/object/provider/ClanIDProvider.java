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
import com.l2jserver.model.id.object.ClanID;
import com.l2jserver.model.id.object.allocator.IDAllocator;
import com.l2jserver.model.id.provider.IDProvider;
import com.l2jserver.service.game.world.WorldIDService;

/**
 * {@link IDProvider} for {@link ClanID}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ClanIDProvider implements ObjectIDProvider<ClanID> {
	/**
	 * The ID allocator
	 */
	private final IDAllocator allocator;
	/**
	 * The {@link WorldIDService}
	 */
	private final WorldIDService idService;
	/**
	 * The Guice factory
	 */
	private final ClanIDGuiceFactory factory;

	/**
	 * @param allocator
	 *            the id allocator
	 * @param idService
	 *            the world id service
	 * @param factory
	 *            the clan id factory
	 */
	@Inject
	public ClanIDProvider(IDAllocator allocator, WorldIDService idService,
			ClanIDGuiceFactory factory) {
		super();
		this.allocator = allocator;
		this.idService = idService;
		this.factory = factory;
	}

	@Override
	public ClanID createID() {
		final ClanID id = factory.create(allocator.allocate());
		idService.add(id);
		return id;
	}

	@Override
	public ClanID resolveID(Integer id) {
		ClanID idObject = idService.resolve(id);
		if (idObject == null) {
			idObject = factory.create(id);
			idService.add(idObject);
		}
		return idObject;
	}

	@Override
	public void destroy(ClanID id) {
		idService.remove(id);
		allocator.release(id.getID());
	}

	/**
	 * This is an Google Guice factory. Assistect Inject extension will
	 * automatically implement it and create the injected instances.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public interface ClanIDGuiceFactory {
		/**
		 * Creates a new ID instance
		 * 
		 * @param id
		 *            the numeric ID
		 * @return the new ID created by injection
		 */
		ClanID create(@Assisted int id);
	}
}
