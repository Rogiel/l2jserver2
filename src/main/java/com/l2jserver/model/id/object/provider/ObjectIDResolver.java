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
import com.l2jserver.model.id.object.ActorID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.NPCID;
import com.l2jserver.service.game.world.WorldIDService;

/**
 * <b>THIS PROVIDER IS READ ONLY!</b>
 * <p>
 * This is an ID resolver that will lookup for <b>existing</b> IDs in
 * {@link WorldIDService}. Since this is only a resolver, only read operations
 * can be performed. Methods {@link #createID()} and {@link #destroy(ObjectID)}
 * will throw {@link UnsupportedOperationException}.
 * <p>
 * Another important aspect is that in {@link #resolveID(Integer)} if the ID is
 * not found, it will <b>NOT</b> create it, instead <tt>null</tt> will be
 * returned. You must use specific a {@link ObjectIDProvider} for that.
 * <p>
 * <h1>Use case</h1>
 * You should only use this class if you don't know which {@link ObjectID} to
 * expect or you are expecting multiple types (like {@link CharacterID} or
 * {@link NPCID} both of them extend {@link ActorID}).
 * <p>
 * <h1>Type safe notice</h1>
 * All IDs returned by this class are {@link ObjectID}. However sub-types cannot
 * be ensured and you must check them manually in order to avoid unneeded
 * {@link ClassCastException}. Even if you are sure that an given
 * {@link ObjectID} is from an certain sub-type, do not assume that it will be
 * always and do an checking.
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

	/**
	 * Resolvers do not support creating new IDs
	 * 
	 * @throws UnsupportedOperationException
	 */
	@Override
	public ObjectID<?> createID() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ObjectID<?> resolveID(Integer id) {
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
		return (T) resolveID(id);
	}

	/**
	 * Resolvers do not support destroying IDs
	 * 
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void destroy(ObjectID<?> id) {
		throw new UnsupportedOperationException();
	}
}
