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
package com.l2jserver.model.id.object.factory;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.model.id.factory.IDFactory;
import com.l2jserver.model.id.object.ClanID;
import com.l2jserver.model.id.object.allocator.IDAllocator;

/**
 * {@link IDFactory} for {@link ClanID}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ClanIDFactory implements ObjectIDFactory<ClanID> {
	/**
	 * The ID allocator
	 */
	private final IDAllocator allocator;
	/**
	 * The Guice factory
	 */
	private final ClanIDGuiceFactory factory;

	@Inject
	public ClanIDFactory(IDAllocator allocator, ClanIDGuiceFactory factory) {
		super();
		this.allocator = allocator;
		this.factory = factory;
	}

	@Override
	public ClanID createID() {
		return createID(allocator.allocate());
	}

	@Override
	public ClanID createID(Integer id) {
		return factory.create(id);
	}

	@Override
	public void destroy(ClanID id) {
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
