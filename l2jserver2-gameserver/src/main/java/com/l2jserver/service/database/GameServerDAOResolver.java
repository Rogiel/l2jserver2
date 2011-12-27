/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.service.database;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.l2jserver.model.Model;
import com.l2jserver.model.dao.CharacterDAO;
import com.l2jserver.model.dao.ClanDAO;
import com.l2jserver.model.dao.ItemDAO;
import com.l2jserver.model.dao.NPCDAO;
import com.l2jserver.model.dao.PetDAO;
import com.l2jserver.model.id.ID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.ClanID;
import com.l2jserver.model.id.object.ItemID;
import com.l2jserver.model.id.object.NPCID;
import com.l2jserver.model.id.object.PetID;
import com.l2jserver.model.world.Clan;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.Pet;
import com.l2jserver.util.ClassUtils;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class GameServerDAOResolver implements DAOResolver {
	/**
	 * The {@link Guice} {@link Injector}
	 */
	private final Injector injector;

	/**
	 * @param injector
	 *            the {@link Guice} {@link Injector}
	 */
	@Inject
	private GameServerDAOResolver(Injector injector) {
		this.injector = injector;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <I extends ID<?>, M extends Model<I>> DataAccessObject<M, I> getDAO(
			M modelObject) {
		return (DataAccessObject) getDAO(modelObject.getClass());
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <M extends Model<I>, I extends ID<M>> DataAccessObject<M, I> getDAO(
			Class<M> modelClass) {
		if (ClassUtils.isSubclass(modelClass, L2Character.class)) {
			return (DataAccessObject) injector.getInstance(CharacterDAO.class);
		} else if (ClassUtils.isSubclass(modelClass, Clan.class)) {
			return (DataAccessObject) injector.getInstance(ClanDAO.class);
		} else if (ClassUtils.isSubclass(modelClass, Item.class)) {
			return (DataAccessObject) injector.getInstance(ItemDAO.class);
		} else if (ClassUtils.isSubclass(modelClass, NPC.class)) {
			return (DataAccessObject) injector.getInstance(NPCDAO.class);
		} else if (ClassUtils.isSubclass(modelClass, Pet.class)) {
			return (DataAccessObject) injector.getInstance(PetDAO.class);
		}
		return null;
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public <I extends ID<?>, M extends Model<I>> DataAccessObject<M, I> getDAOFromID(
			I modelIdObject) {
		return (DataAccessObject<M, I>) getDAOFromID(modelIdObject.getClass());
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <I extends ID<?>, M extends Model<?>> DataAccessObject<M, I> getDAOFromID(
			Class<I> modelIdType) {
		if (ClassUtils.isSubclass(modelIdType, CharacterID.class)) {
			return (DataAccessObject) injector.getInstance(CharacterDAO.class);
		} else if (ClassUtils.isSubclass(modelIdType, ClanID.class)) {
			return (DataAccessObject) injector.getInstance(ClanDAO.class);
		} else if (ClassUtils.isSubclass(modelIdType, ItemID.class)) {
			return (DataAccessObject) injector.getInstance(ItemDAO.class);
		} else if (ClassUtils.isSubclass(modelIdType, NPCID.class)) {
			return (DataAccessObject) injector.getInstance(NPCDAO.class);
		} else if (ClassUtils.isSubclass(modelIdType, PetID.class)) {
			return (DataAccessObject) injector.getInstance(PetDAO.class);
		}
		return null;
	}
}
