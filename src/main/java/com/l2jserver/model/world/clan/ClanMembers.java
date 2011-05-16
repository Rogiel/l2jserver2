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
package com.l2jserver.model.world.clan;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.world.Clan;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * This class handles members inside an clan
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ClanMembers implements Iterable<CharacterID> {
	/**
	 * The parent {@link Clan}
	 */
	private final Clan clan;

	/**
	 * The list of active members
	 */
	private final Set<CharacterID> members = CollectionFactory
			.newSet(CharacterID.class);

	/**
	 * Creates a new instance
	 * 
	 * @param clan
	 *            the clan
	 */
	public ClanMembers(Clan clan) {
		this.clan = clan;
	}

	/**
	 * Effectively add this character as a clan member
	 * 
	 * @param character
	 *            the character
	 * @return true if add, false otherwise
	 */
	public boolean add(L2Character character) {
		return members.add(character.getID());
	}

	/**
	 * Effectively add this character as a clan member
	 * 
	 * @param character
	 *            the character
	 * @return true if add, false otherwise
	 */
	public boolean remove(L2Character character) {
		return members.remove(character.getID());
	}

	/**
	 * Load an list of members to this clan
	 * 
	 * @param members
	 *            the list of members ids
	 */
	public void load(Collection<CharacterID> members) {
		this.members.addAll(members);
	}

	/**
	 * @return the clan
	 */
	public Clan getClan() {
		return clan;
	}

	@Override
	public Iterator<CharacterID> iterator() {
		return members.iterator();
	}
}
