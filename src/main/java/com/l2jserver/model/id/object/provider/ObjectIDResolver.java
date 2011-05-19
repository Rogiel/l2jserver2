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
import com.l2jserver.model.id.ObjectID;
import com.l2jserver.service.game.world.id.WorldIDService;

/**
 * <h1>THIS PROVIDER IS READ ONLY!</h1>
 * <p>
 * This is an ID resolver that will lookup for IDs in {@link WorldIDService}.
 * Since this is only a resolver, only read operations can be performed and
 * {@link #createID()} and {@link #destroy(ObjectID)} will throw
 * {@link UnsupportedOperationException}.
 * <p>
 * Another important aspect is that in {@link #createID(Integer)} if the ID is
 * not found, it will <b>NOT</b> be created, instead <tt>null</tt> will be
 * returned. You must use specific a {@link ObjectIDProvider} for that.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ObjectIDResolver implements ObjectIDProvider<ObjectID<?>> {
	/**
	 * The {@link WorldIDService}
	 */
	private final WorldIDService idService;

	@Inject
	public ObjectIDResolver(WorldIDService idService) {
		this.idService = idService;
	}

	@Override
	public ObjectID<?> createID() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ObjectID<?> createID(Integer id) {
		return idService.resolve(id);
	}

	/**
	 * Resolves an integer ID to an {@link ObjectID}. If the ID does not exists
	 * <tt>null</tt> is returned.
	 * 
	 * @param <T>
	 *            the ID type
	 * @param id
	 *            the id
	 * @return the id found, null if non existent
	 */
	@SuppressWarnings("unchecked")
	public <T extends ObjectID<?>> T resolve(Integer id) {
		return (T) createID(id);
	}

	@Override
	public void destroy(ObjectID<?> id) {
		throw new UnsupportedOperationException();
	}
}
