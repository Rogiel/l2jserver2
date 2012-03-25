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
package com.l2jserver.model.id;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.model.dao.CharacterShortcutDAO;
import com.l2jserver.model.game.CharacterShortcut;
import com.l2jserver.model.id.provider.IDProvider;

/**
 * Each {@link CharacterShortcut} is identified by an {@link ID}.
 * <p>
 * Please, do not directly instantiate this class, use an {@link IDProvider}
 * instead.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterShortcutID extends
		AbstractModelID<Integer, CharacterShortcut> {
	/**
	 * The {@link CharacterShortcutDAO} instance
	 */
	private final CharacterShortcutDAO shortcutDao;

	/**
	 * Creates a new instance
	 * 
	 * @param id
	 *            the id
	 * @param shortcutDao
	 *            the shortcut DAO
	 */
	@Inject
	public CharacterShortcutID(@Assisted int id,
			CharacterShortcutDAO shortcutDao) {
		super(id);
		this.shortcutDao = shortcutDao;
	}

	@Override
	public CharacterShortcut getObject() {
		return shortcutDao.select(this);
	}
}
